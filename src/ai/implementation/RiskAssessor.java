package ai.implementation;

import player.implementation.RoundState;

public class RiskAssessor {
    public static Scale assessRisk(RoundState roundState){
        Integer chips = roundState.getChips();
        Integer callValue = roundState.getCallValue();
        Scale risk = new Scale((int)((callValue * 10.0) / (double)chips));
        return risk;
    }
}
