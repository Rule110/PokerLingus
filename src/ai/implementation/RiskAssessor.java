package ai.implementation;

import player.implementation.RoundState;

public class RiskAssessor {
    public static Scale assessRisk(RoundState roundState){
        Integer chips = roundState.getChips();
        Integer callValue = roundState.getCallValue();
        Double ratio = (double)callValue / (double)chips;
        Integer scale = (int)(ratio * 10);
        Scale risk = new Scale(scale);
        return risk;
    }
}
