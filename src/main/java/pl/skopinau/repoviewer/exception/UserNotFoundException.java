package pl.skopinau.repoviewer.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super(String.format(Message.USER_NOT_FOUND.getMessage(), username));
    }
}
