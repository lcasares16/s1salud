package com.banvenez.ast.farmacia.repository;

import com.banvenez.ast.farmacia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByQrCodeData(String qrCodeData);
    Optional<Product> findByName(String name);
}
