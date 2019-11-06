package mosquitomodel;

public class ParkVisitorBiology {

    // the age of the ParkVisitor
    private int age;

    // the highest frequency that can be heard by this ParkVisitor
    private int maxFrequencyHearable;


    /**
     * Constructor for ParkVisitorBiology that gives a value of zero to the initial aversion to visiting the park.
     * @param age the age of the ParkVisitor
     * @param maxFrequencyHearable the highest frequency that can be heard by this ParkVisitor
     */
    public ParkVisitorBiology(int age, int maxFrequencyHearable){
        this.age = age;
        this.maxFrequencyHearable = maxFrequencyHearable;
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

}
