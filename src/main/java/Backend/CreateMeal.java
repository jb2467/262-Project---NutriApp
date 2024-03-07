package Backend;
import java.util.ArrayList;
import java.util.List;
public class CreateMeal implements Command {
    private String mealName;
    private List<Recipe> recipe;
    private History history;
    private Meal meal;

    public CreateMeal(String mealName, List<Recipe> recipe, History history){
        this.mealName = mealName;
        this.recipe = recipe;
        this.history = history;
        this.meal = null;
    }


    @Override
    public void executeAction() {
        meal = new Meal(mealName, (ArrayList<Recipe>) recipe);
        history.addMeal(meal);
    }

    @Override
    public void undoAction() {
        history.removeMeal(meal);
    }
}
