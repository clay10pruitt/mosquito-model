package agentbasedmodel;

public class Time {

    // the current hour (between 0 (inclusive) and 24 (exclusive))
    private int hour;
    // the current minute (between 0 (inclusive) and 60 (exclusive))
    private int minute;

    // the values for which hour and minute will, upon reaching, revert to zero
    private int maxHour = 24;
    private int maxMinute = 60;

    /**
     * Constructor for Time.
     * @param hour initial hour
     * @param minute initial minute
     */
    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Advances the time by the given amount and ensures that we stay below the maximum time.
     * @param hours amount of hours to advance by
     * @param minutes amount of minutes to advance by
     */
    public void advanceTime(int hours, int minutes){
        hour = ((hour + hours) % maxHour);
        minute = ((minute + minutes) % maxMinute);
    }

    /**
     * Adds the given Time to this Time.
     * @param time the Time to add to this Time
     */
    public void add(Time time){
        this.advanceTime(time.getHour(), time.getMinute());
    }

    /**
     * @return hour
     */
    public int getHour(){
        return this.hour;
    }

    /**
     * @return minute
     */
    public int getMinute() { return this.minute; }

}
