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
     * @return info
     */
    public String getInfo(){
        return this.info;
    }

}
