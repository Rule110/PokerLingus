package ai.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ai.implementation.Personality;
import ai.implementation.Scale;
import ai.implementation.Strategy;

public class StrategyTest {
    private Strategy strategy1;
    private Strategy strategy2;
    private Strategy strategy3;
    private Strategy strategy4;
    private Strategy strategy5;
    private Strategy strategy6;
    private Strategy strategy7;
    private Strategy strategy8;
    private Strategy strategy9;

    @Before
    public void setUp() throws Exception {
        Personality personality = new Personality(new Scale(10), new Scale(10), new Scale(10));
        Integer raisePool = 100;
        
        Scale confidence1 = new Scale(2);
        Scale risk1 = new Scale(3);
        Scale reward1 = new Scale(2);
        this.strategy1 = new Strategy(confidence1, risk1, reward1, personality, raisePool);
        
        Scale confidence2 = new Scale(2);
        Scale risk2 = new Scale(5);
        Scale reward2 = new Scale(2);
        this.strategy2 = new Strategy(confidence2, risk2, reward2, personality, raisePool);
        
        Scale confidence3 = new Scale(2);
        Scale risk3 = new Scale(4);
        Scale reward3 = new Scale(2);
        this.strategy3 = new Strategy(confidence3, risk3, reward3, personality, raisePool);
        
        Scale confidence4 = new Scale(5);
        Scale risk4 = new Scale(7);
        Scale reward4 = new Scale(1);
        this.strategy4 = new Strategy(confidence4, risk4, reward4, personality, raisePool);
        
        Personality personality2 = new Personality(new Scale(10), new Scale(5), new Scale(10));
        Scale confidence5 = new Scale(2);
        Scale risk5 = new Scale(10);
        Scale reward5 = new Scale(3);
        this.strategy5 = new Strategy(confidence5, risk5, reward5, personality2, raisePool);
        
        Scale confidence6 = new Scale(2);
        Scale risk6 = new Scale(9);
        Scale reward6 = new Scale(3);
        this.strategy6 = new Strategy(confidence6, risk6, reward6, personality2, raisePool);
        
        Scale confidence7 = new Scale(2);
        Scale risk7 = new Scale(10);
        Scale reward7 = new Scale(2);
        this.strategy7 = new Strategy(confidence7, risk7, reward7, personality2, raisePool);
        
        Scale confidence8 = new Scale(1);
        Scale risk8 = new Scale(5);
        Scale reward8 = new Scale(1);
        this.strategy8 = new Strategy(confidence8, risk8, reward8, personality2, raisePool);
        
        Scale confidence9 = new Scale(2);
        Scale risk9 = new Scale(6);
        Scale reward9 = new Scale(2);
        this.strategy9 = new Strategy(confidence9, risk9, reward9, personality2, raisePool);
    }

    @Test
    public void testIsFolding() {
        assertEquals(this.strategy1.isFolding(), false);
        assertEquals(this.strategy2.isFolding(), true);
        assertEquals(this.strategy3.isFolding(), false);
        assertEquals(this.strategy4.isFolding(), true);
        assertEquals(this.strategy5.isFolding(), false);
        assertEquals(this.strategy6.isFolding(), false);
        assertEquals(this.strategy7.isFolding(), true);
        assertEquals(this.strategy8.isFolding(), false);
        assertEquals(this.strategy9.isFolding(), false);
    }

    @Test
    public void testIsCalling() {
        assertEquals(this.strategy1.isCalling(), false);
        assertEquals(this.strategy2.isCalling(), false);
        assertEquals(this.strategy3.isCalling(), true);
        assertEquals(this.strategy4.isCalling(), false);
        assertEquals(this.strategy5.isCalling(), true);
        assertEquals(this.strategy6.isCalling(), false);
        assertEquals(this.strategy7.isCalling(), false);
        assertEquals(this.strategy8.isCalling(), true);
        assertEquals(this.strategy9.isCalling(), false);
    }

    @Test
    public void testIsRaising() {
        assertEquals(this.strategy1.isRaising(), true);
        assertEquals(this.strategy2.isRaising(), false);
        assertEquals(this.strategy3.isRaising(), false);
        assertEquals(this.strategy4.isRaising(), false);
        assertEquals(this.strategy5.isRaising(), false);
        assertEquals(this.strategy6.isRaising(), true);
        assertEquals(this.strategy7.isRaising(), false);
        assertEquals(this.strategy8.isRaising(), false);
        assertEquals(this.strategy9.isRaising(), true);
    }

    @Test
    public void testGetRaiseAmount() {
        assertEquals(this.strategy1.getRaiseAmount(), 50);
        assertEquals(this.strategy2.getRaiseAmount(), 0);
        assertEquals(this.strategy3.getRaiseAmount(), 0);
        assertEquals(this.strategy4.getRaiseAmount(), 0);
        assertEquals(this.strategy5.getRaiseAmount(), 0);
        assertEquals(this.strategy6.getRaiseAmount(), 50);
        assertEquals(this.strategy7.getRaiseAmount(), 0);
        assertEquals(this.strategy8.getRaiseAmount(), 0);
        assertEquals(this.strategy9.getRaiseAmount(), 50);
    }
}
