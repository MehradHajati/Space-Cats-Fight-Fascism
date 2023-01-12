package ModelManager;

/**
 * The type Planet.
 */
public class Planet{

    private ModelManager modelManager;
    private int id;
    private String name;
    private int[] location;
    private String symbol;
    private int tokens;
    private String flag;
    private int agent;

    /**
     * Instantiates a new Planet.
     *
     * @param model  the model
     * @param id     the id
     * @param name   the name
     * @param symbol the symbol
     */
    public Planet(ModelManager model, int id, String name, String symbol){
        this.modelManager = model;
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.tokens = 0;
        this.flag = null;
        this.location = new int[2];
    }

    /**
     * Get tokens int.
     *
     * @return the int
     */
    public int getTokens(){ return tokens; }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){ return name; }

    /**
     * Get symbol string.
     *
     * @return the string
     */
    public String getSymbol(){ return symbol; }

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getID(){ return id; }

    /**
     * Mast flag.
     * check the number of tokens and then mast the correct flag accordingly
     */
    public void mastFlag(){
        if(tokens > 3){
            flag = "Liberation";
        }
        else if(tokens < -3){
            flag = "Fascist";
        }
    }

    /**
     * Sets location.
     *
     * @param row the row
     * @param col the col
     */
    public void setLocation(int row, int col) {
        this.location[0] = row;
        this.location[1] = col;
    }

    /**
     * Increase liberation.
     */
    public void increaseLiberation(){ tokens = tokens + 1; mastFlag(); }

    /**
     * Increase fascism.
     */
    public void increaseFascism(){ tokens = tokens -1; mastFlag(); }

    public int getAgents() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public boolean hasAgent() {
        if (agent > 0) {
            return true;
        }
        return false;
    }
}
