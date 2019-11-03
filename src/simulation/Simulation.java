package simulation;

import agentbasedmodel.Agent;
import agentbasedmodel.Report;
import agentbasedmodel.Time;
import mosquitomodel.*;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Simulation {

    private static Collection<Agent> createParkVisitors(){

        Collection<Agent> parkVisitors = new LinkedHashSet<>();

        // create Bob
        ParkVisitorBiology bobBiology = new ParkVisitorBiology(44, 16000, 16000, 0);
        Schedule bobSchedule = new Schedule(new Time(17, 0), new Time(19, 0));
        ParkVisitor bob = new ParkVisitor("Bob", bobBiology, bobSchedule);

        // add Bob to the collection of visitors
        parkVisitors.add(bob);

        return parkVisitors;
    }

    /**
     * Main method for the simulation.
     * @param args
     */
    public static void main(String[] args){

        // create the agents
        Collection<Agent> parkVisitors = createParkVisitors();

        // initialize objects needed for simulation
        Mosquito mosquito = new Mosquito(17500);
        Park park = new Park(mosquito);
        ParkManager parkManager = new ParkManager(park, new Time(0, 15));
        parkManager.seedEnvironment(parkVisitors);
        SimulationOperator simulationOperator = new SimulationOperator(parkManager, 96);

        // run simulation
        simulationOperator.runSimulation();
        Report data = simulationOperator.getData();

        // print report
        System.out.println(data);

    }

}
