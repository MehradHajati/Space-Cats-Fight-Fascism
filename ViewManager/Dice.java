package ViewManager;

/*
 * Class for dice object display.
 * Contains the code to display the dice roll using the
 * data from the model that contains the roll value.
 */
public class Dice {
    private final ViewManager viewManager;

    /**
     * Instantiates a new dice object.
     */
    public Dice(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Gets the value of the dice roll from the
     * model through the viewManager and Controller.
     */
    public int getRolledFace() {
        return viewManager.getDiceRoll();
    }

    /**
     * Contains the code for the terminal to show the dice roll.
     */
    public void showDiceRoll() {
        System.out.println("You rolled a " + getRolledFace() + ".");
    }
}
