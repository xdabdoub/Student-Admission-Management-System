package me.yhamarsheh.projecttwo.managers.sub;

import me.yhamarsheh.projecttwo.objects.Student;
import me.yhamarsheh.projecttwo.structure.SingleLinkedList;

public class StudentsManager {

    private SingleLinkedList<Student> students;
    private SingleLinkedList<Student> notAccepted;
    private SingleLinkedList<Student> accepted;

    public StudentsManager() {
        this.students = new SingleLinkedList<>();
        this.notAccepted = new SingleLinkedList<>();
        this.accepted = new SingleLinkedList<>();
    }

    public SingleLinkedList<Student> getStudents() {
        return students;
    }

    public void setStudents(SingleLinkedList<Student> students) {
        this.students = students;
    }

    public SingleLinkedList<Student> getNotAccepted() {
        return notAccepted;
    }

    public void setNotAccepted(SingleLinkedList<Student> notAccepted) {
        this.notAccepted = notAccepted;
    }

    public SingleLinkedList<Student> getAccepted() {
        return accepted;
    }

    public void setAccepted(SingleLinkedList<Student> accepted) {
        this.accepted = accepted;
    }
}
