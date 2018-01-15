package ai.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ai.implementation.Personality;
import ai.implementation.Scale;

public class PersonalityTest {
    private Personality persona1;
    private Personality persona2;
    private Personality persona3;

    @Before
    public void setUp() throws Exception {
        Scale bluffingAbility1 = new Scale(9);
        Scale riskAversion1 = new Scale(9);
        Scale jitteriness1 = new Scale(9);
        this.persona1 = new Personality(bluffingAbility1, 
                riskAversion1, jitteriness1);
        
        
        Scale bluffingAbility2 = new Scale(2);
        Scale riskAversion2 = new Scale(2);
        Scale jitteriness2 = new Scale(2);
        this.persona2 = new Personality(bluffingAbility2, 
                riskAversion2, jitteriness2);
        
        Scale bluffingAbility3 = new Scale(5);
        Scale riskAversion3 = new Scale(5);
        Scale jitteriness3 = new Scale(5);
        this.persona3 = new Personality(bluffingAbility3, 
                riskAversion3, jitteriness3);
    }

    @Test
    public void testGetBluffedConfidence() {
        Scale confidence = new Scale(10);
        Scale bluffedConfidence1 = this.persona1.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence1, new Scale(6));
        
        Scale bluffedConfidence2 = this.persona2.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence2, new Scale(9));
        
        Scale bluffedConfidence3 = this.persona3.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence3, new Scale(8));
        

        confidence = new Scale(1);
        bluffedConfidence1 = this.persona1.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence1, new Scale(4));
        
        bluffedConfidence2 = this.persona2.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence2, new Scale(1));
        
        bluffedConfidence3 = this.persona3.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence3, new Scale(3));
        

        confidence = new Scale(8);
        bluffedConfidence1 = this.persona1.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence1, new Scale(6));
        
        bluffedConfidence2 = this.persona2.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence2, new Scale(8));
        
        bluffedConfidence3 = this.persona3.getBluffedConfidence(confidence);
        assertEquals(bluffedConfidence3, new Scale(7));
    }

    @Test
    public void testGetPerceivedRisk() {
        Scale risk = new Scale(10);
        Scale perceivedRisk1 = this.persona1.getPerceivedRisk(risk);
        assertEquals(perceivedRisk1, new Scale(9));
        
        Scale perceivedRisk2 = this.persona2.getPerceivedRisk(risk);
        assertEquals(perceivedRisk2, new Scale(2));
        
        Scale perceivedRisk3 = this.persona3.getPerceivedRisk(risk);
        assertEquals(perceivedRisk3, new Scale(5));
        
        risk = new Scale(5);
        perceivedRisk1 = this.persona1.getPerceivedRisk(risk);
        assertEquals(perceivedRisk1, new Scale(4));
        
        perceivedRisk2 = this.persona2.getPerceivedRisk(risk);
        assertEquals(perceivedRisk2, new Scale(1));
        
        perceivedRisk3 = this.persona3.getPerceivedRisk(risk);
        assertEquals(perceivedRisk3, new Scale(2));
        
        risk = new Scale(8);
        perceivedRisk1 = this.persona1.getPerceivedRisk(risk);
        assertEquals(perceivedRisk1, new Scale(7));
        
        perceivedRisk2 = this.persona2.getPerceivedRisk(risk);
        assertEquals(perceivedRisk2, new Scale(1));
        
        perceivedRisk3 = this.persona3.getPerceivedRisk(risk);
        assertEquals(perceivedRisk3, new Scale(4));
    }

}
