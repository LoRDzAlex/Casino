package cloud.stegmann.casino.exceptions.notfound;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(int accountId) {
        super("The account with id '" + accountId + "' could not be found.");
    }
    public AccountNotFoundException(String email) {
        super("The account with email '" + email + "' could not be found.");
    }
}
