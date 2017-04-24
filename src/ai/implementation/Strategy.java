package ai.implementation;


public class Strategy {
    private boolean isFolding;
    private boolean isCalling;
    private boolean isRaising;
    private int raiseAmount;
    private Scale bluffedConfidence;
    
    Strategy(Scale confidence, Scale risk, Scale reward, Personality personality, Integer raisePool){
        
        Scale perceivedRisk = personality.getPerceivedRisk(risk);
        
        Scale rewardCappedConfidence = confidence.min(reward);
        
        this.setDecisions(rewardCappedConfidence, perceivedRisk);
        
        if (this.isRaising){
            
            Scale raiseConfidence = rewardCappedConfidence.differenceOnScale(perceivedRisk);
            
            this.setRaiseAmount(raiseConfidence, personality, raisePool);
        }
    }
    
    private void setDecisions(Scale confidence, Scale perceivedRisk){
        
        if (confidence.compareTo(perceivedRisk) < 0){
            this.isFolding = true;
        }
        else if (confidence.compareTo(perceivedRisk) == 0){
            this.isCalling = true;
        }
        else {
            this.isRaising = true;
        }
    }
    
    private void setRaiseAmount(Scale raiseConfidence, Personality personality, Integer raisePool){
        
        this.bluffedConfidence = personality.getBluffedConfidence(raiseConfidence);
        this.raiseAmount = this.bluffedConfidence.scale(raisePool);
    }
    
    public boolean isFolding(){
        return this.isFolding;
    }
    
    public boolean isCalling(){
        return this.isCalling;
    }
    
    public boolean isRaising(){
        return this.isRaising;
    }
    
    public int getRaiseAmount(){
        return this.raiseAmount;
    }
    
    public Scale getBluffedConfidence(){
        return this.bluffedConfidence;
    }
}
