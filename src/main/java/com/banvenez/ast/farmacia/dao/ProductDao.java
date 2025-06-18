package com.banvenez.ast.farmacia.dao;

import com.banvenez.ast.farmacia.model.Product;
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
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getLong("product_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setQrCodeData(rs.getString("qr_code_data"));
            return product;
        }
    }

    public Product save(Product product) {
        String sql = "INSERT INTO farmacia_products (name, description, qr_code_data) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getQrCodeData());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            product.setProductId(keyHolder.getKey().longValue());
        } else {
            // This case should ideally not happen if RETURN_GENERATED_KEYS works and the DB generates keys.
            // Might indicate an issue with DB setup or JDBC driver.
            // Consider logging a warning or throwing an exception.
            // For now, we'll assume it works as expected.
        }
        return product;
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM farmacia_products WHERE product_id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM farmacia_products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public Optional<Product> findByQrCodeData(String qrCodeData) {
        String sql = "SELECT * FROM farmacia_products WHERE qr_code_data = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{qrCodeData}, new ProductRowMapper());
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Product> findByName(String name) {
        String sql = "SELECT * FROM farmacia_products WHERE name = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{name}, new ProductRowMapper());
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int update(Product product) {
        String sql = "UPDATE farmacia_products SET name = ?, description = ?, qr_code_data = ? WHERE product_id = ?";
        return jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getQrCodeData(), product.getProductId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM farmacia_products WHERE product_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
