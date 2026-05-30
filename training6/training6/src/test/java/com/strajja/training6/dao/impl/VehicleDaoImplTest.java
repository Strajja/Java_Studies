package com.strajja.training6.dao.impl;

import com.strajja.training6.TestDataUtil;
import com.strajja.training6.domain.Vehicle;
import com.strajja.training6.impl.dao.VehicleDao;
import com.strajja.training6.impl.dao.VehicleDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class VehicleDaoImplTest {

    @Autowired
    private VehicleDao underTest;

    @Test
    public void testThatVehicleCanBeDeleted() {
        Vehicle vehicle = TestDataUtil.createVehicleTest();
        underTest.delete(vehicle.getId());


    }

}
