package textupdate.implementation;

import textupdate.framework.TextUpdate;

public class AIConfidenceTextUpdate implements TextUpdate {
    private static final String[] confidenceExpressions = {
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
    
    public AIConfidenceTextUpdate(int internalCode){
        this.index = internalCode;
    }
    
    public String getText(){
        return confidenceExpressions[index];
    }
}
