package gui.visual;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author doyenm
 */
public class LeftZoo extends GridPane {

    public LeftZoo() {
        final Label nameLabel = new Label("Name : ");
        GridPane.setConstraints(nameLabel, 0, 0);
        final TextField nameField = new TextField();
        GridPane.setConstraints(nameField, 1, 0);
        nameField.clear();
        nameField.insertText(0, "Zoo");
        nameField.setEditable(false);

        this.getChildren().addAll(nameLabel, nameField);
    }

}
