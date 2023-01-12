package ViewManager;

/**
 * The type Fascism scale.
 */
public class FascismScale {

    private static FascismScale instance = null;
    private final ViewManager viewManager;

    /**
     * Instantiates a new fascism scale object.
     */
    public FascismScale(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Gets the fascism scale object.
     */
    public static FascismScale getFascismScale(ViewManager viewManager) {
        if (instance == null) {
          instance = new FascismScale(viewManager);
        }
        return instance;
    }

    /**
     * Gets value of the fascism scale.
     */
    public int getValue() {
        return viewManager.getFascismScaleValue();
    }

    /**
     * Contains the code for the terminal to show the fascism scale.
     */
    public void showFascismScale() {
        System.out.println("Fascism Scale is at level: " + getValue());
    }
}
