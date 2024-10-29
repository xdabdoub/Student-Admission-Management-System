package me.yhamarsheh.projecttwo.ui.screens.sub;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.yhamarsheh.projecttwo.Main;
import me.yhamarsheh.projecttwo.objects.sub.MajorData;
import me.yhamarsheh.projecttwo.structure.DoublyLinkedList;
import me.yhamarsheh.projecttwo.structure.nodes.sub.DNode;
import me.yhamarsheh.projecttwo.ui.UIHandler;
import me.yhamarsheh.projecttwo.ui.screens.YazanScreen;
import me.yhamarsheh.projecttwo.utilities.UIUtils;

import java.util.Optional;

public class MajorsScreen extends YazanScreen {

    public MajorsScreen() {
        super("Majors Screen", "View the majors easily");
    }

    @Override
    public Node setup() {
        VBox vBox = new VBox(20);

        TableView<MajorData> tableView = new TableView<>();

        TableColumn<MajorData, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMajor().getName()));

        TableColumn<MajorData, Integer> acceptanceCol = new TableColumn<>("Acceptance Grade");
        acceptanceCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMajor().getAcceptanceGrade()).asObject());

        TableColumn<MajorData, Double> tawjihiWCol = new TableColumn<>("Tawjihi Weight");
        tawjihiWCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getMajor().getTawjihiWeight()).asObject());

        TableColumn<MajorData, Double> placementTCol = new TableColumn<>("Placement Test Weight");
        placementTCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getMajor().getPlacementTestWeight()).asObject());

        tableView.getColumns().add(nameCol);
        tableView.getColumns().add(acceptanceCol);
        tableView.getColumns().add(tawjihiWCol);
        tableView.getColumns().add(placementTCol);

        addMajorsToTableView(tableView);

        HBox searchBox = new HBox(10);
        TextField searchBar = new TextField();
        searchBar.setPromptText("\uD83D\uDD0D Enter search");
        searchBar.setPrefWidth(970);

        searchBar.setOnKeyPressed(e -> {
            if (searchBar.getText().isEmpty()) {
                addMajorsToTableView(tableView);
                return;
            }

            tableView.getItems().clear();
            DNode<MajorData> current = getMajorByPartOfName(searchBar.getText()).getHead();
            while (current != null) {
                tableView.getItems().add(current.getData());
                current = current.getNext();
            }
        });

        searchBox.getChildren().addAll(searchBar);
        searchBox.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(20);
        Button insert = new Button("Insert");
        insert.setPrefWidth(120);
        insert.setPrefHeight(20);

        insert.setOnAction(e -> {
            UIHandler.getInstance().open(new MajorEditorScreen(null), 500, 500);
        });

        Button update = new Button("Update");
        update.setPrefWidth(120);
        update.setPrefHeight(20);

        update.setOnAction(e -> {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                UIUtils.alert("A major must be selected to update!", Alert.AlertType.ERROR).show();
                return;
            }

            UIHandler.getInstance().open(new MajorEditorScreen(tableView.getSelectionModel().getSelectedItem()), 500, 500);
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

            Main.getPrimaryManager().getMajorsManager().getMajors().delete(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
            tableView.refresh();

            UIUtils.alert("Success!", Alert.AlertType.INFORMATION).show();
        });

        Button viewStudents = new Button("View Students");
        viewStudents.setPrefWidth(120);
        viewStudents.setPrefHeight(20);

        viewStudents.setOnAction(e -> {
            UIHandler.getInstance().open(new StudentsScreen(tableView.getSelectionModel().getSelectedItem()), 500, 500);
        });

        buttons.getChildren().addAll(insert, update, delete, viewStudents);
        buttons.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(searchBox, tableView, buttons);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    private void addMajorsToTableView(TableView<MajorData> tableView) {
        tableView.getItems().clear();
        DNode<MajorData> current = Main.getPrimaryManager().getMajorsManager().getMajors().getHead();
        while (current != null) {
            tableView.getItems().add(current.getData());
            current = current.getNext();
        }
    }

    private DoublyLinkedList<MajorData> getMajorByPartOfName(String partOfName) {
        DoublyLinkedList<MajorData> majorDataDoublyLinkedList = new DoublyLinkedList<>();
        DNode<MajorData> current = Main.getPrimaryManager().getMajorsManager().getMajors().getHead();
        while (current != null) {
            if (current.getNext() != null && current.getData().getMajor().getName().compareTo(current.getNext().getData().getMajor().getName()) < 0)
                break;

            if (current.getData().getMajor().getName().contains(partOfName))
                majorDataDoublyLinkedList.insert(current.getData());

            current = current.getNext();
        }

        return majorDataDoublyLinkedList;
    }
}
