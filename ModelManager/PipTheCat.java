package ModelManager;

/**
 * The type Pip the cat.
 */
class PipTheCat extends Cat {
    private static final String CAT_NAME = "Pip";
    private static final String HOME_PLANET = "HotRock";

    /**
     * Instantiates a new Pip the cat.
     */
    public PipTheCat() {
        super(CAT_NAME, HOME_PLANET);
    }

    public String catAbilityDescribe() {
        return "Whenever you Restock, you may also -1 Fascist";
    }

    public void useAbility() {
    }

}
