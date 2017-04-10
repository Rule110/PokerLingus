package pot.framework;

public interface Pot {
    public void addChips(String playerID, int amount);
    
    public void removeChips(String playerID, int amount);
    
    public int getTotalValue();
    
    public void splitPot(String playerID);
    
    public int getPotSplit(String playerID);
}
