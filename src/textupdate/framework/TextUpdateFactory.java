package textupdate.framework;

import textupdate.implementation.AIBragsTextUpdate;
import textupdate.implementation.AIConfidenceTextUpdate;
import textupdate.implementation.AITellsTextUpdate;
import textupdate.implementation.CardTextUpdate;

public class TextUpdateFactory {
    public static TextUpdate getTextUpdate(String type){
        TextUpdate update;
        
        switch (type){
        case "Cards":
            update = new CardTextUpdate();
            break;
        case "AIConfidence":
            update = new AIConfidenceTextUpdate();
            break;
        case "AITells":
            update = new AITellsTextUpdate();
            break;
        case "AIBrags":
            update = new AIBragsTextUpdate();
            break;
        default:
            throw new RuntimeException();
        }
        
        return update;
    }
}
