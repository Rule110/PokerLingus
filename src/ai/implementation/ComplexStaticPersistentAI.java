package ai.implementation;

import round.framework.Round;
import ai.framework.AI;

public class ComplexStaticPersistentAI implements AI {
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
