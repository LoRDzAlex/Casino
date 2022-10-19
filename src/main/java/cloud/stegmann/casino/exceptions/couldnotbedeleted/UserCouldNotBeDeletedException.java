package cloud.stegmann.casino.exceptions.couldnotbedeleted;

public class UserCouldNotBeDeletedException extends RuntimeException{
    public UserCouldNotBeDeletedException(int id) {
        super("The User with id '" + id + "' could not be deleted.");
    }
}
