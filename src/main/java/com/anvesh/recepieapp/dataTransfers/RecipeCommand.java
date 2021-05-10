package com.anvesh.recepieapp.dataTransfers;

import com.anvesh.recepieapp.domain.Categorie;
import com.anvesh.recepieapp.domain.Difficulty;
import com.anvesh.recepieapp.domain.Ingrediant;
import com.anvesh.recepieapp.domain.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private Difficulty difficulty;
    private String directions;
    private Notes notes;
    private Set<Ingrediant> ingrediants = new HashSet<>();
    private Set<Categorie> categories = new HashSet<>();

}
