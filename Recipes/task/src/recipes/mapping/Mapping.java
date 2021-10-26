package recipes.mapping;

import recipes.model.Direction;
import recipes.model.Ingredient;
import recipes.model.Recipe;
import recipes.request.RecipeRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {
    public static RecipeRequest mapToRequest(Recipe recipe) {
        List<String> ingredients = recipe.getIngredients()
                .stream().map(Ingredient::getIngredient)
                .collect(Collectors.toList());
        List<String> directions = recipe.getDirections()
                .stream().map(Direction::getDirection)
                .collect(Collectors.toList());
        return new RecipeRequest(recipe.getName(),
                recipe.getDescription(),
                ingredients,
                directions);
    }

    public static List<Direction> mapToDirection(List<String> directions) {
        List<Direction> ingredientsModelList = new ArrayList<>();
        for (String direction : directions) {
            ingredientsModelList.add(new Direction(direction));
        }
        return ingredientsModelList;
    }

    public static List<Ingredient> mapToIngredient(List<String> ingredients) {
        List<Ingredient> ingredientsModelList = new ArrayList<>();
        for (String ingredient : ingredients) {
            ingredientsModelList.add(new Ingredient(ingredient));
        }
        return ingredientsModelList;
    }
}
