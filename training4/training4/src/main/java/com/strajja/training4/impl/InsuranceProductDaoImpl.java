package com.strajja.training4.impl;

import com.strajja.training4.dao.InsuranceProductDao;
import com.strajja.training4.domain.InsuranceProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class InsuranceProductDaoImpl implements InsuranceProductDao {

    private final JdbcTemplate jdbcTemplate;

    public InsuranceProductDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(InsuranceProduct product) {

        jdbcTemplate.update(
                "INSERT INTO insurance_products (id, name, type, description) VALUES (?, ?, ?, ?)",
                product.getId(), product.getName(), product.getType(), product.getDescription()
        );
    }

    @Override
    public Optional<InsuranceProduct> findById(Long id) {
        List<InsuranceProduct> insuranceProducts = jdbcTemplate.query(
                "SELECT id, name, type, description FROM insurance_products WHERE id = ? LIMIT 1",
                new InsuranceProductMapper(),id
        );
        return insuranceProducts.stream().findFirst();
    }

    public static class InsuranceProductMapper implements RowMapper<InsuranceProduct> {

        @Override
        public InsuranceProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
            return InsuranceProduct.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .type(rs.getString("type"))
                    .description(rs.getString("description"))
                    .build();
        }
    }
}
