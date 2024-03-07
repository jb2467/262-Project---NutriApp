package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import Backend.User;

public class JSONObject {
    private String name;
    
    //Team
    private String owner;
    private ArrayList<String> members = new ArrayList<String>();

    //User
    private double height;
    private double weight;
    private String birthdate;
    private Goal goal;
    private String username;
    private String password;
    private int passwordHash;
    
    public JSONObject(String teamName,String teamOwner, ArrayList<User> Members){
        this.name = teamName;
        this.owner = teamOwner;
        for(int i = 0; i < Members.size()-1 ; i++){
            this.members.add(Members.get(i).getName());
        }
    }


    public JSONObject(User user) {
        this.name = user.getName();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.goal = user.getGoal();
        this.username = user.getUserName();
        this.passwordHash = user.getPasswordHash();
        this.birthdate = user.getBirthDate().toString();
    }

    public String convertToTeam(){
        String JSON_string;
        String members_string = "";
        for(int i = 0;i < members.size(); i++){
            String member_name = members.get(i);
            if(i == members.size()-1){
                members_string += "\""+member_name+"\"";
            }else{
                members_string += "\""+member_name+"\",";
            }
        }
        JSON_string = "{\"team_name\" : \""+name+"\",\"team_owner\" : \""+owner+"\",\"team_members\" : ["+members_string+"]}";

        return JSON_string;
    }
    public String convertToUser() {
        return "{\"username\" : \""+username
            +"\",\"birthdate\" : \""+birthdate
            +"\",\"height\" : \""+height
            +"\",\"weight\" : \""+weight
            +"\",\"goal\" : \""+goal
            +"\",\"targetWeight\" : \""+goal.getTargetWeight()
            +"\",\"averageCalorie\" : \""+goal.getAverageCalorie()
            +"\",\"name\" : \""+name
            +"\",\"passwordHash\" : \""+passwordHash+"\"}";
    }
}
