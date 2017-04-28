package ai.implementation;

/**
 * Strategy class
 * Encapsulates the AI decisions and Strategy formulating methods
 * @author Rory Buckley
 *
 */
public class Strategy {
    private boolean isFolding;
    private boolean isCalling;
    private boolean isRaising;
    private int raiseAmount;
    private Scale bluffedConfidence;
    private Behaviour behaviour;
    
    Strategy(Scale confidence, Scale risk, Scale reward, Personality personality, Integer raisePool){
        this.formulateStrategy(confidence, risk, reward, personality, raisePool);
    }
    
    /**
     * Formulate Strategy based on
     *  degrees of confidence, risk, reward
     *  and the AI personality
     *  and the number of chips available to raise
     * @param confidence
     * @param risk
     * @param reward
     * @param personality
     * @param raisePool
     */
    private void formulateStrategy(Scale confidence, Scale risk, Scale reward, Personality personality, Integer raisePool){
        
        Scale perceivedRisk = Strategy.getPerceivedRisk(personality, risk);
        Scale confidenceScaledReward = Strategy.getConfidenceScaledReward(confidence, reward);
        this.setDecisions(confidenceScaledReward, perceivedRisk);
        
        if (this.isRaising){
            
            Scale raiseConfidence = Strategy.getRaiseConfidence(confidenceScaledReward, perceivedRisk);
            this.setRaiseAmount(raiseConfidence, personality, raisePool);
            
            Scale bluffingDegree = new Scale(Math.abs(this.bluffedConfidence.differenceAsInteger(confidence)));
            this.behaviour = personality.getBehaviour(bluffingDegree);
        }
    }
    
    /**
     * Get Perceived Risk
     * @param personality
     * @param risk
     * @return perceivedRisk
     */
    private static Scale getPerceivedRisk(Personality personality, Scale risk){
        return personality.getPerceivedRisk(risk);
    }
    
    /**
     * Get Confidence Scaled Reward
     * This is the degree of Reward scaled by the objective Confidence in the Hand 
     * @param confidence
     * @param reward
     * @return confidenceScaledReward
     */
    private static Scale getConfidenceScaledReward(Scale confidence, Scale reward){
        Scale confidenceScaledReward = reward.scaleByDegree(confidence);
        return confidenceScaledReward;
    }
    
    /**
     * Set the decisions based on confidenceScaledReward and perceivedRisk
     * @param confidenceScaledReward
     * @param perceivedRisk
     */
    private void setDecisions(Scale confidenceScaledReward, Scale perceivedRisk){
        
        if (confidenceScaledReward.compareTo(perceivedRisk) < 0){
            this.isFolding = true;
        }
        else if (confidenceScaledReward.compareTo(perceivedRisk) == 0){
            this.isCalling = true;
        }
        else {
            this.isRaising = true;
        }
    }
    
    /**
     * Get Raise Confidence
     * Raise Confidence is the difference between the confidenceScaledReward and the Risk
     * It is expressed as a degree of difference on a scale itself
     * Raise Confidence is the excess confidence one has after meeting the current bet
     * @param confidenceScaledReward
     * @param perceivedRisk
     * @return
     */
    private static Scale getRaiseConfidence(Scale confidenceScaledReward, Scale perceivedRisk){
        return confidenceScaledReward.differenceOnScale(perceivedRisk);
    }
    
    /**
     * Set Raise Amount
     * This sets the Raise Amount based on the raiseConfidence applied to the raisePool
     * Raise Confidence is a degree on a scale that determines ...
     *  the proportion of the current chips left (raisePool) that will be used to raise.
     * The raiseConfidence is first obfuscated by the degree of the AI Personality's bluffingAbility
     * @param raiseConfidence
     * @param personality
     * @param raisePool
     */
    private void setRaiseAmount(Scale raiseConfidence, Personality personality, Integer raisePool){
        
        this.bluffedConfidence = personality.getBluffedConfidence(raiseConfidence);
        this.raiseAmount = this.bluffedConfidence.scaleThat(raisePool);
    }
    
    /**
     * Decision to Fold
     * Set by setDecisions()
     * @return true if AI is to fold, else false
     */
    public boolean isFolding(){
        return this.isFolding;
    }
    
    /**
     * Decision to Call
     * Set by setDecisions() if not isFolding
     * @return true if AI is to call, else false
     */
    public boolean isCalling(){
        return this.isCalling;
    }
    
    /**
     * Decision to Raise
     * Set by setDecisions() if not isCalling
     * @return true if AI is to raise, else false
     */
    public boolean isRaising(){
        return this.isRaising;
    }
    
    /**
     * Get Raise Amount
     * Set by setDecisions() if isRaising
     * @return raiseAmount
     */
    public int getRaiseAmount(){
        return this.raiseAmount;
    }
    
    /**
     * Gets the calculated degree of bluffedConfidence for this Strategy
     *  used by Complex AI to express bluffedConfidence
     * @return
     */
    public Scale getBluffedConfidence(){
        return this.bluffedConfidence;
    }
    
    /**
     * Gets the behaviour as this strategy is being deployed
     * Behaviour may be a tell or not depending on personality and ...
     * ...the level of bluffing
     * @return
     */
    public Behaviour getBehaviour(){
        return this.behaviour;
    }
}
