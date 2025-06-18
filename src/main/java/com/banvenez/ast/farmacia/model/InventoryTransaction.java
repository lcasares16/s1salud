package com.banvenez.ast.farmacia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Removed javax.persistence.* imports
// The TransactionType enum is now in its own file (TransactionType.java)
// No explicit import needed as it's in the same package.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransaction {
    private Long transactionId;
    private Long inventoryItemId; // Changed from 'InventoryItem inventoryItem'
    private TransactionType transactionType;
    private int quantityChanged;
    private LocalDateTime transactionDate;
    private BigDecimal priceAtTransaction;
    private Long userId;
    private String notes;
}
