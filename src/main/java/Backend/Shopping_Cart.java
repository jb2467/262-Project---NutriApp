package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Shopping_Cart {

    // List of ingredients for the recipes the user wants to make
    private ArrayList<Ingredient> ingredients;

    // List of recipes the user wants to make
    private ArrayList<Recipe> recipes;

    // To store the quantity of each ingredient
    private HashMap<Ingredient, Double> ingredientQuantity;

    public Shopping_Cart() {
        this.ingredients = new ArrayList<>();
        this.recipes = new ArrayList<>();
        this.ingredientQuantity = new HashMap<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        Set<Ingredient> keys= recipe.getIngredients().keySet();
        // Adding ingredients of the recipe to the cart and updating their quantities
        for (Ingredient ingredient : keys) {
            ingredients.add(ingredient);

            // Assuming the recipe has a method to get the quantity required for each ingredient
            double quantityRequired = recipe.getQuantityForIngredient(ingredient);

            if (ingredientQuantity.containsKey(ingredient)) {
                double currentQuantity = ingredientQuantity.get(ingredient);
                ingredientQuantity.put(ingredient, currentQuantity + quantityRequired);
            } else {
                ingredientQuantity.put(ingredient, quantityRequired);
            }
        }
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        Set<Ingredient> keys= recipe.getIngredients().keySet();
        // Removing the ingredients associated with the recipe and updating their quantities
        for (Ingredient ingredient : keys) {
            double quantityRequired = recipe.getQuantityForIngredient(ingredient);

            if (ingredientQuantity.containsKey(ingredient)) {
                double currentQuantity = ingredientQuantity.get(ingredient);
                double newQuantity = currentQuantity - quantityRequired;

                if (newQuantity <= 0) {
                    ingredientQuantity.remove(ingredient);
                    ingredients.remove(ingredient);
                } else {
                    ingredientQuantity.put(ingredient, newQuantity);
                }
            }
        }
    }

    public void clearCart() {
        recipes.clear();
        ingredients.clear();
        ingredientQuantity.clear();
    }

}