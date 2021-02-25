package com.cookbook.restapi.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "recipes_history")
@JsonSerialize
public class RecipeHistory extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe parentRecipe;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @PrePersist
    public void prePersist() {
        modifyAt = LocalDateTime.now();
    }

    public static RecipeHistory fromRecipe(Recipe recipe) {
        RecipeHistory recipeHistory = new RecipeHistory();

        recipeHistory.name = recipe.getName();
        recipeHistory.description = recipe.getDescription();
        recipeHistory.parentRecipe = recipe;

        return recipeHistory;
    }
}
