package com.anvesh.recepieapp.dataTransfers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategorieCommand {
    private Long id;
    private String description;
}

