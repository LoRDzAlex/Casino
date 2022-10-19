package cloud.stegmann.casino.exceptions.couldnotbesaved;

public class AccountCouldNotBeSavedException extends RuntimeException{
    public AccountCouldNotBeSavedException(String accountName) {
        super("The Account with name '" + accountName + "' could not be saved.");
    }
}
