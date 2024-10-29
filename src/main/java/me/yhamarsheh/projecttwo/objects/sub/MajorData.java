package me.yhamarsheh.projecttwo.objects.sub;

import me.yhamarsheh.projecttwo.managers.sub.StudentsManager;
import me.yhamarsheh.projecttwo.objects.Major;

public class MajorData implements Comparable<MajorData> {

    private final Major major;
    private final StudentsManager studentData;

    public MajorData(Major major) {
        this.major = major;
        this.studentData = new StudentsManager();
    }

    public MajorData(Major major, StudentsManager studentData) {
        this.major = major;
        this.studentData = studentData;
    }

    public Major getMajor() {
        return major;
    }

    public StudentsManager getStudentData() {
        return studentData;
    }

    @Override
    public int compareTo(MajorData o) {
        return this.getMajor().compareTo(o.getMajor());
    }

    @Override
    public String toString() {
        return major.getName();
    }
}
