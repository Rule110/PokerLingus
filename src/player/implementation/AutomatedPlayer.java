package player.implementation;

import player.framework.Player;
import ai.framework.AI;
import ai.framework.AIFactory;

public class AutomatedPlayer implements Player {
    private AI ai;
    
    public AutomatedPlayer(){
        this.ai = AIFactory.getAI("Simple");
    }
}
