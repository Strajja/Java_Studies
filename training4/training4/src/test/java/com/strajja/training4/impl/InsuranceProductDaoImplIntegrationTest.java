package com.strajja.training4.impl;

import com.strajja.training4.TestDataUtil;
import com.strajja.training4.domain.InsuranceProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class InsuranceProductDaoImplIntegrationTest {

    private final InsuranceProductDaoImpl underTest;

    @Autowired
    public InsuranceProductDaoImplIntegrationTest(InsuranceProductDaoImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatProductCanBeCreatedAndRecalled(){

        InsuranceProduct product= TestDataUtil.createInsuranceProduct();

        underTest.create(product);

        Optional<InsuranceProduct> result = underTest.findById(product.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);
    }

}
