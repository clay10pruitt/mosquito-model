package agentbasedmodel;

import java.util.List;

public class Environment {

    private List<Agent> population;

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

}
