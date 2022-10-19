package cloud.stegmann.casino.exceptions.load;

public class BankLoadException extends RuntimeException {
    public BankLoadException() {
        super("Banks could not be loaded.");
    }
}
