package ui.implementation;

import round.framework.RoundState;

public class TextualUI extends UITemplate {
    
    public TextualUI(String networktype){
        super(networktype);
    }
    
    @Override
    public void decideStrategy(RoundState info){
        
    }
    
    @Override
    public boolean isFolding(){
        
        return false;
    }
    
    @Override
    public boolean isCalling(){
        
        return false;
    }
    
    @Override
    public boolean isRaising(){
        
        return false;
    }
    
    @Override
    public int getRaise(){
        
        return 0;
    }
}
