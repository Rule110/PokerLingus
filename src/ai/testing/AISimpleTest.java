package ai.testing;

import static org.junit.Assert.*;
import hand.framework.Hand;
import hand.implementation.DrawPokerHand;
import hand.implementation.Flush;
import hand.implementation.FullHouse;
import hand.implementation.OnePair;
import hand.implementation.Straight;
import hand.implementation.ThreeOfAKind;
import hand.implementation.TwoPair;

import org.junit.Before;
import org.junit.Test;

import ai.framework.AI;
import ai.implementation.AIAssessor;
import ai.implementation.AISimple;
import ai.implementation.Personality;
import ai.implementation.Scale;
import ai.implementation.Strategy;

import player.implementation.RoundState;

public class AISimpleTest {
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
    
    private AI ai;
    private Personality personality;

    @Before
    public void setUp() throws Exception {
        this.personality = new Personality(new Scale(10), new Scale(10), new Scale(10));
        this.ai = new AISimple(null, "AI", this.personality);
        
        this.round1 = new TestRoundState(11, 10, 50);
        this.round2 = new TestRoundState(20, 10, 200);
        this.round3 = new TestRoundState(40, 100, 300);
        this.round4 = new TestRoundState(20, 100, 400);
        this.round5 = new TestRoundState(10, 100, 500);
        this.round6 = new TestRoundState(5,1000, 6000);
        
        this.hand1 = new TestHand(OnePair.ONE_PAIR_DEFAULT);
        this.hand2 = new TestHand(TwoPair.TWO_PAIR_DEFAULT);
        this.hand3 = new TestHand(ThreeOfAKind.THREE_OF_A_KIND_DEFAULT);
        this.hand4 = new TestHand(Straight.STRAIGHT_DEFAULT);
        this.hand5 = new TestHand(Flush.FLUSH_DEFAULT);
        this.hand6 = new TestHand(FullHouse.FULL_HOUSE_DEFAULT);
        
        
    }

    @Test
    public void testDecideOpening() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecideStrategy() {
        Strategy expectedStrategy1 = new Strategy();
        Strategy strategy1 = this.ai.decideStrategy(this.hand1, this.round1);
        assertEquals(expectedStrategy1.isFolding(), strategy1.isFolding());
        assertEquals(expectedStrategy1.isCalling(), strategy1.isCalling());
        assertEquals(expectedStrategy1.isRaising(), strategy1.isRaising());
        assertEquals(expectedStrategy1.getRaiseAmount(), strategy1.getRaiseAmount());

        Strategy expectedStrategy2 = new Strategy();
        Strategy strategy2 = this.ai.decideStrategy(this.hand2, this.round2);
        assertEquals(expectedStrategy2.isFolding(), strategy2.isFolding());
        assertEquals(expectedStrategy1.isCalling(), strategy2.isCalling());
        assertEquals(expectedStrategy2.isRaising(), strategy2.isRaising());
        assertEquals(expectedStrategy2.getRaiseAmount(), strategy2.getRaiseAmount());

        Scale confidence3 = AIAssessor.assessHand(this.hand3);
        Scale risk3 = AIAssessor.assessRisk(this.round3);
        Scale reward3 = AIAssessor.assessReward(this.round3);
        Strategy expectedStrategy3 = new Strategy(confidence3, risk3, reward3, this.personality, 60);
        Strategy strategy3 = this.ai.decideStrategy(this.hand3, this.round3);
        assertEquals(expectedStrategy3.isFolding(), strategy3.isFolding());
        assertEquals(expectedStrategy3.isCalling(), strategy3.isCalling());
        assertEquals(expectedStrategy3.isRaising(), strategy3.isRaising());
        assertEquals(expectedStrategy3.getRaiseAmount(), strategy3.getRaiseAmount());

        Scale confidence4 = AIAssessor.assessHand(this.hand4);
        Scale risk4 = AIAssessor.assessRisk(this.round4);
        Scale reward4 = AIAssessor.assessReward(this.round4);
        Strategy expectedStrategy4 = new Strategy(confidence4, risk4, reward4, this.personality, 40);
        Strategy strategy4 = this.ai.decideStrategy(this.hand4, this.round4);
        assertEquals(expectedStrategy4.isFolding(), strategy4.isFolding());
        assertEquals(expectedStrategy4.isCalling(), strategy4.isCalling());
        assertEquals(expectedStrategy4.isRaising(), strategy4.isRaising());
        assertEquals(expectedStrategy4.getRaiseAmount(), strategy4.getRaiseAmount());

        Scale confidence5 = AIAssessor.assessHand(this.hand5);
        Scale risk5 = AIAssessor.assessRisk(this.round5);
        Scale reward5 = AIAssessor.assessReward(this.round5);
        Strategy expectedStrategy5 = new Strategy(confidence5, risk5, reward5, this.personality, 20);
        Strategy strategy5 = this.ai.decideStrategy(this.hand5, this.round5);
        assertEquals(expectedStrategy5.isFolding(), strategy5.isFolding());
        assertEquals(expectedStrategy5.isCalling(), strategy5.isCalling());
        assertEquals(expectedStrategy5.isRaising(), strategy5.isRaising());
        assertEquals(expectedStrategy5.getRaiseAmount(), strategy5.getRaiseAmount());

        Scale confidence6 = AIAssessor.assessHand(this.hand6);
        Scale risk6 = AIAssessor.assessRisk(this.round6);
        Scale reward6 = AIAssessor.assessReward(this.round6);
        Strategy expectedStrategy6 = new Strategy(confidence6, risk6, reward6, this.personality, 10);
        Strategy strategy6 = this.ai.decideStrategy(this.hand6, this.round6);
        assertEquals(expectedStrategy6.isFolding(), strategy6.isFolding());
        assertEquals(expectedStrategy6.isCalling(), strategy6.isCalling());
        assertEquals(expectedStrategy6.isRaising(), strategy6.isRaising());
        assertEquals(expectedStrategy6.getRaiseAmount(), strategy6.getRaiseAmount());
    }

}
