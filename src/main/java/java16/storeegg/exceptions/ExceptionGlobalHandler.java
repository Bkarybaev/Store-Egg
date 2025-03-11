package java16.storeegg.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {
    @ExceptionHandler(value = {ProductNotFout.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO handleProductNotFout(ProductNotFout productNotFout) {
       return ExceptionDTO.builder()
               .message(productNotFout.getMessage())
               .className(productNotFout.getClass().getSimpleName())
               .httpStatus(HttpStatus.NOT_FOUND)
               .build();
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO handleProductNotFout(RuntimeException productNotFout) {
       return ExceptionDTO.builder()
               .message(productNotFout.getMessage())
               .className(productNotFout.getClass().getSimpleName())
               .httpStatus(HttpStatus.NOT_FOUND)
               .build();
    }
}
