package com.cookbook.restapi.service;

import com.cookbook.restapi.exception.NoSuchRecipeException;
import com.cookbook.restapi.exception.RecipeAlreadyExistsException;
import com.cookbook.restapi.model.Recipe;
import com.cookbook.restapi.dao.RecipeDao;
import com.cookbook.restapi.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@RunWith(SpringRunner.class)
class RecipeServiceTests {

    @Autowired
    private RecipeServiceImpl recipeService;

    @Test
    public void shouldSaveRecipeSuccesfully() throws RecipeAlreadyExistsException {
        Recipe recipe = new Recipe();
        recipe.setName("Recipe");
        recipe.setDescription("Description");
        Recipe result = recipeService.createRecipe(recipe);
        assertThat(result.getName(), equalTo(recipe.getName()));
    }

    @Test
    void shouldThrowExceptionWhenCreateRecipeWithExistingName() throws RecipeAlreadyExistsException {
        final Recipe recipe = new Recipe();
        recipe.setName("Chicken");
        recipe.setDescription("Description");

        recipeService.createRecipe(recipe);

        assertThrows(RecipeAlreadyExistsException.class, () -> {
            recipeService.createRecipe(recipe);
        });
    }
//
    @Test
    void shouldGetAllRecipesSuccesfully() throws RecipeAlreadyExistsException {
        final Recipe recipe1 = new Recipe();
        recipe1.setName("recipe1");
        recipe1.setDescription("description1");
        recipeService.createRecipe(recipe1);

        final Recipe recipe2 = new Recipe();
        recipe2.setName("recipe2");
        recipe2.setDescription("description2");
        recipeService.createRecipe(recipe2);

        final Recipe recipe3 = new Recipe();
        recipe3.setName("recipe3");
        recipe3.setDescription("description3");
        recipeService.createRecipe(recipe3);

        List<Recipe> recipes = List.of(recipe1, recipe2, recipe3);

        assertThat(recipeService.getAllRecipes().get(1).getName(), equalTo(recipes.get(1).getName()));
    }

    @Test
    void shouldRecipeUpdateCorrectly() throws RecipeAlreadyExistsException, NoSuchRecipeException {
        final Recipe recipe = new Recipe();
        recipe.setName("Chicken");
        recipe.setDescription("Delicious chicken");

        recipeService.createRecipe(recipe);

        final Recipe beforeChange = recipeService.getById(1L);

        beforeChange.setName("Pork");

        final Recipe afterChange = recipeService.updateRecipe(beforeChange);
        assertThat(recipeService.getById(1L).getName(), equalTo(afterChange.getName()));
    }
}
