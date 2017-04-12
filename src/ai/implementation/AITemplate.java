package ai.implementation;

import hand.framework.Hand;
import ai.framework.AI;
import pokerfaice.exceptions.TemplateClassInstantiationException;
import round.framework.Round;
import network.framework.Network;

public class AITemplate implements AI {
    protected Network network;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected int raiseAmount;
    
    AITemplate(Network network){
        this.network = network;
    }
    
    public void decideStrategy(Hand hand, Round round){
        throw new TemplateClassInstantiationException();
    }
    
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
