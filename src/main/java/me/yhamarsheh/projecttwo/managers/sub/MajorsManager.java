package me.yhamarsheh.projecttwo.managers.sub;

import me.yhamarsheh.projecttwo.objects.sub.MajorData;
import me.yhamarsheh.projecttwo.structure.DoublyLinkedList;

public class MajorsManager {

    private DoublyLinkedList<MajorData> majors;

    public MajorsManager() {
        this.majors = new DoublyLinkedList<>();
    }

    public DoublyLinkedList<MajorData> getMajors() {
        return majors;
    }

    public void setMajors(DoublyLinkedList<MajorData> majors) {
        this.majors = majors;
    }
}
