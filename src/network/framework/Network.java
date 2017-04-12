package network.framework;

import gfxupdate.framework.GfxUpdate;
import textupdate.framework.TextUpdate;

public interface Network {
    public void sendTextUpdate(TextUpdate textupdate);
    
    public void sendGfxUpdate(GfxUpdate gfxupdate);
}
