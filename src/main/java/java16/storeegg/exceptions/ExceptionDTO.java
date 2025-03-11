package java16.storeegg.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@Builder
public class ExceptionDTO {
    private String message;
    private String className;
    private HttpStatus httpStatus;
}
