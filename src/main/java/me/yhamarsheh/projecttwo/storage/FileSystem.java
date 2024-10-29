package me.yhamarsheh.projecttwo.storage;

import me.yhamarsheh.projecttwo.Main;
import me.yhamarsheh.projecttwo.objects.Major;
import me.yhamarsheh.projecttwo.objects.Student;
import me.yhamarsheh.projecttwo.objects.sub.MajorData;
import me.yhamarsheh.projecttwo.structure.DoublyLinkedList;
import me.yhamarsheh.projecttwo.utilities.FileUtils;
import me.yhamarsheh.projecttwo.utilities.GeneralUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

    private File studentsData;
    private File majorsData;

    public FileSystem() {
    }

    public void readMajorsData() {
        DoublyLinkedList<MajorData> majors = Main.getPrimaryManager().getMajorsManager().getMajors();

        try (Scanner scanner = new Scanner(majorsData)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;

                Major major = null;

                try {
                    major = FileUtils.getMajorFromString(line);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }

                if (GeneralUtils.exists(major)) {
                    System.out.println("Major " + major.getName() + " already exists! Skipping...");
                    continue;
                }

                majors.insert(new MajorData(major));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File couldn't be found!");
        }
    }

    public void readStudentsData() {
        try (Scanner scanner = new Scanner(studentsData)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;

                Student student = null;
                try {
                    student = FileUtils.getStudentFromString(line);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }

                if (GeneralUtils.exists(student)) {
                    System.out.println("Student " + student.getName() + " already exists! Skipping...");
                    continue;
                }

                MajorData majorData = GeneralUtils.getMajorDataFromName(line.split(";")[4]);

                if (majorData == null) {
                    System.out.println("Student named " + student.getName() + " chosen major does not exist!");
                    continue;
                }

                student.setAdmissionMark(((student.getTawjihiGrade() * majorData.getMajor().getTawjihiWeight()) +
                        (student.getPlacementTestGrade()) * majorData.getMajor().getPlacementTestWeight()));

                majorData.getStudentData().getStudents().insert(student);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File couldn't be found");
        }
    }

    public boolean isStudentsDataAvailable() {
        return studentsData != null;
    }

    public boolean isMajorsDataAvailable() {
        return majorsData != null;
    }

    public File getStudentsData() {
        return studentsData;
    }

    public void setStudentsData(File studentsData) {
        this.studentsData = studentsData;
    }

    public File getMajorsData() {
        return majorsData;
    }

    public void setMajorsData(File majorsData) {
        this.majorsData = majorsData;
    }
}
