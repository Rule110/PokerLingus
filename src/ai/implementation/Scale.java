package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Scale class represents the degree of certainty on a scale
 * Can be objective or subjective certainty
 * Scale is 1 to 10 in this implementation
 * @author Rory Buckley
 *
 */
public class Scale {
    static final Integer MAX_SCALE = 10;
    static final Integer MIN_SCALE = 1;
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
        if (scale > Scale.MAX_SCALE){
            this.scale = Scale.MAX_SCALE;
        }
        else if (scale < Scale.MIN_SCALE){
            this.scale = Scale.MIN_SCALE;
        }
        else {
            this.scale = scale;
        }
    }
    
    /**
     * Scale other scale by this scale
     * @param other
     * @return scaled scale
     */
    Scale scaleByDegree(Scale other){
        return new Scale((this.scale * other.scale) / MAX_SCALE);
    }
    
    /**
     * Scale integer by this scale
     * @param toScale
     * @return scaled integer
     */
    Integer scaleThat(Integer toScale){
        Double proportion = (double)this.scale / (double)MAX_SCALE;
        return (int)(toScale * proportion);
    }
    
    /**
     * Represent the difference between two Scales as another scale
     * @param other
     * @return scale representing difference
     */
    Scale differenceOnScale(Scale other){
        Integer difference = this.scale - other.scale;
        if (difference < 0){
            difference = 0;
        }
        Integer residual = MAX_SCALE - other.scale;
        Scale proportionalDifference = new Scale(difference / residual);
        return proportionalDifference;
    }
    
    /**
     * Difference between two scales returned as an integer
     * @param other
     * @return
     */
    Integer differenceAsInteger(Scale other){
        return this.scale - other.scale;
    }
    
    /**
     * Regress a scale to its mean to the degree specified by another scale
     * @param regressionDegree
     * @return scale regressed to mean by degree of input scale
     */
    Scale regressToMeanByDegree(Scale regressionDegree){
        Integer distanceToAvg = AVG_SCALE - this.scale;
        Integer regressionAmount = (distanceToAvg * regressionDegree.scale) / MAX_SCALE;
        Scale regressedToMean = new Scale(this.scale + regressionAmount);
        return regressedToMean;
    }
    
    /**
     * Compares two scales with each other
     * @param other
     * @return 0 if equals,
     *  less than 0 if this scale is greater than other,
     *  greater than 0 if this scale is smaller than other
     */
    int compareTo(Scale other){
        return this.scale.compareTo(other.scale);
    }
    
    int getIntegerRepresentation(){
        return this.scale;
    }
}
