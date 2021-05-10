package com.anvesh.recepieapp.dataTransfers;

import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class IngrediantCommand {
    private Long id;
    private String description;
    private UnitOfMeasurment measurment;
    private BigDecimal amount;
    private Recipe recipe;
}
