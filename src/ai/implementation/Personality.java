package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

public class Personality {
    private Scale bluffingAbility;
    private Scale riskAversion;
    private Scale jitteriness;
    private Tell tell;
    
    Personality(String type){
        switch (type){
        case "Simple":
            break;
        case "StaticRandom":
            break;
        case "StaticPersistent":
            break;
        case "DynamicPersistent":
            break;
        default:
            throw new RuntimeException("Personality Type not recognised!");
        }
    }
    
    public Scale getBluffedConfidence(Scale confidence){
        Scale bluffedConfidence = confidence.regressToMeanByDegree(this.bluffingAbility) ;
        return bluffedConfidence;
    }
    
    public Scale getPerceivedRisk(Scale risk){
        Scale perceivedRisk = risk.scaleByDegree(this.riskAversion);
        return perceivedRisk;
    }
    
    public Behaviour getBehaviour(){
        Behaviour behaviour;
        Scale random = new Scale(ThreadLocalRandom.current().nextInt(0, 11));
        if (random.compareTo(jitteriness) < 0){
            behaviour = tell;
        }
        else {
            behaviour = new Behaviour();
        }
        return behaviour;
    }
}
