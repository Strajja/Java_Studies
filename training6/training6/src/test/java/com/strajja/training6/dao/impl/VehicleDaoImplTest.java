package com.strajja.training6.dao.impl;

import com.strajja.training6.TestDataUtil;
import com.strajja.training6.domain.Vehicle;
import com.strajja.training6.impl.dao.VehicleDao;
import com.strajja.training6.impl.dao.VehicleDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VehicleDaoImplTest {

   @Mock
   private JdbcTemplate jdbcTemplate;

   @InjectMocks
   private VehicleDaoImpl underTest;

    @Test
    public void testThatVehicleCanBeDeleted() {
        Vehicle vehicle = TestDataUtil.createVehicleTest();
        underTest.create(vehicle);
        underTest.delete(vehicle.getId());

        verify(jdbcTemplate).update(
                eq("DELETE FROM vehicles WHERE id=?"),
                eq(vehicle.getId())
        );

    }

    @Test
    public void testThatCreateGeneratesCorrectSql() {

        Vehicle vehicle = TestDataUtil.createVehicleTest();
        underTest.create(vehicle);

        verify(jdbcTemplate).update(
                eq("INSERT INTO vehicles (id, brand, model) VALUES(?, ?, ?)"),
                eq(vehicle.getId()),
                eq(vehicle.getBrand()),
                eq(vehicle.getModel())

        );
    }

    @Test
    public void testThatFindAllGeneratesCorrectSql(){
        underTest.findAll();

        verify(jdbcTemplate).query(
                eq("SELECT id, brand, model FROM vehicles"),
                ArgumentMatchers.<VehicleDaoImpl.VehicleMapper>any()
        );
    }

}
