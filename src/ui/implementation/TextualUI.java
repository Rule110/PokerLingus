package ui.implementation;

import round.framework.Round;

public class TextualUI extends UITemplate {
    
    public TextualUI(String networktype){
        super(networktype);
    }
    
    @Override
    public void decideStrategy(Round round){
        
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
