package cloud.stegmann.casino.exceptions.couldnotbesaved;

public class BankCouldNotBeSavedException extends RuntimeException{
    public BankCouldNotBeSavedException() {
        super("The BankAccount could not be saved.");
    }
}
