package pl.skopinau.repoviewer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler/* extends ResponseEntityExceptionHandler*/ {

    @ExceptionHandler(UserNotExistsException.class)
    public Mono<ResponseEntity<ExceptionResponse>> handleUserNotExistsException(UserNotExistsException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse));
    }

    @ExceptionHandler(UnsupportedMediaTypeException.class)
    public ResponseEntity<ExceptionResponse> handleUnsupportedMediaTypeException(UnsupportedMediaTypeException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exceptionResponse);
    }
}
