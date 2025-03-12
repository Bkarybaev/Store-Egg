package java16.storeegg.models;

import jakarta.persistence.*;
import java16.storeegg.enums.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "gen_id")
    @SequenceGenerator(name = "gen_id", sequenceName = "seq_id", allocationSize = 1, initialValue = 100)
    private Long id;

    private String name;
    private String title;
    private BigDecimal price;
    private String address;
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    private Stock stock;

}
