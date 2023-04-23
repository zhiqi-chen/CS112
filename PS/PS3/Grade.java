package PS.PS3;

/*
 * Grade - a blueprint class for representing a single grade --
 * both its raw score and the associated late penalty (if any).
 */

public class Grade {
    private double rawScore;
    private int latePenalty;   // e.g., 10 for a 10% late penalty
    
    /* 
     * First constructor 
     * inputs: - raw is a non-negative double specifying the raw score
     *         - late is a non-negative integer specifying what 
     *           percentage of the grade should be deducted as a late penalty
     */
    public Grade(double raw, int late) {
        this.setRawScore(raw);
        this.setLatePenalty(late);
    }
    
    /* 
     * Second constructor, which can be used when the late penalty is 0.
     * input: raw is a non-negative double specifying the raw score
     */
    public Grade(double raw) {
        this.setRawScore(raw);
        this.latePenalty = 0;
    }
    
    /*
     * getRawScore - returns the raw score of this Grade object
     */
    public double getRawScore() {
        return this.rawScore;
    }
    
    /*
     * getLatePenlty - returns the late penalty of this Grade object
     */
    public int getLatePenalty() {
        return this.latePenalty;
    }
    
    /*
     * setRawScore - modifies the raw score of this Grade object
     * input: newScore is a non-negative double specifying the new raw score
     */
    public void setRawScore(double newScore) {
        if (newScore < 0.0) {
            throw new IllegalArgumentException();
        }
        
        this.rawScore = newScore;
    }
    
    /*
     * setLatePenalty - modifies the late penalty of this Grade object
     * input: newPenalty is a non-negative integer specifying the 
     *        new late penalty
     */
    public void setLatePenalty(int newPenalty) {
        if (newPenalty < 0) {
            throw new IllegalArgumentException();
        }
        
        this.latePenalty = newPenalty;
    }
    
    /*
     * getAdjustedScore - returns the score that results from applying
     * the late penalty.
     */
    public double getAdjustedScore() {
        double adjustedScore = this.rawScore * (100.0 - this.latePenalty)/100;
        return adjustedScore;
    }
    
    /*
     * equals - compares two Grade objects to see if they are equivalent --
     * i.e., if they have the same adjusted score
     */
    public boolean equals(Grade other) {
        return (other != null 
                    && this.getAdjustedScore() == other.getAdjustedScore());
    }
    
    /*
     * toString - return a string representation of this Grade object
     */
    public String toString() {
        String str = "" + this.rawScore;
        
        if (this.latePenalty != 0) {
            str += " (" + this.getAdjustedScore() + " after applying a ";
            str += this.latePenalty + "% late penalty)";
        }
        
        return str;
    }
}
