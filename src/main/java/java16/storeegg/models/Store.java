package java16.storeegg.models;

import jakarta.persistence.*;
import java16.storeegg.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gen_store_id")
    @SequenceGenerator(
            name = "gen_store_id",
            sequenceName = "seq_store_id",
            allocationSize = 1,
            initialValue = 100)

    private Long id;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private Role role;
}
