package dk.kea.shared;

import java.util.Date;

public class Flights {
    private String name;
    private String model;
    private String flightSize;
    private String luftSelskab;
    private int id;
    private Date departure;
    private Gate gate;

    public Flights() {
    }

    public Flights(String name, String model, String flightSize, String luftSelskab, int id, Date departure, Gate gate) {
        this.name = name;
        this.model = model;
        this.flightSize = flightSize;
        this.luftSelskab = luftSelskab;
        this.id = id;
        this.departure = departure;
        this.gate = gate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFlightSize() {
        return flightSize;
    }

    public void setFlightSize(String flightSize) {
        this.flightSize = flightSize;
    }

    public String getLuftSelskab() {
        return luftSelskab;
    }

    public void setLuftSelskab(String luftSelskab) {
        this.luftSelskab = luftSelskab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}