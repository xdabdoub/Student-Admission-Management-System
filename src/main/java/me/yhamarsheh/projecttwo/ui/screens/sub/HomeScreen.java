package me.yhamarsheh.projecttwo.ui.screens.sub;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.yhamarsheh.projecttwo.Main;
import me.yhamarsheh.projecttwo.ui.UIHandler;
import me.yhamarsheh.projecttwo.ui.screens.YazanScreen;
import me.yhamarsheh.projecttwo.utilities.UIUtils;

import java.io.File;

public class HomeScreen extends YazanScreen {

    private Stage stage;

    public HomeScreen(Stage stage) {
        super("Home", "Project Two - COMP242 @ 1220346");

        this.stage = stage;
    }

    @Override
    public Node setup() {
        VBox vBox = new VBox(20);

        VBox majorsBox = new VBox(5);
        Label selectMajors = UIUtils.label("Select Majors Data File", FontWeight.BOLD, 14, null);

        Button selectMButton = new Button("Click here to select a file");
        selectMButton.setPrefHeight(30);
        selectMButton.setPrefWidth(220);

        VBox studentsBox = new VBox(5);
        Label selectStudents = UIUtils.label("Select Student Data File", FontWeight.BOLD, 14, null);

        Button selectSButton = new Button("Click here to select a file");
        selectSButton.setPrefHeight(30);
        selectSButton.setPrefWidth(220);
        selectSButton.setDisable(true);

        selectMButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file == null) return;

            Main.getPrimaryManager().getFileSystem().setMajorsData(file);
            Main.getPrimaryManager().getFileSystem().readMajorsData();

            selectMButton.setDisable(true);
            selectMButton.setText("Selected: " + file.getName());
            selectSButton.setDisable(false);
        });

        selectSButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file == null) return;

            Main.getPrimaryManager().getFileSystem().setStudentsData(file);
            Main.getPrimaryManager().getFileSystem().readStudentsData();

            selectSButton.setText("Selected: " + file.getName());
            selectSButton.setDisable(true);

            UIHandler.getInstance().open(new MajorsScreen(), 500, 500);
        });

        majorsBox.getChildren().addAll(selectMajors, selectMButton);
        majorsBox.setAlignment(Pos.CENTER);

        studentsBox.getChildren().addAll(selectStudents, selectSButton);
        studentsBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(majorsBox, studentsBox);

        return vBox;
    }
}
