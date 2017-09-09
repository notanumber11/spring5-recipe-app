package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.List;
import java.util.Set;

/**
 * Created by notanumber on 9/09/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();
}
