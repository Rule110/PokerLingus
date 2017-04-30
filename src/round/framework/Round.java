package round.framework;

public interface Round {
    public void beginRound();
    
    public int getPotValue();
    
    public int getAvailableFunds(String playerID);
    
    public String getWinner();
    
    public int getCallValue();
}
