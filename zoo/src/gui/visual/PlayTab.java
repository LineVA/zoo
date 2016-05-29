package gui.visual;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author doyenm
 */
public class PlayTab extends Tab {

    final TextArea textArea = new TextArea();
    final Stage stage;

    public PlayTab(String name, Stage stage) {
        super(name);
        this.stage = stage;
        GridPane grid = new GridPane();

        grid.setGridLinesVisible(true);

        // Two columns 
        grid.getColumnConstraints().setAll(
                new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        grid.getColumnConstraints().get(0).setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().get(1).setHgrow(Priority.ALWAYS);
        // Three rows
        grid.getRowConstraints().setAll(
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        grid.getRowConstraints().get(0).setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().get(1).setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().get(2).setVgrow(Priority.ALWAYS);

        TextArea shell = new TextArea();
        GridPane.setConstraints(shell, 1, 1);

        Button evaluate = new Button("Evaluate");
        GridPane.setConstraints(evaluate, 1, 2);

        LeftZoo zoo = new LeftZoo();
        GridPane.setConstraints(zoo, 0, 0, 1, Integer.MAX_VALUE);

        grid.getChildren().addAll(shell, evaluate, zoo);
        this.setContent(grid);

    }

}
