package gfxupdate.framework;

import gfxupdate.implementation.AIBragsGfxUpdate;
import gfxupdate.implementation.AIConfidenceGfxUpdate;
import gfxupdate.implementation.AITellsGfxUpdate;
import gfxupdate.implementation.CardGfxUpdate;

public class GfxUpdateFactory {
    public static GfxUpdate getGfxUpdate(String type){
        GfxUpdate update;
        
        switch (type){
        case "Cards":
            update = new CardGfxUpdate();
            break;
        case "AIConfidence":
            update = new AIConfidenceGfxUpdate();
            break;
        case "AITells":
            update = new AITellsGfxUpdate();
            break;
        case "AIBrags":
            update = new AIBragsGfxUpdate();
            break;
        default:
            throw new RuntimeException();
        }
        
        return update;
    }
}
