package com.cookbook.restapi.dao;


import com.cookbook.restapi.model.Recipe;
import com.cookbook.restapi.model.RecipeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeHistoryDao extends JpaRepository<RecipeHistory, Long> {
    List<RecipeHistory> findAllByParentRecipeOrderByModifyAtAsc(Recipe recipe);
}
