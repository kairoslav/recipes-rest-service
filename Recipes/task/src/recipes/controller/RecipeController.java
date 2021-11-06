package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.dto.RecipeRequest;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable long id) {
        RecipeRequest request = recipeService.getRecipeById(id);
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity<?> searchRecipe(@RequestParam(required = false) String category,
                                          @RequestParam(required = false) String name) {
        if (category != null && name == null) {
            List<RecipeRequest> recipeRequestList = recipeService.searchRecipesByCategory(category);
            return new ResponseEntity<>(recipeRequestList, HttpStatus.OK);
        } else if (name != null && category == null) {
            List<RecipeRequest> recipeRequestList = recipeService.searchRecipesByName(name);
            return new ResponseEntity<>(recipeRequestList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> postRecipe(@RequestBody @Valid RecipeRequest request) {
        Long id = recipeService.addNewRecipe(request);
        return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> putRecipe(@PathVariable long id, @RequestBody @Valid RecipeRequest request) {
        if (recipeService.getRecipeById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.updateRecipeById(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable long id) {
        if (recipeService.getRecipeById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
