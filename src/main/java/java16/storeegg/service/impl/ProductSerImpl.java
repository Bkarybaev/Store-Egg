package java16.storeegg.service.impl;

import java16.storeegg.dto.SimpleResponse;
import java16.storeegg.dto.request.SaveProductRequest;
import java16.storeegg.exceptions.ProductNotFout;
import java16.storeegg.models.Product;
import java16.storeegg.repo.ProductRepo;
import java16.storeegg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSerImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public SimpleResponse addProduct(SaveProductRequest saveProductRequest) {
        System.err.println(saveProductRequest);
        Product product = new Product();

        product.setName(saveProductRequest.getName());
        product.setTitle(saveProductRequest.getTitle());
        product.setPrice(saveProductRequest.getPrice());
        product.setAddress(saveProductRequest.getAddress());
        product.setImageUrl(saveProductRequest.getImageUrl());
        product.setCategory(saveProductRequest.getCategory());

        try {

            productRepo.save(product);
            return SimpleResponse

                    .builder()
                    .status(HttpStatus.CREATED)
                    .massage("succes createt product")
                    .build();
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .massage("error" + e.getMessage())
                    .build();
        }
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> search(String request) {
        if (request == null || request.isBlank()) {
            throw new ProductNotFout(String.format("Product not found: %s", request));
        }
        return productRepo.searchByNameAndTitle(request);
    }

    @Override
    public SimpleResponse deleteProduct(Long productId) {

        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("not found product Id : " + productId));

        try {
            productRepo.delete(product);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .massage("successfull deletet 👌")
                    .build();
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .massage("error" + e.getMessage())
                    .build();
        }
    }
}
