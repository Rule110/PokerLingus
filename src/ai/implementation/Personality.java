package ai.implementation;

public class Personality {
    private BluffingAbility bluffingAbility;
    private RiskAversion riskAversion;
    private Jitteriness jitteriness;
    private Tell tell;
    
    public Strategy effectStrategy(RoundState roundstate, HandConfidence confidence){
        
        return null;
    }
    
    public ExpressedConfidence bluffConfidence(HandConfidence confidence){
        
        return null;
    }
    
    public Behaviour leakBehaviour(ExpressedConfidence expressedconfidence, HandConfidence confidence){
        
        return null;
    }
}
