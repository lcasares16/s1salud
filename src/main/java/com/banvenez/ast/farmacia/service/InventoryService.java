package com.banvenez.ast.farmacia.service;

import com.banvenez.ast.farmacia.dao.InventoryItemDao; // Changed
import com.banvenez.ast.farmacia.dao.InventoryTransactionDao; // Changed
import com.banvenez.ast.farmacia.dao.ProductDao; // Changed
import com.banvenez.ast.farmacia.model.InventoryItem;
import com.banvenez.ast.farmacia.model.InventoryTransaction;
import com.banvenez.ast.farmacia.model.Product;
import com.banvenez.ast.farmacia.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class InventoryService {

    private final InventoryItemDao inventoryItemDao; // Changed
    private final InventoryTransactionDao transactionDao; // Changed
    private final ProductDao productDao; // Changed

    @Autowired
    public InventoryService(InventoryItemDao inventoryItemDao, // Changed
                            InventoryTransactionDao transactionDao, // Changed
                            ProductDao productDao) { // Changed
        this.inventoryItemDao = inventoryItemDao;
        this.transactionDao = transactionDao;
        this.productDao = productDao;
    }

    @Transactional
    public InventoryItem addStock(Long productId, LocalDate expiryDate, int quantity, BigDecimal purchasePrice, BigDecimal salePrice, Long userId) {
        // Ensure product exists before adding stock for it
        Product product = productDao.findById(productId) // Changed
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        InventoryItem item = new InventoryItem();
        item.setProductId(product.getProductId()); // Use productId from the fetched Product
        item.setEntryDate(LocalDate.now());
        item.setExpiryDate(expiryDate);
        item.setQuantity(quantity);
        item.setPurchasePrice(purchasePrice);
        item.setSalePrice(salePrice);

        InventoryItem savedItem = inventoryItemDao.save(item); // Changed

        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setInventoryItemId(savedItem.getInventoryItemId()); // Use inventoryItemId from the saved InventoryItem
        transaction.setTransactionType(TransactionType.ENTRY);
        transaction.setQuantityChanged(quantity);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPriceAtTransaction(purchasePrice);
        transaction.setUserId(userId);
        transaction.setNotes("Initial stock entry");
        transactionDao.save(transaction); // Changed

        // The savedItem already has its ID and other fields populated by the DAO.
        // If transactions needed to be part of the returned InventoryItem, we would set it here.
        // item.setTransactions(List.of(savedTransaction)); // Example if needed
        return savedItem;
    }

    @Transactional
    public InventoryItem recordSale(Long productId, int quantitySold, Long userId) {
        Product product = productDao.findById(productId) // Changed
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Find an inventory item with sufficient stock, ordered by expiry/entry date (FEFO/FIFO)
        List<InventoryItem> availableItems = inventoryItemDao.findByProductIdAndQuantityGreaterThan(productId, quantitySold - 1); // Changed

        if (availableItems.isEmpty()) {
            throw new RuntimeException("Not enough stock for product: " + product.getName());
        }

        // Taking the first one due to DAO ordering (FEFO/FIFO)
        InventoryItem itemToSellFrom = availableItems.get(0);

        // Double-check quantity in the selected batch (though DAO query helps)
        if (itemToSellFrom.getQuantity() < quantitySold) {
            // This might happen if the sum of quantities across batches is enough, but one batch isn't.
            // A more complex logic would iterate or take from multiple batches.
            // For now, this reflects the logic of taking from the first suitable batch.
            throw new RuntimeException("Not enough stock in selected batch for product: " + product.getName() + ". Batch ID: " + itemToSellFrom.getInventoryItemId());
        }

        itemToSellFrom.setQuantity(itemToSellFrom.getQuantity() - quantitySold);
        inventoryItemDao.update(itemToSellFrom); // Changed
        // itemToSellFrom object is updated in memory and reflects the new quantity.

        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setInventoryItemId(itemToSellFrom.getInventoryItemId()); // Use inventoryItemId
        transaction.setTransactionType(TransactionType.EXIT);
        transaction.setQuantityChanged(quantitySold);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPriceAtTransaction(itemToSellFrom.getSalePrice());
        transaction.setUserId(userId);
        transaction.setNotes("Sale transaction");
        transactionDao.save(transaction); // Changed

        // itemToSellFrom already reflects the changes.
        // If transactions needed to be part of the returned InventoryItem, we would set it here.
        return itemToSellFrom;
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getInventoryForProduct(Long productId) {
        // Optional: Check if product exists first, though DAO method will return empty list if productId is invalid.
        // productDao.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        return inventoryItemDao.findByProductId(productId); // Changed
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getLowStockItems(int threshold) {
        // Current implementation filters all items.
        // A dedicated DAO method would be more efficient for large datasets.
        return inventoryItemDao.findAll().stream() // Changed
            .filter(item -> item.getQuantity() <= threshold)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getExpiredItems() {
        return inventoryItemDao.findByExpiryDateBefore(LocalDate.now()); // Changed
    }
}
