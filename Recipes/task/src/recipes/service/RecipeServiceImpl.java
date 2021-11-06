package recipes.service;

import org.springframework.stereotype.Component;
import recipes.model.Direction;
import recipes.model.Ingredient;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;
import recipes.dto.RecipeRequest;
import recipes.mapping.Mapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Long addNewRecipe(RecipeRequest recipeRequest) {
//        List<Ingredient> ingredients = Mapping.mapToIngredient(recipeRequest.getIngredients());
//        List<Direction> directions = Mapping.mapToDirection(recipeRequest.getDirections());
        Recipe recipe = new Recipe(
                recipeRequest.getName(),
                recipeRequest.getCategory(),
                recipeRequest.getDescription(),
                recipeRequest.getIngredients(),
                recipeRequest.getDirections());
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

    @Override
    public List<RecipeRequest> searchRecipesByCategory(String category) {
        List<Recipe> recipeList = recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
        return recipeList.stream().map(Mapping::mapToRequest).collect(Collectors.toList());
    }

    @Override
    public List<RecipeRequest> searchRecipesByName(String name) {
        List<Recipe> recipeList = recipeRepository.findAllByNameContainsIgnoreCaseOrderByDateDesc(name);
        return recipeList.stream().map(Mapping::mapToRequest).collect(Collectors.toList());
    }

    @Override
    public void updateRecipeById(long id, RecipeRequest request) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            Recipe recipeModel = recipe.get();
            recipeModel.setName(request.getName());
            recipeModel.setCategory(request.getCategory());
            recipeModel.setDescription(request.getDescription());
            recipeModel.setDate(LocalDateTime.now());
            recipeModel.setDirections(request.getDirections());
            recipeModel.setIngredients(request.getIngredients());
            recipeRepository.save(recipeModel);
        }
    }
}
