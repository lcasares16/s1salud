package com.banvenez.ast.farmacia.dao;

import com.banvenez.ast.farmacia.model.InventoryItem;
// Import Product if needed for richer queries, or just use productId
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class InventoryItemDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InventoryItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class InventoryItemRowMapper implements RowMapper<InventoryItem> {
        @Override
        public InventoryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            InventoryItem item = new InventoryItem();
            item.setInventoryItemId(rs.getLong("inventory_item_id"));
            item.setProductId(rs.getLong("product_id"));
            item.setEntryDate(rs.getObject("entry_date", LocalDate.class));
            item.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
            item.setQuantity(rs.getInt("quantity"));
            item.setPurchasePrice(rs.getBigDecimal("purchase_price"));
            item.setSalePrice(rs.getBigDecimal("sale_price"));
            // transactions list would be populated by service/another DAO call
            return item;
        }
    }

    public InventoryItem save(InventoryItem item) {
        String sql = "INSERT INTO farmacia_inventory_items (product_id, entry_date, expiry_date, quantity, purchase_price, sale_price) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, item.getProductId());
            ps.setDate(2, Date.valueOf(item.getEntryDate()));
            ps.setDate(3, item.getExpiryDate() != null ? Date.valueOf(item.getExpiryDate()) : null);
            ps.setInt(4, item.getQuantity());
            ps.setBigDecimal(5, item.getPurchasePrice());
            ps.setBigDecimal(6, item.getSalePrice());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            item.setInventoryItemId(keyHolder.getKey().longValue());
        }
        return item;
    }

    public Optional<InventoryItem> findById(Long id) {
        String sql = "SELECT * FROM farmacia_inventory_items WHERE inventory_item_id = ?";
        try {
            InventoryItem item = jdbcTemplate.queryForObject(sql, new Object[]{id}, new InventoryItemRowMapper());
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<InventoryItem> findAll() {
        String sql = "SELECT * FROM farmacia_inventory_items";
        return jdbcTemplate.query(sql, new InventoryItemRowMapper());
    }

    public List<InventoryItem> findByProductId(Long productId) {
        String sql = "SELECT * FROM farmacia_inventory_items WHERE product_id = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, new InventoryItemRowMapper());
    }

    public List<InventoryItem> findByExpiryDateBefore(LocalDate date) {
        String sql = "SELECT * FROM farmacia_inventory_items WHERE expiry_date < ?";
        return jdbcTemplate.query(sql, new Object[]{Date.valueOf(date)}, new InventoryItemRowMapper());
    }

    public List<InventoryItem> findByProductIdAndQuantityGreaterThan(Long productId, int quantity) {
        String sql = "SELECT * FROM farmacia_inventory_items WHERE product_id = ? AND quantity > ? ORDER BY expiry_date ASC, entry_date ASC"; // FIFO/FEFO for sales
        return jdbcTemplate.query(sql, new Object[]{productId, quantity}, new InventoryItemRowMapper());
    }

    public int update(InventoryItem item) {
        String sql = "UPDATE farmacia_inventory_items SET product_id = ?, entry_date = ?, expiry_date = ?, quantity = ?, purchase_price = ?, sale_price = ? WHERE inventory_item_id = ?";
        return jdbcTemplate.update(sql,
                                   item.getProductId(),
                                   item.getEntryDate() != null ? Date.valueOf(item.getEntryDate()) : null,
                                   item.getExpiryDate() != null ? Date.valueOf(item.getExpiryDate()) : null,
                                   item.getQuantity(),
                                   item.getPurchasePrice(),
                                   item.getSalePrice(),
                                   item.getInventoryItemId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM farmacia_inventory_items WHERE inventory_item_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
