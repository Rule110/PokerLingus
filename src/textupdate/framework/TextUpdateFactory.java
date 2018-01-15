package textupdate.framework;

import textupdate.implementation.AIBragsTextUpdate;
import textupdate.implementation.AIConfidenceTextUpdate;
import textupdate.implementation.AITellsTextUpdate;
import textupdate.implementation.CardTextUpdate;

/**
 * Factory for Text Update implementations
 * 3 AI updates and a Card update are available implementations for Text Update
 * @author Rory Buckley
 *
 */
public class TextUpdateFactory {
    public static TextUpdate getTextUpdate(String type, String playerID, int internalCode){
        TextUpdate update;
        
        switch (type){
        case "Cards":
            update = new CardTextUpdate();
            break;
        case "AIConfidence":
            update = new AIConfidenceTextUpdate(playerID, internalCode);
            break;
        case "AITells":
            update = new AITellsTextUpdate(playerID, internalCode);
            break;
        case "AIBrags":
            update = new AIBragsTextUpdate(playerID, internalCode);
            break;
        default:
            throw new RuntimeException();
        }
        
        return update;
    }
}
