package mosquitomodel;

public class Mosquito {

    // whether or not this Mosquito unit is currently emitting a frequency
    private boolean active;

    // the frequency at which this Mosquito emits
    private float emittedFrequency;

    /**
     * Constructor for Mosqutio that initializes its active state to be false.
     * @param emittedFrequency the frequency this Mosquito emits at
     */
    public Mosquito(float emittedFrequency){
        this.active = false;
        this.emittedFrequency = emittedFrequency;
    }

    /**
     * Sets emittedFrequency to the given frequency.
     */
    public void setEmittedFrequencyFrequency(float frequency){
        this.emittedFrequency = frequency;
    }

    /**
     * @return emittedFrequency
     */
    public float getEmittedFrequency(){ return this.emittedFrequency; }

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

    /**
     * @return whether this Mosquito is active or not
     */
    public boolean isActive(){
        return this.active;
    }

}
