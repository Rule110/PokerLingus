package dealer.implementation;

import card.framework.Card;
import dealer.framework.Dealer;
import hand.implementation.PlayingCard;

public class DrawPokerDealer implements Dealer {
	
	private Deck deck;
	
    public synchronized void reset(){
        deck = new Deck();
        deck.shuffle();
    }
    
    public synchronized PlayingCard dealNext(){
        PlayingCard next_card = deck.dealNext();
        return next_card;
    }
    
    public synchronized void returnCard(PlayingCard card){
        deck.returnCard(card);
    }
}
