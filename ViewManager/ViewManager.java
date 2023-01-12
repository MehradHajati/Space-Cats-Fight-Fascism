package ViewManager;

import Controller.Controller;
import ModelManager.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * The type View manager.
 */
public class ViewManager {

    private final Controller controller;
    private GameBoard gameBoard;
    private FascismScale fascismScale;
    private static ViewManager instance = null;

    /**
     * Instantiates a new View manager.
     *
     * @param controller the controller
     */
    private ViewManager(Controller controller) {
        this.controller = controller;
        this.fascismScale = FascismScale.getFascismScale(this);
    }

    /**
     * Gets the single instance of the ModelManager.
     */
    public static ViewManager getInstance(Controller controller) {
        if (instance == null) {
            instance = new ViewManager(controller);
        }
        return instance;
    }

    /**
     * Sets game board.
     */
    public void setGameBoard() {
        this.gameBoard = new GameBoard(this);
    }

    /**
     * Invalid move.
     */
    public void invalidMove(){ System.out.println("You cannot make that move, please choose another move"); }

    /**
     * Show lost.
     */
    public void showLost(){ System.out.println("You Lost. Better luck next time!"); }

    /**
     * Show won.
     */
    public void showWon(){ System.out.println("You Won! You conquered fascism!"); }

    /**
     * Gets fascism scale value.
     *
     * @return the fascism scale value
     * @returns the fascism scale value
     */
    public int getFascismScaleValue() {
        return controller.getFascismScaleValue();
    }


    /**
     * Update planet tokens.
     */
    public void updatePlanetTokens() {
        // This method needs to get the number of tokens on each planet from the planets and then change what the user sees
    }

    /**
     * Update planet flags.
     */
    public void updatePlanetFlags() {
        // this method needs to get the flag status from each planet and change what the user sees
    }

    /**
     * Update cat locations.
     */
    public void updateCatLocations() {
        // this method needs to get the cat location and then change what the user sees
    }


    /**
     * Get planet array planet [ ] [ ].
     *
     * @return the planet [ ] [ ]
     */
    public Planet[ ] [ ] getPlanetArray() {
        return controller.getPlanetArray();
    }

    /**
     * Gets dice roll.
     *
     * @return the dice roll
     */
    public int getDiceRoll() {
        return controller.getDiceRoll();
    }

    /**
     * Gets player.
     *
     * @param num the num
     * @return the player
     */
    public Player getPlayer(int num) {
        return controller.getPlayer(num);
    }

    /**
     * Show board.
     */
    public void showBoard() {
        gameBoard.showBoard();
    }

    /**
     * Show fascism scale.
     */
    public void showFascismScale() {
        fascismScale.showFascismScale();
    }

    /**
     * Gets planet.
     *
     * @return the planet
     */
    public Planet getPlanet() {
        return null;
    }


    /**
     * Get num players int.
     *
     * @return the int
     */
    public int getNumPlayers(){
        Scanner sc = new Scanner(System.in);
        int num = 0;
        while(num<1 || num>5){
            System.out.println("Please enter the number of players who want to play:");
            try {
                num = sc.nextInt();
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                num = 0;
            }
        }
        return num;
    }

    /**
     * Show menu.
     */
    public void showMenu() {
        System.out.println("________Welcome to Space Cats Fight Fascism!________");
    }

    /**
     * Gets player cat.
     *
     * @return the player cat
     */
    public int getPlayerCat() {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        ArrayList<Cat> cats = this.getCats();
        int size = cats.size();
        while(num <1 || num > size){
            System.out.println("Please enter the number of the cat which you want to play");
            for (Cat cat : cats) {
                System.out.println(cats.indexOf(cat) + 1 + ". " + cat.getName() + "  Home Planet: " + cat.getHomePlanet());
            }
            try {
                num = sc.nextInt();
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                num = 0;
            }
        }
        return num-1;
    }

    /**
     * Ask teleport planet string.
     *
     * @return the string
     */
    public String askTeleportPlanet(){
        Scanner sc = new Scanner(System.in);
        String targetPlanet = "";
        while(!controller.isPlanet(targetPlanet)){
            System.out.println("Please enter the name of a planet");
            targetPlanet = sc.next();
        }
        return targetPlanet;
    }

    private ArrayList<Cat> getCats() {
        return controller.getCats();
    }

    /**
     * Ask action string.
     *
     * @return the string
     */
    public int askAction(){
        int input = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Its your turn player " + (controller.getCurrPlayer()+1) + " (" + controller.getPlayer(controller.getCurrPlayer()).getCat().getName() + ")");
        System.out.print("Please choose an action: ");
        System.out.println("1: Move Up 2: Move Down 3: Move Left  4: Move Right 5: Fight Fascism 6: Restock 7: Play Resist Card");
        input = sc.nextInt();
        while (input < 1 || input > 7){ // input must be valid card number
            try {
                System.out.println("Please enter the number of the card that you would like to play");
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.close();
                System.out.println("Please try again.");
                sc = new Scanner(System.in);
                input = -1;
            }
        }
        return input;
    }

