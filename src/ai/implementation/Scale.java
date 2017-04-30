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
        int scale = (int)((gaussianDistributed * sd) + AVG_SCALE);
        this.setScale(scale);
    }
    
    /**
     * Sets Scale to integer
     * @param scale
     */
    public Scale(Integer scale){
        this.setScale(scale);
    }
    
    /**
     * Sets scale to between MAX_SCALE and MIN_SCALE
     * Doesn't allow scale to exceed these bounds
     * @param scale
     */
    private void setScale(Integer scale){
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
    public Scale scaleByDegree(Scale other){
        return new Scale((this.scale * other.scale) / MAX_SCALE);
    }
    
    /**
     * Scale integer by this scale
     * @param toScale
     * @return scaled integer
     */
    public Integer scaleThat(Integer toScale){
        Double proportion = (double)this.scale / (double)MAX_SCALE;
        return (int)(toScale * proportion);
    }
    
    /**
     * Represent the difference between two Scales as another scale
     * @param other
     * @return scale representing difference
     */
    public Scale differenceOnScale(Scale other){
        Integer difference = this.scale - other.scale;
        if (difference < 0){
            difference = 0;
        }
        Integer residual = MAX_SCALE - other.scale;
        double differenceRatioResidual = (double)difference / (double)residual;
        int proportionalDifferenceOnScale = (int)(differenceRatioResidual * MAX_SCALE);
        Scale proportionalDifference = new Scale(proportionalDifferenceOnScale);
        return proportionalDifference;
    }
    
    /**
     * Difference between two scales returned as an integer
     * @param other
     * @return
     */
    public Integer differenceAsInteger(Scale other){
        return this.scale - other.scale;
    }
    
    /**
     * Regress a scale to its mean to the degree specified by another scale
     * @param regressionDegree
     * @return scale regressed to mean by degree of input scale
     */
    public Scale regressToMeanByDegree(Scale regressionDegree){
        Integer distanceToAvg = AVG_SCALE - this.scale;
        Integer regressionAmount = (distanceToAvg * regressionDegree.scale) / MAX_SCALE;
        Scale regressedToMean = new Scale(this.scale + regressionAmount);
        return regressedToMean;
    }
    
    /**
     * Add two scales together
     * @param other
     * @return sum on Scale
     */
    public Scale add(Scale other){
        Integer sumScale = this.scale + other.scale;
        return new Scale(sumScale);
    }
    
    /**
     * Compares two scales with each other
     * @param other
     * @return 0 if equals,
     *  less than 0 if this scale is greater than other,
     *  greater than 0 if this scale is smaller than other
     */
    public int compareTo(Scale other){
        return this.scale.compareTo(other.scale);
    }
    
    /**
     * Get Integer representation of Scale
     * @return
     */
    public int getIntegerRepresentation(){
        return this.scale;
    }
    
    /**
     * Equality testing method
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object o){
        boolean equals;
        if (o == null){
            equals = false;
        }
        else if (!Scale.class.isAssignableFrom(o.getClass())){
            equals = false;
        
        }
        else {
            final Scale other = (Scale) o;
            if (this.scale.equals(other.scale)){
                equals = true;
            }
            else{
                equals = false;
            }
        }
        return equals;
    }
}
