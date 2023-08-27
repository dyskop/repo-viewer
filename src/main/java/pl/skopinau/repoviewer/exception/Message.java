package pl.skopinau.repoviewer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    USER_NOT_EXISTS("GitHub user does not exist: %s"),
    UNSUPPORTED_MEDIA_TYPE("Unsupported media type: %s");

    private final String message;
}
