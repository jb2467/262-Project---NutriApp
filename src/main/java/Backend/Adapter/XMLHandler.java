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

public class XMLHandler implements NewDatatypeHandler {
    String outputFile;
    String inputFile;
    HashMap<String, CanParseCanPrint> databaseMap;

    public XMLHandler(String inputFile, String outputFile, HashMap<String, CanParseCanPrint> databaseMap) {

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
    public void parse(String inputFile, HashMap<String, CanParseCanPrint> databaseMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            List<String> xmlLines = new ArrayList<>();
            String line;
            br.readLine();
            String type = br.readLine().replace(" <", "").replace(">", "");
            while ((line = br.readLine()) != null) {
                if (line.contains("</" + type + ">") && !xmlLines.isEmpty()) {
                    CanParseCanPrint temp = process(xmlLines, type);
                    xmlLines.clear();
                    databaseMap.put(temp.getName(), temp);
                    br.readLine();
                } else {
                    xmlLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            StringBuilder temp = new StringBuilder("<Objects>\n");
            for (Entry<String, CanParseCanPrint> entry : databaseMap.entrySet()) {
                String str = databaseMap.get(entry.getKey()).xmlString();
                temp.append(str + "\n");
            }
            temp.append("</Objects>");
            fileWriter.write(temp.toString());
        } catch (Exception e) {

        }

    }

    private CanParseCanPrint process(List<String> xmlLines, String type) {
        List<String> values = new ArrayList<>();

        for (String str : xmlLines) {
            String[] temp = str.split(">")[1].split("<");
            String value = temp[0];
            values.add(value);
        }
        String name = values.remove(0);
        CanParseCanPrint returnOBJ = null;
        if (type.equals("ingredient")) {
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

    private  double parseDouble(String value) {
        if (!value.isEmpty()) {
            return Double.parseDouble(value);
        }
        return 0.0;
    }

    public static void main(String[] args) {
        HashMap<String, CanParseCanPrint> database = new HashMap<>();
        String inputFile = "src/main/java/Data/ingredientsTest.xml";
        String outputFile = "src/main/java/Data/outputTest.xml";
        XMLHandler ingredients = new XMLHandler(inputFile, outputFile, database);
        ingredients.parse(inputFile, database);
        inputFile = "src/main/java/Data/recipesTest.xml";
        XMLHandler recipes = new XMLHandler(inputFile, outputFile, database);
        recipes.parse(inputFile, database);
        inputFile = "src/main/java/Data/mealsTest.xml";
        XMLHandler meals = new XMLHandler(inputFile, outputFile, database);
        meals.parse(inputFile, database);
        database.forEach((key, value) -> System.out.println(key + " " + value));

        XMLHandler out = new XMLHandler(inputFile, outputFile, database);
        out.print(outputFile, database);
    }

}
