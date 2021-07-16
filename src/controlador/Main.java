
package controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/ProductoVista.fxml"));
            Pane ventana = (Pane) loader.load();
            int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
            int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
            Scene scene = new Scene(ventana, screenWidth,screenHeight);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            System.out.println(screenWidth + "  "+screenHeight) ;
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
