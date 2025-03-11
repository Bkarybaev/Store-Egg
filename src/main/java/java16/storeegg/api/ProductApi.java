package java16.storeegg.api;

import java16.storeegg.models.Product;
import java16.storeegg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;

    //get all product
    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }
    //find by id
    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable Long id) {
       return productService.findById(id);
    }

    //search by name product
    @GetMapping("/search")
    public List<Product> search(@RequestParam  String query) {
            return productService.search(query);
    }

}
