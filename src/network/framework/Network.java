package network.framework;

import gfxupdate.framework.GfxUpdate;
import textupdate.framework.TextUpdate;

public interface Network {
    public void sendTextUpdate(TextUpdate textupdate);
    
    public void sendGfxUpdate(GfxUpdate gfxupdate);
    
    public String getMessageUpdate();
    
    public void pushMessageUpdate(String update);
    
    public void captureMessageUpdate(String newMessage);
}
