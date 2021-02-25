package com.cookbook.restapi.service;

import com.cookbook.restapi.exception.NoSuchRecipeException;
import com.cookbook.restapi.exception.RecipeAlreadyExistsException;
import com.cookbook.restapi.model.Recipe;
import com.cookbook.restapi.model.RecipeHistory;

import java.util.List;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe) throws RecipeAlreadyExistsException;
    Recipe createChildRecipe(Recipe recipe, Long parentId) throws RecipeAlreadyExistsException, NoSuchRecipeException;
    Recipe updateRecipe(Recipe recipe) throws NoSuchRecipeException;
    Recipe getById(Long id);
    Recipe deleteById(Long id) throws NoSuchRecipeException;
    List<Recipe> getAllRecipes();
    List<Recipe> getChildRecipes(Long parentId) throws NoSuchRecipeException;
    List<RecipeHistory> getPreviousVersionsOfRecipe(Long recipeId) throws NoSuchRecipeException;
}
