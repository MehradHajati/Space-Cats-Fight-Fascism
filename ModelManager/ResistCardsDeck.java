package ModelManager;

import Controller.Controller;

import java.util.Random;
import java.util.ArrayList;

/**
 * The type Resist cards deck.
 */

public class ResistCardsDeck implements CardDeck{

    private ModelManager modelManager;
    private int CardsRemaining;
    private ArrayList<Card> deck;
    private final int TOTAL_RESIST_CARDS = 46;

    private final int TOTAL_1_LIBERATION_CARD = 8;
    private final int TOTAL_HEAL_1_CARD = 6;
    private final int TOTAL_HEAL_2_CARD = 4;
    private final int TOTAL_FIGHT_2_FASCISTS_CARD = 5;
    private final int TOTAL_TELEPORT_CARD = 3;
    private final int TOTAL_EACH_SYMBOL_CARD = 5;
    private final int NUM_EFFECT_OF_RESIST_CARD = 1;

    private static ResistCardsDeck instance = null;

    /**
     * Instantiates a new Resist cards deck.
     *
     * @param model the model
     */
    private ResistCardsDeck(){
        CardsRemaining = TOTAL_RESIST_CARDS; 
        createDeck();
    }

    public static ResistCardsDeck getInstance() {
        if (instance == null) {
            instance = new ResistCardsDeck();
        }
        return instance;
    }

    /**
     * Pick one resist card.
     *
     * randomly pick one card out of the deck
     *
     * @return the resist card
     */
    public Card pickOne(){
        Random rand = new Random();
        int random_int = rand.nextInt(CardsRemaining);
        Card card = deck.get(random_int);
        deck.remove(random_int);
        CardsRemaining--;
        return card;
    }

    /**
     * Pick three array list.
     *
     * randomly pick three cards out of the deck
     *
     * @return the array list
     */
    public ArrayList<Card> pickThree(){
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 3; i++) {
            hand.add(pickOne());
        }
        return hand;
    }

    /**
     * Create deck.
     *
     * create and add all the cards to the deck
     */
    public void createDeck(){
        deck = new ArrayList<Card>();

        String[] l = {"+1 liberation"};
        String[] h1 = {"+1 heal"};
        String[] h2 = {"+2 heal"};
        String[] f = {"-2 fascist"};
        String[] t = {"teleport"};
        String[] sEars = {"Ears"};
        String[] sTail = {"Tail"};
        String[] sWhisker = {"Whiskers"};
        String[] sPaw = {"Paw"};

        for(int i = 0; i < TOTAL_1_LIBERATION_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, l));
        }
        for(int i = 0; i < TOTAL_HEAL_1_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, h1));
        }
        for(int i = 0; i < TOTAL_HEAL_2_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, h2));
        }
        for(int i = 0; i < TOTAL_FIGHT_2_FASCISTS_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, f));
        }
        for(int i = 0; i < TOTAL_TELEPORT_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, t));
        }
        for(int i = 0; i < TOTAL_EACH_SYMBOL_CARD; i++){

            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, sEars));
        }
        for(int i = 0; i < TOTAL_EACH_SYMBOL_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, sTail));
        }
        for(int i = 0; i < TOTAL_EACH_SYMBOL_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, sWhisker));
        }
        for(int i = 0; i < TOTAL_EACH_SYMBOL_CARD; i++){
            deck.add(CardFactory.createCard("RESIST",NUM_EFFECT_OF_RESIST_CARD, sPaw));
        }
    }
}