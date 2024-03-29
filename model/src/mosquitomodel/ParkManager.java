package mosquitomodel;

import agentbasedmodel.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class ParkManager extends EnvironmentManager {

    /*
    Private fields.
     */

    // the earliest Time for the Mosquito to be activated by
    private Time mosquitoActivationTime;
    // the earliest Time for the Mosquito to be deactivated by
    private Time mosquitoDeactivationTime;
    // track whether the Mosquito is currently active
    private boolean mosquitoActive;

    // ParkVisitors that are currently in the Simulation but NOT the Park
    private ArrayList<Agent> limbo;


    /*
    Constructors.
     */

    /**
     * Constructor for ParkManager.
     * @param environment the Park managed by this ParkManager
     * @param cycleLength the length of Time each cycle lasts for
     * @param initialTime the initial time of the Environment
     * @param mosquitoActivationTime the Time the Mosquito will activate
     * @param mosquitoDeactivationTime the Time the Mosquito will deactivate
     */
    public ParkManager(Park environment, Time cycleLength, Time initialTime, Time mosquitoActivationTime, Time mosquitoDeactivationTime){
        // invoke EnvironmentManager's constructor
        super(environment, cycleLength);
        // set private fields
        this.currentTime = initialTime;
        this.mosquitoActivationTime = mosquitoActivationTime;
        this.mosquitoDeactivationTime = mosquitoDeactivationTime;
        this.mosquitoActive = false;
        // initialize limbo
        limbo = new ArrayList<>();
    }

    /**
     * Constructor for a ParkManager that contains a Park with no Mosquito.
     * @param environment the Park managed by this ParkManager
     * @param cycleLength the length of Time each cycle lasts for
     * @param initialTime the initial time of the Environment
     */
    public ParkManager(Park environment, Time cycleLength, Time initialTime){
        this(environment, cycleLength, initialTime, null, null);
    }


    /*
    Checks (helper functions) for runCycle().
     */

    /**
     * Checks what state the Mosquito is in and corrects the state if it is incorrect.
     */
    private void checkAndCorrectMosquito(){

        // if the Park has no set activation/deactivation time for the Mosquito, abort the check
        if (mosquitoActivationTime == null || mosquitoDeactivationTime == null){
            return;
        }

        Park park = (Park) this.environment;

        // determine if we are currently within the Mosquito's active hours
        boolean inActiveTime = currentTime.isBetween(mosquitoActivationTime, mosquitoDeactivationTime);

        // if necessary, correct Mosquito's activation state
        if (inActiveTime){
            if(!park.mosquitoIsActive()){
                park.activateMosquito();
                this.mosquitoActive = true;
            }
        } else {
            if (park.mosquitoIsActive()){
                park.deactivateMosquito();
                this.mosquitoActive = false;
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

    private void checkMosquito(){

        // nothing to check if Mosquito is inactive
        if (!mosquitoActive){
            return;
        }

        // get population
        Park park = (Park) this.environment;
        Collection<Agent> agents = park.getPopulation();
        // get Mosquito frequency
        float frequency = park.getMosquitoFrequency();

        // if an agent cannot tolerate the frequency, have them leave the park
        Collection<Agent> agentstoRemove = new LinkedHashSet<>();
        for (Agent agent : agents){
            ParkVisitor pv = (ParkVisitor) agent;
            if (frequency < pv.getMaxFrequencyHearable()){
                agentstoRemove.add(agent);
            }
        }

        for (Agent agent : agentstoRemove){
            this.environment.removeFromPopulation(agent);
            this.limbo.add(agent);
        }

    }

    /*
    Data collection functions.
     */

    /**
     * Gets the current mean age of the population.
     * @return the mean age of the population
     */
    private float getMeanAge(){
        float sum = 0;
        for (Agent agent : this.environment.getPopulation()){
            ParkVisitor pv = (ParkVisitor) agent;
            sum += pv.getAge();
        }
        return (sum / this.environment.sizeOfPopulation());
    }

    /**
     * Gets the current mode age of the population.
     * @return the mode age of the population
     */
    private int getModeAge(){
        // create count map
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (Agent agent : this.environment.getPopulation()){
            ParkVisitor pv = (ParkVisitor) agent;
            Integer age = new Integer(pv.getAge());

            if (countMap.containsKey(age)) {
                int count = countMap.get(age) + 1;
                countMap.put(age, count);
            } else {
                countMap.put(age, 1);
            }
        }

        int mode = -1;

        // find the mode
        int highest_count = -1;
        for (Integer key : countMap.keySet()){
            if (countMap.get(key) > highest_count){
                highest_count = countMap.get(key);
                mode = key;
            }
        }

        return mode;

    }

    /*
    Report generation functions.
     */

    /**
     * Generates a Report based on the current state of the Simulation.
     */
    private Report generateReport(){
        String text = this.currentCycle + ", " +
                this.currentTime + ", " +
                this.environment.sizeOfPopulation() + ", " +
                this.getMeanAge() + ", " +
                this.getModeAge();
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
        checkMosquito();

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
