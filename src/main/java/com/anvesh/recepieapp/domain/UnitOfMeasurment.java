package com.anvesh.recepieapp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UnitOfMeasurment {

    private String uom;
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
