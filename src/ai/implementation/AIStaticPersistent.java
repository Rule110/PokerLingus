package ai.implementation;

import game.framework.Game;

public class AIStaticPersistent extends AIComplex {
    public AIStaticPersistent(Game game){
        super(game, new Personality());
    }
}
