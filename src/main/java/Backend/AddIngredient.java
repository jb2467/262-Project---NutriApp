package Backend;

// Memento for AddIngredient
class AddIngredient implements Command {
    private Ingredient ingredient;
    private double amountToAdd;
    private double amountBeforeAction;

    public AddIngredient(Ingredient ingredient, double amountToAdd) {
        this.ingredient = ingredient;
        this.amountToAdd = amountToAdd;
        this.amountBeforeAction = ingredient.getAmtInStock();
    }

    @Override
    public void executeAction() {
        ingredient.setAmtInStock(amountBeforeAction + amountToAdd);
    }

    @Override
    public void undoAction() {
        ingredient.setAmtInStock(amountBeforeAction);
    }
}