    public int askMovingWithAgents(int planetAgentsCount) {
        int input = -1;
        Scanner sc = new Scanner(System.in);
        System.out.print("How many agents would you like to move with you?");
        if(input >= 0 && input <= planetAgentsCount){
            return input;
        }
        else{
            System.out.println("Please try again.");
            return askMovingWithAgents(planetAgentsCount);
        }
    }
    /**
     * Print resist cards.
     *
     * @param cards the cards
     */
    public void printResistCards(ArrayList<Card> cards){
        int counter = 1;
        System.out.println("You currently hold these Resist Cards:");
        for (Card card: cards) {
            System.out.println("#" + counter + ": " + card.getEffects()[0]);
            counter++;
        }
    }

    /**
     * Ask for resist card int.
     *
     * @param cards the cards
     * @return the int
     */
    public int askForResistCard(ArrayList<Card> cards) {
        printResistCards(cards);
        Scanner sc = new Scanner(System.in);
        int input = -1;
        while(input < 1 || input > cards.size()){ // input must be valid card number
            try {
                System.out.println("Please enter the number of the card that you would like to play");
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.close();
                sc = new Scanner(System.in);
                input = -1;
            }
        }
        return input;
    }

    /**
     * Get players player [ ].
     *
     * @return the player [ ]
     */
    public Player[] getPlayers() {
        return controller.getPlayers();
    }

    /**
     * Choose difficulty int.
     *
     * @return the int
     */
    public int chooseDifficulty() {
        Scanner sc = new Scanner(System.in);
        int difficulty = -1;
        while(difficulty > 5 || difficulty < 1){
            try {
                System.out.println("Please choose a difficulty level: ");
                System.out.println("1. Playful Kitten");
                difficulty = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.close();
                sc = new Scanner(System.in);
                difficulty = -1;
            }
        }
        return difficulty;
    }

    /**
     * Inform dice roll.
     *
     * @param num the num
     */
    public void informDiceRoll(int num){
        System.out.println("You rolled a " + num + ".");
    }

    /**
     * Inform galactic card.
     *
     * @param card the card
     */
    public void informGalacticCard(Card card){
        System.out.println("A Galactic Card has been drawn with the following effects:");
        int numEffect = card.getNumEffects();
        String[] effects = card.getEffects();
        for(int i = 0; i < numEffect; i++){
            System.out.println(effects[i]);
        }
    }

    public void showMission(Mission mission) {
        System.out.println("Your current Meowssion is: " + mission.getDescription());
    }

    /**
     *
     * @param maxAgents the max number of agents that the player can take with them
     * @return the number of agents that the player would like to take with them
     */
    public int askNumAgentsToTake(int maxAgents){
        Scanner sc = new Scanner(System.in);
        int num;
        do {
            try {
                System.out.println("Out of " + maxAgents + " agents, how many would you like to take with you?");
                num = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.close();
                sc = new Scanner(System.in);
                num = -1;
            }
        } while (num>maxAgents || num == -1);
        return num;
    }

    /**
     *
     * @return whether the player wants to take agents with them
     */
    public boolean askTakeAgent(){
        Scanner sc = new Scanner(System.in);
        String answer;
        do {
            System.out.println("Would you like to take any agents with you? answer y for yes and n for no");
            answer = sc.next();
        } while (!answer.equals("y") && !answer.equals("n"));

        return answer.equals("y");
    }

    public void showBonus(String effect) {
        System.out.println(effect);
    }

    public int askIfTeleport() {
        Scanner scanner = new Scanner(System.in);
        int answer = -1;
        while (answer < 1 || answer > 2) {
            try {
                System.out.println("Would you like to teleport?");
                System.out.println("1: Yes  2: No");
                answer = scanner.nextInt();
            }catch (InputMismatchException e) {
                scanner.close();
                scanner = new Scanner(System.in);
                answer = -1;
            }
        }
        return answer;
    }

    public int askForPlayer() {
        Scanner scanner = new Scanner(System.in);
        int answer = -1;
        while (answer < 1 || answer > controller.getPlayers().length) {
            try {
                System.out.println("Which player would you like?");
                for (Player p : controller.getPlayers()) {
                    System.out.println(p.getId() + " " + p.getCat().getName());
                }
                answer = scanner.nextInt();
            }catch (InputMismatchException e) {
                scanner.close();
                scanner = new Scanner(System.in);
                answer = -1;
            }
        }
        return answer;
    }

    public int askPlanetLiberate() {
        Scanner scanner = new Scanner(System.in);
        int answer = -1;
        while (answer < 1 || answer > 12) {
            try {
                System.out.println("Which planet would you like to liberate?");
                answer = scanner.nextInt();
            }catch (InputMismatchException e) {
                scanner.close();
                scanner = new Scanner(System.in);
                answer = -1;
            }
        }
        return answer;
    }

    public int askPlanetFascism() {
        Scanner scanner = new Scanner(System.in);
        int answer = -1;
        while (answer < 1 || answer > 12) {
            try {
                System.out.println("Which planet would you like to remove fascism?");
                answer = scanner.nextInt();
            }catch (InputMismatchException e) {
                scanner.close();
                scanner = new Scanner(System.in);
                answer = -1;
            }
        }
        return answer;
    }

    public void informBonusCard() {
        System.out.println("You've earned a Bonus Card!");
    }
}
