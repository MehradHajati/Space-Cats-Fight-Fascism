package Controller;

/*** IMPORTS ***/

import ModelManager.*;
import ViewManager.ViewManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Controller class --
 * Controls communication between the
 * ViewManager and the ModelManager.
 */
public class Controller{

    /** The ViewManager */
    private final ViewManager viewManager;
    /** The ModelManager */
    private final ModelManager modelManager;
    private static Controller instance = null;

    /**
     * Controller Constructor:
     *
     * Instantiates a new Controller.
     * Instantiates a new Model
     * Gets the instance of ModelManager
     */
    private Controller(){
        this.viewManager = ViewManager.getInstance(this);
        this.modelManager = ModelManager.getInstance(this);
    }

    /**
     * Gets the single instance of the ModelManager.
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Main.
     *
     * The loop that determines if the game is in progress, won, or lost.
     *
     * It initializes a game and then goes on until either the game is won or lost
     *
     * @param args the args
     */
    public static void main(String args[]){
        Controller controller = getInstance();
        controller.initializeGame();

        boolean run = true;

        while (run) {
            int gameStatus = controller.getGameStatus();
            // Game Over - Won
            if (gameStatus == 1) {
                controller.showWon();
                run = false;
            }
            // Game Over - Lost
            else if (gameStatus == 2) {
                controller.showLost();
                run = false;
            }
            // gameStatus == 0 In Progress
            else {
                controller.takeTurn();
            }

        }
    }

    /**
     * Initialize game:
     *
     * Initializes the necessary components for a game.
     * Initializes the cats to choose from
     * Initializes a planet grid
     * Sets the Fascism Level
     * Places starting Fascism Tokens on planets
     *
     * shows the players the menu/welcome
     * players can choose a difficulty level
     * sets the number of players and their choice of cat
     *
     * shows the players to gameBoard/Planet grid
     * shows the players the Fascism Level
     *
     * sets the GameStatus to 0 (In Progress)
     * sets the Current Player to Player 1 (0 in the array of players)
     */
    public void initializeGame(){
        showMenu();
        chooseDifficulty();
        setCats();
        setUpPlanetGrid();
        initMission();
        setNumPlayers(getNumPlayers());
        setPlayerCat();
        setGameBoard();
        showBoard();
        showFascismLevel();
        setGameStatus(0);
        setPlayerTurn(0);
    }

    private void initMission() {
        modelManager.setMission();
    }

    /**
     * Shows the Fascism Level to the Players
     *
     * calls the viewManager to show the Fascism Level
     */
    private void showFascismLevel() {
        viewManager.showFascismScale();
    }

    /**
     * Shows the GameBoard to the Players
     *
     * calls the viewManager to show the GameBoard
     */
    private void showBoard() {
        viewManager.showBoard();
    }

    /**
     * Sets the GameBoard
     *
     * initializes the starting Fascism tokens
     * sets the planet grid
     */
    private void setGameBoard() {
        modelManager.initializeStartingFascistTokens();
        viewManager.setGameBoard();
    }

    /**
     * Sets the players cat to their chosen cat
     * increases the fascism level on that planet
     */
    private void setPlayerCat() {
        for (Player player: getPlayers()) {
            System.out.println("PLAYER " + (player.getId() + 1));
            modelManager.setPlayersCat(player, viewManager.getPlayerCat());
            player.getCurrentPlanet().increaseFascism();
        }
    }

    /**
     * Gets the Number of Players playing
     *
     */
    private int getNumPlayers() {
        return viewManager.getNumPlayers();
    }

    /**
     * Sets the Number of Players playing
     */
    private void setNumPlayers(int numPlayers) {
        modelManager.setNumPlayers(numPlayers);
    }

    /**
     * Sets the Planet Grid
     */
    private void setUpPlanetGrid() {
        modelManager.setUpPlanetGrid();
    }

    /**
     * Creates the cats available to play
     */
    private void setCats() {
        modelManager.setCats();
    }

    /**
     * Shows the welcome menu
     */
    private void showMenu() {
        viewManager.showMenu();
    }

    /**
     * Asks the players for their desired difficulty level
     * Sets it in the ModelManager
     */
    private void chooseDifficulty() {
        int diff = viewManager.chooseDifficulty();
        modelManager.setDifficulty(diff);
    }

    /**
     * Sets the Turn to the Current player
     */
    private void setPlayerTurn(int player) {
        modelManager.setPlayerTurn(player);
    }

    /**
     * Get game status.
     *
     * @return the int of the gameStatus
     */
    public int getGameStatus(){ return modelManager.getGameStatus(); }

    /**
     * Set game status.
     *
     * @param num the int of the gameStatus
     */
    public void setGameStatus(int num){
        modelManager.setGameStatus(num);
    }

    /**
     * Get planet array planet [] [].
     *
     * @return the planet [] []
     */
    public Planet[][] getPlanetArray(){ return modelManager.getPlanets(); }

    /**
     * Gets the fascism scale value.
     *
     * @return the fascism scale value
     */
    public int getFascismScaleValue() { return modelManager.getFascismScaleValue(); }

    /**
     * Increase fascist scale.
     */
    public void increaseFascistScale(){ modelManager.increaseFascistScale(); }

