package recipes.mapping;

import recipes.dto.RecipeRequest;
import recipes.model.Recipe;

public class Mapping {
    public static RecipeRequest mapToRequest(Recipe recipe) {
        return new RecipeRequest(recipe.getName(),
                recipe.getCategory(),
                recipe.getDate(),
                recipe.getDescription(),
                recipe.getIngredients(),
                recipe.getDirections());
    }
}
