package com.anvesh.recepieapp.repositories;

import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepo extends CrudRepository<UnitOfMeasurment, Long> {

    Optional<UnitOfMeasurment> findByUom(String aLong);
}
