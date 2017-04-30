package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Personality class representing AI personality
 * Personality Traits are represented by ten point Scale classes
 * @author Rory Buckley
 *
 */
public class Personality {
    private Scale bluffingAbility;
    private Scale riskAversion;
    private Scale jitteriness;
    private Behaviour tell;
    
    /**
     * Loads up a persistently stored personality
     * @param persistentStoredPersonality
     */
    Personality(Personality persistentStoredPersonality){
        this.bluffingAbility = persistentStoredPersonality.bluffingAbility;
        this.riskAversion = persistentStoredPersonality.riskAversion;
        this.jitteriness = persistentStoredPersonality.jitteriness;
        this.tell = persistentStoredPersonality.tell;
    }
    
    /**
     * Generates Personality with predefined traits on scales
     * Public access for testing purposes
     * @param bluffingAbility
     * @param riskAversion
     * @param jitteriness
     * @param tell
     */
    public Personality(Scale bluffingAbility, Scale riskAversion, Scale jitteriness, Behaviour tell){
        this.bluffingAbility = bluffingAbility;
        this.riskAversion = riskAversion;
        this.jitteriness = jitteriness;
        this.tell = tell;
    }
    
    /**
     * Generates a new random personality
     */
    Personality(){
        this.bluffingAbility = new Scale();
        this.riskAversion = new Scale();
        this.jitteriness = new Scale();
        this.tell = new Behaviour();
    }
    
    /**
     * Get the degree of Bluffed Confidence on a scale
     *  based on the objective input confidence
     *  and the personality trait bluffingAbility
     * @param confidence
     * @return bluffedConfidence
     */
    public Scale getBluffedConfidence(Scale confidence){
        Scale bluffedConfidence = confidence.regressToMeanByDegree(this.bluffingAbility);
        return bluffedConfidence;
    }
    
    /**
     * Get the degree of Perceived Risk on a scale
     *  based on the objective input risk
     *  and the personality trait riskAversion
     * @param risk
     * @return
     */
    public Scale getPerceivedRisk(Scale risk){
        Scale perceivedRisk = risk.scaleByDegree(this.riskAversion);
        return perceivedRisk;
    }
    
    /**
     * Get the Behaviour
     * Personality trait jitteriness determines the likelihood that
     *  this will be a Tell rather than normally distributed baseline behaviour
     *  Repetitive behaviours are likely to be tells rather than normal behaviour
     * @return
     */
    public Behaviour getBehaviour(Scale bluffingDegree){
        Behaviour behaviour;
        Scale likelihoodOfTell = this.jitteriness.scaleByDegree(bluffingDegree);
        Scale random = new Scale(ThreadLocalRandom.current().nextInt(1, 11));
        if (random.compareTo(likelihoodOfTell) < 0){
            behaviour = this.tell;
        }
        else {
            behaviour = new Behaviour();
        }
        return behaviour;
    }
    
    /**
     * Get the Behaviour in the case of no Bluffing
     * If there is no Bluffing then the jitteriness would be scaled by degree 0
     * The Likelihood of a Tell then would also be 0
     * The behaviour will then be just a randomly generated behaviour
     * @return
     */
    public Behaviour getBehaviour(){
        return new Behaviour();
    }
}
