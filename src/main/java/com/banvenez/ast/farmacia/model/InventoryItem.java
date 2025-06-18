package com.banvenez.ast.farmacia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List; // Keep for List<InventoryTransaction>

// Removed javax.persistence.* imports

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {
    private Long inventoryItemId;
    private Long productId; // Changed from 'Product product'
    private LocalDate entryDate;
    private LocalDate expiryDate;
    private int quantity;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private List<InventoryTransaction> transactions; // Populated by service layer
}
