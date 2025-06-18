package com.banvenez.ast.farmacia.controller;

import com.banvenez.ast.farmacia.model.Product;
import com.banvenez.ast.farmacia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmacia/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-qr/{qrCodeData}")
    public ResponseEntity<Product> getProductByQrCodeData(@PathVariable String qrCodeData) {
        // Consider URL decoding qrCodeData if it contains special characters
        Optional<Product> product = productService.getProductByQrCodeData(qrCodeData);
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        // Consider URL decoding name if it contains special characters
        Optional<Product> product = productService.getProductByName(name);
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            // A more specific exception should be thrown by the service for "not found"
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("not found")) {
                 return ResponseEntity.notFound().build();
            }
            // Log other exceptions: e.printStackTrace(); or use a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Example: service could throw specific exceptions for "not found" or "conflict"
             if (e.getMessage() != null && e.getMessage().toLowerCase().contains("not found")) {
                 return ResponseEntity.notFound().build();
            }
            // Log other exceptions: e.printStackTrace(); or use a logger
            // If it's a foreign key constraint violation, CONFLICT (409) is appropriate.
            // For this example, using a generic server error if not "not found".
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
