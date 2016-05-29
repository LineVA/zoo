package gui.visual;

import gui.visual.onAction.VisualCreateZoo;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author doyenm
 */
public class ZooCreation implements EventHandler {

    final Stage stage;

    public ZooCreation(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(Event event) {

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.setPadding(new Insets(0, 20, 10, 20));

        final Label nameLabel = new Label("Name : ");
        GridPane.setConstraints(nameLabel, 0, 0);
        final TextField nameField = new TextField();
        GridPane.setConstraints(nameField, 1, 0);
        final Label widthLabel = new Label("Width : ");
        GridPane.setConstraints(widthLabel, 0, 1);
        final TextField widthField = new TextField();
        GridPane.setConstraints(widthField, 2, 0);
        final Label heightLabel = new Label("Height : ");
        GridPane.setConstraints(heightLabel, 0, 2);
        final TextField heightField = new TextField();
        GridPane.setConstraints(heightField, 3, 0);
        Button btn = new Button("Create the zoo");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String[] cmd = {nameField.getText(), widthField.getText(), heightField.getText()};
                CommandVisual command = new VisualCreateZoo(cmd);
                command.execute(cmd);
                PlayScene play = new PlayScene(stage);
            }
        });

        vb.getChildren().addAll(nameLabel, nameField, widthLabel, widthField,
                heightLabel, heightField, hbBtn);

        this.stage.setScene(new Scene(vb, 400, 300));
//        this.stage.setFullScreen(true);
    }
}
