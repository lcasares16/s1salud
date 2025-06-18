package com.banvenez.ast.farmacia.service;

import com.banvenez.ast.farmacia.dao.ProductDao; // Changed
import com.banvenez.ast.farmacia.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDao productDao; // Changed

    @Autowired
    public ProductService(ProductDao productDao) { // Changed
        this.productDao = productDao;
    }

    @Transactional
    public Product saveProduct(Product product) {
        // Consider validation for unique name/QR code if not handled by DB constraints directly in DAO
        return productDao.save(product); // Changed
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productDao.findAll(); // Changed
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productDao.findById(id); // Changed
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductByQrCodeData(String qrCodeData) {
        return productDao.findByQrCodeData(qrCodeData); // Changed
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductByName(String name) {
        return productDao.findByName(name); // Changed
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        // Fetch-then-update is good practice to ensure product exists
        Product product = productDao.findById(id) // Changed
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)); // Use custom exception

        // Update fields from productDetails
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setQrCodeData(productDetails.getQrCodeData());
        // Set other fields as necessary

        productDao.update(product); // Changed. ProductDao.update returns int.
                                    // The 'product' object is updated in memory and then passed to DAO.
                                    // This is acceptable as the object state is consistent.
        return product; // Return the modified product object
    }

    @Transactional
    public void deleteProduct(Long id) {
        // Add logic to check dependencies in other tables (e.g., inventory) before deleting if necessary
        productDao.deleteById(id); // Changed
    }
}
