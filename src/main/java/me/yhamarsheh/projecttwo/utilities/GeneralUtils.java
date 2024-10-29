package me.yhamarsheh.projecttwo.utilities;

import me.yhamarsheh.projecttwo.Main;
import me.yhamarsheh.projecttwo.objects.Major;
import me.yhamarsheh.projecttwo.objects.Student;
import me.yhamarsheh.projecttwo.objects.sub.MajorData;
import me.yhamarsheh.projecttwo.structure.DoublyLinkedList;
import me.yhamarsheh.projecttwo.structure.nodes.Node;
import me.yhamarsheh.projecttwo.structure.nodes.sub.DNode;

public class GeneralUtils {

    public static MajorData getMajorDataFromName(String majorName) {
        DoublyLinkedList<MajorData> majors = Main.getPrimaryManager().getMajorsManager().getMajors();

        DNode<MajorData> current = majors.getHead();
        while (current != null) {
            if (current.getData().getMajor().getName().compareTo(majorName) > 0) return null;
            if (current.getData().getMajor().getName().compareTo(majorName) == 0) return current.getData();

            current = current.getNext();
        }

        return null;
    }

    public static boolean exists(Major major) {
        DNode<MajorData> current = Main.getPrimaryManager().getMajorsManager().getMajors().getHead();

        while (current != null) {
            if (current.getData().getMajor().compareTo(major) > 0) return false;
            if (current.getData().getMajor().compareTo(major) == 0) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public static boolean exists(Student student) {
        Node<Student> current = Main.getPrimaryManager().getStudentsManager().getStudents().getHead();

        while (current != null) {
            if (current.getData().getId() > student.getId()) return false;
            if (current.getData().getId() == student.getId()) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
