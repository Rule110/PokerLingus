package game.framework;

import gfxupdate.framework.GfxUpdate;
import textupdate.framework.TextUpdate;

public interface Game {
    public void pushTextUpdate(TextUpdate update);
    
    public void pushGfxUpdate(GfxUpdate update);
}
