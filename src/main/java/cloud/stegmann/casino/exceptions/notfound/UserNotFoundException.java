package cloud.stegmann.casino.exceptions.notfound;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int userId) {
        super("The user with id '" + userId + "' could not be found.");
    }
}
