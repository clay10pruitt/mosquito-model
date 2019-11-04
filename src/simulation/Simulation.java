package simulation;

import agentbasedmodel.*;
import mosquitomodel.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

public class Simulation {

    /**
     * Creates a BufferedReader for the given file.
     * @param file name of file to create BufferedReader for
     * @return a BufferedReader for the given file
     * @throws FileNotFoundException
     */
    private static BufferedReader createBufferedReaderForFile(String file) throws FileNotFoundException{
        return new BufferedReader(new FileReader(file));
    }

    /**
     * Parses a .csv file to create Agents. Lines should be in the following form:
     *      [id, time in, time out, age, max hearable frequency]
     * @param reader BufferedReader for reading the .csv file
     * @return a Collection of Agents
     * @throws IOException
     */
    private static Collection<Agent> parseParkVisitorsFromFile(BufferedReader reader) throws IOException {

        // get data from file
        String line = reader.readLine();
        ArrayList<String[]> abomimatrix = new ArrayList<>();
        while ((line = reader.readLine()) != null){
            String[] values = line.split(",");
            abomimatrix.add(values);
        }

        // build collection of Agents
        Collection<Agent> agents = new LinkedHashSet<>();
        for (String[] args : abomimatrix){
            agents.add(new ParkVisitor(args));
        }

        return agents;
    }

    /**
     * Main method for the simulation.
     * @param args
     */
    public static void main(String[] args){

        // create buffered reader to read input file
        BufferedReader reader = null;
        try {
            reader = createBufferedReaderForFile("resources/agents.csv");
        } catch (FileNotFoundException e){
            return;
        }

        // create collection of Agents from file
        Collection<Agent> agents = null;
        try {
            agents = parseParkVisitorsFromFile(reader);
        } catch (IOException e){
            return;
        }

        Park e = new Park(new Mosquito(17500));
        EnvironmentManager em = new ParkManager(e, new Time(0, 30));
        em.seedEnvironment(agents);

        SimulationOperator so = new SimulationOperator(em, 48);

        so.runSimulation();
        System.out.println(so.getData());

    }

}
