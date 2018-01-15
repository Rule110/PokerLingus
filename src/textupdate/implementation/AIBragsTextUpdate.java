package textupdate.implementation;

import ai.implementation.AISymbols;
import textupdate.framework.TextUpdate;

public class AIBragsTextUpdate implements TextUpdate {
    private String name;
    private int index;
    
    public AIBragsTextUpdate(String playerID, int internalCode){
        this.index = internalCode;
        this.name = playerID;
    }
    
    public String getText(){
        return this.name + AISymbols.brags[index];
    }
}
