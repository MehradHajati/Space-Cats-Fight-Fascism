package ModelManager;

/**
 * The type Alias the cat.
 */
class AliasTheCat extends Cat {
    private final static String CAT_NAME = "Alias:SC";
    private final static String HOME_PLANET = "WaterDish";

    /**
     * Instantiates a new Alias the cat.
     */
    public AliasTheCat() {
        super(CAT_NAME, HOME_PLANET);
    }

    public String catAbilityDescribe() {
        return "When you Fight Fascism, you may draw ONE Resist Card (unless your hand is full)";
    }

    public void useAbility() {
    }
}
