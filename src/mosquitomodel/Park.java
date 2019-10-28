package mosquitomodel;

import agentbasedmodel.Environment;

public class Park extends Environment {

    private Mosquito mosquito;

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
}
