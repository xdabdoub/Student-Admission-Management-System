package me.yhamarsheh.projecttwo;

import me.yhamarsheh.projecttwo.managers.PrimaryManager;
import me.yhamarsheh.projecttwo.ui.UIHandler;

public class Main {

    private static PrimaryManager primaryManager;
    private static UIHandler uiHandler;

    public static void main(String[] args) {
        primaryManager = new PrimaryManager();
        uiHandler = new UIHandler();

        UIHandler.launchApp(args);
    }

    public static PrimaryManager getPrimaryManager() {
        return primaryManager;
    }
}
