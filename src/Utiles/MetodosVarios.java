/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JTextField;

/**
 *
 * @author sergi
 */
public class MetodosVarios {

    public static void cerrarVentanas(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void cancelar(TextField id, TextField nombre, TextField cantidad, TextField unidad, TextField precio, TextField cantMin) {
        id.setText("");
        nombre.setText("");
        cantidad.setText("");
        unidad.setText("");
        precio.setText("");
        cantMin.setText("");
    }

    public boolean actualizar(double cantidad, int id) throws SQLException {
        Conexion conexion = new Conexion();

        // Formo el SQL
        String SQL = "";
        SQL += "UPDATE productos SET ";
        SQL += "pro_cantidad=" + cantidad + "";
        SQL += " WHERE pro_id = '" + id + "'";

        // Recupero las filas
        int filas = conexion.ejecutarInstruccion(SQL);
        /*libros1(event);
            libros2(event);
            libros3(event);*/
        conexion.cerrarConexion();

        return filas > 0;
    }


}
