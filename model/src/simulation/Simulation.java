package simulation;

import agentbasedmodel.*;
import mosquitomodel.*;

import java.io.*;
import java.util.*;

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
     * Outputs a given Report to a given file.
     * @param report the Report to output
     * @param fileName the file to output to
     * @throws IOException
     */
    private static void outputReportToFile(Report report, File fileName) throws IOException{
        // create the file and output to it
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
        out.write(report.getInfo());
        // close output stream
        out.close();
    }


    /**
     * Parses configuration parameters from the give=n file.
     * @param reader BufferedReader for reading the file
     * @return a Map with param names as keys and their values as the map's values
     * @throws IOException
     */
    private static Map<String, Object> parseConfig(BufferedReader reader) throws IOException {

        Map<String, Object> config = new HashMap<>();

        // get data from file
        String line;
        while ((line = reader.readLine()) != null){
            String[] paramAndValue = line.split("=");
            String param = paramAndValue[0].replaceAll("\\s", "");
            String value = paramAndValue[1].replaceAll("\\s", "");
            config.put(param, value);
        }

        // interpret data
        for (String param : config.keySet()){
            Object value = config.get(param);
            if (param.equals("cycleLength")) {
                value = new Time((String) value);
            } else if (param.equals("numberOfCycles")){
                value = Integer.parseInt((String) value);
            } else if (param.equals("initialTime")) {
                value = new Time((String) value);
            } else if (param.equals("mosquitoFrequency")){
                value = Integer.parseInt((String) value);
            } else if (param.equals("mosquitoActivationTime")){
                value = new Time((String) value);
            } else if (param.equals("mosquitoDeactivationTime")) {
                value = new Time((String) value);
            }
            config.put(param, value);
        }

        return config;

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

        // create buffered reader to read config file
        BufferedReader reader = null;
        try {
            reader = createBufferedReaderForFile("resources/config.txt");
        } catch (FileNotFoundException e){
            return;
        }

        // retrieve configuration parameters
        Map<String, Object> config = null;
        try {
            config = parseConfig(reader);
        } catch (IOException e){
            return;
        }

        // create another buffered reader to read agent input file
        reader = null;
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


        // create two EnvironmentManagers; one with a Mosquito and one without
        Park e1 = new Park(new Mosquito((Integer) config.get("mosquitoFrequency")));
        Park e2 = new Park(null);

        EnvironmentManager em_mosquitoyes = new ParkManager(
                e1,
                (Time) config.get("cycleLength"),
                (Time) config.get("initialTime"),
                (Time) config.get("mosquitoActivationTime"),
                (Time) config.get("mosquitoDeactivationTime")
        );
        em_mosquitoyes.seedEnvironment(agents);

        EnvironmentManager em_mosquitono = new ParkManager(
                e2,
                (Time) config.get("cycleLength"),
                (Time) config.get("initialTime")
        );
        em_mosquitono.seedEnvironment(agents);

        // create a SimulationOperator for each EnvironmentManager
        SimulationOperator so_mosquitoyes = new SimulationOperator(
                em_mosquitoyes,
                (Integer) config.get("numberOfCycles")
        );

        SimulationOperator so_mosquitono = new SimulationOperator(
                em_mosquitono,
                (Integer) config.get("numberOfCycles")
        );

        // run both SimulationOperators
        so_mosquitoyes.runSimulation();
        so_mosquitono.runSimulation();

        // output files
        File file_mosquitoyes = new File("reports/reportwithmosquito.csv");
        File file_mosquitono = new File("reports/reportwithoutmosquito.csv");

        try {
            outputReportToFile(so_mosquitoyes.getData(), file_mosquitoyes);
            outputReportToFile(so_mosquitono.getData(), file_mosquitono);
        } catch (IOException e){
            return;
        }

    }

}
