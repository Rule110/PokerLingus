package ai.implementation;

import ai.framework.AI;
import pokerfaice.exceptions.TemplateClassInstantiationException;
import round.framework.Round;
import network.framework.Network;
import network.framework.NetworkFactory;

public class AITemplate implements AI {
    protected Network network;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected int raiseAmount;
    
    AITemplate(String networktype){
        this.network = NetworkFactory.getNetwork(networktype);
    }
    
    public void decideStrategy(Round round){
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
