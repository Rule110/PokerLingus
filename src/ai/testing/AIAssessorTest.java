package ai.testing;

import static org.junit.Assert.*;
import hand.framework.Hand;
import hand.framework.HandFactory;

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
        
        this.hand1 = HandFactory.getHand("DrawPoker");

        this.hand2 = HandFactory.getHand("DrawPoker");

        this.hand3 = HandFactory.getHand("DrawPoker");

        this.hand4 = HandFactory.getHand("DrawPoker");

        this.hand5 = HandFactory.getHand("DrawPoker");

        this.hand6 = HandFactory.getHand("DrawPoker");

        this.hand7 = HandFactory.getHand("DrawPoker");

        this.hand8 = HandFactory.getHand("DrawPoker");

        this.hand9 = HandFactory.getHand("DrawPoker");
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
        fail("Not yet implemented");
    }

}
