package java16.storeegg.dto.request;

import java16.storeegg.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class SaveStockRequest {

    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private Role role;
}
