package simulation;

import agentbasedmodel.Agent;
import mosquitomodel.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

public class Simulation {

    private static BufferedReader createBufferedReaderForFile(String file) throws FileNotFoundException{
        return new BufferedReader(new FileReader(file));
    }

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
    }

}
