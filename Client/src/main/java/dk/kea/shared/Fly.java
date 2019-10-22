package dk.kea.shared;

public class Fly {
    private String name;
    private String model;
    private String flightSize;
    private String luftSelskab;
    private String id;

    public Fly() {
    }

    public Fly(String name, String model, String size, String luftSelskab, String id) {
        this.name = name;
        this.model = model;
        this.flightSize = flightSize;
        this.luftSelskab = luftSelskab;
        this.id = id;
    }

    public String getFlightSize() {
        return flightSize;
    }

    public void setFlightSize(String flightSize) {
        this.flightSize = flightSize;
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



    public String getLuftSelskab() {
        return luftSelskab;
    }

    public void setLuftSelskab(String luftSelskab) {
        this.luftSelskab = luftSelskab;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fly: " +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", size='" + flightSize + '\'' +
                ", luftSelskab='" + luftSelskab + '\'' +
                ", id='" + id + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fly fly = (Fly) o;
        return name.equals(fly.name) &&
                model.equals(fly.model) &&
                flightSize.equals(fly.flightSize) &&
                luftSelskab.equals(fly.luftSelskab) &&
                id.equals(fly.id);
    }


}
