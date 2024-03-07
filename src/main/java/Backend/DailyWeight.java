package Backend;

// Memento for Daily Weight
class DailyWeight implements Command {
    private User user;
    private double previousWeight;
    private double newWeight;

    public DailyWeight(User user, double newWeight) {
        this.user = user;
        this.previousWeight = user.getWeight();
        this.newWeight = newWeight;
    }

    @Override
    public void executeAction() {
        user.setWeight(newWeight);
    }

    @Override
    public void undoAction() {
        user.setWeight(previousWeight);
    }
}
