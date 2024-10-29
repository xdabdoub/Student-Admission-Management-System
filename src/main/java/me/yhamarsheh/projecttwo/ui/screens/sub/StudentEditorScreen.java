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
import me.yhamarsheh.projecttwo.objects.Student;
import me.yhamarsheh.projecttwo.objects.sub.MajorData;
import me.yhamarsheh.projecttwo.structure.SingleLinkedList;
import me.yhamarsheh.projecttwo.structure.nodes.sub.DNode;
import me.yhamarsheh.projecttwo.ui.UIHandler;
import me.yhamarsheh.projecttwo.ui.screens.YazanScreen;
import me.yhamarsheh.projecttwo.utilities.UIUtils;

import java.util.Optional;

public class StudentEditorScreen extends YazanScreen {

    private final Student student;
    private final MajorData major;
    public StudentEditorScreen(Student student, MajorData major) {
        super("Student Editor Screen", "");
        this.student = student;
        this.major = major;
        setCenter(setup());
    }

    @Override
    public Node setup() {
        VBox vBox = new VBox(20);
        GridPane gp = new GridPane();

        Label idL = UIUtils.label("ID", FontWeight.BOLD, 12, null);
        TextField idF = new TextField(student == null ? "" : student.getId() + "");
        idF.setPrefHeight(20);
        idF.setPrefWidth(120);

        Label nameL = UIUtils.label("Name", FontWeight.BOLD, 12, null);
        TextField nameF = new TextField(student == null ? "" : student.getName());
        nameF.setPrefHeight(20);
        nameF.setPrefWidth(120);

        Label tawjihiGL = UIUtils.label("Tawjihi Grade", FontWeight.BOLD, 12, null);
        TextField tawjihiGF = new TextField(student == null ? "" : student.getTawjihiGrade() + "");
        tawjihiGF.setPrefHeight(20);
        tawjihiGF.setPrefWidth(120);


        Label placementTL = UIUtils.label("Placement Test Grade", FontWeight.BOLD, 12, null);
        TextField placementTF = new TextField(student == null ? "" : student.getPlacementTestGrade() + "");
        placementTF.setPrefHeight(20);
        placementTF.setPrefWidth(120);

        Label admissionML = UIUtils.label("Admission Mark", FontWeight.BOLD, 12, null);
        TextField admissionMF = new TextField(student == null ? "" : student.getAdmissionMark() + "");
        admissionMF.setPrefHeight(20);
        admissionMF.setPrefWidth(120);
        admissionMF.setDisable(true);

        Label majorL = UIUtils.label("Major Selection", FontWeight.BOLD, 12, null);
        ComboBox<MajorData> majorsCB = new ComboBox<>();
        majorsCB.setPrefHeight(20);
        majorsCB.setPrefWidth(120);


        MajorData any = new MajorData(new Major("Auto", 0, 0, 0));
        majorsCB.getItems().add(any);

        majorsCB.setValue(major == null ? any : major);

        DNode<MajorData> current = Main.getPrimaryManager().getMajorsManager().getMajors().getHead();
        while (current != null) {
            majorsCB.getItems().add(current.getData());

            current = current.getNext();
        }


        gp.setHgap(20);
        gp.setVgap(20);

        gp.setAlignment(Pos.CENTER);

        gp.add(idL, 0, 0);
        gp.add(idF, 0, 1);

        gp.add(nameL, 1, 0);
        gp.add(nameF, 1, 1);

        gp.add(tawjihiGL, 0, 2);
        gp.add(tawjihiGF, 0, 3);

        gp.add(placementTL, 1, 2);
        gp.add(placementTF, 1, 3);

        gp.add(admissionML, 2, 0);
        gp.add(admissionMF, 2, 1);

        gp.add(majorL, 2, 2);
        gp.add(majorsCB, 2, 3);

        HBox actions = new HBox(10);
        Button insert = new Button("Insert");
        insert.setDisable(student != null);

        insert.setOnAction(e -> {
            if (!allFilledAndCorrect(idF, nameF, tawjihiGF, placementTF)) {
                UIUtils.alert("One or more of the fields contained invalid values", Alert.AlertType.ERROR).show();
                return;
            }

            Student newStudent = new Student(Integer.parseInt(idF.getText()), nameF.getText(), Double.parseDouble(tawjihiGF.getText()),
                    Double.parseDouble(placementTF.getText()));

            if (majorsCB.getValue().getMajor().getName().equalsIgnoreCase("Auto")) {
                // TODO: Initial Suggestion
                return;
            } else {
                // TODO: Alternative Suggestion
                return;
            }

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

        actions.getChildren().addAll(insert);
        actions.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(gp, actions);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    private boolean allFilledAndCorrect(TextField id, TextField name, TextField tawjihi, TextField placement) {
        if (id.getText().isEmpty() || name.getText().isEmpty() || tawjihi.getText().isEmpty() || placement.getText().isEmpty())
            return false;

        try {
            Integer.parseInt(id.getText());
            Double.parseDouble(tawjihi.getText());
            Double.parseDouble(placement.getText());
        } catch (NumberFormatException ex) { return false; }

        return true;
    }

    private SingleLinkedList<MajorData> checkForMajorsBasedOnAM(Student student) {
        SingleLinkedList<MajorData> canEnrollIn = new SingleLinkedList<>();
        DNode<MajorData> current = Main.getPrimaryManager().getMajorsManager().getMajors().getHead();

        while (current != null) {
            Major currentMajor = current.getData().getMajor();
            double tempAdmissionMark = (student.getTawjihiGrade() * currentMajor.getTawjihiWeight())
                    + (student.getPlacementTestGrade() * currentMajor.getTawjihiWeight());

            if (currentMajor.getAcceptanceGrade() <= tempAdmissionMark) canEnrollIn.insert(current.getData());
            current = current.getNext();
        }

        return canEnrollIn;
    }
}

