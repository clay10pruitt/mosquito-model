package agentbasedmodel;

public class Agent {

    // the String associated with this Agent
    private String id;

    /**
     * Agent constructor.
     * @param id the identification string of this agent
     */
    public Agent(String id){
        this.id = id;
    }

    /**
     * @return id
     */
    public String getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return ("agent#" + id);
    }

    /**
     * Two Agents are equivalent if their IDs are the same.
     */
    @Override
    public boolean equals(Object obj) {
        // an Agent will always be equivalent to itself
        if (obj == this){
            return true;
        }

        // an Agent cannot equal a non-Agent
        if (!(obj instanceof  Agent)){
            return false;
        }

        // two Agents are equal if their IDs are equal
        Agent a = (Agent) obj;
        return (this.id.equals(a.id));
    }
}
