package mosquitomodel;

import agentbasedmodel.*;

import java.util.Collection;
import java.util.List;

public class ParkManager extends EnvironmentManager {

    /**
     * Constructor for ParkManager.
     */
    public ParkManager(Environment environment, Time cycleLength){
        super(environment, cycleLength);
    }

    @Override
    public Report runCycle() {
        return null;
    }

    @Override
    public void seedEnvironment(Collection<Agent> agents) {

    }
}
