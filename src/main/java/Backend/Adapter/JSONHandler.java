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

public class JSONHandler implements NewDatatypeHandler {
    String outputFile;
    String inputFile;
    HashMap<String, CanParseCanPrint> databaseMap;

    public JSONHandler(String inputFile, String outputFile, HashMap<String, CanParseCanPrint> databaseMap) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.databaseMap = databaseMap;
        if (databaseMap == null){
            this.databaseMap.putAll(Ingredient.getDataBase());
            this.databaseMap.putAll(Meal.getDataBase());
            this.databaseMap.putAll(Recipe.getDataBase());
            this.databaseMap.putAll(History.getDataBase());
        }else{
            this.databaseMap = databaseMap;
        }
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
        // TODO Auto-generated method stub
        if(database == null){
            database = this.databaseMap;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            List<String> jsonLines = new ArrayList<>();
            String line;
            br.readLine();
            String type = br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null) {

                if (line.contains("}") && !jsonLines.isEmpty() && !line.contains(": [")) {

                    CanParseCanPrint temp = process(jsonLines, type);
                    jsonLines.clear();
                    br.readLine();
                    database.put(temp.getName(), temp);
                } else {
                    jsonLines.add(line);
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
        // TODO Auto-generated method stub
        if(databaseMap == null){
            databaseMap = this.databaseMap;
        }
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            StringBuilder temp = new StringBuilder("{\"Objects\":\n[\n");

            for (Entry<String, CanParseCanPrint> entry : databaseMap.entrySet()) {
                String str = databaseMap.get(entry.getKey()).jString();
                temp.append(str + ",\n");
            }
            temp.deleteCharAt(temp.length()-2);
            temp.append("]\n}");
            fileWriter.write(temp.toString());

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private CanParseCanPrint process(List<String> jsonLines, String type) {
        List<String> values = new ArrayList<>();
        for (String str : jsonLines) {
            String[] temp = str.split(": ");
            String val = temp[1];
            String value = val.replace("\"", "").replace(",", "");
            values.add(value);
        }
        String name = values.remove(0);
        CanParseCanPrint returnOBJ = null;
        if (type.contains("ingredients")) {
            double[] numbers = new double[6];
            for (int i = 0; i < values.size(); i++) {
                numbers[i] = parseDouble(values.get(i));
            }
            returnOBJ = new Ingredient(name, numbers);
        } else if (type.contains("day")) {
            String intensity = values.remove(0);
            String date = values.remove(0);
            int minutes = (int) parseDouble(values.remove(0));
            Date d = User.parseDate(date);
            Workout workout = new Workout(minutes, intensity, d);
            Meal meal = Meal.getDataBase().get(values.remove(0));
            User user = null;
            int day = Integer
                    .parseInt(type.split(":")[0].split("y")[1].replace("\"", "").replace(",", ""));
            History temp = new History();
            temp = temp.getHistory(day);
            if (temp == null) {
                ArrayList<User> userInfo = new ArrayList<>();
                ArrayList<Workout> workoutInfo = new ArrayList<>();
                ArrayList<Meal> mealInfo = new ArrayList<>();
                userInfo.add(user);
                workoutInfo.add(workout);
                mealInfo.add(meal);
                temp = new History(userInfo, workoutInfo, mealInfo, day);
            } else {
                temp.addMeal(meal);
                temp.addUser(user);
                temp.addWorkout(workout);
            }
            History.getDataBase().put("day"+day, temp);
            returnOBJ = temp;
        } else if (type.contains("meal")) {
            ArrayList<Recipe> recipes = new ArrayList<>();
            HashMap<String, Recipe> database = Recipe.getDataBase();
            double[] numbers = new double[5];
            for (String s : values) {
                Recipe r = database.get(s);
                recipes.add(r);
                numbers[0] += r.getCalories();
                numbers[1] += r.getFat();
                numbers[2] += r.getProtein();
                numbers[3] += r.getFiber();
                numbers[4] += r.getCarbs();
            }
            returnOBJ = new Meal(name, recipes, numbers);
        } else if (type.contains("recipe")) {
            int len = values.size();
            HashMap<Ingredient, Double> data = new HashMap<>();
            HashMap<String, Ingredient> database = Ingredient.getDataBase();
            Ingredient temp = null;
            String n = "";
            String instructions = values.remove(len - 1);
            double[] numbers = new double[5];
            for (int i = 0; i < len - 1; i++) {
                //calories,fat,protein,fiber,carbs
                if (i % 2 == 0) {
                    n = values.get(i);
                    temp = database.get(n);
                    numbers[0] = temp.getCalories();
                    numbers[1] = temp.getFat();
                    numbers[2] = temp.getProtein();
                    numbers[3] = temp.getFiber();
                    numbers[4] = temp.getCarbs();
                } else {
                    double amount = parseDouble(values.get(i));
                    for (int j = 0; j<5; j++) {
                        numbers[j] = numbers[j] * amount;
                        
                    }
                    data.put(temp, amount);
                    database.put(n, temp);
                }
            }
            returnOBJ = new Recipe(name, data, instructions, numbers);
        }
        return returnOBJ;
    }

    private static double parseDouble(String object) {
        if (!(object).isEmpty()) {
            return Double.parseDouble(object);
        }
        return 0.0;
    }

    public static void main(String[] args) {
        HashMap<String, CanParseCanPrint> database = new HashMap<>();
        String outputFile = "src/main/java/Data/TestWriting.json";
        String inputFile = "src/main/java/Data/ingredientsTest.json";
//        String outputFile = "src/main/java/Data/outputTest.json";
        JSONHandler ingredients = new JSONHandler(inputFile, outputFile, database);
        ingredients.parse(inputFile, database);
        inputFile = "src/main/java/Data/recipesTest.json";
        JSONHandler recipes = new JSONHandler(inputFile, outputFile, database);
        recipes.parse(inputFile, database);
        inputFile = "src/main/java/Data/mealsTest.json";
        JSONHandler meals = new JSONHandler(inputFile, outputFile, database);
        meals.parse(inputFile, database);
        inputFile = "src/main/java/Data/historyTest.json";
        JSONHandler histories = new JSONHandler(inputFile, outputFile, database);
        histories.parse(inputFile, database);
        database.forEach((key, value) -> System.out.println(key + " " + value));
//
        JSONHandler out = new JSONHandler(inputFile, outputFile, database);
        out.print(outputFile, database);



    }
}
