package agentbasedmodel;

import java.util.Collection;

public class Environment {

    // the collection of Agents currently in the environment
    private Collection<Agent> population;

    /**
     * Initializes the Enivornment with the given collection of Agents.
     * @param population pre-determined list of Agents
     */
    public Environment(Collection<Agent> population){
        this.population = population;
    }

    /**
     * Adds the given Agent to the population of the Environment.
     * @param agent the Agent to add to the Environment's population
     */
    public void addToPopulation(Agent agent){
        this.population.add(agent);
    }

    /**
     * Removes the given Agent from the population of the Environment.
     * @param agent the Agent to remove from the Environment's population
     */
    public void removeFromPopulation(Agent agent){
        this.population.remove(agent);
    }

    /**
     * @return the size of the Environment's population
     */
    public int sizeOfPopulation(){
        return this.population.size();
    }

    /**
     * Two Environments are equal if their populations are equal.
     */
    @Override
    public boolean equals(Object obj) {
        // An Environment is equal to itself.
        if (obj == this){
            return true;
        }

        // An Environment cannot be equal to a non-Environment.
        if (!(obj instanceof Environment)){
            return false;
        }

        // Two Environments are equal if their populations are equal.
        Environment e = (Environment) obj;
        return (this.population.equals(e.population));
    }
}
