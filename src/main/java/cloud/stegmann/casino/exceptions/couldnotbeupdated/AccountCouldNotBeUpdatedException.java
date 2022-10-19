package cloud.stegmann.casino.exceptions.couldnotbeupdated;

public class AccountCouldNotBeUpdatedException extends RuntimeException{
    public AccountCouldNotBeUpdatedException(String accountName) {
        super("The Account with name '" + accountName + "' could not be updated.");
    }
}
