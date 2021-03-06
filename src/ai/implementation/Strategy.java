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
    
    public Strategy(Scale confidence, Scale risk, Scale reward, Personality personality, Integer raisePool){
        this.formulateStrategy(confidence, risk, reward, personality, raisePool);
    }
    
    /**
     * Default constructor used to default to Fold
     */
    public Strategy(){
        this.isFolding = true;
        this.isCalling = false;
        this.isRaising = false;
        this.raiseAmount = 0;
        this.bluffedConfidence = new Scale(1);
        this.behaviour = new Behaviour();
    }
    
    /**
     * Constructor used to default to Call
     * @param callFlag
     */
    public Strategy(boolean call){
        this.isFolding = !call;
        this.isCalling = call;
        this.isRaising = false;
        this.raiseAmount = 0;
        this.bluffedConfidence = new Scale(1);
        this.behaviour = new Behaviour();
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
        Scale rewardBoostedConfidence = Strategy.getRewardBoostedConfidence(confidence, reward);
        this.setDecisions(rewardBoostedConfidence, perceivedRisk);
        
        if (this.isRaising){
            
            Scale raiseConfidence = Strategy.getRaiseConfidence(rewardBoostedConfidence, perceivedRisk);
            this.bluffedConfidence = personality.getBluffedConfidence(raiseConfidence);
            this.setRaiseAmount(personality, raisePool);
            
            Scale bluffingDegree = new Scale(Math.abs(this.bluffedConfidence.differenceAsInteger(confidence)));
            this.behaviour = personality.getBehaviour(bluffingDegree);
        }
        else {
            this.bluffedConfidence = confidence;
            this.behaviour = personality.getBehaviour();
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
     * Get Reward Boosted Confidence
     * Confidence Boosted by potential Reward
     * @param confidence
     * @param reward
     * @return rewardBoostedConfidence
     */
    private static Scale getRewardBoostedConfidence(Scale confidence, Scale reward){
        Scale rewardBoostedConfidence = confidence.add(reward);
        return rewardBoostedConfidence;
    }
    
    /**
     * Set the decisions based on rewardBoostedConfidence and perceivedRisk
     * @param rewardBoostedConfidence
     * @param perceivedRisk
     */
    private void setDecisions(Scale rewardBoostedConfidence, Scale perceivedRisk){
        
        if (rewardBoostedConfidence.compareTo(perceivedRisk) < 0){
            this.isFolding = true;
        }
        else if (rewardBoostedConfidence.compareTo(perceivedRisk) == 0){
            this.isCalling = true;
        }
        else {
            this.isRaising = true;
        }
    }
    
    /**
     * Get Raise Confidence
     * Raise Confidence is the difference between the rewardBoostedConfidence and the Risk
     * It is expressed as a degree of difference on a scale itself
     * Raise Confidence is the excess confidence one has after meeting the current bet
     * @param rewardBoostedConfidence
     * @param perceivedRisk
     * @return
     */
    private static Scale getRaiseConfidence(Scale rewardBoostedConfidence, Scale perceivedRisk){
        return rewardBoostedConfidence.differenceOnScale(perceivedRisk);
    }
    
    /**
     * Set Raise Amount
     * This sets the Raise Amount based on the bluffedConfidence applied to the raisePool
     * Bluffed Confidence is the raiseConfidence obfuscated by the degree of the AI Personality's bluffingAbility
     * Raise Confidence is a degree on a scale that determines ...
     *  the proportion of the current chips left (raisePool) that will be used to raise.
     * @param personality
     * @param raisePool
     */
    private void setRaiseAmount(Personality personality, Integer raisePool){
        this.raiseAmount = 1 + this.bluffedConfidence.scaleThat(raisePool);
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
