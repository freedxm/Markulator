package model;

public class Subject {
    private String name;
    private double average;

    public Subject(String name, double average){
        this.name = name;
        this.average = average;
    }

    public Subject(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
