package com.anvesh.recepieapp.dataTransfers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class IngrediantCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private UnitOfMeasurementCommand measurment;
    private BigDecimal amount;
}
