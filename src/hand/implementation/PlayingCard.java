package hand.implementation;

/**
 * This class is used to represent a standard playing card.
 * @author Darragh Fay
 */
public class PlayingCard {

	//Fields represent possible playing card suite shared by all instances of this object.
	public static final char HEARTS   = 'H';
	public static final char DIAMONDS = 'D';
	public static final char CLUBS    = 'C';
	public static final char SPADES   = 'S';

	private String type;
	private char suit;
	private int  faceVal;
	private int  gameVal;
/**
 * Public constructor used to instantiate PlayingCard	
 * @param type        Type of the card, number ('0' - '10') or face ('J', 'Q', 'K', 'A').
 * @param suit		  Which suit the card belongs to, HEARTS, DIAMONDS, CLUBS, SPADES.
 * @param faceVal     The value of the face of the card, should b in the range 1-13.
 * @param gameVal	  The value of the playing card within the game of use, e.g in poker ACE = 14.
 */
	public PlayingCard(String type, char suit, int faceVal, int gameVal){
		this.type    = type;
		this.suit    = suit;
		this.faceVal = faceVal;
		this.gameVal = gameVal;
	}
/** 
 * Returns suit of PlayingCard
 * @return suit
 */	
	public char getSuit(){
		return this.suit;
	}
/** 
 * Returns faceValue of PlayingCard
 * @return faceVal
 */
	public int getFaceVal(){
		return this.faceVal;
	}
/** 
 * Returns gameValue of PlayingCard
 * @return gameVal
 */	
	public int getGameVal(){
		return this.gameVal;
	}
/** 
 * Returns String Representation of PlayingCard
 * @return String
 */
	public String toString(){
		return this.type + Character.toString(this.suit);	
	}
/**
 * Method used to test class in a simple application which instantiates a deck of 52 cards.	
 * @param args
 */
	public static void main(String args []){
		
		PlayingCard[] deck = new PlayingCard[52];
		char[] suits = {PlayingCard.HEARTS, PlayingCard.DIAMONDS, PlayingCard.CLUBS, PlayingCard.SPADES};
		String[] faceTuple = {"10", "J", "Q", "K"}; 
		int i = 0;
		int j = 0;
		
		//Loop through all 4 suits.
		for(char curSuit : suits){
			
			//Add ace to deck.
			deck[j] = new PlayingCard("A", curSuit, 1, 14);
			j++;
	
			//Add number cards to deck.
			for(i = 2; i < 10; i++, j++){
				deck[j] = new PlayingCard(Integer.toString(i), curSuit, i, i);
			}
			
			//Add face cards to deck.
			for(;i < 14 ; i++, j++){
				deck[j] = new PlayingCard(faceTuple[i - 10] , curSuit, i, i);
			}
		}
		
		//Print all cards in deck.
		for(PlayingCard card : deck){
			System.out.println(card);
		}
		
	}
}
