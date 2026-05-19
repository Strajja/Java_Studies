package com.strajja.training4;

import com.strajja.training4.domain.InsuranceProduct;

public class TestDataUtil {

    private TestDataUtil() {}

    public static InsuranceProduct createInsuranceProduct() {
        return InsuranceProduct.builder()
                .id(100L)
                .name("Kasko Auto")
                .type("VEHICLE")
                .description("Total insurance care of the vehicle.")
                .build();
    }
}
