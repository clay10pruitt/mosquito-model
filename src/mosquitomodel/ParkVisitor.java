package mosquitomodel;

import agentbasedmodel.Agent;
import agentbasedmodel.Time;

public class ParkVisitor extends Agent {

    // the biological information contained within this ParkVisitor
    private ParkVisitorBiology biology;
    // this ParkVisitor's schedule for visiting the Park
    private Schedule schedule;

    /**
     * Constructor for ParkVisitor.
     * @param id the String associated with this ParkVisitor
     * @param biology the biological information of this ParkVisitor
     */
    public ParkVisitor(String id, ParkVisitorBiology biology, Schedule schedule){
        super(id);
        this.biology = biology;
        this.schedule = schedule;
    }

    /**
     * Constructor for ParkVisitor that builds a ParkVisitor from a string of arguments.
     * @param args: array of arguments set up as:
     *            args[0] => id
     *            args[1] => scheduled time in
     *            args[2] => scheduled time out
     *            args[3] => age
     *            args[4] => maxFrequencyHearable
     */
    public ParkVisitor(String[] args){

        // invoke Agent's constructor
        super(args[0]);

        // build Schedule
        Time timeIn = new Time(args[1]);
        Time timeOut = new Time(args[2]);
        Schedule schedule = new Schedule(timeIn, timeOut);

        // build Biology
        int age = Integer.parseInt(args[3]);
        int maxFrequencyHearable = Integer.parseInt(args[4]);
        ParkVisitorBiology biology = new ParkVisitorBiology(age, maxFrequencyHearable);

        // construct this ParkVisitor
        this.schedule = schedule;
        this.biology = biology;

    }

    /**
     * @return the age of this visitor
     */
    public int getAge(){
        return biology.getAge();
    }

    /**
     * @return the hearing loss of this visitor
     */
    public float getMaxFrequencyHearable(){
        return biology.getMaxFrequencyHearable();
    }


    /**
     * Gets the Time this ParkVisitor goes to the Park.
     * @return Time object
     */
    public Time getTimeIGoToPark(){
        return this.schedule.getInTime();
    }

    /**
     * Gets the Time this ParkVisitor leaves the Park.
     * @return Time object
     */
    public Time getTimeILeavePark(){
        return this.schedule.getOutTime();
    }

    @Override
    public String toString() {
        return ("Agent(" + this.getId() + ") => " +
                "age: " + this.getAge() +
                "; max frequency: " + this.getMaxFrequencyHearable() +
                "; time in: " + this.getTimeIGoToPark() +
                "; time out: " + getTimeILeavePark() +
                "\n");
    }
}
