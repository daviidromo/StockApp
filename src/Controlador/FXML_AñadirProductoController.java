/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Utiles.Conexion;
import Utiles.MetodosVarios;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private boolean aceptar_añadirProducto(ActionEvent event) throws SQLException {
        String id = id_añadirProducto.getText();
        String nombre = nombre_añadirProducto.getText();
        String cantidad = cantidad_añadirProducto.getText();
        String unidad = unidad_añadirProducto.getText();
        String precio = precio_añadirProducto.getText();
        String cantMin = cantidadMinima_añadirProducto.getText();

        Conexion conexion = new Conexion();

        // Formo el SQL
        String SQL = "";
        SQL += "INSERT INTO productos VALUES('";
        SQL +=  id + "', '" + nombre + "', '";
        SQL += cantidad + "', '" + unidad + "', '" + precio+"','" ;
        SQL += cantMin + "' )";
        // Recupero las filas
        int filas = conexion.ejecutarInstruccion(SQL);

        conexion.cerrarConexion();
        MetodosVarios.cerrarVentanas(event);
        return filas > 0;
    
    }

    @FXML
    private void restaurar_añadirProducto(ActionEvent event) {
        // Abrimos una ventana de confirmacion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("CONFIRMAR");
        alert.setContentText("¿Quieres restaurar el producto?");
        // Cogemos el resultado del boton seleccionado
        Optional<ButtonType> action = alert.showAndWait();

        // Si hemos pulsado en aceptar
        if (action.get() == ButtonType.OK) {

            MetodosVarios m = new MetodosVarios();
            m.cancelar(id_añadirProducto, nombre_añadirProducto, cantidad_añadirProducto, unidad_añadirProducto, precio_añadirProducto, cantidadMinima_añadirProducto);

        } else {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setHeaderText(null);
            alertt.setTitle("Error");
            alertt.setContentText("No se ha restaurado el producto");
            alertt.showAndWait();
        }

    }

    @FXML
    private void cancelar_añadirProducto(ActionEvent event) {
        MetodosVarios.cerrarVentanas(event);
    }

}
