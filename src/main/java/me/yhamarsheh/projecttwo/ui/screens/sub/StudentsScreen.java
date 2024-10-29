package me.yhamarsheh.projecttwo.ui.screens.sub;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.yhamarsheh.projecttwo.objects.Student;
import me.yhamarsheh.projecttwo.objects.sub.MajorData;
import me.yhamarsheh.projecttwo.structure.SingleLinkedList;
import me.yhamarsheh.projecttwo.structure.nodes.sub.SNode;
import me.yhamarsheh.projecttwo.ui.UIHandler;
import me.yhamarsheh.projecttwo.ui.screens.YazanScreen;
import me.yhamarsheh.projecttwo.utilities.UIUtils;

import java.util.Optional;

public class StudentsScreen extends YazanScreen {

    private MajorData majorData;
    public StudentsScreen(MajorData majorData) {
        super("Students Screen", "View the students easily");
        this.majorData = majorData;
        setCenter(setup());
    }

    @Override
    public Node setup() {
        VBox vBox = new VBox(20);

        TableView<Student> tableView = new TableView<>();

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Student, Double> tawjihiWCol = new TableColumn<>("Tawjihi Grade");
        tawjihiWCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTawjihiGrade()).asObject());

        TableColumn<Student, Double> placementTCol = new TableColumn<>("Placement Test Grade");
        placementTCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPlacementTestGrade()).asObject());

        TableColumn<Student, Integer> admissionMCol = new TableColumn<>("Admission Mark");
        placementTCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getAdmissionMark()).asObject());

        tableView.getColumns().add(idCol);
        tableView.getColumns().add(nameCol);
        tableView.getColumns().add(tawjihiWCol);
        tableView.getColumns().add(placementTCol);
        tableView.getColumns().add(admissionMCol);

        addStudentsToTable(tableView);

        HBox searchBox = new HBox(10);
        TextField searchBar = new TextField();
        searchBar.setPromptText("\uD83D\uDD0D Enter search");
        searchBar.setPrefWidth(970);

        searchBar.setOnKeyPressed(e -> {
            if (searchBar.getText().isEmpty()) {
                addStudentsToTable(tableView);
                return;
            }

            tableView.getItems().clear();
            SNode<Student> current = getStudentByPartOfName(searchBar.getText()).getHead();
            while (current != null) {
                tableView.getItems().add(current.getData());
                current = (SNode<Student>) current.getNext();
            }
        });

        searchBox.getChildren().addAll(searchBar);
        searchBox.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(20);
        Button insert = new Button("Insert");
        insert.setPrefWidth(120);
        insert.setPrefHeight(20);

        insert.setOnAction(e -> {
            UIHandler.getInstance().open(new StudentEditorScreen(null, majorData), 500, 500);
        });

        Button update = new Button("Update");
        update.setPrefWidth(120);
        update.setPrefHeight(20);

        update.setOnAction(e -> {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                UIUtils.alert("A major must be selected to update!", Alert.AlertType.ERROR).show();
                return;
            }

            UIHandler.getInstance().open(new StudentEditorScreen(tableView.getSelectionModel().getSelectedItem(), majorData), 500, 500);
        });

        Button delete = new Button("Delete");
        delete.setPrefWidth(120);
        delete.setPrefHeight(20);

        delete.setOnAction(e -> {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                UIUtils.alert("A major must be selected to delete!", Alert.AlertType.ERROR).show();
                return;
            }

            Optional<ButtonType> confirmation = UIUtils.alert("Are you sure you'd like to PERMANENTLY delete this Major?",
                    Alert.AlertType.CONFIRMATION).showAndWait();

            if (confirmation.isEmpty()) return;
            if (confirmation.get() == ButtonType.NO) return;

            majorData.getStudentData().getStudents().delete(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
            tableView.refresh();

            UIUtils.alert("Success!", Alert.AlertType.INFORMATION).show();
        });

        buttons.getChildren().addAll(insert, update, delete);
        buttons.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(searchBox, tableView, buttons);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    private void addStudentsToTable(TableView<Student> tableView) {
        if (majorData == null) return;
        tableView.getItems().clear();
        SNode<Student> current = this.majorData.getStudentData().getStudents().getHead();

        while (current != null) {
            tableView.getItems().add(current.getData());
            current = (SNode<Student>) current.getNext();
        }
    }

    private SingleLinkedList<Student> getStudentByPartOfName(String partOfName) {
        if (majorData == null) return new SingleLinkedList<>();

        SingleLinkedList<Student> studentSingleLinkedList = new SingleLinkedList<>();
        SNode<Student> current = this.majorData.getStudentData().getStudents().getHead();
        while (current != null) {
            if (current.getNext() != null && current.getData().getName().compareTo(current.getNext().getData().getName()) < 0)
                break;

            if (current.getData().getName().contains(partOfName))
                studentSingleLinkedList.insert(current.getData());

            current = (SNode<Student>) current.getNext();
        }

        return studentSingleLinkedList;
    }
}