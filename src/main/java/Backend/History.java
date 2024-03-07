package Backend;

import java.util.ArrayList;
import java.util.HashMap;

import Backend.Adapter.CanParseCanPrint;

public class History implements CanParseCanPrint {

    private ArrayList<User> userInfo;
    private ArrayList<Workout> workoutInfo;
    private ArrayList<Meal> mealInfo;
    private int day;
    static HashMap<String, History> database = new HashMap<>();

    // Constructor
    public History() {
        this.userInfo = new ArrayList<>();
        this.workoutInfo = new ArrayList<Workout>();
        this.mealInfo = new ArrayList<>();
        this.day = 0;
    }

    public History(ArrayList<User> userInfo, ArrayList<Workout> workoutInfo, ArrayList<Meal> mealInfo, int day) {
        this.userInfo = userInfo;
        this.workoutInfo = workoutInfo;
        this.mealInfo = mealInfo;
        this.day = day;
        database.put("day"+day, this);
    }

    // Getters and Setters
    public ArrayList<User> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(ArrayList<User> userInfo) {
        this.userInfo = userInfo;
    }

    public ArrayList<Workout> getWorkoutInfo() {
        return workoutInfo;
    }

    public void setWorkoutInfo(ArrayList<Workout> workoutInfo) {
        this.workoutInfo = workoutInfo;
    }

    public ArrayList<Meal> getMealInfo() {
        return mealInfo;
    }

    public void setMealInfo(ArrayList<Meal> mealInfo) {
        this.mealInfo = mealInfo;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void addUser(User user) {
        this.userInfo.add(user);
    }

    public void addWorkout(Workout workout) {
        this.workoutInfo.add(workout);
    }

    public void addMeal(Meal meal) {
        this.mealInfo.add(meal);
    }

    public History getHistory(int day) {
        return database.getOrDefault(day, null);
    }

    public void removeMeal(Meal meal) {
        this.mealInfo.remove(meal);
    }
    public static HashMap<String,History> getDataBase(){
        return database;
        
    }

    @Override
    public String getName() {
        return "Day: " + day + "'s history ";
    }

    @Override
    public String jString() {
        StringBuilder temp = new StringBuilder();
        temp.append("{\n\"day" + day + '"' + ": [");
        for (int index = 0; index < mealInfo.size(); index++) {
            temp.append("\n{\n");
            temp.append("\"user\": " + userInfo.get(index) +",\n" );
            Workout w = workoutInfo.get(index);
            temp.append("\"intensity\": " + '"'  + w.getIntensity() + '"'   +",\n") ;
            temp.append("\"date\": " + '"' + w.getDate()+ '"'  +",\n");
            temp.append("\"minutes\": " + '"' + w.getMinutes()+'"' +",\n");
            temp.append("\"meal\": " + '"'+ mealInfo.get(index).getName() +'"' + "\n}");
            if (index != mealInfo.size()-1) {
                temp.append(',');
            }
        }
        temp.append("\n]\n}");
        return temp.toString();

    }

    @Override
    public String xmlString() {
        StringBuilder temp = new StringBuilder();
        for (int index = 0; index < mealInfo.size(); index++) {
            temp.append("<entry>");
            temp.append("<user>: " + userInfo.get(index) +"</user>\n" );
            Workout w = workoutInfo.get(index);
            temp.append("<intensity>" + w.getIntensity() + "</intensity>\n") ;
            temp.append("<date>" + w.getDate()+ "</date>\n");
            temp.append("<minutes>"+ w.getMinutes() +"</minutes>\n");
            temp.append("<meal>" + mealInfo.get(index).getName() + "</meal>\n");
            temp.append("</entry");
        }
        temp.append("</day" + day + ">");
        return temp.toString();
    }

    @Override
    public String csvString() {
        StringBuilder temp = new StringBuilder();
        for (int index = 0; index < mealInfo.size(); index++) {
            temp.append("day" + day + ",");
            temp.append(userInfo.get(index)+"," );
            Workout w = workoutInfo.get(index);
            temp.append(w.getIntensity() + ",") ;
            temp.append(w.getDate()+ ",");
            temp.append(w.getMinutes() +",");
            temp.append(mealInfo.get(index).getName() + ",");
            temp.append("\n");
        }
        temp.deleteCharAt(temp.length()-1);
        return temp.toString();
    }

}
