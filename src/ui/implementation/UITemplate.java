package ui.implementation;

import pokerfaice.exceptions.TemplateClassInstantiationException;
import round.framework.RoundState;
import ui.framework.UI;
import network.framework.Network;
import network.framework.NetworkFactory;

public class UITemplate implements UI {
    protected Network network;
    
    UITemplate(String networktype){
        this.network = NetworkFactory.getNetwork(networktype);
    }
    
    public void decideStrategy(RoundState info){
        throw new TemplateClassInstantiationException();
    }
    
    public boolean isFolding(){
        throw new TemplateClassInstantiationException();
    }
    
    public boolean isCalling(){
        throw new TemplateClassInstantiationException();
    }
    
    public boolean isRaising(){
        throw new TemplateClassInstantiationException();
    }
    
    public int getRaise(){
        throw new TemplateClassInstantiationException();
    }
}
