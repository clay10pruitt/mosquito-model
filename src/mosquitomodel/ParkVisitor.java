package mosquitomodel;

import agentbasedmodel.Agent;

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
     * @return the maximum frequency this visitor will tolerate
     */
    public float getMaxFrequencyTolerated(){
        return biology.getMaxFrequencyTolerated();
    }

    /**
     * @return the tolerance this visitor has to enduring the max frequency
     */
    public float getToleranceToMaxFrequency(){
        return biology.getToleranceToMaxFrequency();
    }

    /**
     * @return this visitor's aversion to visiting the park
     */
    public float getAversionToPark(){
        return biology.getAversionToPark();
    }

    /**
     * Sets this ParkVisitor's aversion to visiting the park.
     * @param aversionToPark new value of aversionToPark
     */
    public void setAversionToPark(int aversionToPark) { biology.setAversionToPark(aversionToPark); }

}
