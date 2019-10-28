package agentbasedmodel;

public class Time {

    private int time;
    private int minTime;
    private int MaxTime;

    /**
     * Advances the time by the given amount and ensures that we stay within the defined bounds of time.
     * @param amount amount of time to advance by
     */
    public void advanceTime(int amount){
    }

    /**
     * @return time
     */
    public int getTime(){
        return this.time;
    }

}
