package java16.storeegg.dto.request;


import java16.storeegg.enums.Category;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Builder
public class SaveProductRequest {
    private String name;
    private String title;
    private BigDecimal price;
    private String address;
    private String imageUrl;
    private Category category;

}
