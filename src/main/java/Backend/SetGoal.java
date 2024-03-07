package Backend;

// Memento for SetGoal
class SetGoal implements Command {
    private User user;
    private String previousGoal;
    private String newGoal;

    private Goal goal;

    public SetGoal(User user, String newGoal) {
        this.user = user;
        this.previousGoal =user.getGoal().getGoal();
        this.newGoal = newGoal;
        this.goal = user.getGoal();
    }

    @Override
    public void executeAction() {
        goal.setGoal(newGoal);
        user.setGoal(goal);
    }

    @Override
    public void undoAction() {
        goal.setGoal(previousGoal);
        user.setGoal(goal);
    }

}
