package pot.implementation;

import java.util.Map;

import pot.framework.Pot;

public class DrawPokerPot implements Pot {
    private int totalValue;
    private Map<String, Integer> playerSplit;
    
    public void addChips(String playerID, int amount){
        this.totalValue += amount;
    }
    
    public void removeChips(String playerID, int amount){	//winnings?
        this.totalValue -= amount;
    }
    
    public int getTotalValue(){
        return totalValue;
    }
    
    public void enteringSplitPot(String playerID){
        this.playerSplit.put(playerID, this.totalValue);
    }
    
    public int getPotSplit(String playerID){
      	int numberOfWinners = playerSplit.size();
    	//int winnings = getTotalValue();
      	//int playerWinnings = this.playerSplit.get(playerID)/numberOfWinners;
        int playerWinnings = getTotalValue()/numberOfWinners;
        //test
        return playerWinnings;
    }
}
