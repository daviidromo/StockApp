package Controlador;


import Utiles.MetodosVarios;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Controlador de la vista FXML_Login.fxml
 * Se encarga de gestionar el inicio de sesión en la aplicación de inventario.
 * @author david y sergio 
 */
public class FXML_LoginController implements Initializable {

    @FXML
    private TextField log_usuario;
    @FXML
    private TextField log_contraseña;
    @FXML
    private Button log_boton_cancelar;
    @FXML
    private Button log_boton_aceptar;

    /**
     * Inicializa el controlador.
     * @param url la URL de inicialización
     * @param rb los recursos de inicialización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * Maneja el evento del botón "log_boton_cancelar".
     * Cierra la ventana actual.
     * @param event el evento de acción generado por el botón
     */
    @FXML
    private void log_boton_cancelar(ActionEvent event) {
        MetodosVarios.cerrarVentanas(event);
    }

    /**
     * Maneja el evento del botón "log_boton_aceptar".
     * Realiza la validación del usuario y la contraseña introducidos y muestra la ventana principal si son correctos.
     * @param event el evento de acción generado por el botón
     */
    @FXML
    private void log_boton_aceptar(ActionEvent event) {
        String usuario = "admin";
        String contraseña = "admin";

        try {
            if (usuario.equals(log_usuario.getText()) && contraseña.equals(log_contraseña.getText())) {
                // Carga la vista FXML_Principal.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/FXML_Principal.fxml"));
                
                MetodosVarios.cerrarVentanas(event);

                // Carga el padre
                Parent root = loader.load();

                // Crea la escena
                Scene scene = new Scene(root);

                // Crea la etapa
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("StockApp");
                Image icon = new Image(getClass().getResourceAsStream("/Utiles/favicon.png"));
                stage.getIcons().add(icon);
                stage.showAndWait();
            } else if (usuario.equals(log_usuario.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Contraseña incorrecta");
                alert.showAndWait();
            } else if (contraseña.equals(log_contraseña.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Usuario incorrecto");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Usuario y contraseña incorrectos");
                alert.showAndWait();
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    } 
}