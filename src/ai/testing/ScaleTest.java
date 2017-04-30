package ai.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ai.implementation.Scale;

public class ScaleTest {
    private Scale SCALE_ONE;
    private Scale SCALE_TWO;
    private Scale SCALE_THREE;
    private Scale SCALE_FOUR;
    private Scale SCALE_FIVE;
    private Scale SCALE_SIX;
    private Scale SCALE_SEVEN;
    private Scale SCALE_EIGHT;
    private Scale SCALE_NINE;
    private Scale SCALE_TEN;

    @Before
    public void setUp() throws Exception {
        SCALE_ONE = new Scale(1);
        SCALE_TWO = new Scale(2);
        SCALE_THREE = new Scale(3);
        SCALE_FOUR = new Scale(4);
        SCALE_FIVE = new Scale(5);
        SCALE_SIX = new Scale(6);
        SCALE_SEVEN = new Scale(7);
        SCALE_EIGHT = new Scale(8);
        SCALE_NINE = new Scale(9);
        SCALE_TEN = new Scale(10);
    }

    @Test
    public void testScaleInteger() {
        Scale scale = new Scale(5);
        assertEquals(scale, SCALE_FIVE);
        assertEquals(scale.getIntegerRepresentation(), 5);
    }

    @Test
    public void testScaleByDegree() {
        Scale scaled0 = SCALE_ONE.scaleByDegree(SCALE_ONE);
        assertEquals(scaled0.getIntegerRepresentation(), 1);
        
        Scale scaled1 = SCALE_TWO.scaleByDegree(SCALE_THREE);
        assertEquals(scaled1.getIntegerRepresentation(), 1);
        
        Scale scaled2 = SCALE_NINE.scaleByDegree(SCALE_EIGHT);
        assertEquals(scaled2.getIntegerRepresentation(), 7);
        
        Scale scaled3 = SCALE_SIX.scaleByDegree(SCALE_TEN);
        assertEquals(scaled3.getIntegerRepresentation(), 6);
        
        Scale scaled4 = SCALE_FIVE.scaleByDegree(SCALE_FIVE);
        assertEquals(scaled4.getIntegerRepresentation(), 2);
    }

    @Test
    public void testScaleThat() {
        Integer integer1 = new Integer(10);
        Integer scaledInteger1 = SCALE_FIVE.scaleThat(integer1);
        assertEquals(scaledInteger1, new Integer(5));
        

        Integer integer2 = new Integer(10);
        Integer scaledInteger2 = SCALE_SIX.scaleThat(integer2);
        assertEquals(scaledInteger2, new Integer(6));
        

        Integer integer3 = new Integer(10);
        Integer scaledInteger3 = SCALE_SEVEN.scaleThat(integer3);
        assertEquals(scaledInteger3, new Integer(7));
        

        Integer integer4 = new Integer(10);
        Integer scaledInteger4 = SCALE_EIGHT.scaleThat(integer4);
        assertEquals(scaledInteger4, new Integer(8));
        

        Integer integer5 = new Integer(25);
        Integer scaledInteger5 = SCALE_NINE.scaleThat(integer5);
        assertEquals(scaledInteger5, new Integer(22));
        

        Integer integer6 = new Integer(76);
        Integer scaledInteger6 = SCALE_TWO.scaleThat(integer6);
        assertEquals(scaledInteger6, new Integer(15));
    }

    @Test
    public void testDifferenceOnScale() {
        Scale expected0 = new Scale(5);
        Scale difference0 = SCALE_EIGHT.differenceOnScale(SCALE_SIX);
        assertEquals(difference0, expected0);
        
        Scale expected1 = new Scale(8);
        Scale difference1 = SCALE_NINE.differenceOnScale(SCALE_TWO);
        assertEquals(difference1, expected1);
        
        Scale expected2 = new Scale(10);
        Scale difference2 = SCALE_TEN.differenceOnScale(SCALE_TWO);
        assertEquals(difference2, expected2);
        
        Scale expected3 = new Scale(1);
        Scale difference3 = SCALE_THREE.differenceOnScale(SCALE_EIGHT);
        assertEquals(difference3, expected3);
    }

