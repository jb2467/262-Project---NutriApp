package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateRecipe implements Command{
    private String recipeName;
    private HashMap<Ingredient, Double> ingredients;
    private String instructions;
    private Meal meal;
    private Recipe createdRecipe;

    public CreateRecipe(String recipeName, HashMap<Ingredient,Double> ingredients, String instructions, Meal meal) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.meal = meal;
    }

    @Override
    public void executeAction() {
        // Create a new Recipe object
        createdRecipe = new Recipe(recipeName, ingredients, instructions);
        meal.addRecipe(createdRecipe);
    }

    @Override
    public void undoAction() {
        if (createdRecipe != null) {
            meal.removeRecipe(createdRecipe);
            createdRecipe = null;
        }
    }

}
