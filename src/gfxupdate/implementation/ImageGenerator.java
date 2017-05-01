package gfxupdate.implementation;

// Class to create an image consisting of the player's total number of chips and their five cards

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;

import hand.framework.Hand;
import hand.implementation.PlayingCard;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import java.util.Iterator;
public class ImageGenerator
{
	// Width and height of the final image
	// Each individual card has the dimensions 500x726
	// Extra space has been allowed for a uniform border
	private static final int width = 2900;
	private static final int height = 1126;
	private static final int MAX_CARDS = 5;
	
	public static final Image generateImage(int chips, Hand hand) throws IOException{
		
		String[] cardAr = new String[MAX_CARDS];
		int cardInd = 0;
		Iterator<PlayingCard> handIt = hand.iterator();
		while(handIt.hasNext()){
			cardAr[cardInd] = (handIt.next()).toString();
			cardInd++;
		}
		String[] cardLocations = new String[MAX_CARDS];
		
		for (int i = 0; i < MAX_CARDS; i++)
		{
			cardLocations[i] = "src/Images/Cards/" + cardAr[i] + ".png";
		}
		
		// System message for testing
		System.out.println("// ImageGenerator method invoked.");
		
		BufferedImage generatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = generatedImage.getGraphics();
		
		// Perpendicular distances from the edge of the cards to the edge of the image
		int x = 100, y = 100;
		
		// Loop to add each individual card image (500x726) to the final image
		for (String cardLocation: cardLocations)
		{
			BufferedImage tempImage = ImageIO.read(new File(cardLocation));
			g.drawImage(tempImage, x, y, null);
			x += 550;
		}
		
		// Adds the "Total Chips:" (1000x200) text to the final image
		x = 700;
		y += 776;
		BufferedImage tempImage = ImageIO.read(new File("src/Images/Numbers/TotalChips.png"));
		g.drawImage(tempImage, x, y, null);
		
		// Adds the individual number images (100x200) to represent the chip total
		x = 1450;
		y -= 15;
		String chipsTotal = Integer.toString(chips);
		
		for(int i = 0; i <= (chips/10); i++){
			tempImage = ImageIO.read(new File("src/Images/Numbers/" + chipsTotal.charAt(i) + ".png"));
			g.drawImage(tempImage, x, y, null);
			x+= 75;
		}
		
		// Writes the generated image to the specified location
		return generatedImage;
	}
}