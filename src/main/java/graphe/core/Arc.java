package main.java.graphe.core;

public class Arc {
    private String source, destination;
    private int valuation;

    public Arc(String source){
        this(source, "", 0);
    }

    public Arc(String source, String destination, int valuation) {
        this.source = source;
        this.destination = destination;
        this.valuation = valuation;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Integer getValuation() {
        return valuation;
    }
}
