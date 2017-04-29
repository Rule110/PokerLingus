package textupdate.implementation;

import textupdate.framework.TextUpdate;

public class AITellsTextUpdate implements TextUpdate {
    private static final String[] tells = {
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
    
    public AITellsTextUpdate(int internalCode){
        this.index = internalCode;
    }
    
    public String getText(){
        return tells[index];
    }
}
