package ViewManager;

import ModelManager.Card;
import ModelManager.Player;

public class CardViewer {

    private final ViewManager viewManager;

    /**
     * Instantiates a new card viewer.
     */
    public CardViewer(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Displays the resist cards held by the
     * player passed as a parameter.
     */
    public void showCard(Player player) {
        System.out.println("Your currently hold " + player.getResistCards().size() + " Resist Cards.");
        int i = 1;
        for (Card card : player.getResistCards()) {
            System.out.println(i + ". " + card.getEffects());
            i++;
        }
    }

    /**
     * Returns the player object associated
     * with the player ID entered as a parameter.
     */
    public Player getPlayer(int num) {
        return viewManager.getPlayer(num);
    }


}
