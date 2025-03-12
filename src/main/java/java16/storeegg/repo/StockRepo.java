package java16.storeegg.repo;



import java16.storeegg.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Long> {

}
