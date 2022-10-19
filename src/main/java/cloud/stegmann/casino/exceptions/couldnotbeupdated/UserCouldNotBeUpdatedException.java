package cloud.stegmann.casino.exceptions.couldnotbeupdated;

public class UserCouldNotBeUpdatedException extends RuntimeException{
    public UserCouldNotBeUpdatedException(int id) {
        super("The User with id '" + id + "' could not be updated.");
    }
}
