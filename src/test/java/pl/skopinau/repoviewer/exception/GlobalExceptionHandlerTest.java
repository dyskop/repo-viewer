package pl.skopinau.repoviewer.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    @DisplayName("When UserNotFoundException occurred then return mono of ResponseEntity<ExceptionDetails> and 404")
    void handleUserNotFoundException() {
        String username = "username";
        UserNotFoundException ex = new UserNotFoundException(username);
        ExceptionDetails details = new ExceptionDetails(NOT_FOUND.value(), ex.getMessage());

        ResponseEntity<ExceptionDetails> expectedResponseEntity = ResponseEntity.status(NOT_FOUND).body(details);
        Mono<ResponseEntity<ExceptionDetails>> actual = exceptionHandler.handleUserNotFoundException(ex);

        assertEquals(NOT_FOUND, Objects.requireNonNull(actual.block()).getStatusCode());
        assertEquals(expectedResponseEntity.getBody().getStatus(), actual.block().getBody().getStatus());
        assertEquals(expectedResponseEntity.getBody().getMessage(), actual.block().getBody().getMessage());
    }

    @Test
    @DisplayName("When NotAcceptableException occurred then return mono of ResponseEntity<ExceptionDetails> and 406")
    void handleNotAcceptableException() {
        String requiredAccept = MediaType.APPLICATION_JSON_VALUE;
        NotAcceptableException ex = new NotAcceptableException(requiredAccept);
        ExceptionDetails details = new ExceptionDetails(NOT_ACCEPTABLE.value(), ex.getMessage());

        ResponseEntity<ExceptionDetails> expectedResponseEntity = ResponseEntity.status(NOT_ACCEPTABLE).body(details);
        Mono<ResponseEntity<ExceptionDetails>> actual = exceptionHandler.handleNotAcceptableException(ex);

        assertEquals(NOT_ACCEPTABLE, Objects.requireNonNull(actual.block()).getStatusCode());
        assertEquals(expectedResponseEntity.getBody().getStatus(), actual.block().getBody().getStatus());
        assertEquals(expectedResponseEntity.getBody().getMessage(), actual.block().getBody().getMessage());
    }
}