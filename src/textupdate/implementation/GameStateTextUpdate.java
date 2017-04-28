package textupdate.implementation;

import textupdate.framework.TextUpdate;

public class GameStateTextUpdate implements TextUpdate {
	String update;
	
	public void setText(String s){
		update = s;
	}
	
    public String getText(){
        return update;
    }
}
