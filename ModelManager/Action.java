package ModelManager;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Action.
 */
public class Action {

    private final String MOVE_DOWN = "MoveDown";
    private final String MOVE_UP = "MoveUp";
    private final String MOVE_LEFT = "MoveLeft";
    private final String MOVE_RIGHT = "MoveRight";
    private final String FIGHT_FASCISM = "FightFascism";
    private final String PLAY_CARD = "PlayCard";
    private final String RESTOCK = "Restock";
    private int fascismFought = 0;

    private final ModelManager modelManager;

    private int liberationToken = 0;
    private int move = 0;
    private int cardPlayed = 0;
    private int restock = 0;
    private int healWithAgent = 0;
    private final ArrayList<String> actions = new ArrayList<>();

    /**
     * Instantiates a new Action.
     *
     * @param model the model
     */
    public Action(ModelManager model) {
        modelManager = model;
        actions.add(MOVE_UP);
        actions.add(MOVE_DOWN);
        actions.add(MOVE_LEFT);
        actions.add(MOVE_RIGHT);
        actions.add(FIGHT_FASCISM);
        actions.add(RESTOCK);
        actions.add(PLAY_CARD);
    }

    /**
     * Take turn.
     * Processes a player's desired actions
     *
     * @param player the player
     */
    public void takeTurn(Player player) {
        fascismFought = 0;
        int moveCounter = 3;
        while (moveCounter> 0) {
            String notifyMoves = "You have " + moveCounter;
            notifyMoves += (moveCounter == 1) ? " move left" : " moves left";
            System.out.println(notifyMoves);
            int input = modelManager.askAction();
            if (processInput(player, input)){
                checkAgentScore();
                modelManager.updateBoard();
                moveCounter--;
            } else {
                modelManager.invalidMove();
            }
        }
        modelManager.calculateNumDice(fascismFought);
        resetTrackers();
    }

    private void resetTrackers() {
        liberationToken = 0;
        move = 0;
        restock = 0;
        cardPlayed = 0;
    }

    public void checkAgentScore() {
        String mission = modelManager.getCurrentMeowssion().getDescription();
        switch (mission) {
            case "Add at least 3 liberation tokens to 1 Planet in a single turn.":
                checkThreeLiberationTokens();
            case "Play at least 2 Resist Cards on the same planet with a Fascist token.":
                checkPlayTwoResistCardsOnSamePlanet();
            case "Remove at least 2 scratches from a cat in a single turn.":
                checkTwoScratchesRemoved();
            case "Restock on a planet with at least 1 Fascist token.":
                checkRestockWithFascistToken();
        }
    }

    private void checkRestockWithFascistToken() {
        if (restock > 0) {
            modelManager.getCurrentMeowssion().agentScored();
        }
    }

    private void checkTwoScratchesRemoved() {
        if (healWithAgent >= 2) {
            modelManager.getCurrentMeowssion().agentScored();
        }
    }

    private void checkPlayTwoResistCardsOnSamePlanet() {
        if (cardPlayed > 2) {
            modelManager.getCurrentMeowssion().agentScored();
        }
    }

    private void checkThreeLiberationTokens() {
        if (liberationToken >= 3) {
            modelManager.getCurrentMeowssion().agentScored();
        }
    }


    private boolean processInput(Player player, int input) {
        for (String action : actions) {
            if (actions.indexOf(action) == (input - 1)) {
                return actionProcessor(player, action);
            }
        }
        return false;
    }

    private boolean actionProcessor(Player player, String action) {
        switch (action) {
            case MOVE_UP: return checkMoveUp(player);
            case MOVE_DOWN: return checkMoveDown(player);
            case MOVE_LEFT: return checkMoveLeft(player);
            case MOVE_RIGHT: return checkMoveRight(player);
            case FIGHT_FASCISM: return checkFightFascism(player);
            case RESTOCK: return checkRestock(player);
            case PLAY_CARD: return checkPlayCard(player);
            default: return false;
        }
    }

    private boolean checkFightFascism(Player player) {
        Planet currPlanet = player.getCurrentPlanet();
        if (currPlanet.getTokens() < 0) {
            currPlanet.increaseLiberation();
            fascismFought++;
            return true;
        } else {
            return false;
        }
    }


