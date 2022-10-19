package cloud.stegmann.casino.exceptions.couldnotbesaved;

public class UserCouldNotBeSavedException extends RuntimeException{
    public UserCouldNotBeSavedException(String userName) {
        super("The User with name '" + userName + "' could not be saved.");
    }
}
