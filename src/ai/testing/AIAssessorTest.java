package ai.testing;

import static org.junit.Assert.*;
import hand.framework.Hand;
import hand.implementation.DrawPokerHand;
import hand.implementation.Flush;
import hand.implementation.FourOfAKind;
import hand.implementation.FullHouse;
import hand.implementation.OnePair;
import hand.implementation.RoyalFlush;
import hand.implementation.Straight;
import hand.implementation.StraightFlush;
import hand.implementation.ThreeOfAKind;
import hand.implementation.TwoPair;

import org.junit.Before;
import org.junit.Test;

import ai.implementation.AIAssessor;
import ai.implementation.Scale;

import player.implementation.RoundState;

public class AIAssessorTest {
    private class TestRoundState extends RoundState{        
        TestRoundState(int callValue, int chips, int potValue){
            super.callValue = callValue;
            super.chips = chips;
            super.potValue = potValue;
        }
    };
    private RoundState round1;
    private RoundState round2;
    private RoundState round3;
    private RoundState round4;
    private RoundState round5;
    private RoundState round6;
    
    private class TestHand extends DrawPokerHand{
        private int hardCodedGameValue;
        
        TestHand(int gameValue){
            this.hardCodedGameValue = gameValue;
        }
        
        @Override
        public int getGameValue(){
            return this.hardCodedGameValue;
        }
        
    };
    private Hand hand1;
    private Hand hand2;
    private Hand hand3;
    private Hand hand4;
    private Hand hand5;
    private Hand hand6;
    private Hand hand7;
    private Hand hand8;
    private Hand hand9;

    @Before
    public void setUp() throws Exception {
        this.round1 = new TestRoundState(50, 50, 50);
        this.round2 = new TestRoundState(9, 100, 9);
        this.round3 = new TestRoundState(50, 100, 50);
        this.round4 = new TestRoundState(20, 100, 20);
        this.round5 = new TestRoundState(100, 10, 100);
        this.round6 = new TestRoundState(5,1000, 5);
        
        this.hand1 = new TestHand(OnePair.ONE_PAIR_DEFAULT);
        this.hand2 = new TestHand(TwoPair.TWO_PAIR_DEFAULT);
        this.hand3 = new TestHand(ThreeOfAKind.THREE_OF_A_KIND_DEFAULT);
        this.hand4 = new TestHand(Straight.STRAIGHT_DEFAULT);
        this.hand5 = new TestHand(Flush.FLUSH_DEFAULT);
        this.hand6 = new TestHand(FullHouse.FULL_HOUSE_DEFAULT);
        this.hand7 = new TestHand(FourOfAKind.FOUR_OF_A_KIND_DEFAULT);
        this.hand8 = new TestHand(StraightFlush.STRAIGHT_FLUSH_DEFAULT);
        this.hand9 = new TestHand(RoyalFlush.ROYAL_FLUSH_DEFAULT);
    }

    @Test
    public void testAssessReward() {
        assertEquals(AIAssessor.assessReward(round1), new Scale(9));
        assertEquals(AIAssessor.assessReward(round2), new Scale(1));
        assertEquals(AIAssessor.assessReward(round3), new Scale(4));
        assertEquals(AIAssessor.assessReward(round4), new Scale(1));
        assertEquals(AIAssessor.assessReward(round5), new Scale(10));
        assertEquals(AIAssessor.assessReward(round6), new Scale(1));
    }

    @Test
    public void testAssessRisk() {
        assertEquals(AIAssessor.assessRisk(round1), new Scale(10));
        assertEquals(AIAssessor.assessRisk(round2), new Scale(1));
        assertEquals(AIAssessor.assessRisk(round3), new Scale(5));
        assertEquals(AIAssessor.assessRisk(round4), new Scale(2));
        assertEquals(AIAssessor.assessRisk(round5), new Scale(10));
        assertEquals(AIAssessor.assessRisk(round6), new Scale(1));
    }

    @Test
    public void testAssessHand() {
        assertEquals(AIAssessor.assessHand(hand1), new Scale(1));
        assertEquals(AIAssessor.assessHand(hand2), new Scale(2));
        assertEquals(AIAssessor.assessHand(hand3), new Scale(3));
        assertEquals(AIAssessor.assessHand(hand4), new Scale(4));
        assertEquals(AIAssessor.assessHand(hand5), new Scale(5));
        assertEquals(AIAssessor.assessHand(hand6), new Scale(6));
        assertEquals(AIAssessor.assessHand(hand7), new Scale(7));
        assertEquals(AIAssessor.assessHand(hand8), new Scale(8));
        assertEquals(AIAssessor.assessHand(hand9), new Scale(10));
    }
}
