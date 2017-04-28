package textupdate.implementation;

import textupdate.framework.TextUpdate;

public class AIBragsTextUpdate implements TextUpdate {
    private static final String[] brags = {
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    };
    private int index;
    
    public AIBragsTextUpdate(int internalCode){
        this.index = internalCode;
    }
    
    public String getText(){
        return brags[index];
    }
}