    private boolean checkPlayCard(Player player) {
        ArrayList<Card> cards = player.getResistCards();
        if (cards.size() == 0) { return false; } // cannot play card if player has none
        int num = modelManager.askResistCards(cards) - 1;
        Card cardToPlay = cards.get(num);
        boolean check = modelManager.playResistCard(cardToPlay);
        if (check) {
            cards.remove(num);
            player.setResistCards(cards);
            if (isWithAgent()) {
                if (player.getCurrentPlanet().getTokens() < 0) {
                    addPlayCard();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void addPlayCard() {
        cardPlayed++;
    }
    private void addRestock() {
        restock++;
    }
    private void addMove() {
        move++;
    }

    private boolean checkRestock(Player player) {
        ArrayList<Card> cards = player.getResistCards();
        if (cards.size() == 3) {
            return false;
        } else {
            while(cards.size() != 3){
                cards.add(modelManager.drawResistCard());
            }
            player.setResistCards(cards);
            if (isWithAgent()) {
                if (player.getCurrentPlanet().getTokens() < 0) {
                    addRestock();
                }
            }
            return true;
        }
    }


    private void makeMove(Player player, int[] location, Planet currPlanet) {
        Planet destinationPlanet = modelManager.getPlanet(location);

        // here we are checking if the planet has any agents on it that the player can take with them
        int agents = currPlanet.getAgents();
        boolean takeAgent = modelManager.askTakeAgent();

        if(takeAgent && agents == 1){
            currPlanet.setAgent(0);
            destinationPlanet.setAgent(destinationPlanet.getAgents() + 1);
        } else if (takeAgent && agents > 1){
            int numAgentsToTake = modelManager.askNumAgentsToTake(agents);
            currPlanet.setAgent(agents - numAgentsToTake);
            destinationPlanet.setAgent(destinationPlanet.getAgents() + numAgentsToTake);
        }

        player.setCurrentPlanet(location);
        addMove();
    }

    private boolean checkMoveRight(Player player) {
        int[] location = player.getCurrentPlanetID();
        Planet currPlanet = player.getCurrentPlanet();
        if (location[1] == 3) {return false;}
        location[1]++;
        makeMove(player, location, currPlanet);
        return true;
    }

    private boolean checkMoveLeft(Player player) {
        int[] location = player.getCurrentPlanetID();
        Planet currPlanet = player.getCurrentPlanet();
        if (location[1] == 0) {return false;}
        location[1]--;
        makeMove(player, location, currPlanet);
        return true;
    }

    private boolean checkMoveUp(Player player) {
        int[] location = player.getCurrentPlanetID();
        Planet currPlanet = player.getCurrentPlanet();
        if (location[0] == 0) { return false; }
        location[0]--;
        makeMove(player, location, currPlanet);
        return true;
    }

    private boolean checkMoveDown(Player player) {
        int[] location = player.getCurrentPlanetID();
        Planet currPlanet = player.getCurrentPlanet();
        if (location[0] == 2) { return false;}
        location[0]++;
        makeMove(player, location, currPlanet);
        return true;
    }

    public void teleport() {
        boolean teleport = modelManager.askTeleport();
        if (teleport) {
            Player p = modelManager.askPlayerToTeleport();
            int[] destination = modelManager.askTeleportPlanet();
            p.setCurrentPlanet(destination);
        }
    }

    public void removeScratch() {
        Player player = modelManager.askPlayerToRemoveScratch();
        player.heal();
        if (isWithAgent()) {
            healWithAgent++;
        }
    }

    private boolean isWithAgent() {
       return modelManager.getPlayers()[modelManager.getCurrPlayer()].getCurrentPlanet().hasAgent();
    }

    public void addLiberation() {
        Planet planet = modelManager.askPlanetToLiberate();
        if (checkValidPlanet("liberate", planet)) {
            planet.increaseLiberation();
        } else {
            addLiberation();
        }
    }

    private boolean checkValidPlanet(String type, Planet planet) {
        if (type.equals("liberate")) {
            return (planet.getTokens() >= 0);
        } else if (type.equals("fascism")) {
            return (planet.getTokens() < 0);
        }
        return false;
    }

    public void removeFascism() {
        Planet planet = modelManager.askPlanetToRemoveFascism();
        if (checkValidPlanet("fascism", planet)) {
            planet.increaseLiberation();
        } else {
            removeFascism();
        }
    }
}
