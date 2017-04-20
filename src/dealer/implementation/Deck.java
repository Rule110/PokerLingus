package dealer.implementation;

import java.util.Random;

import hand.implementation.PlayingCard;

class Deck {

		private PlayingCard[] deck = new PlayingCard[52];
		private int counter = 0;
		private int replaced = 0;

			
		//Empty Constructor	
		public Deck() {
			reset();
		}
		
		public void printFunction() {
			for (int a=0; a<52; a++){
				System.out.println(deck[a]);
			}
		}
		
		public void reset() {	
			String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; 	//Array of card ranks
			int[] faceValue = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};							//Array of card face values
			int[] gameValue = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};							//Array of card game value 
			char[] suits = {PlayingCard.HEARTS,  PlayingCard.SPADES,  PlayingCard.CLUBS,  PlayingCard.DIAMONDS};
				
					
			int k = 0; //counter for storing cards in deck array
			int q = 0; //counter to list through suits		
					
			for (q = 0; q < 4; q++){	
					for (int i = 0; i<13; i++){
							deck[k] = new PlayingCard(ranks[i], suits[q], faceValue[i], gameValue[i]);   //for loop to store each card in deck. Calls on constructor for each card.
							k++;
					}
			}
			counter = 0;
			replaced = 0;
			
		} 
		
		public void shuffle() {				
			for (int q=0; q<Math.pow(deck.length, 2); q++){
					Random ran1 = new Random();
					int x = ran1.nextInt(52);
					PlayingCard temp1 = deck[x];
					Random ran2 = new Random();
					int y = ran2.nextInt(52);
					PlayingCard temp2 = deck[y];
					deck[x] = temp2;
					deck[y] = temp1;
				}
			
			int nullCounter = deck.length-1;
			int cardCounter = 0;
			
			
			for (int i = 0; i < deck.length -1; i++){
				if (deck[cardCounter] == null){
					if(deck[nullCounter] != null){
						PlayingCard temp = deck[nullCounter];
						deck[nullCounter] = deck[cardCounter];
						deck[cardCounter] = temp;
						cardCounter++;
					} else {	
						nullCounter--;
					}
				} else {
					cardCounter++;
				}
			}
			counter = 0;
			replaced = 0;
		}
		
		public synchronized int deckPosition(){
			if (counter == 51){
				System.out.println("Sorry, we seemed have reached the end of the deck.");
			}
			return counter;
		}
		
		public synchronized PlayingCard dealNext() {
			if (this.nextCardCheck() == null) {			//check to make sure there are still cards in deck to be dealt.
				System.out.println("Sorry, we seemed have reached the end of the deck. We need to reshuffle.");
				this.shuffle();
			} 
			PlayingCard dealtcard = deck[counter];
			deck[counter] = null;	//sets place in deck array to null as card is taken out of deck.
			counter++;
			return dealtcard;
		}
		
		public synchronized PlayingCard nextCardCheck() {
			PlayingCard nextcard = deck[counter];
			return nextcard;
		}
		
		public synchronized void returnCard (PlayingCard discarded) {	//Places returned card at start of deck. 
			for(replaced = 0; replaced < deck.length; replaced++){	
				if(deck[replaced] == null){
						deck[replaced] = discarded;						//Currently only discards most recently dealt card as currently no "PokerHand" created.
						break;
				}
			}
		}
}

