package gui.visual;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author doyenm
 */
public class PlayScene {

    final Stage stage;

    public PlayScene(Stage stage) {
        this.stage = stage;
        final Tab zooTab = new PlayTab("Zoo", stage);
        zooTab.setClosable(false);
        final Tab padTab = new PlayTab("Paddocks", stage);
        padTab.setClosable(false);
        final Tab animalTab = new PlayTab("Animals", stage);
        animalTab.setClosable(false);
        final Tab cTTab = new PlayTab("Care takers", stage);
        cTTab.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().setAll(zooTab, padTab, animalTab, cTTab);
        tabPane.setSide(Side.LEFT);
        
        
        this.stage.setScene(new Scene(tabPane, 300, 275));
        this.stage.setFullScreen(true);
    }
}
