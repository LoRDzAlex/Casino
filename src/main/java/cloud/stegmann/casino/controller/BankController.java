package cloud.stegmann.casino.controller;


import cloud.stegmann.casino.exceptions.couldnotbedeleted.BankCouldNotBeDeletedException;
import cloud.stegmann.casino.exceptions.couldnotbesaved.BankCouldNotBeSavedException;
import cloud.stegmann.casino.exceptions.couldnotbeupdated.BankCouldNotBeUpdatedException;
import cloud.stegmann.casino.exceptions.load.BankLoadException;
import cloud.stegmann.casino.exceptions.notfound.BankNotFoundException;
import cloud.stegmann.casino.model.Bank;
import cloud.stegmann.casino.model.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(path = "/bank")
public class BankController {

    /**
     * Wire BankRepository
     *
     * @param bankRepository
     *
     * @return Wired BankRepository
     */
    private BankRepository bankRepository;
    @Autowired
    public void setBankRepository(BankRepository bankRepository){this.bankRepository = bankRepository;}

    /**
     * List all existing BankAccounts
     *
     * @return all existing BankAccounts
     * @throws BankLoadException if something went wrong
     */
    @GetMapping(path = "")
    public ResponseEntity<Iterable<Bank>> getAllBankAccounts() {
        Iterable<Bank> banks = null;

        try {
            banks = bankRepository.findAll();
        } catch (Exception ex) {
            throw new BankLoadException();
        }

        return ResponseEntity.ok(banks);
    }

    /**
     * List specific BankAccount by ID
     *
     * @param id
     *
     * @return specific BankAccount
     * @throws BankNotFoundException if something went wrong
     */
    @GetMapping(path = "/id")
    public ResponseEntity<Optional<Bank>> getBankById(@RequestParam Integer id) {
        Optional<Bank> bank = null;

        try {
            bank = bankRepository.findById(id);
        } catch (Exception ex) {
            throw new BankNotFoundException(id);
        }

        return ResponseEntity.ok(bank);
    }

    /**
     * Creates new BankAccount
     *
     * @param currencyname
     * @param credit
     *
     * @return new BankAccount
     * @throws BankCouldNotBeSavedException if something went wrong
     */
    @PostMapping(path = "")
    public ResponseEntity<String> createBankAccount(@RequestParam String currencyname,
                                                    @RequestParam Double credit,
                                                    @RequestParam String bankCard){
        try{
            Bank bank = new Bank(currencyname, credit, bankCard);
            bankRepository.save(bank);
        }
        catch (Exception exception){
            throw new BankCouldNotBeSavedException();
        }

        return ResponseEntity.ok("Saved");
    }

    /**
     * Deletes BankAccount by ID
     *
     * @param id
     *
     * @return deleted BankAccount
     * @throws BankCouldNotBeDeletedException if something went wrong
     */
    @DeleteMapping(path = "")
    public ResponseEntity<String> deleteBankAccount(@RequestParam int id) {
        try {
            bankRepository.deleteById(id);
        } catch (Exception e) {
            throw new BankCouldNotBeDeletedException(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    /**
     * Updates BankAccount by ID
     *
     * @param id
     * @param credit
     * @param currency
     *
     * @return updated BankAccount
     * @throws BankCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateBankAccount(@RequestParam int id,
                                                    @RequestParam String currency,
                                                    @RequestParam Double credit){
        Bank c = bankRepository.findById(id);
        try {
            c.setCurrency(currency);
            c.setCredit(credit);
            bankRepository.save(c);
        } catch (Exception e) {
            //TODO: Exception Schreiben
            throw new BankCouldNotBeUpdatedException(id);
        }
        return ResponseEntity.ok("Updated");
    }

    /**
     * Withdraw Money from ID
     *
     * @param id
     * @param bankCredit
     *
     * @return updated BankCredit
     * @throws BankCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "/withdraw")
    public ResponseEntity<String> withdrawBankCredit(@RequestParam int id,
                                                     @RequestParam Double bankCredit){
        Bank c = bankRepository.findById(id);
        String bankCard = c.getBankCard();
        try {
            c.setCredit(c.getCredit()-bankCredit);
            if (c.getCredit() == null || c.getCredit() <= 0){
                throw new BankCouldNotBeUpdatedException(id);
            }
            bankRepository.save(c);
        }catch (Exception ex){
            throw new BankCouldNotBeUpdatedException(id);
        }
        return ResponseEntity.ok("Withdrawn " + bankCredit+ "and sent to "+bankCard+ " Successfully");

    }

    /**
     * Deposit Money from ID
     *
     * @param id
     * @param bankCredit
     *
     * @return updated BankCredit
     * @throws BankCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "/deposit")
    public ResponseEntity<String> depositBankCredit(@RequestParam int id,
                                                     @RequestParam Double bankCredit){
        Bank b = bankRepository.findById(id);
        try {
            b.setCredit(b.getCredit()+bankCredit);
            if (b.getCredit() == null || b.getCredit() < 0){
                throw new BankCouldNotBeUpdatedException(id);
            }
            bankRepository.save(b);
        }catch (Exception ex){
            throw new BankCouldNotBeUpdatedException(id);
        }
        return ResponseEntity.ok("Deposit " + bankCredit+ "and sent to ur Account Successfully");
    }
}
