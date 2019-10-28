package mosquitomodel;

public class Mosquito {

    // private fields
    private boolean active;
    private float emittedFrequency;

    /**
     * Sets emittedFrequency to the given frequency.
     */
    public void setEmittedFrequencyFrequency(float frequency){
        this.emittedFrequency = frequency;
    }

    /**
     * @return emittedFrequency
     */
    public float getEmittedFrequency(){
        return this.emittedFrequency;
    }

    /**
     * Marks this Mosquito as active.
     */
    public void turnOn(){
        this.active = true;
    }

    /**
     * Marks this Mosquito as inactive.
     */
    public void turnOff(){
        this.active = false;
    }

}
