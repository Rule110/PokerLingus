package ai.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ai.implementation.Personality;
import ai.implementation.Scale;
import ai.implementation.Strategy;

import player.implementation.RoundState;

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
    private Strategy strategy10;
    private Strategy strategy11;
    private Strategy strategy12;

    @Before
    public void setUp() throws Exception {
        Personality personality = new Personality(new Scale(10), new Scale(10), new Scale(10));
        Integer raisePool = 100;
        
        Scale confidence1 = new Scale(10);
        Scale risk1 = new Scale(3);
        Scale reward1 = new Scale(4);
        this.strategy1 = new Strategy(confidence1, risk1, reward1, personality, raisePool);
        
        Scale confidence2 = new Scale(10);
        Scale risk2 = new Scale(2);
        Scale reward2 = new Scale(1);
        this.strategy2 = new Strategy(confidence2, risk2, reward2, personality, raisePool);
        
        Scale confidence3 = new Scale(2);
        Scale risk3 = new Scale(4);
        Scale reward3 = new Scale(2);
        this.strategy3 = new Strategy(confidence3, risk3, reward3, personality, raisePool);
        
        /*Scale confidence4 = new Scale();
        Scale risk4 = new Scale();
        Scale reward4 = new Scale();
        this.strategy4 = new Strategy(confidence4, risk4, reward4, personality, raisePool);
        
        Scale confidence5 = new Scale();
        Scale risk5 = new Scale();
        Scale reward5 = new Scale();
        this.strategy5 = new Strategy(confidence5, risk5, reward5, personality, raisePool);
        
        Scale confidence6 = new Scale();
        Scale risk6 = new Scale();
        Scale reward6 = new Scale();
        this.strategy6 = new Strategy(confidence6, risk6, reward6, personality, raisePool);
        
        Scale confidence7 = new Scale();
        Scale risk7 = new Scale();
        Scale reward7 = new Scale();
        this.strategy7 = new Strategy(confidence7, risk7, reward7, personality, raisePool);
        
        Scale confidence8 = new Scale();
        Scale risk8 = new Scale();
        Scale reward8 = new Scale();
        this.strategy8 = new Strategy(confidence8, risk8, reward8, personality, raisePool);
        
        Scale confidence9 = new Scale();
        Scale risk9 = new Scale();
        Scale reward9 = new Scale();
        this.strategy9 = new Strategy(confidence9, risk9, reward9, personality, raisePool);
        
        Scale confidence10 = new Scale();
        Scale risk10 = new Scale();
        Scale reward10 = new Scale();
        this.strategy10 = new Strategy(confidence10, risk10, reward10, personality, raisePool);
        
        Scale confidence11 = new Scale();
        Scale risk11 = new Scale();
        Scale reward11 = new Scale();
        this.strategy11 = new Strategy(confidence11, risk11, reward11, personality, raisePool);
        
        Scale confidence12 = new Scale();
        Scale risk12 = new Scale();
        Scale reward12 = new Scale();
        this.strategy12 = new Strategy(confidence12, risk12, reward12, personality, raisePool);*/
    }

    @Test
    public void testIsFolding() {
        assertEquals(this.strategy1.isFolding(), false);
        assertEquals(this.strategy2.isFolding(), true);
        /*assertEquals(this.strategy3.isFolding(), );
        assertEquals(this.strategy4.isFolding(), );
        assertEquals(this.strategy5.isFolding(), );
        assertEquals(this.strategy6.isFolding(), );
        assertEquals(this.strategy7.isFolding(), );
        assertEquals(this.strategy8.isFolding(), );
        assertEquals(this.strategy9.isFolding(), );
        assertEquals(this.strategy10.isFolding(), );
        assertEquals(this.strategy11.isFolding(), );
        assertEquals(this.strategy12.isFolding(), );*/
    }

    @Test
    public void testIsCalling() {
        assertEquals(this.strategy1.isCalling(), false);
        assertEquals(this.strategy2.isCalling(), false);
        /*assertEquals(this.strategy3.isCalling(), );
        assertEquals(this.strategy4.isCalling(), );
        assertEquals(this.strategy5.isCalling(), );
        assertEquals(this.strategy6.isCalling(), );
        assertEquals(this.strategy7.isCalling(), );
        assertEquals(this.strategy8.isCalling(), );
        assertEquals(this.strategy9.isCalling(), );
        assertEquals(this.strategy10.isCalling(), );
        assertEquals(this.strategy11.isCalling(), );
        assertEquals(this.strategy12.isCalling(), );*/
    }

    @Test
    public void testIsRaising() {
        assertEquals(this.strategy1.isRaising(), true);
        assertEquals(this.strategy2.isRaising(), false);
        /*assertEquals(this.strategy3.isRaising(), );
        assertEquals(this.strategy4.isRaising(), );
        assertEquals(this.strategy5.isRaising(), );
        assertEquals(this.strategy6.isRaising(), );
        assertEquals(this.strategy7.isRaising(), );
        assertEquals(this.strategy8.isRaising(), );
        assertEquals(this.strategy9.isRaising(), );
        assertEquals(this.strategy10.isRaising(), );
        assertEquals(this.strategy11.isRaising(), );
        assertEquals(this.strategy12.isRaising(), );*/
    }

    @Test
    public void testGetRaiseAmount() {
        assertEquals(this.strategy1.getRaiseAmount(), 50);
        assertEquals(this.strategy2.getRaiseAmount(), 0);
        /*assertEquals(this.strategy3.getRaiseAmount(), );
        assertEquals(this.strategy4.getRaiseAmount(), );
        assertEquals(this.strategy5.getRaiseAmount(), );
        assertEquals(this.strategy6.getRaiseAmount(), );
        assertEquals(this.strategy7.getRaiseAmount(), );
        assertEquals(this.strategy8.getRaiseAmount(), );
        assertEquals(this.strategy9.getRaiseAmount(), );
        assertEquals(this.strategy10.getRaiseAmount(), );
        assertEquals(this.strategy11.getRaiseAmount(), );
        assertEquals(this.strategy12.getRaiseAmount(), );*/
    }
}
