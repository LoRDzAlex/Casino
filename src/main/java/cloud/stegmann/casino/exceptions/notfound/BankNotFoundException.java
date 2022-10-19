package cloud.stegmann.casino.exceptions.notfound;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(int bankId) {
        super("The bank with id '" + bankId + "' could not be found.");
    }
}
