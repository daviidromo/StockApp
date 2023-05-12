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
public class FXML_EditarProductoController implements Initializable {

    @FXML
    private TextField id_editarProducto;
    @FXML
    private TextField cantidadMinima_editarProducto;
    @FXML
    private TextField precio_editarProducto;
    @FXML
    private TextField unidad_editarProducto;
    @FXML
    private TextField cantidad_editarProducto;
    @FXML
    private TextField nombre_editarProducto;
    @FXML
    private Button aceptar_editarProducto;
    @FXML
    private Button restaurar_editarProducto;
    @FXML
    private Button cancelar_editarProducto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
