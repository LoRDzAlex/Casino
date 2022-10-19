package cloud.stegmann.casino.exceptions.load;

public class UserLoadException extends RuntimeException {
    public UserLoadException() {
        super("Users could not be loaded.");
    }
}
