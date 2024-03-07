package Backend;

public class Notification {
    private String message;
    private boolean isRequestAccepted; // true for accepted and false for declined

    public Notification(String message, boolean isRequestAccepted) {
        this.message = message;
        this.isRequestAccepted = isRequestAccepted;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRequestAccepted() {
        return isRequestAccepted;
    }
}
