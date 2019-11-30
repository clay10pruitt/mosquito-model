package simulation;

import agentbasedmodel.EnvironmentManager;
import agentbasedmodel.Report;
import agentbasedmodel.Time;
import mosquitomodel.ParkVisitor;
import mosquitomodel.ParkVisitorBiology;
import mosquitomodel.Schedule;

import java.util.LinkedHashSet;

public class SimulationOperator {

    // private fields
    private Report data;

    // properties
    private EnvironmentManager environmentManager;
    private int numberOfCyclesToRun;

    /**
     * Constructor for SimulationOperator.
     * @param environmentManager: the Simulation's EnvironmentManager
     * @param numberOfCyclesToRun: the amount of cycles to run the EnvironmentManager through
     */
    public SimulationOperator(EnvironmentManager environmentManager, int numberOfCyclesToRun){
        this.environmentManager = environmentManager;
        this.numberOfCyclesToRun = numberOfCyclesToRun;
        this.data = new Report("cycle, time, park population, age mean");
    }


    /**
     * Runs the simulation by putting the EnvironmentManager through the number of cycles specified in the constructor.
     */
    public void runSimulation(){
        for (int c = 0; c < numberOfCyclesToRun; c++){
            Report cycleReport = environmentManager.runCycle();
            data.add(cycleReport);
        }
    }


    /**
     * Gets the Report contained within this SimulationOperator.
     * @return data
     */
    public Report getData(){
        return this.data;
    }

}
