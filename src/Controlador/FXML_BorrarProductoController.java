/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author byrom
 */
public class FXML_BorrarProductoController implements Initializable {

    @FXML
    private TextField id_BorrarProducto;
    @FXML
    private TextField cantidadMinima_añadirProducto;
    @FXML
    private TextField precio_BorrarProducto;
    @FXML
    private TextField unidad_BorrarProducto;
    @FXML
    private TextField cantidad_BorrarProducto;
    @FXML
    private TextField nombre_BorrarProducto;
    @FXML
    private Button aceptar_BorrarProducto;
    @FXML
    private Button restaurar_BorrarProducto;
    @FXML
    private Button cancelar_BorrarProducto;
    @FXML
    private TextField cantidadMinima_añadirProducto1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aceptar_añadirProducto(ActionEvent event) {
    }

    @FXML
    private void restaurar_añadirProducto(ActionEvent event) {
    }

    @FXML
    private void cancelar_añadirProducto(ActionEvent event) {
    }
    
}
