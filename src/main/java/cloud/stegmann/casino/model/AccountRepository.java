package cloud.stegmann.casino.model;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository <Account, Integer>{
    Account findByUsername(String username);
    Account findById(int id);

    Account findByEmail(String email);
}
