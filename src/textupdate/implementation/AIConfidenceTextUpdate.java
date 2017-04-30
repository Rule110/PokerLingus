package textupdate.implementation;

import ai.implementation.AISymbols;
import textupdate.framework.TextUpdate;

public class AIConfidenceTextUpdate implements TextUpdate {
    private int index;
    private String name;
    
    public AIConfidenceTextUpdate(String playerID, int internalCode){
        this.index = internalCode;
        this.name = playerID;
    }
    
    public String getText(){
        return this.name + AISymbols.confidenceExpressions[index];
    }
}
