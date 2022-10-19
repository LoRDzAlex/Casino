package cloud.stegmann.casino.model;

import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository <Bank, Integer>{
    Bank findById(int id);

    Bank findByCredit(Double credit);
}
