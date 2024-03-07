package Backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Team {
    private String team_name;
    private String team_owner;
    private final int max_members = 10;
    private ArrayList<User> team_members = new ArrayList<User>();
    private static int num = 0;

    public Team(String team_name, User user){
        this.team_name = team_name;
        this.team_owner = user.getName();
        team_members.add(user);
    }
    public String getTeamName(){
        return team_name;
    }
    public String getTeamOwner(){
        return team_owner;
    }
    public void addMember(User user){
        if(team_members.size() == max_members){
            AlertBox.display("Invalid Request", "Max members for team reached");
        }else{
            team_members.add(user);
            try {
                JSONObject team_JSON = new JSONObject(team_name, team_owner, team_members);

                //Reader
                BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/JSONTeam.JSON"));
                String line = reader.readLine();

                //Checks if file is empty
                if(line == null || line.equals("")){
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/JSONTeam.JSON"));
                    writer1.write("[]");
                    writer1.close();
                    
                    BufferedReader reader2 = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/JSONTeam.JSON"));
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
                
                if(num == 0){
                    final_line = new_line +""+team_JSON.convertToTeam()+"]" ;
                    num++;
                }else{ 
                    final_line = new_line +","+team_JSON.convertToTeam()+"]" ; 
                }
                reader.close();


                //Writer
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/JSONTeam.JSON"));
                writer.write(final_line);
                System.out.println("File written successfully!");
                writer.close();
            } catch (Exception e) {
                System.out.println("Error Adding member\n Error: "+e);
            }
        }
    }
    public void removeMember(User user){
        try{
            team_members.remove(user);
            removeMemberFromJSON(user);
        }catch(Exception e){
            System.err.print("ERROR: "+e);
        }
    }

    private void removeMemberFromJSON(User user) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/JSONTeam.JSON"));
            String line = reader.readLine();
            reader.close();

            int startOfUsername = line.indexOf(user.getUserName()) - 1;
            
            if (startOfUsername - line.lastIndexOf(",", startOfUsername) == 1) { //if theres a comma right before the username
                startOfUsername -= 1;
            } 

            int lengthOfUsername = user.getUserName().length();
            int endOfUsername = startOfUsername + lengthOfUsername;

            lineBefore = line.substring(0, startOfUsername);
            lineAfter = line.substring(endOfUsername);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/JSONTeam.JSON"));
            writer.write(lineBefore  + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }
    public String getMembers(){
        String members = "";
        String name;
        for(int i = 0; i < team_members.size(); i++){
            name = team_members.get(i).getName();
            members += name+",\n";
        }
        return members;
    }
}
