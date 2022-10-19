package cloud.stegmann.casino.controller;
import cloud.stegmann.casino.exceptions.couldnotbedeleted.AccountCouldNotBeDeletedException;
import cloud.stegmann.casino.exceptions.couldnotbesaved.AccountCouldNotBeSavedException;
import cloud.stegmann.casino.exceptions.couldnotbeupdated.AccountCouldNotBeUpdatedException;
import cloud.stegmann.casino.exceptions.couldnotbeupdated.BankCouldNotBeUpdatedException;
import cloud.stegmann.casino.exceptions.load.AccountLoadException;
import cloud.stegmann.casino.exceptions.notfound.AccountNotFoundException;
import cloud.stegmann.casino.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(path = "/account")
public class AccountController {

    /**
     * Wire the Repository's
     *
     * @param accountRepository
     * @param bankRepository
     * @param userRepository
     */
    private AccountRepository accountRepository;
    @Autowired
    public void setAccountRepository(AccountRepository accountRepository){this.accountRepository = accountRepository;}
    private BankRepository bankRepository;
    @Autowired
    public void setBankRepository(BankRepository bankRepository){this.bankRepository = bankRepository;}
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){this.userRepository = userRepository;}

    /**
     * List all existing Accounts
     *
     * @return all existing Accounts
     * @throws AccountLoadException if something went wrong
     */
    @GetMapping(path = "")
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        Iterable<Account> accounts = null;

        try {
            accounts = accountRepository.findAll();
        } catch (Exception ex) {
             throw new AccountLoadException();
        }

        return ResponseEntity.ok(accounts);
    }

    /**
     * List specific Account by ID
     *
     * @param id
     *
     * @return specific Account
     * @throws AccountNotFoundException if something went wrong
     */
    @GetMapping(path = "/id")
    public ResponseEntity<Account> getAccountById(@RequestParam int id) {
        Account account = null;

        try {
            account = accountRepository.findById(id);
        } catch (Exception ex) {
            throw new AccountNotFoundException(id);
        }

        return ResponseEntity.ok(account);
    }

    /**
     * List specific Account by Email
     *
     * @param email
     *
     * @return specific Account
     * @throws AccountNotFoundException if something went wrong
     */
    @GetMapping(path = "/email")
    public ResponseEntity<Account> getAccountByEmail(@RequestParam String email) {
        Account account = null;

        try {
            account = accountRepository.findByEmail(email);
        } catch (Exception ex) {
            throw new AccountNotFoundException(email);
        }

        return ResponseEntity.ok(account);
    }

    /**
     * Creates new Account
     *
     * @param username
     * @param password
     * @param email
     * @param bankid
     * @param userid
     *
     * @return new Account
     * @throws AccountCouldNotBeSavedException if something went wrong
     */
    @PostMapping(path = "")
    public ResponseEntity<String> createAccount(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam int bankid,
            @RequestParam int userid) {

        try {
            Account account = new Account(username, password, email, bankRepository.findById(bankid), userRepository.findById(userid));
            accountRepository.save(account);
        } catch (Exception e) {
            throw new AccountCouldNotBeSavedException(username);
        }
        return ResponseEntity.ok("Saved");
    }

    /**
     * Deletes Account by ID
     *
     * @param id
     *
     * @return deleted Account
     * @throws AccountCouldNotBeDeletedException if something went wrong
     */
    @DeleteMapping(path = "")
    public ResponseEntity<String> deleteAccount(@RequestParam int id) {
        try {
            accountRepository.deleteById(id);
        } catch (Exception e) {
            throw new AccountCouldNotBeDeletedException(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    /**
     * Updates Account by ID
     *
     * @param id
     * @param username
     * @param password
     * @param email
     * @param userid
     * @param bankid
     *
     * @return updated Account
     * @throws AccountCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateAccount(@RequestParam int id,
                                                @RequestParam String username,
                                                @RequestParam String password,
                                                @RequestParam String email,
                                                @RequestParam int userid,
                                                @RequestParam int bankid){
        Account a = accountRepository.findById(id);
        try{

            a.setUsername(username);
            a.setPassword(password);
            a.setEmail(email);
            a.setUser(userRepository.findById(userid));
            a.setBank(bankRepository.findById(bankid));
            accountRepository.save(a);
        }
        catch (Exception ex){
            throw new AccountCouldNotBeUpdatedException(username);
        }
        return ResponseEntity.ok("Updated");
    }

    /**
     * Updates Username of an Account by ID
     *
     * @param id
     * @param username
     *
     * @return updated Account Username
     * @throws AccountCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "/username")
    public ResponseEntity<String> updateUsername(@RequestParam int id,
                                                 @RequestParam String username){
        try {
            Account a = accountRepository.findById(id);
            if(a.getUsername() == username){
                throw new BankCouldNotBeUpdatedException(id);
            }else if (accountRepository.findByUsername(username) != null){
                throw new BankCouldNotBeUpdatedException(id);
            }
            a.setUsername(username);
            accountRepository.save(a);
        }catch (Exception ex){
            throw new BankCouldNotBeUpdatedException(id);
        }

        return ResponseEntity.ok("Updated");
    }

    /**
     * Updates Password of an Account by ID
     *
     * @param id
     * @param password
     *
     * @return updated Account Password
     * @throws AccountCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "/password")
    public ResponseEntity<String> updatePassword(@RequestParam int id,
                                                 @RequestParam String password){
        try {
            Account a = accountRepository.findById(id);
            if(a.getPassword() == password) {
                throw new BankCouldNotBeUpdatedException(id);
            }
            a.setPassword(password);
            accountRepository.save(a);
        }catch (Exception ex){
            throw new BankCouldNotBeUpdatedException(id);
        }

        return ResponseEntity.ok("Updated");
    }
}
