package java16.storeegg.service.impl;

import java16.storeegg.models.Product;
import java16.storeegg.repo.ProductRepo;
import java16.storeegg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSerImpl implements ProductService {
private final ProductRepo productRepo;

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }
}
