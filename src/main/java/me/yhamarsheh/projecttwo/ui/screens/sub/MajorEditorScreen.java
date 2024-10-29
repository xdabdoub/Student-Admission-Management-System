package me.yhamarsheh.projecttwo.ui.screens.sub;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import me.yhamarsheh.projecttwo.Main;
import me.yhamarsheh.projecttwo.objects.Major;
import me.yhamarsheh.projecttwo.objects.sub.MajorData;
import me.yhamarsheh.projecttwo.ui.UIHandler;
import me.yhamarsheh.projecttwo.ui.screens.YazanScreen;
import me.yhamarsheh.projecttwo.utilities.UIUtils;

import java.util.Optional;


public class MajorEditorScreen extends YazanScreen {

    private final MajorData major;
    public MajorEditorScreen(MajorData major) {
        super("Major Editor Screen", "");
        this.major = major;
        setCenter(setup());
    }

    @Override
    public Node setup() {
        VBox vBox = new VBox(20);
        GridPane gp = new GridPane();

        Label nameL = UIUtils.label("Name", FontWeight.BOLD, 12, null);
        TextField nameF = new TextField(major == null ? "" : major.getMajor().getName());
        nameF.setPrefHeight(20);
        nameF.setPrefWidth(120);

        Label acceptanceGL = UIUtils.label("Acceptance Grade", FontWeight.BOLD, 12, null);
        TextField acceptanceGF = new TextField(major == null ? "" : major.getMajor().getAcceptanceGrade() + "");
        acceptanceGF.setPrefHeight(20);
        acceptanceGF.setPrefWidth(120);


        Label tawjihiL = UIUtils.label("Tawjihi Weight", FontWeight.BOLD, 12, null);
        TextField tawjihiF = new TextField(major == null ? "" : major.getMajor().getTawjihiWeight() + "");
        tawjihiF.setPrefHeight(20);
        tawjihiF.setPrefWidth(120);

        Label placementTL = UIUtils.label("Placement Test Grade", FontWeight.BOLD, 12, null);
        TextField placementTF = new TextField(major == null ? "" : major.getMajor().getPlacementTestWeight() + "");
        acceptanceGF.setPrefHeight(20);
        acceptanceGF.setPrefWidth(120);

        gp.setHgap(20);
        gp.setVgap(20);

        gp.setAlignment(Pos.CENTER);

        gp.add(nameL, 0, 0);
        gp.add(nameF, 0, 1);

        gp.add(acceptanceGL, 0, 2);
        gp.add(acceptanceGF, 0, 3);

        gp.add(tawjihiL, 1, 0);
        gp.add(tawjihiF, 1, 1);

        gp.add(placementTL, 1, 2);
        gp.add(placementTF, 1, 3);

        HBox actions = new HBox(10);
        Button insert = new Button("Insert");
        insert.setDisable(major != null);
        insert.setOnAction(e -> {
            if (!allFilledAndCorrect(nameF, acceptanceGF, tawjihiF, placementTF)) {
                UIUtils.alert("One or more of the fields contained invalid values", Alert.AlertType.ERROR).show();
                return;
            }

            MajorData majorData = new MajorData(new Major(nameF.getText(), Integer.parseInt(acceptanceGF.getText()),
                    Double.parseDouble(tawjihiF.getText()), Double.parseDouble(placementTF.getText())));
            Main.getPrimaryManager().getMajorsManager().getMajors().insert(majorData);

            UIUtils.alert("Success!", Alert.AlertType.INFORMATION).show();

            nameF.clear();
            acceptanceGF.clear();
            tawjihiF.clear();
            placementTF.clear();
        });

        Button update = new Button("Update");
        update.setDisable(major == null);

        update.setOnAction(e -> {
            if (!allFilledAndCorrect(nameF, acceptanceGF, tawjihiF, placementTF)) {
                UIUtils.alert("One or more of the fields contained invalid values", Alert.AlertType.ERROR).show();
                return;
            }

            Optional<ButtonType> confirmation = UIUtils.alert("Are you sure you'd like to update these values?", Alert.AlertType.CONFIRMATION).showAndWait();

            if (confirmation.isEmpty()) return;
            if (confirmation.get() == ButtonType.NO) return;

            if (major == null) return;
            major.getMajor().setName(nameF.getText());
            major.getMajor().setAcceptanceGrade(Integer.parseInt(acceptanceGF.getText()));
            major.getMajor().setTawjihiWeight(Double.parseDouble(tawjihiF.getText()));
            major.getMajor().setPlacementTestWeight(Double.parseDouble(placementTF.getText()));

            UIUtils.alert("Success!", Alert.AlertType.INFORMATION).show();

            nameF.clear();
            acceptanceGF.clear();
            tawjihiF.clear();
            placementTF.clear();
        });

        Button back = new Button("Back");
        acceptanceGF.setPrefHeight(20);
        acceptanceGF.setPrefWidth(120);

        back.setOnAction(e -> {
            UIHandler.getInstance().open(new MajorsScreen(), 500, 500);
        });

        actions.getChildren().addAll(insert, update, back);
        actions.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(gp, actions);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    private boolean allFilledAndCorrect(TextField name, TextField acceptanceGrade, TextField tawjihi, TextField placement) {
        if (name.getText().isEmpty() || acceptanceGrade.getText().isEmpty() || tawjihi.getText().isEmpty() || placement.getText().isEmpty())
            return false;

        try {
            Integer.parseInt(acceptanceGrade.getText());
            Double.parseDouble(tawjihi.getText());
            Double.parseDouble(placement.getText());
        } catch (NumberFormatException ex) { return false; }

        return true;
    }
}
