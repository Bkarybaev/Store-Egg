package java16.storeegg.api;

import java16.storeegg.dto.SimpleResponse;
import java16.storeegg.dto.request.SaveProductRequest;
import java16.storeegg.models.Product;
import java16.storeegg.service.ImageService;
import java16.storeegg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java16.storeegg.service.impl.ImageServiceImpl.UPLOAD_DIR;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;
    private final ImageService imageService;

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

    // add product
    @PostMapping("/addProduct")
    private SimpleResponse saveProduct(@RequestBody SaveProductRequest saveProductRequest){
        return productService.addProduct(saveProductRequest);
    }

    @DeleteMapping("/deletePoduct/{productId}")
    public SimpleResponse deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }


    //image
    @PostMapping("/{productId}/upload")
    public ResponseEntity<String> uploadImage(@PathVariable Long productId, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = imageService.saveImage(file);
            productService.addImageToProduct(productId, fileName);
            return ResponseEntity.ok("Image uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("File not found: " + fileName);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }


}
