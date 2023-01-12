package ModelManager;

import java.util.ArrayList;
import java.util.Random;

public class BonusDeck implements CardDeck{

    private final ModelManager modelManager;
    private final String[] RISE_OF_BUNNY = {"-2 fascism anywhere"};
    private final String[] WELCOMED_HEROES = {"+1 Liberation anywhere", "remove 1 scratch"};
    private final String[] TURMOIL = {"-1 fascist token anywhere", "1 cat may teleport"};
    private final String[] LICK_FUR = {"remove up to 2 scratches total form all cats"};
    private ArrayList<Card> deck;

    public BonusDeck(ModelManager model){
        modelManager = model;
        createDeck();
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
        int random_int = rand.nextInt();
        Card card = deck.get(deck.size()-1);
        deck.remove(random_int);
        return card;
    }

    /**
     * Create deck.
     *
     * create and add all the cards to the deck
     */
    public void createDeck(){
        deck = new ArrayList<Card>();

        deck.add(CardFactory.createCard("BONUS",RISE_OF_BUNNY.length, RISE_OF_BUNNY));
        deck.add(CardFactory.createCard("BONUS",WELCOMED_HEROES.length, WELCOMED_HEROES));

        deck.add(CardFactory.createCard("BONUS",TURMOIL.length, TURMOIL));
        deck.add(CardFactory.createCard("BONUS",LICK_FUR.length, LICK_FUR));
    }
}
