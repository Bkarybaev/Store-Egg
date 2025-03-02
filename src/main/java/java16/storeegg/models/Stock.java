package java16.storeegg.models;

import jakarta.persistence.*;
import java16.storeegg.enums.Role;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_stock_id")
    @SequenceGenerator(name = "gen_stock_id", sequenceName = "seq_stock_id", allocationSize = 1, initialValue = 100)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private Role role;
    @OneToMany(mappedBy = "stock")
    private List<Product> products;
}