    /**
     * Draws a galactic news card.
     */
    public void drawGalacticNews(){ modelManager.drawGalacticNews(); }

    /**
     * Checks if name is a Planet
     *
     * @param name the name
     * @return the boolean
     */
    public boolean isPlanet(String name){ return modelManager.isPlanet(name); }

    /**
     * Get current player int.
     *
     * @return the int
     */
    public int getCurrPlayer(){ return modelManager.getCurrPlayer(); }

    /**
     * Update planet tokens.
     */
    public void updatePlanetTokens(){ viewManager.updatePlanetTokens(); }

    /**
     * Update planet flags.
     */
    public void updatePlanetFlags(){ viewManager.updatePlanetFlags(); }

    /**
     * Update cat locations.
     */
    public void updateCatLocations(){ viewManager.updateCatLocations(); }

    /**
     * Invalid move.
     */
    public void invalidMove(){ viewManager.invalidMove(); }

    /**
     * Show lost.
     */
    public void showLost(){ viewManager.showLost(); }

    /**
     * Show won.
     */
    public void showWon(){ viewManager.showWon(); }

    /**
     * Asks the player what planet
     * the want to teleport to.
     *
     * @return the string
     */
    public String askTeleportPlanet() { return viewManager.askTeleportPlanet(); }

    /**
     * Asks the Player to choose a Resist Card
     *
     * @param cards the cards in their hand
     * @return the int picked card
     */
    public int askResistCards(ArrayList<Card> cards){ return viewManager.askForResistCard(cards); }

    /**
     * Gets dice roll.
     *
     * @return the dice roll
     */
    public int getDiceRoll() {
        Random random = new Random();
        return random.nextInt(6)+1; // generate a number between 0-5 inclusive then add 1
    }

    /**
     * 
     * @return Number of agents the player would like to take with them
     */
    public int askNumAgentsToTake(int maxAgents){ return viewManager.askMovingWithAgents(maxAgents); }

    /**
     * 
     * @return whether the player wants to take agents with them
     */
    public boolean askTakeAgent(){ return viewManager.askTakeAgent(); }

    /**
     * Gets a player's hand of resist cards.
     *
     * @param player the player
     * @return the resist cards
     */
    public ArrayList<Card> getResistCards(Player player) {
        return player.getResistCards();
    }

    /**
     * Gets player.
     *
     * @param num the number
     * @return the player
     */
    public Player getPlayer(int num) {
        return modelManager.getPlayers()[num];
    }

    /**
     * Shows the player his hand of resist cards
     *
     */
    private void showPlayerHand() {
        viewManager.printResistCards(getResistCards(getPlayer(modelManager.getCurrPlayer())));
    }

    /**
     * Take turn.
     * A player takes their turn
     * It shows them their hand of resist cards
     *
     * it will cycle to the next player when done
     */
    public void takeTurn() {
        showMeowssion();
        showPlayerHand();
        modelManager.takeTurn();
        if (modelManager.getCurrPlayer() + 1 > modelManager.getPlayers().length - 1) {
            setPlayerTurn(0);
        } else {
            setPlayerTurn(modelManager.getCurrPlayer() + 1);
        }
    }

    private void showMeowssion() {
        viewManager.showMission(getMeowssion());
    }

    private Mission getMeowssion() {
        return modelManager.getCurrentMeowssion();
    }

    /**
     * Gets cats.
     *
     *
     * @return the arraylist of cats available
     */
    public ArrayList<Cat> getCats() {
        return modelManager.getCats();
    }

    /**
     * Get players player [ ].
     * The player [ ] stores the players playing a game.
     *
     * @return the player [ ]
     */
    public Player[] getPlayers() {
        return modelManager.getPlayers();
    }

    /**
     * Ask action asks a player to choose an action.
     *
     * @return the string choice of action
     */
    public int askAction(){ return viewManager.askAction(); }

    /**
     * Update board.
     * Updates the board to the players
     */
    public void updateBoard() {
        viewManager.showBoard();
    }

    /**
     * Inform dice roll.
     * Informs the player of the rolled dice
     *
     * @param num the num
     */
    public void informDiceRoll(int num){ viewManager.informDiceRoll(num); }

    /**
     * Inform galactic card.
     * Informs the player of the drawn galactic news card
     *
     * @param card the card
     */
    public void informGalacticCard(Card card){ viewManager.informGalacticCard(card); }

    public void informBonusCard(Card card) {
        viewManager.informBonusCard();
        for (String effect : card.getEffects()) {
            viewManager.showBonus(effect);
        }
    }

    public boolean askIfTeleport() {
        int response = viewManager.askIfTeleport();
        if (response == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Player askForPlayerToTeleport() {
        return getPlayer(viewManager.askForPlayer());
    }

    public Player askPlayerToRemoveScratch() {
        return getPlayer(viewManager.askForPlayer());
    }

    public Planet askPlanetToLiberate() {
        return getPlanet(viewManager.askPlanetLiberate());
    }

    private Planet getPlanet(int planetID) {
        return modelManager.getPlanetByID(planetID);
    }

    public Planet askPlanetToRemoveFascism() {
        return getPlanet(viewManager.askPlanetFascism());
    }
}
