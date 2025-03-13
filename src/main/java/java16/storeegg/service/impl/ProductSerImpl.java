package java16.storeegg.service.impl;

import java16.storeegg.dto.ImageDto;
import java16.storeegg.dto.ProductDto;
import java16.storeegg.dto.SimpleResponse;
import java16.storeegg.dto.request.SaveProductRequest;
import java16.storeegg.exceptions.ProductNotFout;
import java16.storeegg.models.Image;
import java16.storeegg.models.Product;
import java16.storeegg.repo.ImageRepository;
import java16.storeegg.repo.ProductRepo;
import java16.storeegg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSerImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ImageRepository imageRepository;

    @Override
    public SimpleResponse addProduct(SaveProductRequest saveProductRequest) {
        System.err.println(saveProductRequest);
        Product product = new Product();

        product.setName(saveProductRequest.getName());
        product.setTitle(saveProductRequest.getTitle());
        product.setPrice(saveProductRequest.getPrice());
        product.setAddress(saveProductRequest.getAddress());
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
    public List<?> getAll() {
        List<Product> all = productRepo.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : all) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            productDto.setAddress(product.getAddress());
            productDto.setCategory(product.getCategory());
            List<ImageDto> images = new ArrayList<>();
            for (Image image : product.getImages()) {
                ImageDto imageDto = new ImageDto();
                imageDto.setImg(image.getFileName());
                images.add(imageDto);
            }
            productDto.setImages(images);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepo.findById(id).orElse(null);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setAddress(product.getAddress());
        productDto.setCategory(product.getCategory());
        List<ImageDto> images = new ArrayList<>();
        for (Image image : product.getImages()) {
            ImageDto imageDto = new ImageDto();
            imageDto.setImg(image.getFileName());
            images.add(imageDto);
        }
        productDto.setImages(images);
        return productDto;

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


    //image


@Override
public void addImageToProduct(Long productId, String fileName) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Image image = new Image();
        image.setFileName(fileName);
        image.setProduct(product);

        imageRepository.save(image);
    }
}
