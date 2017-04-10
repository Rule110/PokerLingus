package dealer.framework;

import card.framework.Card;

public interface Dealer {
    public void reset();
    
    public Card dealNext();
    
    public void returnCard(Card card);
}
