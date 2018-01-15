package dealer.framework;

import hand.implementation.PlayingCard;

public interface Dealer {
    public void reset();
    
    public PlayingCard dealNext();
    
    public void returnCard(PlayingCard card);
}
