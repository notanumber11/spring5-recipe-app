package guru.springframework.bootstrap;

/**
 * Created by notanumber on 9/09/17.
 */

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRespotiory;

    public DevBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRespotiory) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRespotiory = unitOfMeasureRespotiory;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        // Recovering unit of measure
        UnitOfMeasure teaspoon = unitOfMeasureRespotiory.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRespotiory.findByDescription("Tablespoon").get();
        UnitOfMeasure cup = unitOfMeasureRespotiory.findByDescription("Cup").get();
        UnitOfMeasure pinch = unitOfMeasureRespotiory.findByDescription("Pinch").get();
        UnitOfMeasure ounce = unitOfMeasureRespotiory.findByDescription("Ounce").get();
        UnitOfMeasure unit = unitOfMeasureRespotiory.findByDescription("Unit").get();
        UnitOfMeasure tbsp = unitOfMeasureRespotiory.findByDescription("Tbsp").get();
        UnitOfMeasure dash = unitOfMeasureRespotiory.findByDescription("Dash").get();

        // Recovering categorys
        Category american = categoryRepository.findByDescription("American").get();
        Category italian = categoryRepository.findByDescription("Italian").get();
        Category mexican = categoryRepository.findByDescription("Mexican").get();
        Category fastFood = categoryRepository.findByDescription("Fast Food").get();


        // Creating note
        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).");

        // Creating ingredients
            // Avocado
            Ingredient avocado = new Ingredient();
            avocado.setAmount(new BigDecimal(2));
            avocado.setDescription("avocado");
            avocado.setUom(unit);
            // Kosher salt
            Ingredient kosherSalt = new Ingredient();
            kosherSalt.setAmount(new BigDecimal(0.5));
            kosherSalt.setDescription("Kosher salt");
            kosherSalt.setUom(teaspoon);

            // Creating set of ingredients
            Set<Ingredient> guacamoleIngredients = new HashSet<>();
            guacamoleIngredients.add(avocado);
            guacamoleIngredients.add(kosherSalt);

        // Creating guacamole recipe
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setDescription("Avocado recipe");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed.");
        guacamoleRecipe.setSource("www.simplyrecipes.com");


        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setCookTime(10);

        // Binding to recipe
        guacamoleRecipe.setNotes(guacamoleNotes);
        guacamoleRecipe.setIngredients(guacamoleIngredients);
        avocado.setRecipe(guacamoleRecipe);
        kosherSalt.setRecipe(guacamoleRecipe);

        // Saving guacamole recipe
        recipeRepository.save(guacamoleRecipe);



    }
}
