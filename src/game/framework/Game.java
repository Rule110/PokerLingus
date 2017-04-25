package game.framework;

import database.framework.Database;
import gfxupdate.framework.GfxUpdate;
import textupdate.framework.TextUpdate;

public abstract class Game extends Thread{
	public abstract void run();
	
    public abstract void pushTextUpdate(TextUpdate update);
    
    public abstract void pushGfxUpdate(GfxUpdate update);
    
    public abstract void pushMessageUpdate(String update);
    
    public abstract String getMessageUpdate();
    
    public abstract void captureMessageUpdate(String newMessage);
    
    public abstract Database getDatabase();
    
    public abstract Integer getStartChips();
}
