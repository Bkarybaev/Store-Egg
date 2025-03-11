package java16.storeegg.repo;

import java16.storeegg.enums.Category;
import java16.storeegg.exceptions.ProductNotFout;
import java16.storeegg.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name ILIKE %:request% or p.title ilike %:request%")
    Optional<List<Product>> search(@Param("request") String request);

    default List<Product> searchByNameAndTitle(String query){
        List<Product> products = search(query).orElse(null);
        if (products.isEmpty()) throw new ProductNotFout(String.format("No product found with query '%s'", query));
        return products;
    }
}
