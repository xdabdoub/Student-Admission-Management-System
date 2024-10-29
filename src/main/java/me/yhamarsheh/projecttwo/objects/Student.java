package me.yhamarsheh.projecttwo.objects;

public class Student implements Comparable<Student> {

    private int id;
    private String name;
    private double tawjihiGrade;
    private double placementTestGrade;
    private double admissionMark;

    public Student(int id, String name, double tawjihiGrade, double placementTestGrade) {
        this.id = id;
        this.name = name;
        this.tawjihiGrade = tawjihiGrade;
        this.placementTestGrade = placementTestGrade;
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

    public double getTawjihiGrade() {
        return tawjihiGrade;
    }

    public void setTawjihiGrade(double tawjihiGrade) {
        this.tawjihiGrade = tawjihiGrade;
    }

    public double getPlacementTestGrade() {
        return placementTestGrade;
    }

    public void setPlacementTestGrade(double placementTestGrade) {
        this.placementTestGrade = placementTestGrade;
    }

    public double getAdmissionMark() {
        return admissionMark;
    }

    public void setAdmissionMark(double admissionMark) {
        this.admissionMark = admissionMark;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", name: " + name + ", tawjihiGrade: " + tawjihiGrade + ", placementTestGrade: " + placementTestGrade + "}";
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(getAdmissionMark(), o.getAdmissionMark());
    }
}
