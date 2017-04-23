package ai.implementation;

import ai.exceptions.OffTheScaleException;

public class Scale {
    private static final Integer MAX_SCALE = 10;
    private static final Integer MIN_SCALE = 0;
    private static final Integer AVG_SCALE = 5;
    private Integer scale;
    
    Scale(Integer scale){
        if (scale > MAX_SCALE || scale < MIN_SCALE) throw new OffTheScaleException();
        
        this.scale = scale;
    }
    
    Scale scaleByDegree(Scale other){
        return new Scale((this.scale * other.scale) / MAX_SCALE);
    }
    
    Scale proportionalDifference(Scale other){
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
}
