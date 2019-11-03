package mosquitomodel;

import agentbasedmodel.Agent;
import agentbasedmodel.Environment;

import java.util.LinkedHashSet;
import java.util.Set;

public class Park extends Environment {

    // the Mosquito unit used by this Park
    private Mosquito mosquito;

    /**
     * Constructor for Park that initializes its population with type LinkedHashSet.
     */
    public Park(){
        super(new LinkedHashSet<Agent>());
    }

    /**
     * Activates this Park's Mosquito.
     */
    public void activateMosquito(){
        this.mosquito.turnOn();
    }

    /**
     * Deactivates this Park's Mosquito.
     */
    public void deactivateMosquito(){
        this.mosquito.turnOff();
    }

    /**
     * @return whether or not this Park's Mosquito is active
     */
    public boolean mosquitoIsActive() { return this.mosquito.isActive(); }
}
