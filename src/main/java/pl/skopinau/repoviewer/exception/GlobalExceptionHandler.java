package pl.skopinau.repoviewer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public Mono<ResponseEntity<ExceptionDetails>> handleUserNotFoundException(UserNotFoundException ex) {

        ExceptionDetails details = new ExceptionDetails(NOT_FOUND.value(), ex.getMessage());
        ResponseEntity<ExceptionDetails> responseEntity = ResponseEntity
                .status(NOT_FOUND)
                .body(details);
        return Mono.just(responseEntity);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public Mono<ResponseEntity<ExceptionDetails>> handleNotAcceptableException(NotAcceptableException ex) {

        ExceptionDetails details = new ExceptionDetails(NOT_ACCEPTABLE.value(), ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ExceptionDetails> response = ResponseEntity
                .status(NOT_ACCEPTABLE)
                .headers(headers)
                .body(details);
        return Mono.just(response);
    }
}
