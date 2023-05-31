package Controlador;

import Modelo.Productos;
import Utiles.Conexion;
import Utiles.MetodosVarios;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * Controlador de la vista FXML_AñadirProducto.fxml.
 * Se encarga de gestionar la funcionalidad de la interfaz gráfica de usuario
 * relacionada con la adición de productos en la aplicación.
 * @author david y sergio 
 */
public class FXML_AñadirProductoController implements Initializable {

    @FXML
    private TextField id_añadirProducto;
    @FXML
    private TextField cantidadMinima_añadirProducto;
    @FXML
    private TextField precio_añadirProducto;
    @FXML
    private ComboBox<String> unidad_añadirProducto;
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
     * Inicializa el controlador después de que se haya cargado el archivo FXML.
     * Configura el combo box "unidad_añadirProducto" añadiendo opciones predefinidas.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unidad_añadirProducto.getItems().add("Kilogramos");
        unidad_añadirProducto.getItems().add("Litros");
        unidad_añadirProducto.getItems().add("Unidades");
    }

    /**
     * Maneja el evento del botón "aceptar_añadirProducto".
     * Realiza la validación de los campos ingresados y guarda los valores en la base de datos.
     *
     * @param event el evento de acción generado por el botón
     * @return true si se ha guardado el producto correctamente, false en caso contrario
     * @throws SQLException si ocurre un error en la ejecución de la consulta SQL
     */
    @FXML
    private boolean aceptar_añadirProducto(ActionEvent event) throws SQLException {

        String id = id_añadirProducto.getText();
        int idComprobar = 0;
        try {
            idComprobar = Integer.parseInt(id);

            boolean idExists = false;

            // Recorre la lista de productos existentes
            for (Productos product : FXML_PrincipalController.getProductList()) {
                if (product.getId() == idComprobar) {
                    idExists = true;
                    break;
                }
            }

            // Verifica si el ID ya existe
            if (idExists) {
                Alert alertt = new Alert(Alert.AlertType.ERROR);
                alertt.setHeaderText(null);
                alertt.setTitle("Error");
                alertt.setContentText("El id existe");
                alertt.showAndWait();
            } else {
                String nombre = nombre_añadirProducto.getText();
                String cantidad = cantidad_añadirProducto.getText();
                String unidad = unidad_añadirProducto.getValue();
                String precio = precio_añadirProducto.getText();
                String cantMin = cantidadMinima_añadirProducto.getText();

                double cantidadI = 0;
                double cantidadM = 0;
                double cantidadComprobar = 0;
                double precioComprobar = 0;
                double cantidadMinComprobar = 0;
                try {
                    cantidadComprobar = Double.parseDouble(cantidad);
                    precioComprobar = Double.parseDouble(precio);
                    cantidadMinComprobar = Double.parseDouble(cantMin);

                    cantidadI = Double.parseDouble(cantidad);
                    cantidadM = Double.parseDouble(cantMin);
                } catch (NumberFormatException e1) {
                    Alert alertt = new Alert(Alert.AlertType.ERROR);
                    alertt.setHeaderText(null);
                    alertt.setTitle("Error");
                    alertt.setContentText("Uno de los campos (cantidad, precio y/o cantidad minima no es un numero");
                    alertt.showAndWait();

                }
                if (cantidadM >= cantidadI) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Debes poner menos cantidad minima de la cantidad que hay");
                    alert.showAndWait();
                } else {

                    Conexion conexion = new Conexion();

                    // Formo el SQL
                    String SQL = "";
                    SQL += "INSERT INTO productos VALUES('";
                    SQL += id + "', '" + nombre + "', '";
                    SQL += cantidad + "', '" + unidad + "', '" + precio + "','";
                    SQL += cantMin + "' )";
                    // Recupero las filas
                    int filas = conexion.ejecutarInstruccion(SQL);

                    conexion.cerrarConexion();
                    MetodosVarios.cerrarVentanas(event);
                    return filas > 0;
                }
            }
        } catch (NumberFormatException e) {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setHeaderText(null);
            alertt.setTitle("Error");
            alertt.setContentText("El id no es un numero");
            alertt.showAndWait();

        }

        return false;
    }

    /**
     * Maneja el evento del botón "restaurar_añadirProducto".
     * Restaura los campos de texto a sus valores predeterminados.
     *
     * @param event el evento de acción generado por el botón
     */
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

    /**
     * Maneja el evento del botón "cancelar_añadirProducto".
     * Cierra la ventana actual.
     *
     * @param event el evento de acción generado por el botón
     */
    @FXML
    private void cancelar_añadirProducto(ActionEvent event) {
        MetodosVarios.cerrarVentanas(event);
    }

}