package com.anvesh.recepieapp.dataTransfers;

import com.anvesh.recepieapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 250)
    private String description;
    @Min(1)
    @Max(999)
    private Integer prepTime;
    @Min(1)
    @Max(999)
    private Integer cookTime;
    @Min(1)
    @Max(999)
    private Integer servings;
    private String source;
    //    @URL
    private String url;

    private Difficulty difficulty;
    @NotBlank
    private String directions;
    private NotesCommand notes;
    private Byte[] image;
    private Set<IngrediantCommand> ingrediants = new HashSet<>();
    private Set<CategorieCommand> categories = new HashSet<>();

}
