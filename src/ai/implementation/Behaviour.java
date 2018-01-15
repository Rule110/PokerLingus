package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Behaviour class
 * Encapsulates a potential auxilliary expression or Behaviour
 * @author Rory Buckley
 *
 */
public class Behaviour {
    private Integer behaviourNumber;
    
    /**
     * Upon Construction generate a random behaviour
     */
    Behaviour(){
        this.behaviourNumber = ThreadLocalRandom.current().nextInt(0, 10);
    }
    
    /**
     * Get Behaviour Number representing this behaviour
     * @return behaviourNumber
     */
    public Integer getBehaviourNumber(){
        return this.behaviourNumber;
    }
}
