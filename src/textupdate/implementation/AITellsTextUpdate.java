package textupdate.implementation;

import ai.implementation.AISymbols;
import textupdate.framework.TextUpdate;

public class AITellsTextUpdate implements TextUpdate {
    private String name;
    private int index;
    
    public AITellsTextUpdate(String playerID, int internalCode){
        this.index = internalCode;
        this.name = playerID;
    }
    
    public String getText(){
        return this.name + AISymbols.tells[index];
    }
}
