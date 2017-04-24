package ai.implementation;

import game.framework.Game;

public class AIStaticRandom extends AIComplex {
    public AIStaticRandom(Game game){
        super(game, new Personality("StaticRandom"));
    }
}
