package me.yhamarsheh.projecttwo.managers;

import me.yhamarsheh.projecttwo.managers.sub.MajorsManager;
import me.yhamarsheh.projecttwo.managers.sub.StudentsManager;
import me.yhamarsheh.projecttwo.storage.FileSystem;

public class PrimaryManager {

    private MajorsManager majorsManager;
    private StudentsManager studentsManager;

    private FileSystem fileSystem;

    public PrimaryManager() {
        init();
    }

    private void init() {
        majorsManager = new MajorsManager();
        studentsManager = new StudentsManager();

        fileSystem = new FileSystem();
    }

    public MajorsManager getMajorsManager() {
        return majorsManager;
    }

    public StudentsManager getStudentsManager() {
        return studentsManager;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }
}
