package pl.skopinau.repoviewer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDetails {

    private int status;

    @JsonProperty("Message")
    private String message;
}
