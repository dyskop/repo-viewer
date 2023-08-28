package pl.skopinau.repoviewer.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Message {

    USER_NOT_FOUND("GitHub user not found: %s"),
    NOT_ACCEPTABLE("Accept header must be %s");

    private final String message;
}
