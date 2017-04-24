package ai.implementation;

import game.framework.Game;

public class AISimple extends AITemplate {
    public AISimple(Game game){
        super(game, new Personality());
    }
    
    protected void expressPersonality(Strategy strategy){
        // Simple AI doesn't express Personality
    }
}
