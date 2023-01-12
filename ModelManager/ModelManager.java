package ModelManager;

import Controller.Controller;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Model manager.
 */
public class ModelManager {

    public static final String LIBERATION = "+1 liberation";
    public static final String HEAL = "+1 heal";
    public static final String DOUBLE_HEAL = "+2 heal";
    public static final String FASCIST = "-2 fascist";
    public static final String TELEPORT = "teleport";
    public static final String TAIL = "Tail";
    public static final String WHISKERS = "Whiskers";
    public static final String PAW = "Paw";
    public static final String EARS = "Ears";

    private final int numRows = 3;
    private final int numCols = 4;
    private int numPlayers;
    private int currPlayer = 0;
    private Player[] players;
    private Planet[][] planets;
    private int fascismScaleValue = 1;
    private ArrayList<Cat> cats = new ArrayList<>();
    private int gameStatus;
    private GalacticNewsDeck galacticNewsDeck;
    private ResistCardsDeck resistCardsDeck;
    private BonusDeck bonusCardDeck;
    private Action action;
    private int difficultyLevel;
    
    private int numMissionsCompleted = 0;
    private Mission mission;

    private final Controller controller;
    private static ModelManager instance = null;

    //Constructor
    private ModelManager(Controller controller){
        this.controller = controller;

        galacticNewsDeck = GalacticNewsDeck.getInstance();
        resistCardsDeck = ResistCardsDeck.getInstance();

        action = new Action(this);
        mission = new Mission(this);
    }

    /**
     * Gets the single instance of the ModelManager.
     */
    public static ModelManager getInstance(Controller controller) {
        if (instance == null) {
            instance = new ModelManager(controller);
        }
        return instance;
    }

    /**
     * Get fascism scale value int.
     *
     * @return the int
     */
    public int getFascismScaleValue(){ return fascismScaleValue; }

    /**
     * Get players player [ ].
     *
     * @return the player [ ]
     */
    public Player[] getPlayers() { return players; }

    /**
     * Gets game status.
     *
     * @return the game status
     */
    public int getGameStatus() { return gameStatus; }

    /**
     * Gets cats.
     *
     * @return the cats
     */
    public ArrayList<Cat> getCats() { return cats; }

    /**
     * Get curr player int.
     *
     * @return the int
     */
    public int getCurrPlayer(){ return currPlayer; }

    /**
     * Get planets planet [ ] [ ].
     *
     * @return the planet [ ] [ ]
     */
    public Planet[][] getPlanets() {
        return planets;
    }

