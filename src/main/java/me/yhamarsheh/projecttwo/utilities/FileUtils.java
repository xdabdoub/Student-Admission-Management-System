package me.yhamarsheh.projecttwo.utilities;

import me.yhamarsheh.projecttwo.objects.Major;
import me.yhamarsheh.projecttwo.objects.Student;

public class FileUtils {

    private static final String SEPARATOR = ";";
    public static Major getMajorFromString(String line) {
        String[] data = line.split(SEPARATOR);
        if (data.length != 4) throw new IllegalArgumentException("Missing Data: Data Length is not 4");
        String name = data[0];
        int acceptanceGrade = 0;
        double tawjihiWeight = 0;
        double placementTestGrade = 0;

        try {
            acceptanceGrade = Integer.parseInt(data[1]);
            tawjihiWeight = Double.parseDouble(data[2]);
            placementTestGrade = Double.parseDouble(data[3]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid Data Type: " + ex.getMessage());
        }

        return new Major(name, acceptanceGrade, tawjihiWeight, placementTestGrade);
    }

    public static Student getStudentFromString(String line) {
        String[] data = line.split(SEPARATOR);

        if (data.length != 5) throw new IllegalArgumentException("Missing Data: Data Length is not 5");
        int id = 0;
        String name = data[1];
        double tawjihiGrade = 0;
        double placementTestGrade = 0;

        try {
            id = Integer.parseInt(data[0]);
            tawjihiGrade = Integer.parseInt(data[2]);
            placementTestGrade = Double.parseDouble(data[3]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid Data Type: " + ex.getMessage());
        }

        return new Student(id, name, tawjihiGrade, placementTestGrade);
    }

}
