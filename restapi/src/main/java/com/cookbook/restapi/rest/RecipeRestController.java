package com.cookbook.restapi.rest;

import com.cookbook.restapi.dto.SaveRecipeRequestDto;
import com.cookbook.restapi.dto.UpdateRecipeRequestDto;
import com.cookbook.restapi.exception.NoSuchRecipeException;
import com.cookbook.restapi.exception.RecipeAlreadyExistsException;
import com.cookbook.restapi.model.Recipe;
import com.cookbook.restapi.dao.RecipeDao;
import com.cookbook.restapi.service.RecipeService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/cookbook/recipes")
public class RecipeRestController {

    private final RecipeService recipeService;

    public RecipeRestController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(recipeService.deleteById(id));
        } catch (NoSuchRecipeException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getRecipeById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recipeService.getById(id));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity getRecipeHistoryById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(recipeService.getPreviousVersionsOfRecipe(id));
        } catch (NoSuchRecipeException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }

    @GetMapping("/{parentId}/child")
    public ResponseEntity getChildRecipesByParentId(@PathVariable Long parentId) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(recipeService.getChildRecipes(parentId));
        } catch (NoSuchRecipeException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }

    @SneakyThrows
    @PostMapping("")
    public ResponseEntity saveRecipe(@RequestBody SaveRecipeRequestDto requestDto) {
        Recipe recipe = new Recipe();

        recipe.setName(requestDto.getName());
        recipe.setDescription(requestDto.getDescription());

        try {
            Recipe result = requestDto.getParentId() == null ?
                    recipeService.createRecipe(recipe) :
                    recipeService.createChildRecipe(recipe, requestDto.getParentId());

            return ResponseEntity.ok(result);
        } catch (RecipeAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }

    @SneakyThrows
    @PutMapping("")
    public ResponseEntity updateRecipe(@RequestBody UpdateRecipeRequestDto requestDto) {
        Recipe recipe = new Recipe();

        recipe.setId(requestDto.getId());
        recipe.setName(requestDto.getName());
        recipe.setDescription(requestDto.getDescription());
        if(requestDto.getParentId() != null)
            recipe.setParentRecipe(recipeService.getById(requestDto.getParentId()));

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(recipeService.updateRecipe(recipe));
        } catch (NoSuchRecipeException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }
}
