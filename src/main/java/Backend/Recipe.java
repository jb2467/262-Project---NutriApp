package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Backend.Adapter.CanParseCanPrint;

public class Recipe extends Food implements CanParseCanPrint {
    // sting for name
    private String name;
    private static HashMap<String, Recipe> databaseMap = new HashMap<>();
    private HashMap<Ingredient, Double> ingredients;
    private String instructions;

    public Recipe(String name, HashMap<Ingredient, Double> ingredients2, String instructions) {
        super(name);
        this.name = name;
        this.ingredients = ingredients2;
        this.instructions = instructions;
        databaseMap.put(name, this);
    }

    public Recipe(String name, HashMap<Ingredient, Double> ingredients2, String instructions, double[] numbers) {
        super(name,numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]);
        this.name = name;
        this.ingredients = ingredients2;
        this.instructions = instructions;
        databaseMap.put(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static HashMap<String, Recipe> getDataBase() {
        return databaseMap;
    }

    public HashMap<Ingredient, Double> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<Ingredient, Double> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getQuantityForIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient))
            return ingredients.get(ingredient);
        return 0;
    }

    public String toString() {
        String temp = name + " ";
        for (Map.Entry<Ingredient, Double> set : ingredients.entrySet()) {

            // Printing all elements of a Map
            temp += (set.getKey() + " = "
                    + set.getValue());
        }
        return temp;
    }

    public String jString() {
        StringBuilder temp = new StringBuilder();
        temp.append("{\n\"name\": " + '"' + name + '"' + ",\n");
        int index = 1;
        for (Entry<Ingredient, Double> Ingred : ingredients.entrySet()) {
            temp.append("\"ingredient" + index + "\" :" + '"' + Ingred.getKey().getName() + '"'  + ",\n");
            temp.append("\"ingredient" + index + "amount\"" + " :" + Ingred.getValue() + ",\n");
            index++;
        }
        temp.append("\"instructions\": " + '"' + instructions + '"' + "\n}");
        return temp.toString();
    }

    @Override
    public String xmlString() {

        StringBuilder temp = new StringBuilder();

        temp.append("<recipe>\n");
        int index =1 ;
        temp.append("<name>" + name + "</name>\n");
        for (Entry<Ingredient, Double> Ingred : ingredients.entrySet()) {
            temp.append("<ingredient" + index + ">" + Ingred.getKey().getName() + "</ingredient" + index + ">\n" );
            temp.append("<ingredient" + index + "amount>" + Ingred.getValue() + "</ingredient" + index + "amount>\n");
            index++;
        }
        temp.append("<instructions>" + instructions + "</instructions>\n");
        temp.append("</recipe>");

        return temp.toString();
    }

    @Override
    public String csvString() {
        StringBuilder temp = new StringBuilder();
        temp.append(name + ",");
        for (Entry<Ingredient, Double> Ingred : ingredients.entrySet()) {
            temp.append(Ingred.getKey().getName() + ",");
            temp.append(Ingred.getValue() + ",");
        }
        temp.append(instructions);
        return temp.toString();
    }
}
