package agentbasedmodel;

public class Report {

    // the info contained within this report
    private String info;

    /**
     * Constructor for Report.
     * @param info the info contained within this report
     */
    public Report(String info){
        this.info = info;
    }

    /**
     * Adds the given Report to this Report.
     * @param report the Report to combine with this Report
     */
    public void add(Report report){
        this.info += "\n\n" + report.info;
    }

    /**
     * @return info
     */
    public String getInfo(){
        return this.info;
    }

}
