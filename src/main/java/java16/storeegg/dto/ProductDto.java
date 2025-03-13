package java16.storeegg.dto;

import java16.storeegg.enums.Category;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    private Long id;

    private String name;
    private String title;
    @ToString.Exclude
    private List<ImageDto> images = new ArrayList<>();
    private BigDecimal price;
    private String address;
    private Category category;
}
