package com.strajja.training6.dao.impl;

import com.strajja.training6.TestDataUtil;
import com.strajja.training6.domain.Vehicle;
import com.strajja.training6.impl.dao.VehicleDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VehicleDaoImplIntegrationTest {

    @Autowired
    private VehicleDao vehicleDao;

    @Test
    public void testThatVehicleCanBeDeleted() {
        Vehicle vehicle = TestDataUtil.createVehicleTest();
        vehicleDao.create(vehicle);
        vehicleDao.delete(vehicle.getId());

        Optional<Vehicle> result=vehicleDao.findOne(vehicle.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatVehicleCanBeCreatedAndRecalled() {
        Vehicle vehicle = TestDataUtil.createVehicleTest();
        vehicleDao.create(vehicle);

        Optional<Vehicle> result=vehicleDao.findOne(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getBrand()).isEqualTo("Fiat");
    }

    @Test
    public void testThatFindAllGeneratesCorrectSql() {

        Vehicle vehicle = TestDataUtil.createVehicleTest();
        vehicleDao.create(vehicle);

        List<Vehicle> results = vehicleDao.findAll();

        assertThat(results)
                .hasSize(1)
                .contains(vehicle);

    }
}
