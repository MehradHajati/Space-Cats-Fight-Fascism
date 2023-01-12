package ViewManager;

import ModelManager.Planet;
import ModelManager.Player;

/**
 * The type Game board.
 */
public class GameBoard {

    private final ViewManager viewManager;

    /**
     * Instantiates a new Game board.
     */
    public GameBoard(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Get planets array planet [ ] [ ].
     *
     * @return the planet [ ] [ ]
     */
    public Planet[][] getPlanetsArray() {
        return viewManager.getPlanetArray();
    }

    /**
     * Show board.
     *
     * Prints the board information in a visually appealing way
     */
    public void showBoard() {
        Planet[][] planets = getPlanetsArray();
        Player[] players = viewManager.getPlayers();

        for (int i = 0; i < planets[0].length - 1; i++) {
            System.out.println();
            printDash();
            printCat(planets, players, i);
            printDivider();
            printAgents(planets, i);
            printDivider();
            printIdFlag(planets, i);
            printPlanet(planets, i);
            printSymbol(planets, i);
            printDivider();
            printTokens(planets, i);
            printDivider();
            printDash();
        }
    }

    private void printAgents(Planet[][] planets, int i) {
        int w = 38;
        for (int j = 0; j < planets[i].length; j++) {
            int agent = planets[i][j].getAgents();
                System.out.print(center(center(agent + " agents" , w-2, " "), w, "|"));
                System.out.print(" ");
        }
        System.out.println();
    }

    private String center(String str, int size, String padStr) throws IllegalArgumentException{
        if (str == null) return null;
        if (size < str.length()) return str;
        if (padStr == null || padStr.isEmpty()) throw new IllegalArgumentException("padStr cannot be empty or null.");

        double padlen = (size-str.length())/2.0;
        return padStr.repeat((int)Math.floor(padlen)) + str + padStr.repeat((int)Math.ceil(padlen));
    }

    private void printDash() {
        System.out.println("||----------------------------------|| ||----------------------------------||" +
                " ||----------------------------------|| ||----------------------------------||");
    }

    private void printTokens(Planet[][] planets, int i) {
        int w = 38;
        for (int j = 0; j < planets[i].length; j++) {
            int token = planets[i][j].getTokens();
            String tokenType = "";
            if (token < 0) {
                tokenType = " Fascism";
            } else if (token > 0) {
                tokenType = " Liberation";
            }
            System.out.print(center(center(Math.abs(token) + tokenType + " tokens" , w-2, " "), w, "|"));
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printSymbol(Planet[][] planets, int i) {
        int w = 38;
        for (int j = 0; j < planets[i].length; j++) {
            System.out.print(center(center(planets[i][j].getSymbol() , w-2, " "), w, "|"));
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printPlanet(Planet[][] planets, int i) {
        int w = 38;
        for (int j = 0; j < planets[i].length; j++) {
            System.out.print(center(center(planets[i][j].getName() , w-2, " "), w, "|"));
            System.out.print(" ");
        }
        System.out.println();

    }

    private void printIdFlag(Planet[][] planets, int i) {
        int w = 38;
        for (int j = 0; j < planets[i].length; j++) {
            System.out.print(center(center(Integer.toString(planets[i][j].getID()) , w-2, " "), w, "|"));
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printDivider() {
        System.out.println("|                                    | |                                    |" +
                " |                                    | |                                    |");
    }

    private void printCat(Planet[][] planets, Player[] players, int i) {
        int w = 38;
        StringBuilder playerOnPlanet = new StringBuilder();
        for (int j = 0; j < planets[i].length; j++) {
            for (Player player : players) {
                if (player.getCurrentPlanet().getID() == planets[i][j].getID()) {
                    playerOnPlanet.append(player.getCat().getName()).append(" ");
                }

            }
            System.out.print(center(center(playerOnPlanet.toString(), w-2, " "), w, "|"));
            System.out.print(" ");
            playerOnPlanet = new StringBuilder();
        }
        System.out.println();
    }
}
