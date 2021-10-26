package recipes.service;

import org.springframework.stereotype.Component;
import recipes.model.Direction;
import recipes.model.Ingredient;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;
import recipes.request.RecipeRequest;
import recipes.mapping.Mapping;

import java.util.List;
import java.util.Optional;

@Component
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Long addNewRecipe(RecipeRequest recipeRequest) {
        List<Ingredient> ingredients = Mapping.mapToIngredient(recipeRequest.getIngredients());
        List<Direction> directions = Mapping.mapToDirection(recipeRequest.getDirections());
        Recipe recipe = new Recipe(
                recipeRequest.getName(),
                recipeRequest.getDescription(),
                ingredients,
                directions);
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    @Override
    public RecipeRequest getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.map(Mapping::mapToRequest).orElse(null);
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
}
