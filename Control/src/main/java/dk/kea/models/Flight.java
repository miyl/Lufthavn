package dk.kea.models;

import java.io.Serializable;
import java.util.*;

public class Flight implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String model;
    private String flightSize;
    private String luftSelskab;
    private Date departure;
    private Date arrival;
    private Gate gate;
    private int priorityNumber;
    private Date expectedDeparture;

    public Flight() {}

    public Flight(int id, String name, String model, String flightSize, String luftSelskab, Date departure, Date arrival, Gate gate, int priorityNumber, int standPlads, Date expectedDeparture) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.flightSize = flightSize;
        this.luftSelskab = luftSelskab;
        this.departure = departure;
        this.arrival = arrival;
        this.gate = gate;
        this.priorityNumber = priorityNumber;
        this.expectedDeparture = expectedDeparture;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }


    public Date getExpectedDeparture() {
        return expectedDeparture;
    }

    public void setExpectedDeparture(Date expectedDeparture) {
        this.expectedDeparture = expectedDeparture;
    }
}
