package com.strajja.training4.dao;

import com.strajja.training4.domain.InsuranceProduct;

import java.util.Optional;

public interface InsuranceProductDao {
    void create(InsuranceProduct product);
    Optional<InsuranceProduct> findById(Long id);
}
