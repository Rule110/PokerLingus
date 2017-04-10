package pot.implementation;

import java.util.Map;

import pot.framework.Pot;

public class DrawPokerPot implements Pot {
    private int totalValue;
    private Map<String, Integer> playerSplit;
    
    public void addChips(String playerID, int amount){
        this.totalValue += amount;
    }
    
    public void removeChips(String playerID, int amount){
        this.totalValue -= amount;
    }
    
    public int getTotalValue(){
        return totalValue;
    }
    
    public void splitPot(String playerID){
        this.playerSplit.put(playerID, this.totalValue);
    }
    
    public int getPotSplit(String playerID){
        return this.playerSplit.get(playerID);
    }
}
