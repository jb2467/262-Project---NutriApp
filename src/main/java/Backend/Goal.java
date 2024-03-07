package Backend;


public class Goal implements Goal_Interface{
    private int target_calorie = -1;
    private double target_weight = -1;
    private Workout workout;
    private GoalEnum goalVal;
    private int averageCalorieIntake;
    private int deficit = 500;
    private String fitness = "Easy";

    public Goal(double target_weight, int averageCalorieIntake, String goal){
        this.target_weight = target_weight;
        this.averageCalorieIntake = averageCalorieIntake;
        if(goal.equals("Lose Weight")){
            this.goalVal = GoalEnum.LOSEWEIGHT;
            target_calorie = averageCalorieIntake-deficit;
        }else if(goal.equals("Gain Weight")){
            this.goalVal = GoalEnum.GAINWEIGHT;
            target_calorie = averageCalorieIntake+deficit;
        }else if(goal.equals("Maintain Weight")){
            this.goalVal = GoalEnum.MAINTAINWEIGHT;
            target_calorie = averageCalorieIntake;
        }
    }

    public void setGoal(String goal){
        if(goal == "Gain Weight"){
            goalVal = GoalEnum.GAINWEIGHT;
            gainWeight();
        }else if(goal == "Lose Weight"){
            goalVal = GoalEnum.LOSEWEIGHT;
            loseWeight();
        }else if(goal == "Maintain Weight"){
            goalVal = GoalEnum.MAINTAINWEIGHT;
            maintainWeight();
        }
    }
    public String getGoal(){
        if(goalVal == GoalEnum.GAINWEIGHT){
            return "Gain Weight";
        }else if(goalVal == GoalEnum.LOSEWEIGHT){
            return "Lose Weight";
        }
        return "Maintain Weight";
    }

    public double getTargetWeight(){
        return target_weight;
    }
    public void setTargetWeight(double target_weight){
        this.target_weight = target_weight;
    }

    public int getAverageCalorie(){
        return averageCalorieIntake;
    }
    public int getDailyTargetCalorie(){
        if(goalVal == GoalEnum.GAINWEIGHT){
            target_calorie = averageCalorieIntake+deficit;
            return target_calorie;
        }else if(goalVal == GoalEnum.LOSEWEIGHT){
            target_calorie = averageCalorieIntake-deficit;
            return target_calorie;
        }
        target_calorie = averageCalorieIntake;
        return target_calorie;
    }

    public void setAverageCalorie(int a){
        averageCalorieIntake = a;
    }

    public void setFitness(String i){
        if(i.equals("Hard")){
            fitness = "Hard";
        }else if(i.equals("Easy")){
            fitness = "Easy";
        }else if(i.equals("Medium")){
            fitness = "Medium";
        }
    }
    public String getFitness(){
        return fitness;
    }

    @Override
    public void loseWeight() {
        goalVal = GoalEnum.LOSEWEIGHT;
        target_calorie = averageCalorieIntake - deficit;
    }

    @Override
    public void maintainWeight() {
        goalVal = GoalEnum.MAINTAINWEIGHT;
        target_calorie = averageCalorieIntake;
    }

    @Override
    public void gainWeight() {
        goalVal = GoalEnum.GAINWEIGHT;
        target_calorie = averageCalorieIntake + deficit;
    }

    @Override
    public String toString() {
        return "Goal [target_weight=" + target_weight + ", goalVal=" + goalVal + ", averageCalorieIntake="
                + averageCalorieIntake + "]";
    }
/** {HAVE TO WORK ON THIS}
    private void adjustGoal(){
       
        if(goalVal == GoalEnum.MAINTAINWEIGHT){
            if((target_weight - currentWeight) >= 5){
                goalVal = GoalEnum.GAINWEIGHT;
            }else if((currentWeight - target_weight) >= 5){
                goalVal = GoalEnum.LOSEWEIGHT;
            }
        }
        else if(goalVal == GoalEnum.GAINWEIGHT){
            if((currentWeight - target_weight) >= 5){
                goalVal = GoalEnum.LOSEWEIGHT;
            }else if(Math.abs(currentWeight - target_weight) < 5){
                goalVal = GoalEnum.MAINTAINWEIGHT;
            }
        }else if(goalVal == GoalEnum.LOSEWEIGHT){
            if((target_weight - currentWeight) >= 5){
                goalVal = GoalEnum.GAINWEIGHT;
            }else if(Math.abs(currentWeight - target_weight) < 5){
                goalVal = GoalEnum.MAINTAINWEIGHT;
            }
        }
    }
    **/
}
