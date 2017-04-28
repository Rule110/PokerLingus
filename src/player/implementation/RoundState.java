package player.implementation;

import round.framework.Round;

/**
 * Encapsulates current Round State for use by AI and UI in Player implementations
 * @author Rory Buckley
 *
 */
public class RoundState {
    private Integer callValue;
    private Integer chips;
    private Integer potValue;
    
    RoundState(Round round, String playerID){
        this.callValue = round.getCallValue();
        this.chips = round.getAvailableFunds(playerID);
        this.potValue = round.getPotValue();
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
