package recipes.service;

import org.springframework.stereotype.Service;
import recipes.dto.RecipeRequest;

import java.util.List;

@Service
public interface RecipeService {

    Long addNewRecipe(RecipeRequest recipeRequest);

    RecipeRequest getRecipeById(Long id);

    void deleteRecipeById(Long id);

    List<RecipeRequest> searchRecipesByCategory(String category);

    List<RecipeRequest> searchRecipesByName(String name);

    void updateRecipeById(long id, RecipeRequest recipeRequest);
}
