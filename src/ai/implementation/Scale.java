package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

import ai.exceptions.OffTheScaleException;

public class Scale {
    static final Integer MAX_SCALE = 10;
    static final Integer MIN_SCALE = 0;
    static final Integer AVG_SCALE = 5;
    private Integer scale;
    
    /**
     * Sets Scale to random Integer between MAX_SCALE and MIN_SCALE
     * Gaussian Distributed around AVG_SCALE
     */
    Scale(){
        Double gaussianDistributed = ThreadLocalRandom.current().nextGaussian();
        Double sd = AVG_SCALE / 2.0;
        this.scale = (int)((gaussianDistributed * sd) + AVG_SCALE);
        
        if (this.scale < MIN_SCALE) this.scale = MIN_SCALE;
        else if (this.scale > MAX_SCALE) this.scale = MAX_SCALE;
    }
    
    /**
     * Sets Scale to integer
     * @param scale
     */
    Scale(Integer scale){
        if (scale > MAX_SCALE || scale < MIN_SCALE) throw new OffTheScaleException();
        
        this.scale = scale;
    }
    
    Scale scaleByDegree(Scale other){
        return new Scale((this.scale * other.scale) / MAX_SCALE);
    }
    
    Scale differenceOnScale(Scale other){
        Integer difference = this.scale - other.scale;
        if (difference < 0){
            difference = 0;
        }
        Integer residual = MAX_SCALE - other.scale;
        Scale proportionalDifference = new Scale(difference / residual);
        return proportionalDifference;
    }
    
    Scale regressToMeanByDegree(Scale regressionDegree){
        Integer distanceToAvg = AVG_SCALE - this.scale;
        Integer regressionAmount = (distanceToAvg * regressionDegree.scale) / MAX_SCALE;
        Scale regressedToMean = new Scale(this.scale + regressionAmount);
        return regressedToMean;
    }
    
    int compareTo(Scale other){
        return this.scale.compareTo(other.scale);
    }
    
    Integer scale(Integer toScale){
        return (toScale * this.scale) / MAX_SCALE;
    }
    
    Scale min(Scale other){
        return this.scale < other.scale ? this : other;
    }
}
