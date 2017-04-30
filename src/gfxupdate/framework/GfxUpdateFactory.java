package gfxupdate.framework;

import gfxupdate.implementation.AIBragsGfxUpdate;
import gfxupdate.implementation.AIConfidenceGfxUpdate;
import gfxupdate.implementation.AITellsGfxUpdate;
import gfxupdate.implementation.CardGfxUpdate;

public class GfxUpdateFactory {
    public static GfxUpdate getGfxUpdate(String type, String playerID, int internalCode){
        GfxUpdate update;
        
        switch (type){
        case "Cards":
            update = new CardGfxUpdate();
            break;
        case "AIConfidence":
            update = new AIConfidenceGfxUpdate(playerID, internalCode);
            break;
        case "AITells":
            update = new AITellsGfxUpdate(playerID, internalCode);
            break;
        case "AIBrags":
            update = new AIBragsGfxUpdate(playerID, internalCode);
            break;
        default:
            throw new RuntimeException();
        }
        
        return update;
    }
}
