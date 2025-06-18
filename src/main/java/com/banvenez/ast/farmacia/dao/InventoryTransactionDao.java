package com.banvenez.ast.farmacia.dao;

import com.banvenez.ast.farmacia.model.InventoryTransaction;
import com.banvenez.ast.farmacia.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types; // For java.sql.Types.BIGINT
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class InventoryTransactionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InventoryTransactionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class TransactionRowMapper implements RowMapper<InventoryTransaction> {
        @Override
        public InventoryTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            InventoryTransaction tx = new InventoryTransaction();
            tx.setTransactionId(rs.getLong("transaction_id"));
            tx.setInventoryItemId(rs.getLong("inventory_item_id"));
            tx.setTransactionType(TransactionType.valueOf(rs.getString("transaction_type")));
            tx.setQuantityChanged(rs.getInt("quantity_changed"));
            tx.setTransactionDate(rs.getObject("transaction_date", LocalDateTime.class));
            tx.setPriceAtTransaction(rs.getBigDecimal("price_at_transaction"));

            // Handle null for Long userId
            long userId = rs.getLong("user_id");
            if (rs.wasNull()) {
                tx.setUserId(null);
            } else {
                tx.setUserId(userId);
            }

            tx.setNotes(rs.getString("notes"));
            return tx;
        }
    }

    public InventoryTransaction save(InventoryTransaction transaction) {
        String sql = "INSERT INTO farmacia_inventory_transactions (inventory_item_id, transaction_type, quantity_changed, transaction_date, price_at_transaction, user_id, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, transaction.getInventoryItemId());
            ps.setString(2, transaction.getTransactionType().name());
            ps.setInt(3, transaction.getQuantityChanged());
            ps.setTimestamp(4, Timestamp.valueOf(transaction.getTransactionDate()));
            ps.setBigDecimal(5, transaction.getPriceAtTransaction());
            if (transaction.getUserId() != null) {
                ps.setLong(6, transaction.getUserId());
            } else {
                ps.setNull(6, Types.BIGINT);
            }
            ps.setString(7, transaction.getNotes());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            transaction.setTransactionId(keyHolder.getKey().longValue());
        }
        return transaction;
    }

    public Optional<InventoryTransaction> findById(Long id) {
        String sql = "SELECT * FROM farmacia_inventory_transactions WHERE transaction_id = ?";
        try {
            InventoryTransaction tx = jdbcTemplate.queryForObject(sql, new Object[]{id}, new TransactionRowMapper());
            return Optional.ofNullable(tx);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<InventoryTransaction> findAll() {
        String sql = "SELECT * FROM farmacia_inventory_transactions";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }

    public List<InventoryTransaction> findByInventoryItemId(Long inventoryItemId) {
        String sql = "SELECT * FROM farmacia_inventory_transactions WHERE inventory_item_id = ?";
        return jdbcTemplate.query(sql, new Object[]{inventoryItemId}, new TransactionRowMapper());
    }

    public List<InventoryTransaction> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        String sql = "SELECT * FROM farmacia_inventory_transactions WHERE transaction_date >= ? AND transaction_date <= ?";
        return jdbcTemplate.query(sql, new Object[]{Timestamp.valueOf(startDate), Timestamp.valueOf(endDate)}, new TransactionRowMapper());
    }
}
