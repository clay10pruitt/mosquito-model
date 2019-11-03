package mosquitomodel;

import agentbasedmodel.*;

import java.util.Collection;
import java.util.List;

public class ParkManager extends EnvironmentManager {

    /*
    Private fields.
     */

    // the earliest Time for the Mosquito to be activated by
    private Time mosquitoActivationTime = new Time(20, 0);
    // the earliest Time for the Mosquito to be deactivated by
    private Time mosquitoDeactivationTime = new Time(8, 0);

    /*
    Constructor.
     */

    /**
     * Constructor for ParkManager.
     * @param environment the Park this ParkManager will manage
     * @param cycleLength the amount of Time that will pass with every cycle
     */
    public ParkManager(Park environment, Time cycleLength){
        // invoke EnvironmentManager's constructor
        super(environment, cycleLength);
        // set starting time to 8:00 AM
        this.currentTime = new Time(8, 0);
    }

    /*
    Helper functions for runCycle().
     */

    /**
     * Checks what state the Mosquito is in and corrects the state if it is incorrect.
     */
    private void checkAndCorrectMosquito(){

        Park park = (Park) this.environment;

        // determine if we are currently within the Mosquito's active hours
        boolean inActiveTime =
                currentTime.isBetween(mosquitoActivationTime, new Time(23, 59), false)
                || currentTime.isBetween(new Time(0, 0), mosquitoDeactivationTime, false);

        // if necessary, correct Mosquito's activation state
        if (inActiveTime){
            if(!park.mosquitoIsActive()){
                park.activateMosquito();
            }
        } else {
            if (park.mosquitoIsActive()){
                park.deactivateMosquito();
            }
        }
    }

    /*
    EnvironmentManager functions.
     */

    /**
     * Runs the Environment through a single cycle of length cycleLength.
     * @return a single Report which reflects the state of the Environment by the end of the cycle
     */
    @Override
    public Report runCycle() {
        return null;
    }


    /**
     * Adds the given collection of Agents to the managed Environment's population. Should be run at least once
     * before the first cycle is run.
     * @param agents the Collection of Agents to add to the Environment's population
     */
    @Override
    public void seedEnvironment(Collection<Agent> agents) {
        this.environment.addToPopulation(agents);
    }
}
