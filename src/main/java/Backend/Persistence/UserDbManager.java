package Backend.Persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import Backend.Date;
import Backend.User;

public class UserDbManager {
    public UserDbManager (){}

    public boolean passwordMatch(String username, String attemptedPassword) {
        String userJSONString = getStoredUserString(username);

        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("passwordHash") + 5 + 12;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        int passwordHash = Integer.parseInt(userJSONString.substring(startOfNext, endOfNext));
        
        if (attemptedPassword.hashCode() == passwordHash) {
            return true;
        } else {
            return false;
        }
    }

    public boolean alreadyExists(User user) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            if (line.indexOf(user.getUserName()) != -1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error Adding User\n Error: "+e);
        }
        return false;
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

    //#region getters
    public String getUsernameFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("username") + 5 + 8; // + 5 + length of word
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }

    public String getBirthdateFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("birthdate") + 5 + 9;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }

    public String getHeightFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("height") + 5 + 6;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
        
    }
    public String getWeightFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("weight") + 5 + 6;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }
    public String getGoalValFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("goal") + 4 + 6;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }
    public String getTargetWeightFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("targetWeight") + 5 + 12;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }
    public String getAverageCalorieFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("averageCalorie") + 5 + 14;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }
    public String getNameFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("name") + 5 + 4;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }
    public String getPasswordHashFromJSON(String user_name) {
        String userJSONString = getStoredUserString(user_name);
        int startOfNext = 0;
        int endOfNext = 0;

        startOfNext = userJSONString.indexOf("passwordHash") + 5 + 12;
        endOfNext = userJSONString.indexOf(",", startOfNext) - 1;
        return userJSONString.substring(startOfNext, endOfNext);
    }
    //#endregion

    //#region setters
    public void setUsernameJSON(String user_name, String newUsername) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);
            
            int startOfValue = line.indexOf("username", indexOfusername) + 5 + 8; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + newUsername + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }

    public void setBirthdateToJSON(String user_name, Date birthdate) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);

            int startOfValue = line.indexOf("birthdate", indexOfusername) + 5 + 9; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + birthdate.toString() + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }

    public void setHeightToJSON(String user_name, Double height) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);

            int startOfValue = line.indexOf("height", indexOfusername) + 5 + 6; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + height + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
        
    }
    public void setWeightToJSON(String user_name, Double weight) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);

            int startOfValue = line.indexOf("weight", indexOfusername) + 5 + 6; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + weight + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }
    public void setGoalValToJSON(String user_name, String newGoalVal) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);

            int startOfValue = line.indexOf("goal", indexOfusername) + 5 + 4; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + newGoalVal + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }
    public void setTargetWeightToJSON(String user_name, Double newTargetWeight) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);
            
            int startOfValue = line.indexOf("targetWeight", indexOfusername) + 5 + 12; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + newTargetWeight + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }
    public void setAverageCalorieToJSON(String user_name, int newAverageCalorie) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);
            
            int startOfValue = line.indexOf("averageCalorie", indexOfusername) + 5 + 14; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + newAverageCalorie + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }
    public void setNameToJSON(String user_name, String newName) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);
            
            int startOfValue = line.indexOf("name", indexOfusername) + 5 + 4; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + newName + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }
    public void setPasswordHashToJSON(String user_name, String newPassword) {
        BufferedReader reader;
        String lineBefore;
        String lineAfter;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            String line = reader.readLine();
            reader.close();

            int indexOfusername = line.indexOf(user_name);
            
            int startOfValue = line.indexOf("passwordHash", indexOfusername) + 5 + 4; // + 5 + length of word
            int endOfValue = line.indexOf(",", startOfValue) - 1;

            lineBefore = line.substring(0, startOfValue);
            lineAfter = line.substring(endOfValue);
            
            //Writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Backend/Persistence/UserPersistence.JSON"));
            writer.write(lineBefore + newPassword.hashCode() + lineAfter);
            System.out.println("File written successfully!");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Setting\n Error: "+e);
        }
    }
    //#endregion
}