    @Test
    public void testDifferenceAsInteger() {
        Integer difference0 = SCALE_ONE.differenceAsInteger(SCALE_TEN);
        assertEquals(difference0, new Integer(-9));
        
        Integer difference1 = SCALE_FIVE.differenceAsInteger(SCALE_FOUR);
        assertEquals(difference1, new Integer(1));
        
        Integer difference2 = SCALE_TEN.differenceAsInteger(SCALE_ONE);
        assertEquals(difference2, new Integer(9));
        
        Integer difference3 = SCALE_EIGHT.differenceAsInteger(SCALE_SIX);
        assertEquals(difference3, new Integer(2));
    }

    @Test
    public void testRegressToMeanByDegree() {
        Scale expected0 = SCALE_TEN.regressToMeanByDegree(SCALE_FIVE);
        assertEquals(expected0, SCALE_EIGHT);
        
        Scale expected1 = SCALE_TEN.regressToMeanByDegree(SCALE_NINE);
        assertEquals(expected1, SCALE_SIX);
        
        Scale expected2 = SCALE_TEN.regressToMeanByDegree(SCALE_ONE);
        assertEquals(expected2, SCALE_TEN);
        
        Scale expected3 = SCALE_TEN.regressToMeanByDegree(SCALE_TEN);
        assertEquals(expected3, SCALE_FIVE);
        
        Scale expected4 = SCALE_EIGHT.regressToMeanByDegree(SCALE_FIVE);
        assertEquals(expected4, SCALE_SEVEN);
        
        Scale expected5 = SCALE_EIGHT.regressToMeanByDegree(SCALE_ONE);
        assertEquals(expected5, SCALE_EIGHT);
        
        Scale expected6 = SCALE_EIGHT.regressToMeanByDegree(SCALE_TEN);
        assertEquals(expected6, SCALE_FIVE);
        
        Scale expected7 = SCALE_ONE.regressToMeanByDegree(SCALE_FIVE);
        assertEquals(expected7, SCALE_THREE);
        
        Scale expected8 = SCALE_ONE.regressToMeanByDegree(SCALE_ONE);
        assertEquals(expected8, SCALE_ONE);
        
        Scale expected9 = SCALE_ONE.regressToMeanByDegree(SCALE_TEN);
        assertEquals(expected9, SCALE_FIVE);
    }
    
    @Test
    public void testAdd() {
        Scale sum1 = SCALE_ONE.add(SCALE_ONE);
        assertEquals(sum1, SCALE_TWO);
        Scale sum2 = SCALE_NINE.add(SCALE_TWO);
        assertEquals(sum2, SCALE_TEN);
        Scale sum3 = SCALE_TWO.add(SCALE_THREE);
        assertEquals(sum3, SCALE_FIVE);
        Scale sum4 = SCALE_THREE.add(SCALE_FIVE);
        assertEquals(sum4, SCALE_EIGHT);
    }

    @Test
    public void testCompareTo() {
        assertEquals(SCALE_ONE.compareTo(SCALE_THREE), -1);
        assertEquals(SCALE_SEVEN.compareTo(SCALE_NINE), -1);
        assertEquals(SCALE_THREE.compareTo(SCALE_ONE), 1);
        assertEquals(SCALE_NINE.compareTo(SCALE_SEVEN), 1);
        assertEquals(SCALE_FIVE.compareTo(SCALE_FIVE), 0);
    }

    @Test
    public void testGetIntegerRepresentation() {
        assertEquals(SCALE_ONE.getIntegerRepresentation(), 1);
        assertEquals(SCALE_TWO.getIntegerRepresentation(), 2);
        assertEquals(SCALE_THREE.getIntegerRepresentation(), 3);
        assertEquals(SCALE_FOUR.getIntegerRepresentation(), 4);
        assertEquals(SCALE_FIVE.getIntegerRepresentation(), 5);
        assertEquals(SCALE_SIX.getIntegerRepresentation(), 6);
        assertEquals(SCALE_SEVEN.getIntegerRepresentation(), 7);
        assertEquals(SCALE_EIGHT.getIntegerRepresentation(), 8);
        assertEquals(SCALE_NINE.getIntegerRepresentation(), 9);
        assertEquals(SCALE_TEN.getIntegerRepresentation(), 10);
    }
    
    @Test
    public void testEquals(){
        Scale scale = new Scale(5);
        boolean works = scale.equals(SCALE_FIVE);
        assert(works);
    }

}
