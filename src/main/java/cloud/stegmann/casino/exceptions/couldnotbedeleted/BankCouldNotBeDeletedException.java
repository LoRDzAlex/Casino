package cloud.stegmann.casino.exceptions.couldnotbedeleted;

public class BankCouldNotBeDeletedException extends RuntimeException{
    public BankCouldNotBeDeletedException(int id) {
        super("The Bank with id '" + id + "' could not be deleted.");
    }
}
