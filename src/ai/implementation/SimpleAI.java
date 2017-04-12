package ai.implementation;

import hand.framework.Hand;
import network.framework.Network;
import round.framework.Round;

public class SimpleAI extends AITemplate {
    public SimpleAI(Network network){
        super(network);
    }
    
    @Override
    public void decideStrategy(Hand hand, Round round){
        
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
