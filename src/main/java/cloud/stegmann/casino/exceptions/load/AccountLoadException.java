package cloud.stegmann.casino.exceptions.load;

public class AccountLoadException extends RuntimeException {
    public AccountLoadException() {
        super("Accounts could not be loaded.");
    }
}
