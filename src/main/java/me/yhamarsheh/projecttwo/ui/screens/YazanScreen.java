package me.yhamarsheh.projecttwo.ui.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import me.yhamarsheh.projecttwo.utilities.UIUtils;

public abstract class YazanScreen extends BorderPane {

    protected String title;
    protected String description;
    public YazanScreen(String title, String description) {
        this.title = title;
        this.description = description;

        init();
    }

    private void init() {
        setPadding(new Insets(20, 20, 20, 20));

        VBox header = new VBox(10);
        Label titleLabel = UIUtils.label(title, FontWeight.BOLD, 24, null);
        Label descriptionLabel = UIUtils.label(description, null, 16, null);

        header.getChildren().addAll(titleLabel, descriptionLabel);
        header.setAlignment(Pos.CENTER);


        setTop(header);
        setCenter(setup());
    }

    public abstract Node setup();

}
