package ai.implementation;

import game.framework.Game;

public class AIDynamicPersistent extends AIComplex {
    public AIDynamicPersistent(Game game){
        super(game, new Personality());
    }
}
