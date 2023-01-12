package ModelManager;

/**
 * The type Card.
 */
public abstract class Card{

    private int numEffects;
    private String[] effects;

    public Card(int numEffects, String[] effects){
        this.numEffects = numEffects;
        this.effects = effects;
    }

    /**
     * Get effects string [ ].
     *
     * @return the string [ ]
     */
//Getter Methods
    public String[] getEffects(){ return effects; }

    /**
     * Get num effects int.
     *
     * @return the int
     */
    public int getNumEffects(){ return numEffects; }
}
