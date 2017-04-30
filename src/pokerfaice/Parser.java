package pokerfaice;

import java.util.concurrent.ThreadLocalRandom;

import ai.implementation.AISymbols;

import game.framework.Game;
import gfxupdate.framework.GfxUpdate;
import gfxupdate.framework.GfxUpdateFactory;
import textupdate.framework.TextUpdate;
import textupdate.framework.TextUpdateFactory;

public class Parser {
    private String updateType;
    
    public Parser(String updateType){
        this.updateType = updateType;
    }
    
    private static final String[] messages = {
      "", //0
      "", //1
      "", //2
      "", //3
      "", //4
      "", //5       
    };
    
    public static final String getMessage(int internalCode){
        return messages[internalCode];
    }
    
    public void pushUpdateToGame(Game game, String type, String playerID, int internalCode){
        switch (updateType){
        case "Gfx":
            GfxUpdate gfxUpdate = 
            GfxUpdateFactory.getGfxUpdate(type, playerID, internalCode);
            game.pushGfxUpdate(gfxUpdate);
            break;
        case "Text":
            TextUpdate textUpdate = 
            TextUpdateFactory.getTextUpdate(type, playerID, internalCode);
            game.pushTextUpdate(textUpdate);
            break;
        default: throw new RuntimeException();
        }
        
    }
    
    public static final String getName(){
        int index = ThreadLocalRandom.current().nextInt(1, 11);
        return AISymbols.names[index];
    }
}