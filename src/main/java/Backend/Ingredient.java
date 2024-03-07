package Backend;

import Backend.Adapter.CanParseCanPrint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Ingredient extends Food implements CanParseCanPrint {
    private final String INPUT_FILE = "src\\main\\java\\Data\\ingredients.csv";
    public static HashMap<String, Ingredient> IngredientsDatabase = new HashMap<String, Ingredient>();
    private double amtInStock;

    public Ingredient(String name, double calories, double fat, double protein, double fiber, double carbs,
            double amtInStock) {
        super(name, calories, fat, protein, fiber, carbs);
        this.amtInStock = 0;
        IngredientsDatabase.put(name, this);
    }

    public Ingredient(String name, double[] numbers) {
        super(name, numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]);
        this.amtInStock = numbers[5];
        IngredientsDatabase.put(name, this);
    }

    public String toString() {
        return super.toString();
    }

    public Ingredient() {
        super("");
        this.amtInStock = 0;
    }

    public double getAmtInStock() {
        return this.amtInStock;
    }

    public void setAmtInStock(double newAmt) {
        this.amtInStock = newAmt;
    }

    public String getName() {
        return super.getName();
    }

    public void parseIngredientCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            // Hard code values by looking at the values
            int nameIndex = 1;
            int caloriesIndex = 3;
            int fatIndex = 5; // Lipids
            int proteinIndex = 4;
            int fiberIndex = 8;
            int carbsIndex = 7;
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[nameIndex].replace("\"", "");
                double calories = parseDouble(values[caloriesIndex]);
                double fat = parseDouble(values[fatIndex]);
                double protein = parseDouble(values[proteinIndex]);
                double fiber = parseDouble(values[fiberIndex]);
                double carbs = parseDouble(values[carbsIndex]);
                // need to calculate the amtInStock using zero as a placeholder
                Ingredient ingredient = new Ingredient(name, calories, fat, protein, fiber, carbs, 50.0);
                IngredientsDatabase.put(name, ingredient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double parseDouble(String value) {
        if (!value.isEmpty()) {
            return Double.parseDouble(value);
        }
        return 0.0;
    }


    public static HashMap<String, Ingredient> getDataBase() {
        return IngredientsDatabase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
    @Override
    public String jString() {
        StringBuilder temp = new StringBuilder();
        temp.append("{\n\"name\": " + '"' + super.getName() + '"' + ",\n");
        temp.append("\"calories\": " + '"' + super.getCalories() + '"' + ",\n");
        temp.append("\"fat\": " + '"' + super.getFat() + '"' + ",\n");
        temp.append("\"protien\": " + '"' + super.getProtein() + '"' + ",\n");
        temp.append("\"fiber\": " + '"' + super.getFiber() + '"' + ",\n");
        temp.append("\"carbs\": " + '"' + super.getCarbs() + '"' + ",\n");
        temp.append("\"AmountInStock\": " + '"' + amtInStock + '"' + "\n}");
        return temp.toString();
    }


    @Override
    public String xmlString() {
        StringBuilder temp = new StringBuilder();

        temp.append("<name>" + super.getName() + "</name>");
        temp.append("<calories>" + super.getCalories() + "</calories>");
        temp.append("<fat>" + super.getFat() + "</fat>");
        temp.append("<protien>" + super.getProtein() + "</protien>");
        temp.append("<fiber>" + super.getFiber() + "</fiber>");
        temp.append("<carbs>" + super.getCarbs() + "</carbs>");
        temp.append("<AmountInStock>" + amtInStock + "</AmountInStock>");

        return temp.toString();
    }

    @Override
    public String csvString() {
        StringBuilder temp = new StringBuilder();
        temp.append(super.getName()+",");
        temp.append(super.getCalories()+",");
        temp.append(super.getFat()+",");
        temp.append(super.getProtein()+",");
        temp.append(super.getFiber()+",");
        temp.append(super.getCarbs()+",");
        temp.append(amtInStock+",");
        return temp.toString();
    }

    public static void main(String[] args) {
        Ingredient ingredientInstance = new Ingredient("temp", 0, 0, 0, 0, 0, 0);
        ingredientInstance.parseIngredientCSV();
        IngredientsDatabase.forEach((key, value) -> System.out.println(key + " " + value));
    }

}
