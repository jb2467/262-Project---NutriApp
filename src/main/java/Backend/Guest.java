package Backend;

public class Guest{

    private String name;
    private static int guestValue = 1;

    public Guest(){
        name = "Guest"+Integer.toString(guestValue);
        guestValue++;
    }

    public String getGuestName(){
        return name;
    }
}