    /**
     * This method will take in a name and then find the planet location in the array and then return an array of size two
     * The first number is the row number and the second is the column
     */
    public int[] getPlanetLocation(String planetName) {
        int[] planetLocation = {2, 2};
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(planets[i][j].getName().equals(planetName)){
                    planetLocation[0] = i;
                    planetLocation[1] = j;
                }
            }
        }
        return planetLocation;
    }

    /**
     * Gets planet.
     *
     * take a location within the array and return the corresponding planet
     *
     * @param location the location
     * @return the planet
     */
    public Planet getPlanet(int[] location) {
        return planets[location[0]][location[1]];
    }

    /**
     * Get planet by id planet.
     *
     * @param num the num
     * @return the planet
     */
    public Planet getPlanetByID(int num){
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                Planet p = planets[i][j];
                if(p.getID() == num){
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Sets cats.
     */
    public void setCats() {
        Cat jasper = new JasperTheCat();
        Cat ophelia = new OpheliaTheCat();
        Cat pip = new PipTheCat();
        Cat aliasSC = new AliasTheCat();
        cats.add(jasper);
        cats.add(ophelia);
        cats.add(pip);
        cats.add(aliasSC);
    }

    /**
     * Increase fascist scale.
     */
    public void increaseFascistScale(){
        fascismScaleValue++;
        checkFascismScale();
    }

    /**
     * Checks if the fascism scale has reached the LOSE level
     */
    private void checkFascismScale() {
        if (fascismScaleValue >= 13) {
            setGameStatus(2);
        }
    }

    /**
     * Creates the Planets array and fills it with planets
     */
    public void setUpPlanetGrid(){
        // creating the planets array
        planets = new Planet[numRows][numCols];
        // creating each planet and then randomly assigning them into the array
        Planet scratchPost = new Planet(this, 1, "ScratchPost", EARS);
        randomlyAssign(scratchPost);
        Planet dustBunny = new Planet(this, 5, "DustBunny", EARS);
        randomlyAssign(dustBunny);
        Planet hairBall = new Planet(this, 9, "HairBall", EARS);
        randomlyAssign(hairBall);
        Planet frostNip = new Planet(this, 4, "FrostNip", TAIL);
        randomlyAssign(frostNip);
        Planet coldBath = new Planet(this, 10, "ColdBath", EARS);
        randomlyAssign(coldBath);
        Planet litterBox = new Planet(this, 2, "LitterBox", WHISKERS);
        randomlyAssign(litterBox);
        Planet spaceVet = new Planet(this, 12, "Space-Vet", TAIL);
        randomlyAssign(spaceVet);
        Planet fishBowl = new Planet(this, 6, "FishBowl", WHISKERS);
        randomlyAssign(fishBowl);
        Planet hotRock = new Planet(this, 8, "HotRock", TAIL);
        randomlyAssign(hotRock);
        Planet scratchStone = new Planet(this, 7, "ScratchStone", PAW);
        randomlyAssign(scratchStone);
        Planet waterDish = new Planet(this, 3, "WaterDish", PAW);
        randomlyAssign(waterDish);
        Planet  laserLight = new Planet(this, 11, "LaserLight", PAW);
        randomlyAssign(laserLight);
    }


    /**
     * Randomly Assign a planet in the planets array
     * @param planet the planet to be assigned
    */
    public void randomlyAssign(Planet planet){
        Random rn = new Random();
        int row = rn.nextInt(numRows);
        int col = rn.nextInt(numCols);
        while(planets[row][col] != null){
            row = rn.nextInt(numRows);
            col = rn.nextInt(numCols);
        }
        planets[row][col] = planet;
        planet.setLocation(row, col);
    }

    /**
     * Set num players.
     *
     * saves the number of players and then creates an array to save the players themselves
     *
     * @param num the num
     */
    public void setNumPlayers(int num){
        this.numPlayers = num;
        players = new Player[numPlayers];
        for (int i = 0; i < num; i++) {
            players[i] = new Player(i, this);
        }
    }

    /**
     * Play resist card boolean.
     *
     * @param card the card
     * @return the boolean
     * @throws IllegalStateException the illegal state exception
     */
    public boolean playResistCard(Card card) throws IllegalStateException{
        String effect = card.getEffects()[0];
        Player p = players[currPlayer];
        Planet currPlanet = p.getCurrentPlanet();
        switch (effect) {
            case LIBERATION:
                if (currPlanet.getTokens() > -1){
                    currPlanet.increaseLiberation();
                    return true;
                } else {
                    return false;
                }

            case HEAL:
                p.heal();
                return true;

            case DOUBLE_HEAL:
                p.heal();
                p.heal();
                return true;

            case FASCIST:
                if (currPlanet.getTokens() < 0){
                    currPlanet.increaseLiberation();
                } else {
                    return false;
                }

                if (currPlanet.getTokens() < 0){
                    currPlanet.increaseLiberation();
                }

                return true;

            case TELEPORT:
                int[] location = getPlanetLocation(controller.askTeleportPlanet());
                p.setCurrentPlanet(location);
                return true;

            case EARS:
                if (currPlanet.getSymbol().equals(EARS)){
                    currPlanet.increaseLiberation();
                    currPlanet.increaseLiberation();
                    return true;
                } else {
                    return false;
                }

            case TAIL:
                if (currPlanet.getSymbol().equals(TAIL)){
                    currPlanet.increaseLiberation();
                    currPlanet.increaseLiberation();
                    return true;
                } else {
                    return false;
                }

            case WHISKERS:
                if (currPlanet.getSymbol().equals(WHISKERS)){
                    currPlanet.increaseLiberation();
                    currPlanet.increaseLiberation();
                    return true;
                } else {
                    return false;
                }

            case PAW:
                if (currPlanet.getSymbol().equals(PAW)){
                    currPlanet.increaseLiberation();
                    currPlanet.increaseLiberation();
                    return true;
                } else {
                    return false;
                }

            default:
                throw new IllegalStateException("Received undefined resist card effect.");
        }
    }

    /**
     * Draw galactic news card.
     */
    public void drawGalacticNews(){
        Card card = galacticNewsDeck.pickOne();
        controller.informGalacticCard(card);
        int num = card.getNumEffects();
        String[] effects = card.getEffects();
        for(int i = 0; i < num; i++){
            processGalacticCardEffect(effects[i]);
        }
    }

    /**
     * Process galactic card effect.
     *
     * Method to process the effect of Galactic News Cards
     *
     * @param effect the effect
     * @throws IllegalStateException the illegal state exception
     */
    public void processGalacticCardEffect(String effect) throws IllegalStateException{
        Player p = players[currPlayer];

        switch (effect) {
            case "+1 Fascism Scale":
                increaseFascistScale();
                return;

            case "+1 Fascism Planet":
                Planet currPlanet = p.getCurrentPlanet();
                currPlanet.increaseFascism();
                return;

            case "Take scratch":
                p.scratch();
                return;

            case "Discard entire hand":
                p.discardCards();
                return;

            case "Return home":
                Cat c = p.getCat();
                String homePlanet = c.getHomePlanet();
                int[] location = getPlanetLocation(homePlanet);
                p.setCurrentPlanet(location);
                return;

            case "Every Cat Curr planet Take scratch":
                String targetPlanet = p.getCurrentPlanet().getName();
                String planetName;
                for(int i = 0; i < numPlayers; i++){
                    p = players[i];
                    planetName = p.getCurrentPlanet().getName();
                    if(targetPlanet.equals(planetName)){
                        p.scratch();
                    }
                }
                return;

            default:
                throw new IllegalStateException("Received undefined galactic news card effect.");
        }
    }


    public void drawBonus(){
        Card bonusCard = bonusCardDeck.pickOne();
        controller.informBonusCard(bonusCard);
        int num = bonusCard.getNumEffects();
        String[] effects = bonusCard.getEffects();
        for(int i = 0; i < num; i++){
            processBonusCardEffect(effects[i]);
        }
    }

    /**
     * Process galactic card effect.
     *
     * Method to process the effect of Galactic News Cards
     *
     * @param effect the effect
     * @throws IllegalStateException the illegal state exception
     */
    public void processBonusCardEffect(String effect) throws IllegalStateException {
        Player p = players[currPlayer];

        switch (effect) {
            case "-2 fascism anywhere":
                removeFascism();
                removeFascism();
                return;

            case "+1 Liberation anywhere":
                addLiberation();
                return;

            case "remove 1 scratch":
                removeScratch();
                return;

            case "-1 fascist token anywhere":
                removeFascism();
                return;

            case "1 cat may teleport":
                teleport();
                return;

            case "remove up to 2 scratches total form all cat":
                removeScratch();
                removeScratch();
                return;

            default:
                throw new IllegalStateException("Received bonus card effect.");
        }
    }

    private void teleport() {
        action.teleport();
    }

    private void removeScratch() {
        action.removeScratch();
    }

    private void addLiberation() {
        action.addLiberation();
    }

    private void removeFascism() {
        action.removeFascism();
    }


    /**
     * Sets players cat.
     *
     * @param player    the player
     * @param playerCat the player cat
     */
    public void setPlayersCat(Player player, int playerCat) {
        player.setCat(cats.get(playerCat));
        removeCat(playerCat);
    }

    private void removeCat(int playerCat) {
        cats.remove(playerCat);
    }

    /**
     * Sets game status.
     *
     * @param num the num
     */
    public void setGameStatus(int num) {
        this.gameStatus = num;
    }

    /**
     * Sets player turn.
     *
     * @param player the player
     */
    public void setPlayerTurn(int player) {
        this.currPlayer = player;
    }

    /**
     * Invalid move.
     */
    public void invalidMove(){ controller.invalidMove(); }

    /**
     * Is planet boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean isPlanet(String name){
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(planets[i][j].getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Take turn.
     */
    public void takeTurn(){
        Player p = players[currPlayer];
        action.takeTurn(p);
        controller.updateBoard();
    }

    /**
     * Ask action string.
     *
     * @return the string
     */
    public int askAction(){ return controller.askAction(); }

    /**
     * Print resist cards int.
     *
     * @param cards the cards
     * @return the int
     */
    public int askResistCards(ArrayList<Card> cards){ return controller.askResistCards(cards); }

    /**
     * Draw resist card.
     *
     * @return the resist card
     */
    public Card drawResistCard(){ return resistCardsDeck.pickOne(); }

    /**
     * Sets fascism scale.
     *
     * @param fascismScale the fascism scale
     */
    public void setFascismScale(int fascismScale) {
        this.fascismScaleValue = fascismScale;
    }

    /**
     * Sets difficulty.
     *
     * @param chooseDifficulty the chosen difficulty
     */
    public void setDifficulty(int chooseDifficulty) {
        this.difficultyLevel = chooseDifficulty;
        setFascismScale(chooseDifficulty);
    }

    /**
     * Draw three resist cards array list.
     *
     * @return the array list
     */
    public ArrayList<Card> drawThreeResistCards() {
        return resistCardsDeck.pickThree();
    }

    /**
     * Update board.
     */
    public void updateBoard() {
        controller.updateBoard();
    }


    /**
     * Calculate num dice.
     *
     * @param fascismfought the fascismfought
     */
    public void calculateNumDice(int fascismfought){
        int fascismScale = getFascismScaleValue();
        int numDice;
        if(fascismScale > 6){
            numDice = 3;
        }
        else{
            numDice = 2;
        }
        numDice = numDice - fascismfought;
        if(numDice < 1){
            numDice = 1;
        }
        rollDice(numDice);
    }


    /**
     * Roll dice.
     *
     * @param numDice the num dice
     */
    public void rollDice(int numDice){
        int galacticdraws = 1;
        Random rn = new Random();
        while(numDice > 0){
            int dice = rn.nextInt(12) + 1;
            controller.informDiceRoll(dice);
            Planet p = getPlanetByID(dice);
            p.increaseFascism();
            if(dice > 8 && galacticdraws > 0){
                drawGalacticNews();
                galacticdraws--;
            }
            numDice--;
        }
    }

    /**
     * Initialize starting fascist tokens.
     */
    public void initializeStartingFascistTokens() {
        for (Planet[] planetCol: planets) {
            for (Planet planet: planetCol) {
                if (planet.getID() % 2 == 0) {
                    planet.increaseFascism();
                }
            }
        }
    }

    public Mission getCurrentMeowssion() {
        return mission;
    }

    public void setMission() {
        
        updatePlanets();
    }

    private void updatePlanets() {
        int i = mission.getAgents();
        for (int j = i; j < i+3; j++) {
            for (Planet[] planetCol : planets) {
                for (Planet planet : planetCol) {
                    if (planet.getID() == j) {
                        planet.setAgent(1);
                    }
                }
            }
        }
    }

    public int askNumAgentsToTake(int maxAgents){ return controller.askNumAgentsToTake(maxAgents); }
    public boolean askTakeAgent(){ return controller.askTakeAgent(); }

    public void missionCompleted(){ numMissionsCompleted++; }

    public boolean askTeleport() {
        return controller.askIfTeleport();
    }

    public Player askPlayerToTeleport() {
        return controller.askForPlayerToTeleport();
    }

    public int[] askTeleportPlanet() {
        return getPlanetLocation(controller.askTeleportPlanet());
    }

    public Player askPlayerToRemoveScratch() {
        return controller.askPlayerToRemoveScratch();
    }

    public Planet askPlanetToLiberate() {
        return controller.askPlanetToLiberate();
    }

    public Planet askPlanetToRemoveFascism() {
        return controller.askPlanetToRemoveFascism();
    }
}
