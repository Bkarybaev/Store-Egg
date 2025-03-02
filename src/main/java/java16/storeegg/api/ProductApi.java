package java16.storeegg.controller;

import java16.storeegg.models.Product;
import java16.storeegg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getAll() {
       return productService.getAll();
    }
}
