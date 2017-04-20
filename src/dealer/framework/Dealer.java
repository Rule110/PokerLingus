package dealer.framework;

import card.framework.Card;
import hand.implementation.PlayingCard;

public interface Dealer {
    public void reset();
    
    public PlayingCard dealNext();
    
    public void returnCard(PlayingCard card);
}
