package ai.implementation;

import player.implementation.RoundState;

public class RewardAssessor {
    public static Scale assessReward(RoundState roundState){
        Integer potValue = roundState.getPotValue();
        Integer chips = roundState.getChips();
        Double ratio = (double)potValue / (double)chips;
        Integer scale = (int)(ratio * 10);
        Scale reward = new Scale(scale);
        return reward;
    }
}
