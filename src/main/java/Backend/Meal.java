package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.Attributes.Name;

import Backend.Adapter.CanParseCanPrint;

public class Meal extends Food implements Meal_Interface, CanParseCanPrint {
    ArrayList<Recipe> recipes;
    private static HashMap<String, Meal> mealDatabaseMap = new HashMap<>();

    public Meal(String name, ArrayList<Recipe> recipes) {
        super(name);
        this.recipes = recipes;
        mealDatabaseMap.put(name, this);
    }

    public Meal(String name, ArrayList<Recipe> recipes, double[] numbers) {
        super(name,numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]);
        this.recipes = recipes;
        mealDatabaseMap.put(name, this);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    @Override
    public void executes() {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executes'");
    }

    public static HashMap<String, Meal> getDataBase() {
        return mealDatabaseMap;
    }

    public String toString() {
        return super.toString() + " " + recipes.toString();
    }

    @Override
    public String jString() {
        StringBuilder temp = new StringBuilder();
        temp.append("{\n\"name\": " + '"' + super.getName() + '"' + ",\n");
        int index = 1;
        for (int i =0; i < recipes.size(); i++) {
            temp.append("\"recipe" + index + "\" :" +  '"' + recipes.get(i).getName() +  '"');
            index++;
            if (i != recipes.size()-1) {
                temp.append(',');
            }
            temp.append("\n");
        }
        temp.append('}');
        return temp.toString();

    }

    @Override
    public String xmlString() {
        StringBuilder temp = new StringBuilder();
        temp.append("<meal>\n");
        temp.append("<name>" +super.getName() + "</name>\n");
        int index = 1;
        for (int i =0; i < recipes.size(); i++) {
            temp.append("<recipe" + index + ">" + recipes.get(i).getName() + "</recipe" + index + ">\n" );
            index++;
        }
        temp.append("</meal>");
        return temp.toString();
    }

    @Override
    public String csvString() {
        StringBuilder temp = new StringBuilder();
        temp.append(super.getName() + ",");
        for (int i =0; i < recipes.size(); i++)
        {
            temp.append(recipes.get(i).getName()+ ",");
        }
        temp.deleteCharAt(temp.length()-1);
        return temp.toString();
    }

}
