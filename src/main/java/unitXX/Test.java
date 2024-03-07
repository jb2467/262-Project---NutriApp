package unitXX;
import Backend.*;

public class Test {
    public static void main(String[] arg){
        Goal goal = new Goal(0, 0, "Lose Weight");
        User user = new User("name", 1, 1, "1/1/1", goal, "Username", "password");
        User user2 = new User("name2", 1, 1, "2/2/2", goal, "Username1", "password1");
        User user3 = new User("name3", 1, 1, "2/2/2", goal, "Username1", "password1");
        User user4 = new User("name4", 1, 1, "2/2/2", goal, "Username1", "password1");
        User user5 = new User("name5", 1, 1, "2/2/2", goal, "Username1", "password1");
        User user6 = new User("name6", 1, 1, "2/2/2", goal, "Username1", "password1");
        User user7 = new User("name7", 1, 1, "2/2/2", goal, "Username1", "password1");

        Team team = new Team("GODFATHER", user);
        team.addMember(user2);
        team.addMember(user3);
        team.addMember(user4);
        team.addMember(user5);
        team.addMember(user6);
        team.addMember(user7);
        System.out.println("it worked!");
    }
}
