package gui.shellGui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author doyenm
 */
public class ShellGui extends Application{
  @Override
    public void start(Stage stage) {
        StackPane pane = new StackPane();
        TextArea text = new TextArea();
        text.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        text.setStyle("-fx-background-color-content: #FF0000");
        pane.getChildren().add(text);
        Scene scene = new Scene(pane, 500, 500);
        
        
        stage.setTitle("Zoo");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
