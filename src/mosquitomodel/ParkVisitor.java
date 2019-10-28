package mosquitomodel;

import agentbasedmodel.Agent;

public class ParkVisitor extends Agent {

    private ParkVisitorBiology biology;

    /**
     * @return the age of this visitor
     */
    public int getAge(){
        return biology.getAge();
    }

    /**
     * @return the hearing loss of this visitor
     */
    public float getHearingLoss(){
        return biology.getHearingLoss();
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

}
