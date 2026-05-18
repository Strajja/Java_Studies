package com.strajja.training4.impl;

import com.strajja.training4.TestDataUtil;
import com.strajja.training4.dao.InsuranceProductDao;
import com.strajja.training4.domain.InsuranceProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InsuranceProductDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private InsuranceProductDaoImpl insuranceProductDao;

    @Test
    public void testThatCreateProductGeneratesCorrectSql() {
        InsuranceProduct product= TestDataUtil.createInsuranceProduct();

        insuranceProductDao.create(product);

        verify(jdbcTemplate).update(
                eq("INSERT INTO insurance_products (id, name, type, description) VALUES (?, ?, ?, ?)"),
                eq(100L), eq("Kasko Auto"), eq("VEHICLE"), eq("Total insurance care of the vehicle.")
        );
    }

    @Test
    public void testFindByIdProductGeneratesCorrectSql() {

        insuranceProductDao.findById(100L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, type, description FROM insurance_products WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<InsuranceProductDaoImpl.InsuranceProductMapper>any(),
                eq(100L)
        );
    }
}
