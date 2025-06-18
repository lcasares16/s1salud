package com.banvenez.ast.farmacia.service;

import com.banvenez.ast.farmacia.dao.InventoryItemDao; // Changed
import com.banvenez.ast.farmacia.dao.InventoryTransactionDao; // Changed
import com.banvenez.ast.farmacia.model.InventoryItem;
import com.banvenez.ast.farmacia.model.InventoryTransaction;
import com.banvenez.ast.farmacia.model.TransactionType; // Added for filtering
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors; // Added for filtering

@Service
public class ReportService {

    private final InventoryItemDao inventoryItemDao; // Changed
    private final InventoryTransactionDao transactionDao; // Changed

    @Autowired
    public ReportService(InventoryItemDao inventoryItemDao, InventoryTransactionDao transactionDao) { // Changed
        this.inventoryItemDao = inventoryItemDao;
        this.transactionDao = transactionDao;
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getCurrentInventoryReport() {
        return inventoryItemDao.findAll(); // Changed
    }

    @Transactional(readOnly = true)
    public List<InventoryTransaction> getSalesTransactions(LocalDateTime startDate, LocalDateTime endDate) {
        // The DAO method findByDateRange fetches all transactions in the range.
        // Filter for EXIT type here as per requirement.
        return transactionDao.findByDateRange(startDate, endDate).stream() // Changed
               .filter(tx -> tx.getTransactionType() == TransactionType.EXIT)
               .collect(Collectors.toList());
    }
}
