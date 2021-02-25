package com.cookbook.restapi.service.impl;

import com.cookbook.restapi.dao.RecipeHistoryDao;
import com.cookbook.restapi.exception.NoSuchRecipeException;
import com.cookbook.restapi.exception.RecipeAlreadyExistsException;
import com.cookbook.restapi.model.Recipe;
import com.cookbook.restapi.dao.RecipeDao;
import com.cookbook.restapi.model.RecipeHistory;
import com.cookbook.restapi.service.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeDao recipeDao;
    private final RecipeHistoryDao recipeHistoryDao;

    public RecipeServiceImpl(RecipeDao recipeDao, RecipeHistoryDao recipeHistoryDao) {
        this.recipeDao = recipeDao;
        this.recipeHistoryDao = recipeHistoryDao;
    }

    @Override
    public Recipe createRecipe(Recipe recipe) throws RecipeAlreadyExistsException {
        if(recipeDao.findByName(recipe.getName()).isPresent()) {
            throw new RecipeAlreadyExistsException();
        }
        return recipeDao.save(recipe);
    }

    @Override
    public Recipe createChildRecipe(Recipe recipe, Long parentId) throws RecipeAlreadyExistsException, NoSuchRecipeException {
        if(recipeDao.findByName(recipe.getName()).isPresent()) {
            throw new RecipeAlreadyExistsException();
        }

        Optional<Recipe> parentRecipe = recipeDao.findById(parentId);
        if(parentRecipe.isEmpty()) {
            throw new NoSuchRecipeException();
        }

        recipe.setParentRecipe(parentRecipe.get());

        return recipeDao.save(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) throws NoSuchRecipeException {
        Optional<Recipe> oldRecipe = recipeDao.findById(recipe.getId());

        if(oldRecipe.isEmpty()) {
            throw new NoSuchRecipeException();
        }

        RecipeHistory recipeHistory = RecipeHistory.fromRecipe(oldRecipe.get());
        recipeHistoryDao.save(recipeHistory);

        return recipeDao.save(recipe);
    }

    @Override
    public Recipe getById(Long id) {
        Optional<Recipe> result = recipeDao.findById(id);
        if(result.isEmpty()) return null;
        return result.get();
    }

    @Override
    public Recipe deleteById(Long id) throws NoSuchRecipeException {
        Optional<Recipe> recipe = recipeDao.findById(id);
        if(recipe.isEmpty()) {
            throw new NoSuchRecipeException();
        }
        recipeDao.deleteById(id);
        return recipe.get();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeDao.findAllByOrderByNameAsc();
    }

    @Override
    public List<Recipe> getChildRecipes(Long parentId) throws NoSuchRecipeException {
        Optional<Recipe> parentRecipe = recipeDao.findById(parentId);
        if(parentRecipe.isEmpty()) {
            throw new NoSuchRecipeException();
        }
        return recipeDao.findAllByParentRecipe(parentRecipe.get());
    }

    @Override
    public List<RecipeHistory> getPreviousVersionsOfRecipe(Long recipeId) throws NoSuchRecipeException {
        Optional<Recipe> recipe = recipeDao.findById(recipeId);

        if(recipe.isEmpty()) {
            throw new NoSuchRecipeException();
        }

        return recipeHistoryDao.findAllByParentRecipeOrderByModifyAtAsc(recipe.get());
    }
}
