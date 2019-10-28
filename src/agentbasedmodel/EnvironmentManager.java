package agentbasedmodel;

import java.util.List;

public abstract class EnvironmentManager {

    private Environment environment;
    private Time time;
    private int cycleLength;

    public abstract Report runCycle();

    public abstract void seedEnvironment(List<Agent> agents);

}
