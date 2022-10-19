package cloud.stegmann.casino.controller;

import cloud.stegmann.casino.exceptions.couldnotbedeleted.UserCouldNotBeDeletedException;
import cloud.stegmann.casino.exceptions.couldnotbesaved.UserCouldNotBeSavedException;
import cloud.stegmann.casino.exceptions.couldnotbeupdated.UserCouldNotBeUpdatedException;
import cloud.stegmann.casino.exceptions.load.UserLoadException;
import cloud.stegmann.casino.exceptions.notfound.UserNotFoundException;
import cloud.stegmann.casino.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * Wire UserRepository
     *
     * @param userRepository
     *
     * @return Wired UserRepository
     */
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){this.userRepository = userRepository;}

    /**
     * List all existing Users
     *
     * @return all existing Users
     * @throws UserLoadException if something went wrong
     */
    @GetMapping(path = "")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = null;

        try {
            users = userRepository.findAll();
        } catch (Exception ex) {
            throw new UserLoadException();
        }

        return ResponseEntity.ok(users);
    }

    /**
     * List specific User by ID
     *
     * @param id
     *
     * @return specific User
     * @throws UserNotFoundException if something went wrong
     */
    @GetMapping(path = "/id")
    public ResponseEntity<Optional<User>> getUserById(@RequestParam Integer id) {
        Optional<User> user = null;

        try {
            user = userRepository.findById(id);
        } catch (Exception ex) {
            throw new UserNotFoundException(id);
        }

        return ResponseEntity.ok(user);
    }

    /**
     * Creates new User
     *
     * @param fName
     * @param lName
     * @param bday
     *
     * @return new User
     * @throws UserCouldNotBeSavedException if something went wrong
     */
    @PostMapping("")
    public ResponseEntity<String> createUser(
            @RequestParam String fName,
            @RequestParam String lName,
            @RequestParam String bday){

        try {
            User u = new User(fName, lName, Date.valueOf(bday));
            userRepository.save(u);
        }catch (Exception ex){
            throw new UserCouldNotBeSavedException(fName);
        }
        return ResponseEntity.ok("Saved");
    }

    /**
     * Deletes User by ID
     *
     * @param id
     *
     * @return deleted User
     * @throws UserCouldNotBeDeletedException if something went wrong
     */
    @DeleteMapping(path = "")
    public ResponseEntity<String> deleteUser(@RequestParam int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserCouldNotBeDeletedException(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    /**
     * Updates User by ID
     *
     * @param id
     * @param fName
     * @param lName
     * @param bday
     *
     * @return updated User
     * @throws UserCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateUser(@RequestParam int id,
                                             @RequestParam String fName,
                                             @RequestParam String lName,
                                             @RequestParam String bday) {
        try {
            User u = userRepository.findById(id);
            u.setFname(fName);
            u.setLname(lName);
            u.setBday(Date.valueOf(bday));
            userRepository.save(u);
        } catch (Exception e) {
            throw new UserCouldNotBeUpdatedException(id);
        }
        return ResponseEntity.ok("Updated");
    }
}

