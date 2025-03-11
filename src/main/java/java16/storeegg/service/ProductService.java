package java16.storeegg.service;

import java16.storeegg.dto.SimpleResponse;
import java16.storeegg.dto.request.SaveProductRequest;
import java16.storeegg.models.Product;

import java.util.List;

public interface ProductService {


    SimpleResponse addProduct(SaveProductRequest saveProductRequest);

    List<Product> getAll();

    Product findById(Long id);

    List<Product> search(String request);
}
