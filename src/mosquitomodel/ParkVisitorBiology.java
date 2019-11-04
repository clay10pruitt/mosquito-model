package mosquitomodel;

public class ParkVisitorBiology {

    // the age of the ParkVisitor
    private int age;

    // the highest frequency that can be heard by this ParkVisitor
    private float maxFrequencyHearable;

    // the percent aversion to visiting the park with 0% = will always visit park and 100% = will never visit park
    private float aversionToPark;

    /**
     * Constructor for ParkVisitorBiology that gives a value of zero to the initial aversion to visiting the park.
     * @param age the age of the ParkVisitor
     * @param maxFrequencyHearable the highest frequency that can be heard by this ParkVisitor
     */
    public ParkVisitorBiology(int age, float maxFrequencyHearable){
        this.age = age;
        this.maxFrequencyHearable = maxFrequencyHearable;
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
