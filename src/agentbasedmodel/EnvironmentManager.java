package agentbasedmodel;

import java.util.Collection;
import java.util.List;

public abstract class EnvironmentManager {

    // the Environment this EnvironmentManager will manage
    private Environment environment;
    // the current Time the managed Environment is in
    private Time currentTime;
    // the amount of Time that passes per one run of a cycle
    private Time cycleLength;

    /**
     * Constructor for EnvironmentManager.
     * @param environment the Environment this EnvironmentManager will manage
     * @param cycleLength the amount of Time that passes per one run of a cycle
     */
    public EnvironmentManager(Environment environment, Time cycleLength){
        this.environment = environment;
        this.cycleLength = cycleLength;
    }

    /**
     * Runs the Environment through a single cycle of length cycleLength.
     * @return a single Report which reflects the state of the Environment by the end of the cycle
     */
    public abstract Report runCycle();

    /**
     * Adds the given collection of Agents to the managed Environment's population. Should be run at least once
     * before the first cycle is run.
     * @param agents the Collection of Agents to add to the Environment's population
     */
    public abstract void seedEnvironment(Collection<Agent> agents);

}
