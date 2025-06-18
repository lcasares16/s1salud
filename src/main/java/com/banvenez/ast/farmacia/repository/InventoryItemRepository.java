package com.banvenez.ast.farmacia.repository;

import com.banvenez.ast.farmacia.model.InventoryItem;
import com.banvenez.ast.farmacia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByProduct(Product product);
    List<InventoryItem> findByExpiryDateBefore(LocalDate date);
    List<InventoryItem> findByProductAndQuantityGreaterThan(Product product, int quantity);
}
