package pl.skopinau.repoviewer.exception;

public class NotAcceptableException extends RuntimeException {

    public NotAcceptableException(String requiredAccept) {
        super(String.format(Message.NOT_ACCEPTABLE.getMessage(), requiredAccept));
    }
}
