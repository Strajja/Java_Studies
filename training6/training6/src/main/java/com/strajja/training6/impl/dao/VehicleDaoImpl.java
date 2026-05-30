package com.strajja.training6.impl.dao;

import com.strajja.training6.domain.Vehicle;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class VehicleDaoImpl implements VehicleDao{

    private final JdbcTemplate jdbcTemplate;

   public VehicleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(Long id) {

       jdbcTemplate.update("delete from vehicle where id=?",id);

    }

    @Override
    public Optional<Vehicle> findOne(Long id) {
        List<Vehicle> result= jdbcTemplate.query(
                "SELECT id, brand, model FROM vehicles WHERE id=? LIMIT 1",
                new VehicleMapper(),
                id
        );
        return result.stream().findFirst();
    }


    public class VehicleMapper implements RowMapper<Vehicle> {
       @Override
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
           return Vehicle.builder()
                   .id(rs.getLong("id"))
                   .brand(rs.getString("brand"))
                   .model(rs.getString("model"))
                   .build();
       }
    }
}
