package mosquitomodel;

import agentbasedmodel.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

public class ParkManager extends EnvironmentManager {

    /*
    Private fields.
     */

    // the earliest Time for the Mosquito to be activated by
    private Time mosquitoActivationTime;
    // the earliest Time for the Mosquito to be deactivated by
    private Time mosquitoDeactivationTime;

    // ParkVisitors that are currently in the Simulation but NOT the Park
    private ArrayList<Agent> limbo;


    /*
    Constructor.
     */

    public ParkManager(Park environment, Time cycleLength, Time currentTime, Time mosquitoActivationTime, Time mosquitoDeactivationTime){
        // invoke EnvironmentManager's constructor
        super(environment, cycleLength);
        // set private fields
        this.currentTime = currentTime;
        this.mosquitoActivationTime = mosquitoActivationTime;
        this.mosquitoDeactivationTime = mosquitoDeactivationTime;
        // initialize limbo
        limbo = new ArrayList<>();
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


    /**
     * Checks the Schedules of every ParkVisitor within the Park to correct if they should be in or out of the Park.
     */
    private void checkAndCorrectSchedules(){

        // get Park and Park's population
        Park park = (Park) this.environment;
        LinkedHashSet<Agent> population = (LinkedHashSet<Agent>) park.getPopulation();

        // determine who needs to be removed from the Park
        ArrayList<Agent> agentsToRemove = new ArrayList<>();
        for (Agent agent : population){

            // get this specific ParkVisitor
            ParkVisitor parkVisitor = (ParkVisitor) agent;

            /*
            If the current Time is not within the range of Time specified by this ParkVisitor's schedule, queue them
            to leave the Park.
             */
            if (!currentTime.isBetween(parkVisitor.getTimeIGoToPark(), parkVisitor.getTimeILeavePark())){
                agentsToRemove.add(agent);
            }
        }

        // remove appropriate Agents from the Park and add them into limbo
        for (Agent agent : agentsToRemove){
            this.environment.removeFromPopulation(agent);
            this.limbo.add(agent);
        }

        // determine who needs to be added to the Park
        ArrayList<Agent> agentsToAdd = new ArrayList<>();
        for (Agent agent : limbo){

            // get this specific ParkVisitor
            ParkVisitor parkVisitor = (ParkVisitor) agent;

            /*
            If the current Time is within the range of Time specified by this ParkVisitor's schedule, queue them to
            enter the Park.
             */
            if (currentTime.isBetween(parkVisitor.getTimeIGoToPark(), parkVisitor.getTimeILeavePark())){
                agentsToAdd.add(agent);
            }
        }

        // remove appropriate Agents from limbo and add them into the Park
        for (Agent agent : agentsToAdd){
            this.environment.addToPopulation(agent);
            this.limbo.remove(agent);
        }

    }

    /**
     * Generates a Report based on the current state of the Simulation.
     */
    private Report generateReport(){
        String text = "Report for cycle#" + this.currentCycle;
        text += "\t\n Time at Cycle Start: " + this.currentTime;
        text += "\n\tCurrent Population (" + this.environment.sizeOfPopulation() + "): ";
        for (Agent agent : this.environment.getPopulation()){
            text += "\n\t\t" + agent;
        }
        text += "\n";

        return new Report(text);
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

        checkAndCorrectMosquito();
        checkAndCorrectSchedules();

        Report report = generateReport();

        this.currentTime.add(cycleLength);
        this.currentCycle++;
        return report;
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
