package gfxupdate.implementation;

import java.awt.Image;
import java.io.IOException;

import gfxupdate.framework.Gfx;
import gfxupdate.framework.GfxUpdate;
import hand.framework.Hand;

public class CardGfxUpdate implements GfxUpdate {
	
	Image cardGraphic;
	
    public Gfx getGfx(){
        
        return null;
    }
    
    public void generateImage(int chips, Hand hand){
    	try {
			cardGraphic = ImageGenerator.generateImage(chips, hand);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public Image getImage(){
    	return cardGraphic;
    }
}
