package Backend.Adapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Backend.Date;
import Backend.History;
import Backend.Ingredient;
import Backend.Meal;
import Backend.Recipe;
import Backend.User;
import Backend.Workout;

public class HandlerCSV implements NewDatatypeHandler {
    String outputFile;
    String inputFile;
    HashMap<String, CanParseCanPrint> databaseMap;

    public HandlerCSV(String inputFile, String outputFile, HashMap<String, CanParseCanPrint> databaseMap) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.databaseMap = databaseMap;
    }

    @Override
    public String getInputFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInputFile'");
    }

    @Override
    public void setNewInputFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNewInputFile'");
    }

    @Override
    public void parse(String inputFile, HashMap<String, CanParseCanPrint> database) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            List<String> jsonLines = new ArrayList<>();
            String line;
            String type = br.readLine().split(",")[0];
            while ((line = br.readLine()) != null) {
                jsonLines.add(line);
                CanParseCanPrint temp = process(jsonLines, type);
                jsonLines.clear();
                br.readLine();
                database.put(temp.getName(), temp);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CanParseCanPrint process(List<String> jsonLines, String type) {
        List<String> values = new ArrayList<>();

        for (String str : jsonLines) {
            String[] temp = str.split(",");
            for (String val : temp) {
                values.add(val);
            }
        }
        String name = values.remove(0);
        CanParseCanPrint returnOBJ = null;
        if (type.equals("ingredients")) {
            double[] numbers = new double[6];
            for (int i = 0; i < values.size(); i++) {
                numbers[i] = parseDouble(values.get(i));
            }
            returnOBJ = new Ingredient(name, numbers);
        } else if (type.equals("history")) {

        } else if (type.equals("meal")) {
            ArrayList<Recipe> recipes = new ArrayList<>();
            HashMap<String, Recipe> database = Recipe.getDataBase();
            for (String s : values) {
                recipes.add(database.get(s));
            }
            returnOBJ = new Meal(name, recipes);
        } else if (type.equals("recipe")) {
            int len = values.size();
            HashMap<Ingredient, Double> data = new HashMap<>();
            HashMap<String, Ingredient> database = Ingredient.getDataBase();
            Ingredient temp = null;
            String n = "";
            String instructions = values.remove(len - 1);
            for (int i = 0; i < len - 1; i++) {
                if (i % 2 == 0) {
                    n = values.get(i);
                    temp = database.get(n);
                } else {
                    data.put(temp, parseDouble(values.get(i)));
                    database.put(n, temp);
                }
            }
            returnOBJ = new Recipe(name, data, instructions);
        }
        return returnOBJ;
    }

    private static double parseDouble(String value) {
        if (!value.isEmpty()) {
            return Double.parseDouble(value);
        }
        return 0.0;
    }

    @Override
    public String getOutputFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutputFile'");
    }

    @Override
    public void setNewOutputFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNewOutputFile'");
    }

    @Override
    public void print(String outputFile, HashMap<String, CanParseCanPrint> databaseMap) {
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            StringBuilder temp = new StringBuilder("Objects\n");
            for (Entry<String, CanParseCanPrint> entry : databaseMap.entrySet()) {
                String str = databaseMap.get(entry.getKey()).csvString();
                temp.append(str + "\n");
            }

            temp.deleteCharAt(temp.length()-1);
            fileWriter.write(temp.toString());

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        HashMap<String, CanParseCanPrint> database = new HashMap<>();
        String inputFile = "src/main/java/Data/ingredientsTest.csv";
        String outputFile = "src/main/java/Data/outputTest.csv";
        HandlerCSV ingredients = new HandlerCSV(inputFile, outputFile, database);
        ingredients.parse(inputFile, database);
        inputFile = "src/main/java/Data/recipesTest.csv";
        HandlerCSV recipes = new HandlerCSV(inputFile, outputFile, database);
        recipes.parse(inputFile, database);
        inputFile = "src/main/java/Data/mealsTest.csv";
        HandlerCSV meals = new HandlerCSV(inputFile, outputFile, database);
        meals.parse(inputFile, database);
        database.forEach((key, value) -> System.out.println(key + " " + value));
        HandlerCSV out = new HandlerCSV(inputFile, outputFile, database);
        out.print(outputFile, database);

    }
}
