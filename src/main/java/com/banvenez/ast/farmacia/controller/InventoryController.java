package com.banvenez.ast.farmacia.controller;

import com.banvenez.ast.farmacia.model.InventoryItem;
import com.banvenez.ast.farmacia.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Basic DTO for adding stock - place in a 'dto' package in a real project
class AddStockRequest {
    public Long productId;
    public LocalDate expiryDate;
    public int quantity;
    public BigDecimal purchasePrice;
    public BigDecimal salePrice;
    public Long userId; // This should ideally come from security context
}

// Basic DTO for recording a sale - place in a 'dto' package in a real project
class RecordSaleRequest {
    public Long productId;
    public int quantitySold;
    public Long userId; // This should ideally come from security context
}

@RestController
@RequestMapping("/api/farmacia/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/stock")
    public ResponseEntity<?> addStock(@RequestBody AddStockRequest request) {
        try {
            InventoryItem item = inventoryService.addStock(
                request.productId,
                request.expiryDate,
                request.quantity,
                request.purchasePrice,
                request.salePrice,
                request.userId
            );
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Log error: e.printStackTrace(); or use a proper logger
            // Return the exception message for client context, can be refined with error codes/DTOs
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sale")
    public ResponseEntity<?> recordSale(@RequestBody RecordSaleRequest request) {
        try {
            InventoryItem item = inventoryService.recordSale(
                request.productId,
                request.quantitySold,
                request.userId
            );
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
             // Log error: e.printStackTrace(); or use a proper logger
             // Return the exception message, e.g., for "out of stock" or "product not found"
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getInventoryForProduct(@PathVariable Long productId) {
        try {
            List<InventoryItem> items = inventoryService.getInventoryForProduct(productId);
            // Service might throw if product itself not found, or return empty list if product exists but has no inventory.
            // If items is empty because product doesn't exist, service should ideally throw an exception caught below.
            return ResponseEntity.ok(items);
        } catch (RuntimeException e) {
            // Assuming service throws if product ID is invalid
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            // Log error: e.printStackTrace(); or use a proper logger
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryItem>> getLowStockItems(@RequestParam(defaultValue = "10") int threshold) {
        List<InventoryItem> items = inventoryService.getLowStockItems(threshold);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<InventoryItem>> getExpiredItems() {
        List<InventoryItem> items = inventoryService.getExpiredItems();
        return ResponseEntity.ok(items);
    }
}
