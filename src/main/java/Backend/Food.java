package Backend;

public class Food {
    private String name;
    private double calories;
    private double fat;
    private double protein;
    private double fiber;
    private double carbs;
    public Food(String name, double calories, double fat, double protein, double fiber,double carbs){
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.fiber = fiber;
        this.carbs = carbs;
    }

    
    public Food(String name){
        this.name = name;
        this.calories = -1;
        this.fat = -1;
        this.protein = -1;
        this.fiber = -1;
        this.carbs = -1;
    }
    public String getName(){
        return name;
    }
    public double getCalories(){
        return calories;
    }
    public double getFat(){
        return fat;
    }
    public double getProtein(){
        return protein;
    }
    public double getFiber(){
        return fiber;
    }
    public double getCarbs(){
        return carbs;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCalories(double calories){
        this.calories = calories;
    }
    public void setFat(double fat){
        this.fat = fat;
    }
    public void setFiber(double fiber){
        this.fiber = fiber;
    }
    public void setCarbs(double carbs){
        this.carbs = carbs;
    }
    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", fat=" + fat +
                ", protein=" + protein +
                ", fiber=" + fiber +
                ", carbs=" + carbs +
                '}';
    }


}
