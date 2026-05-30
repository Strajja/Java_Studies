package com.strajja.training6.impl.dao;

import com.strajja.training6.domain.Vehicle;

import java.util.Optional;

public interface VehicleDao{

    public void delete(Long id);

    Optional <Vehicle> findOne(Long id);

}
