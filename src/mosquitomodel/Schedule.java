package mosquitomodel;

import agentbasedmodel.Time;

/**
 * Helper class for ParkManager to associate ParkVisitors with the Times that they are in the Park for.
 * @author Clayton Pruitt : clay10pruitt@gmail.com
 */

public class Schedule
{

    private Time in;
    private Time out;

    /**
     * Constructor for Schedule.
     * @param in the in Time
     * @param out the out Time
     */
    public Schedule(Time in, Time out){
        this.in = in;
        this.out = out;
    }

    /**
     * @return in
     */
    public Time getInTime(){
        return this.in;
    }

    /**
     * @return out
     */
    public Time getOutTime(){
        return this.out;
    }

}
