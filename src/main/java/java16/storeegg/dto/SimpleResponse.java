package java16.storeegg.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
public class SimpleResponse {

    private HttpStatus status;
    private String massage;


}
