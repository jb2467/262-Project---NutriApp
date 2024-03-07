package Backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import Backend.Persistence.UserDbManager;

public class User implements User_Interface{
    private String name;
    private double height;
    private double weight;
    private Date birthdate;
    private Goal goal;
    private Team team;
    private String user_name;
    private int passwordHash;
    //private Inbox inbox;

    //#region constructors
    //Create User
    public User(String name, double height, double weight, String birthdate, Goal goal, String user_name, String password){
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
        this.user_name = user_name;
        this.passwordHash = password.hashCode();
        this.birthdate = parseDate(birthdate);
    }

    //Login JSON USAGE
    //must exist before calling

    /* "username" : "username","birthdate" : "birthdate","height" : "height","weight" : "weight","goal" : "goal","targetWeight" : "goal.getTargetWeight()","averageCalorie" : "goal.getAverageCalorie()","name" : "name","passwordHash" : "passwordHash"; */
    public User(String user_name){
        UserDbManager manager = new UserDbManager();
        
        this.user_name = manager.getUsernameFromJSON(user_name);
        this.birthdate = parseDate(manager.getBirthdateFromJSON(user_name));
        this.height = Double.parseDouble(manager.getHeightFromJSON(user_name));
        this.weight = Double.parseDouble(manager.getWeightFromJSON(user_name));
        String goalString = manager.getGoalValFromJSON(user_name);
        String targetWeight = manager.getTargetWeightFromJSON(user_name);
        String averageCalorie = manager.getAverageCalorieFromJSON(user_name);
        this.goal = new Goal(Double.parseDouble(targetWeight), Integer.parseInt(averageCalorie), goalString);
        this.name = manager.getNameFromJSON(user_name);
        this.passwordHash = Integer.parseInt(manager.getPasswordHashFromJSON(user_name));
    }

    //Guest
    public User(){
        this.name = "NO NAME";
        this.height = -1.0;
        this.weight = -1.0;
        this.birthdate = parseDate("-1/-1/-1");
    }
    //#endregion

    //#region Team Stuff
    //Invite other members to your team by using their user_name.
    public void inviteToTeam(String user_name){

    }
    public void createTeam(String teamName){
        Team createdTeam = new Team(teamName, this);
    }
    public void leaveTeam(){
        team.removeMember(this);
        team = null;
    }
    public void joinTeam(Team team){
        this.team = team;
    }
    public String getTeam(){
        if(team == null){
            return "No Team";
        }
        return team.getTeamName();
    }
    //#endregion

    //#region Getters and setters
    public Goal getGoal() {
        return goal;
    }
    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    public void setUserName(String user_name){
        this.user_name = user_name;
    }
    public String getUserName(){
        return user_name;
    }
    public int getPasswordHash(){
        return passwordHash;
    }
    public void setPassword(String password){
        this.passwordHash = password.hashCode();
    }
    public static Date parseDate(String d){
        String monthStr = d.substring(0, d.indexOf("/"));
        String newStr = d.substring(d.indexOf("/")+1, d.length());

        String dayStr = newStr.substring(0, newStr.indexOf("/"));
        String newStr2 = newStr.substring(newStr.indexOf("/")+1, newStr.length());

        String yearStr = newStr2.substring(0, newStr2.length());

        int monthVal = Integer.parseInt(monthStr);
        int dayVal = Integer.parseInt(dayStr);
        int yearVal = Integer.parseInt(yearStr);
    
        Date parsedDate = new Date(dayVal, monthVal, yearVal);
        return parsedDate;
    }

    
    public String getName() {
        return name;
    }  
    public double getHeight() {
        return height;
    }
    public double getWeight() {
        return weight;
    }
    public Date getBirthDate() {
        return birthdate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setBirthDate(String date){

        Date newDate = parseDate(date);
        birthdate = newDate;
    }
    //#endregion

    //#region JSON Management
    private void addUserToJSON() {
        try {
                JSONObject userJSON = new JSONObject(this);

                //Reader
                BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
                String line = reader.readLine();

                //Checks if file is empty
                if(line == null || line.equals("")){
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
                    writer1.write("[]");
                    writer1.close();
                    
                    BufferedReader reader2 = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
                    String line2 = reader2.readLine();
                    line = line2;
                    reader2.close();
                    System.err.println("File successfully Overwritten!");
                }

                //Writes to the file
                String new_line = "";
                String final_line = "";

                int length = line.length();
                new_line = line.substring(0, length-1);

                if(length < 4){               //if its empty
                    final_line = new_line + userJSON.convertToUser()+"]" ;
                }else{ 
                    final_line = new_line +","+userJSON.convertToUser()+"]" ; 
                }
                reader.close();

                //Writer
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
                writer.write(final_line);
                System.out.println("File written successfully!");
                writer.close();
        } catch (Exception e) {
            System.out.println("Error Adding User\n Error: "+e);
        }
    }
    
    public String getStoredUserString(String username) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();
            int indexOfusername = line.indexOf(username);
            if (indexOfusername == -1) {
                System.err.println("username not in database");
            } else {
                int startIndex = line.lastIndexOf('{', indexOfusername);
                int endIndex = line.indexOf('}', indexOfusername);
                return line.substring(startIndex, endIndex + 1);
            }

        } catch (Exception e) {
            System.out.println("Error Loging In\n Error: "+e);
        }
        return null;
       
    }

    //#endregion
    @Override
    public boolean Authenticates(String user_name, String password) {
        if(user_name.length() >= 5 && password.indexOf('@') != -1){
            return true;
        }
        return false;
    }
    
}