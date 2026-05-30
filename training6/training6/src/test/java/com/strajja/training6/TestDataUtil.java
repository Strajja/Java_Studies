package com.strajja.training6;

import com.strajja.training6.domain.Vehicle;

public class TestDataUtil {

    public static Vehicle createVehicleTest() {
        return Vehicle.builder()
                .id(1L)
                .brand("Fiat")
                .model("Punto")
                .build();
    }
}
