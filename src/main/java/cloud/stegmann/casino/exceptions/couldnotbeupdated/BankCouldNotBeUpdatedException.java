package cloud.stegmann.casino.exceptions.couldnotbeupdated;

public class BankCouldNotBeUpdatedException extends RuntimeException{
    public BankCouldNotBeUpdatedException(int id) {
        super("The Bank with id '" + id + "' could not be updated.");
    }
}
