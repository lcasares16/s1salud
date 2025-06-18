package com.banvenez.ast.farmacia.controller;

import com.banvenez.ast.farmacia.model.InventoryItem;
import com.banvenez.ast.farmacia.model.InventoryTransaction;
import com.banvenez.ast.farmacia.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/farmacia/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/inventory/current")
    public ResponseEntity<List<InventoryItem>> getCurrentInventoryReport() {
        List<InventoryItem> report = reportService.getCurrentInventoryReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/sales")
    public ResponseEntity<List<InventoryTransaction>> getSalesTransactions(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<InventoryTransaction> report = reportService.getSalesTransactions(startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
