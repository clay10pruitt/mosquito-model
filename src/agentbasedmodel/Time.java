package agentbasedmodel;

public class Time implements Comparable<Time> {

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
     * Constructor for Time that constructs a new Time object from a string in HH:MM format.
     * @param arg String in HH:MM form
     */
    public Time(String arg){
        String[] args = arg.split(":");
        this.hour = Integer.parseInt(args[0]);
        this.minute = Integer.parseInt(args[1]);
    }

    /**
     * Advances the time by the given amount and ensures that we stay below the maximum time.
     * @param hours amount of hours to advance by
     * @param minutes amount of minutes to advance by
     */
    public void advanceTime(int hours, int minutes){

        // 60 min = 1 hour
        hours += minutes / maxMinute;
        minutes = minutes % maxMinute;
        hours += (minute + minutes) / 60;

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
     * Checks if this Time is between the two given Times.
     * @param time01 first Time
     * @param time02 second Time
     * @param inclusive whether the check will be inclusive of time02 and time02
     * @return boolean
     */
    public boolean isBetween(Time time01, Time time02, boolean inclusive){

        // determine the greater and lesser times
        Time greater = null;
        Time lesser = null;
        switch (time01.compareTo(time02)){
            case -1:
                greater = time02;
                lesser = time01;
                break;
            case 1:
                greater = time01;
                lesser = time02;
                break;
            case 0:
                /*
                If both time01 and time02 are equal, then this Time can only be between both of them if
                    1) inclusive = true
                    2) This Time equals time01 or time02 (as if it equals one it must equal both).
                 */
                if (inclusive){
                    return (this.equals(time01));
                } else {
                    return false;
                }
        }

        // determine if this Time object is greater than the greater Time
        switch (this.compareTo(greater)){
            case 1:
                // this Time is greater than the greater Time
                return false;
            case 0:
                // if we are not inclusive, then this Time is not less than the greater Time
                if (!inclusive){
                    return false;
                }
                break;
        }

        // determine if this Time object is less than the lesser Time
        switch (this.compareTo(lesser)){
            case -1:
                // this Time is less than the lesser Time
                return false;
            case 0:
                // if we are not inclusive, then this Time is not greater than the lesser Time
                if (!inclusive){
                    return false;
                }
                break;
        }

        // this Time must be in-between the lesser and greater Times
        return true;
    }

    /**
     * Overload for isBetween where inclusive defaults to true.
     */
    public boolean isBetween(Time time01, Time time02){
        return this.isBetween(time01, time02, true);
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

    /**
     * Two Times are equal if they have the same values for their hour and minute fields.
     * @param obj an Object to compare equality to
     * @return whether thi Time equals the given object
     */
    @Override
    public boolean equals(Object obj) {
        // a Time object will always equal itself
        if (obj == this){
            return true;
        }

        // a Time object cannot equal a non-Time objecet
        if (!(obj instanceof Time)){
            return false;
        }

        // two Times are equivalent if they have the same hour and minute values
        Time t = (Time) obj;
        return ((this.hour == t.hour) && (this.minute == t.minute));
    }

    @Override
    public String toString() {

        String hourText = "";
        String minuteText = "";

        if (this.hour < 10){
            hourText = "0" + this.hour;
        } else {
            hourText += this.hour;
        }

        if (this.minute < 10){
            minuteText = "0" + this.minute;
        } else {
            minuteText += this.minute;
        }

        return hourText + ":" + minuteText;
    }

    /**
     * Compares this Time object to another.
     * @param o another Time Object
     * @return -1 if this Time is less than the other Time, 0 if they are equal, and 1 if this Time is greater
     */
    @Override
    public int compareTo(Time o) {
        // compare hours
        if (this.hour < o.hour){
            return -1;
        } else if (this.hour > o.hour){
            return 1;
        }

        // hours are equivalent; check minutes
        if (this.minute < o.minute){
            return -1;
        } else if (this.minute > o.minute){
            return 1;
        }

        // hours and minutes are equivalents; both Times are equivalent
        return 0;
    }
}
