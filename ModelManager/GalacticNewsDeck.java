package ModelManager;

import java.util.Random;
import java.util.ArrayList;

/**
 * The type Galactic news deck.
 */

public class GalacticNewsDeck implements CardDeck{
    
    private ModelManager modelManager;
    private int CardsRemaining;
    private ArrayList<Card> deck;
    private static final int TOTAL_OF_GALACTIC_CARDS = 16;
    private static final int NUM_OF_FSS_CARDS = 10;

    private static GalacticNewsDeck instance = null;

    /**
     * Instantiates a new Galactic news deck.
     *
     * @param model the model
     */
    private GalacticNewsDeck(){
        CardsRemaining = TOTAL_OF_GALACTIC_CARDS;
        createDeck();
    }

    public static GalacticNewsDeck getInstance() {
        if (instance == null) {
            instance = new GalacticNewsDeck();
        }
        return instance;
    }

    /**
     * Pick one galactic card.
     *
     * randomly pick one card out of the deck
     *
     * @return the galactic card
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
     * Create deck.
     *
     * create and add all the cards to the deck
     */
    public void createDeck(){
        deck = new ArrayList<Card>();
        String[] fss = {"+1 Fascism Scale", "Take scratch"};
        String[] fpse = {"+1 Fascism Planet", "Every Cat Curr planet Take scratch"};
        String[] fsd = {"+1 Fascism Scale", "Discard entire hand"};
        String[] fsfpse = {"+1 Fascism Scale", "+1 Fascism Planet", "Every Cat Curr planet Take scratch"};
        String[] srh = {"Take scratch", "Return home"};

        for(int i = 0; i < NUM_OF_FSS_CARDS; i++){
            deck.add(CardFactory.createCard("GALACTIC",fss.length, fss));
        }
        deck.add(CardFactory.createCard("GALACTIC",fpse.length, fpse));
        deck.add(CardFactory.createCard("GALACTIC",fpse.length, fpse));

        deck.add(CardFactory.createCard("GALACTIC",fsd.length, fsd));
        deck.add(CardFactory.createCard("GALACTIC",fsd.length, fsd));

        deck.add(CardFactory.createCard("GALACTIC",fsfpse.length, fsfpse));

        deck.add(CardFactory.createCard("GALACTIC",srh.length, srh));
    }
}