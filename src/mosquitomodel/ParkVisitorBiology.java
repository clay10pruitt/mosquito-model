package mosquitomodel;

public class ParkVisitorBiology {

    // the age of the ParkVisitor
    private int age;

    // the highest frequency that can be heard by this ParkVisitor
    private float maxFrequencyHearable;

    // the highest frequency that this ParkVisitor will tolerate
    private float maxFrequencyTolerated;

    // the percent tolerance to the max tolerated frequency with 0% = no tolerance and 100% = complete tolerance
    private float toleranceToMaxFrequency;

    // the percent aversion to visiting the park with 0% = will always visit park and 100% = will never visit park
    private float aversionToPark;

    /**
     * Constructor for ParkVisitorBiology that gives a value of zero to the initial aversion to visiting the park.
     * @param age the age of the ParkVisitor
     * @param maxFrequencyHearable the highest frequency that can be heard by this ParkVisitor
     * @param maxFrequencyTolerated the highest frequency this ParkVisitor will tolerate
     * @param toleranceToMaxFrequency the percent tolerance this ParkVisitor has to the max tolerated frequency
     */
    public ParkVisitorBiology(int age, float maxFrequencyHearable, float maxFrequencyTolerated, float toleranceToMaxFrequency){
        this.age = age;
        this.maxFrequencyHearable = maxFrequencyHearable;
        this.maxFrequencyTolerated = maxFrequencyTolerated;
        this.toleranceToMaxFrequency = toleranceToMaxFrequency;
        this.aversionToPark = 0;
    }

    /**
     * @return age
     */
    public int getAge(){
        return this.age;
    }

    /**
     * @return hearingLoss
     */
    public float getMaxFrequencyHearable(){
        return this.maxFrequencyHearable;
    }

    /**
     * @return maxFrequencyTolerated
     */
    public float getMaxFrequencyTolerated(){
        return this.maxFrequencyTolerated;
    }

    /**
     * @return toleranceToMaxFrequency
     */
    public float getToleranceToMaxFrequency() {
        return this.toleranceToMaxFrequency;
    }

    /**
     * @return aversionToPark
     */
    public float getAversionToPark(){
        return this.aversionToPark;
    }

    /**
     * Sets aversionToPark.
     * @param aversionToPark new value of aversionToPark
     */
    public void setAversionToPark(float aversionToPark) { this.aversionToPark = aversionToPark; }
}
