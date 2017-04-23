package game.framework;

import gfxupdate.framework.GfxUpdate;
import textupdate.framework.TextUpdate;

public abstract class Game extends Thread{
	public abstract void run();
	
    public abstract void pushTextUpdate(TextUpdate update);
    
    public abstract void pushGfxUpdate(GfxUpdate update);
    
    public abstract String getMessageUpdate();
    
    public abstract void captureMessageUpdate(String newMessage);
}
