package pl.skopinau.repoviewer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.ProblemDetail;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomProblemDetail extends ProblemDetail {

    private String message;

    public CustomProblemDetail() {
        this.message = super.getDetail();
    }
}
