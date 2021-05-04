package com.anvesh.recepieapp.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingrediant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasurment measurment;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    public Ingrediant() {
    }

    public Ingrediant(String description, UnitOfMeasurment measurment, BigDecimal amount, Recipe recipe) {
        this.description = description;
        this.measurment = measurment;
        this.amount = amount;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasurment getMeasurment() {
        return measurment;
    }

    public void setMeasurment(UnitOfMeasurment measurment) {
        this.measurment = measurment;
    }
}
