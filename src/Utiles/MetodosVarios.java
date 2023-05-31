package Utiles;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JTextField;
/**
 * Clase que contiene varios métodos útiles.
 * Estos métodos realizan tareas como cerrar ventanas, cancelar campos de texto y actualizar datos en la base de datos.
 * 
 * @author David y Sergio
 */
public class MetodosVarios {

    /**
     * Cierra la ventana actual en respuesta a un evento.
     * 
     * @param event El evento que desencadena el cierre de la ventana.
     */
    public static void cerrarVentanas(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Cancela la edición de los campos de texto, restableciéndolos a sus valores iniciales.
     * 
     * @param id El campo de texto para el ID.
     * @param nombre El campo de texto para el nombre.
     * @param cantidad El campo de texto para la cantidad.
     * @param unidad El ComboBox para la unidad.
     * @param precio El campo de texto para el precio.
     * @param cantMin El campo de texto para la cantidad mínima.
     */
    public void cancelar(TextField id, TextField nombre, TextField cantidad, ComboBox unidad, TextField precio, TextField cantMin) {
        id.setText("");
        nombre.setText("");
        cantidad.setText("");
        unidad.setValue("");
        precio.setText("");
        cantMin.setText("");
    }

    /**
     * Actualiza la cantidad de un producto en la base de datos.
     * 
     * @param cantidad La nueva cantidad del producto.
     * @param id El ID del producto a actualizar.
     * @return true si la actualización se realizó correctamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la actualización.
     */
    public boolean actualizar(double cantidad, int id) throws SQLException {
        Conexion conexion = new Conexion();

        // Formo el SQL
        String SQL = "";
        SQL += "UPDATE productos SET ";
        SQL += "pro_cantidad=" + cantidad + "";
        SQL += " WHERE pro_id = '" + id + "'";

        // Recupero las filas
        int filas = conexion.ejecutarInstruccion(SQL);
        conexion.cerrarConexion();

        return filas > 0;
    }

}