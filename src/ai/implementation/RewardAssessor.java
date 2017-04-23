package ai.implementation;

import player.implementation.RoundState;

public class RewardAssessor {
    public static Scale assessReward(RoundState roundState){
        Scale reward = new Scale((int)((roundState.getChips() * 10.0) / (double)roundState.getPotValue()));
        return reward;
    }
}
