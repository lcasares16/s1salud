package com.banvenez.ast.farmacia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Removed javax.persistence.* imports
// Removed java.util.List import as it's not used

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productId;
    private String name;
    private String description;
    private String qrCodeData;
}
