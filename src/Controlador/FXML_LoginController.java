/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sergi
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
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void log_boton_cancelar(ActionEvent event) {
          MetodosVarios.cerrarVentanas(event);

    }

    @FXML
    private void log_boton_aceptar(ActionEvent event) {
       
        String usuario = "";
        String contraseña = "";
        
        
        try {
            if (usuario.equals(log_usuario.getText()) && contraseña.equals(log_contraseña.getText())){
                
                // carga la vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/FXML_Principal.fxml"));
                
                MetodosVarios.cerrarVentanas(event);

                // Cargo el padre
                Parent root = loader.load();

                // Creo la scene
                Scene scene = new Scene(root);

                // Creo la stage
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("StockApp");
                stage.showAndWait();
                }
            else if(usuario.equals(log_usuario.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Contraseña incorrecto");
                alert.showAndWait();
            }
            else if(contraseña.equals(log_contraseña.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Usuario incorrecto");
                alert.showAndWait();
            }
            else{
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
