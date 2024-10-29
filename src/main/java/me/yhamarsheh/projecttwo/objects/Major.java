package me.yhamarsheh.projecttwo.objects;

public class Major implements Comparable<Major> {

    private String name;
    private int acceptanceGrade;
    private double tawjihiWeight;
    private double placementTestWeight;

    public Major(String name, int acceptanceGrade, double tawjihiWeight, double placementTestWeight) {
        this.name = name;
        this.acceptanceGrade = acceptanceGrade;
        this.tawjihiWeight = tawjihiWeight;
        this.placementTestWeight = placementTestWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAcceptanceGrade() {
        return acceptanceGrade;
    }

    public void setAcceptanceGrade(int acceptanceGrade) {
        this.acceptanceGrade = acceptanceGrade;
    }

    public double getTawjihiWeight() {
        return tawjihiWeight;
    }

    public void setTawjihiWeight(double tawjihiWeight) {
        this.tawjihiWeight = tawjihiWeight;
    }

    public double getPlacementTestWeight() {
        return placementTestWeight;
    }

    public void setPlacementTestWeight(double placementTestWeight) {
        this.placementTestWeight = placementTestWeight;
    }

    @Override
    public String toString() {
        return "{name: " + name + ", acceptanceGrade: " + acceptanceGrade + ", tawjihiWeight: " + tawjihiWeight + ", placementTestWeight: " + placementTestWeight + "}";
    }

    @Override
    public int compareTo(Major o) {
        return this.getName().compareTo(o.getName());
    }
}
