package gui.visual;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author doyenm
 */
public class ZooGui extends Application {

    @Override
    public void start(Stage stage) {
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));

        Scene scene = new Scene(vbButtons, 300, 275);
        stage.setScene(scene);

        Button newGame = new Button("New game");
        newGame.setMaxWidth(Double.MAX_VALUE);
        newGame.setOnAction(new ZooCreation(stage));

        Button challengeGame = new Button("Challenge game");
        challengeGame.setMaxWidth(Double.MAX_VALUE);

        Button loadGame = new Button("Load game");
        loadGame.setMaxWidth(Double.MAX_VALUE);

        Button options = new Button("Options");
        options.setMaxWidth(Double.MAX_VALUE);

        Button credits = new Button("Credits");
        credits.setMaxWidth(Double.MAX_VALUE);

        vbButtons.getChildren().addAll(newGame, challengeGame, loadGame, options, credits);

        stage.setTitle("Zoo");

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
