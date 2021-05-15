package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import com.anvesh.recepieapp.repositories.UnitOfMeasureRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UnitOfMeasureServiceImp implements UnitOfMeasureService {
    private final UnitOfMeasureRepo repository;

    //    Spring INjection
    public UnitOfMeasureServiceImp(UnitOfMeasureRepo repository) {
        this.repository = repository;
    }

    //    Getting all unit of measurements from DataBase
    @Override
    public Set<UnitOfMeasurment> findAllUOM() {
        Set<UnitOfMeasurment> measurments = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(measurments::add);
        return measurments;
    }
}
