package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

public class Personality {
    private Scale bluffingAbility;
    private Scale riskAversion;
    private Scale jitteriness;
    private Tell tell;
    
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
     * Generates a new random personality
     */
    Personality(){
        this.bluffingAbility = new Scale();
        this.riskAversion = new Scale();
        this.jitteriness = new Scale();
        this.tell = new Tell();
    }
    
    public Scale getBluffedConfidence(Scale confidence){
        Scale bluffedConfidence = confidence.regressToMeanByDegree(this.bluffingAbility);
        return bluffedConfidence;
    }
    
    public Scale getPerceivedRisk(Scale risk){
        Scale perceivedRisk = risk.scaleByDegree(this.riskAversion);
        return perceivedRisk;
    }
    
    public Behaviour getBehaviour(){
        Behaviour behaviour;
        Scale random = new Scale(ThreadLocalRandom.current().nextInt(0, 11));
        if (random.compareTo(this.jitteriness) < 0){
            behaviour = this.tell;
        }
        else {
            behaviour = new Behaviour();
        }
        return behaviour;
    }
}
