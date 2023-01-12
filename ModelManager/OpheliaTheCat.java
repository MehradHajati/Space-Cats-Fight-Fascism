package ModelManager;

/**
 * The type Ophelia the cat.
 */
class OpheliaTheCat extends Cat {
    private static final String CAT_NAME = "Ophelia";
    private static final String HOME_PLANET = "FrostNip";

    /**
     * Instantiates a new Ophelia the cat.
     */
    public OpheliaTheCat() {
        super(CAT_NAME, HOME_PLANET);
    }

    public String catAbilityDescribe() {
        return "Whenever you remove your third fascist in a single in tuurn, you may teleport yourself or any other cat is on the same planet as you.";
    }

    public void useAbility() {
    }

}
