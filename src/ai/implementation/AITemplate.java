package ai.implementation;

import game.framework.Game;
import hand.framework.Hand;
import ai.framework.AI;
import round.framework.Round;

abstract public class AITemplate implements AI {
    protected Game game;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected int raiseAmount;
    
    AITemplate(Game game){
        this.game = game;
    }
    
    abstract public void decideStrategy(Hand hand, Round round);
    
    public boolean isFolding(){
        return isFolding;
    }
    
    public boolean isCalling(){
        return isCalling;
    }
    
    public boolean isRaising(){
        return isRaising;
    }
    
    public int getRaise(){
        return raiseAmount;
    }
}
