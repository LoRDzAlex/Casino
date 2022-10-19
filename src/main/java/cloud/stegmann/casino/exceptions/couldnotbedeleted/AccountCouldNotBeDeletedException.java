package cloud.stegmann.casino.exceptions.couldnotbedeleted;

public class AccountCouldNotBeDeletedException extends RuntimeException{
    public AccountCouldNotBeDeletedException(int id) {
        super("The Account with id '" + id + "' could not be deleted.");
    }
}
