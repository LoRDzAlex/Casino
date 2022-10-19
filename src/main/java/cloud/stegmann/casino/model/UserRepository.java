package cloud.stegmann.casino.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Integer> {
    User findById(int id);
}
