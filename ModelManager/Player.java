package ModelManager;

import java.util.ArrayList;

/**
 * The type Player.
 */
public class Player{

    private int id;
    private final ModelManager modelManager;
    private Cat cat;
    private ArrayList<Card> resistCards;
    private int scratch;
    private int [] currPlanet;
    private final int MAX_SCRATCH = 2;

    /**
     * Instantiates a new Player.
     *
     * @param id    the id
     * @param model the model
     */
    public Player(int id, ModelManager model) {
        this.id = id;
        this.modelManager = model;
        scratch = 0;
        resistCards = modelManager.drawThreeResistCards();
    }

    /**
     * Instantiates a new Player.
     *
     * @param model the model
     * @param cat   the cat
     */
    public Player(ModelManager model, Cat cat){
        this.modelManager = model;
        this.cat = cat;
        scratch = 0;
        resistCards = modelManager.drawThreeResistCards();
        String planet = cat.getHomePlanet();
        currPlanet = modelManager.getPlanetLocation(planet);
    }

    /**
     * Instantiates a new Player.
     *
     * @param model the model
     */
    public Player(ModelManager model){  //without cat, yet
        this.modelManager = model;
    }

    /**
     * Get scratches int.
     *
     * @return the int
     */
    public int getScratches(){ return scratch; }

    /**
     * Get current planet.
     *
     * @return the planet
     */
    public Planet getCurrentPlanet(){ return modelManager.getPlanet(currPlanet); }

    /**
     * Get current planet id int [ ].
     *
     * @return the int [ ]
     */
    public int[] getCurrentPlanetID(){ return currPlanet; }

    /**
     * Get resist cards array list.
     *
     * @return the array list
     */
    public ArrayList<Card> getResistCards(){ return resistCards; }

    /**
     * Gets cat.
     *
     * @return the cat
     */
    public Cat getCat() { return cat; }

    /**
     * Sets cat.
     *
     * @param cat the cat
     */
    public void setCat(Cat cat) {
        this.cat = cat;
        String planet = cat.getHomePlanet();
        currPlanet = modelManager.getPlanetLocation(planet);
    }

    /**
     * Set current planet.
     *
     * @param location the location
     */
//Setter Methods
    public void setCurrentPlanet(int[] location){ currPlanet = location; }

    /**
     * Set resist cards.
     *
     * @param cards the cards
     */
    public void setResistCards(ArrayList<Card> cards){ resistCards = cards; }

    /**
     * Discard cards.
     */
// Method to discard entire hand
    public void discardCards(){ resistCards = new ArrayList<Card>(); }

    /**
     * Scratch.
     */
    public void scratch(){
        if(scratch == MAX_SCRATCH){
            modelManager.increaseFascistScale();
        }
        else{
            scratch = scratch + 1;
        }
    }

    /**
     * Heal.
     */
    public void heal(){
        if(scratch > 0){
            scratch = scratch - 1;
        }
    }

    /**
     * Use cat ability.
     */
    public void useCatAbility(){}

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() { return id; }
}
