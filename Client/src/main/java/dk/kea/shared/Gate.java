package dk.kea.shared;

public class Gate {
    private int number;
    private String gateSize;
    private String terminal;

    //husk at tage stilling til 1, 2, 15 og 16 kun har én nabogate


    public Gate() {
    }

    public Gate(int number, String gateSize, String terminal) {
        this.number = number;
        this.gateSize = gateSize;
        this.terminal = terminal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGateSize() {
        return gateSize;
    }

    public void setGateSize(String gateSize) {
        this.gateSize = gateSize;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
