package stockapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * Clase principal de la aplicación StockApp.
 * Esta clase inicia la aplicación y muestra la ventana de inicio de sesión.
 * Extiende la clase Application de JavaFX.
 * 
 * @author David y Sergio
 */
public class StockApp extends Application {
    
    /**
     * Método de inicio de la aplicación.
     * Carga y muestra la ventana de inicio de sesión.
     * 
     * @param stage El escenario principal de la aplicación.
     * @throws Exception Si ocurre un error al cargar la ventana.
     */
    @Override
    public void start(Stage stage) throws Exception  {
         Parent root = FXMLLoader.load(getClass().getResource("/Vistas/FXML_Login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("StockApp");
        stage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("/Utiles/favicon.png"));
        stage.getIcons().add(icon);
        stage.show();
    }

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Los argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}