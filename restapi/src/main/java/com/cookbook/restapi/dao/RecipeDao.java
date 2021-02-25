package com.cookbook.restapi.dao;

import com.cookbook.restapi.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RecipeDao extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByName(String name);
    List<Recipe> findAllByOrderByNameAsc();
    List<Recipe> findAllByParentRecipe(Recipe recipe);
}
