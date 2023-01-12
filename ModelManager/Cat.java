package ModelManager;

/**
 * The type Cat.
 */
public abstract class Cat{
    
    private final String CAT_NAME;
    private final String HOME_PLANET;

    /**
     * Instantiates a new Cat.
     *
     * @param CAT_NAME    the cat name
     * @param HOME_PLANET the home planet
     */
    public Cat(String CAT_NAME, String HOME_PLANET) {
        this.CAT_NAME = CAT_NAME;
        this.HOME_PLANET = HOME_PLANET;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){return CAT_NAME;}

    /**
     * Get home planet string.
     *
     * @return the string
     */
    public String getHomePlanet(){return HOME_PLANET;}

    /**
     * Cat ability describe string.
     *
     * @return the string
     */
    public abstract String catAbilityDescribe();

    /**
     * Use ability.
     */
    public abstract void useAbility();

}
