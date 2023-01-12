package ModelManager;

public class CardFactory {
    public static Card createCard(String cardType, int numEffects, String[] effects){
        Card card = null;
        switch(cardType){
            case "BONUS":
                card = new BonusCard(numEffects, effects);
                break;

            case "GALACTIC":
                card = new GalacticCard(numEffects, effects);
                break;

            case "RESIST":
                card = new ResistCard(numEffects, effects);
                break;

            default:
                throw new IllegalStateException("Invalid card type!");

        }
        return card;
    }

}
