/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Productos;
import Utiles.MetodosVarios;
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
public class FXML_AñadirProductoController implements Initializable {

    @FXML
    private TextField id_añadirProducto;
    @FXML
    private TextField cantidadMinima_añadirProducto;
    @FXML
    private TextField precio_añadirProducto;
    @FXML
    private TextField unidad_añadirProducto;
    @FXML
    private TextField cantidad_añadirProducto;
    @FXML
    private TextField nombre_añadirProducto;
    @FXML
    private Button aceptar_añadirProducto;
    @FXML
    private Button restaurar_añadirProducto;
    @FXML
    private Button cancelar_añadirProducto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void aceptar_añadirProducto(ActionEvent event) {
        
    }

    @FXML
    private void restaurar_añadirProducto(ActionEvent event) {
    }

    @FXML
    private void cancelar_añadirProducto(ActionEvent event) {
        MetodosVarios.cerrarVentanas(event);
    }

    
    
 
   
    
}
