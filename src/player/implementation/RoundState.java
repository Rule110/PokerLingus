package player.implementation;

import round.framework.Round;

/**
 * Encapsulates current Round State for use by AI and UI in Player implementations
 * @author Rory Buckley
 *
 */
public class RoundState {
    protected Integer callValue;
    protected Integer chips;
    protected Integer potValue;
    
    RoundState(Round round, String playerID){
        this.callValue = round.getCallValue();
        this.chips = round.getAvailableFunds(playerID);
        this.potValue = round.getPotValue();
    }
    
    /**
     * Constructor for testing
     */
    public RoundState(){
        
    }
    
    public Integer getCallValue(){
        return this.callValue;
    }
    
    public Integer getChips(){
        return this.chips;
    }
    
    public Integer getPotValue(){
        return this.potValue;
    }
}
