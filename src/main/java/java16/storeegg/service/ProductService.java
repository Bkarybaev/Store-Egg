package java16.storeegg.service;

import java16.storeegg.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product findById(Long id);
}
