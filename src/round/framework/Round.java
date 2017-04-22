package round.framework;

public interface Round {
    public void beginRound();
    
    public int getCallValue();
    
    public int getPotValue();
    
    public int getAvailableFunds(String playerID);
    
    public String getWinner();
}
