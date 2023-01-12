package ModelManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mission {


    private static final String FREE_THE_DOGS = "Add at least 3 liberation tokens to 1 Planet in a single turn.";
    private static final String BUILD_NETWORK = "Play at least 2 Resist Cards on the same planet with a Fascist token.";
    private static final String STEAL_MEDICINE = "Remove at least 2 scratches from a cat in a single turn.";
    private static final String RECRUIT_NEW_ALLIES = "Restock on a planet with at least 1 Fascist token.";
    private final HashMap<String, Integer> missions;
    private static final ArrayList<String> missionList = new ArrayList<>();
    private String description;
    private final ModelManager modelManager;
    private int score = 0;
    private int currMission;

    public Mission(ModelManager manager) {
        modelManager = manager;
        addMission();
        this.missions = new HashMap<>();
        missions.put(FREE_THE_DOGS, 10);
        missions.put(BUILD_NETWORK, 7);
        missions.put(STEAL_MEDICINE, 1);
        missions.put(RECRUIT_NEW_ALLIES, 4);
        pickNewMission();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgents() {
        return missions.get(description);
    }

    public void agentScored(){
        modelManager.drawBonus();
        score++;
        if(score == 3){
            modelManager.missionCompleted();
            score = 0;
            missionList.remove(currMission);
            pickNewMission();
        }
    }

    public void pickNewMission(){
        if(missionList.size() == 0){
            addMission();
        }
        Random rand = new Random();
        currMission = rand.nextInt(missionList.size());
        this.description = missionList.get(currMission);
    }

    public void addMission(){
        missionList.add(FREE_THE_DOGS);
        missionList.add(BUILD_NETWORK);
        missionList.add(STEAL_MEDICINE);
        missionList.add(RECRUIT_NEW_ALLIES);
    }
}
