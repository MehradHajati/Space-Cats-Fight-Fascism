package ModelManager;

/**
 * The type Jasper the cat.
 */
class JasperTheCat extends Cat {
    private static final String CAT_NAME = "Jasper";
    private static final String HOME_PLANET = "FishBowl";

    /**
     * Instantiates a new Jasper the cat.
     */
    public JasperTheCat() {
        super(CAT_NAME, HOME_PLANET);
    }

    public String catAbilityDescribe() {
        return "When you use the Fight Fascism action, you may -1 Fascist from an adjacent planet instead of your current planet";
    }

    public void useAbility() {
    }

}
