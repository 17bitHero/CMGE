package Expression.gui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GeneExpApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root =
                    (BorderPane)loader.load(getClass().getResource("GeneExpGUI.fxml").openStream());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setTitle("Cellular Model of Basic Genetics");
            primaryStage.getIcons().add(new Image(GeneExpApp.class.getResourceAsStream("icon.png")));
            root.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
