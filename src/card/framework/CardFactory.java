package card.framework;

import card.implementation.DrawPokerCard;

public class CardFactory {
    public static Card getCard(String type){
        Card card;
        
        switch (type){
        case "DrawPoker":
            card = new DrawPokerCard();
            break;
        default:
            throw new RuntimeException();
        }
        
        return card;
    }
}
