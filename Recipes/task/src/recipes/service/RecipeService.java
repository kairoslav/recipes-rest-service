package recipes.service;

import org.springframework.stereotype.Service;
import recipes.request.RecipeRequest;

@Service
public interface RecipeService {

    Long addNewRecipe(RecipeRequest recipeRequest);

    RecipeRequest getRecipeById(Long id);

    void deleteRecipeById(Long id);
}
