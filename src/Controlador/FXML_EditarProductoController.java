package Controlador;

import Modelo.Productos;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Controlador de la vista FXML_EditarProducto.fxml
 * Se encarga de gestionar la edición de productos en la aplicación de inventario.
 * @author david y sergio 
 */
public class FXML_EditarProductoController implements Initializable {

    @FXML
    private TextField id_editarProducto;
    @FXML
    private TextField cantidadMinima_editarProducto;
    @FXML
    private TextField precio_editarProducto;
    @FXML
    private ComboBox<String> unidad_editarProducto;
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
    private Productos p;

    /**
     * Inicializa el controlador.
     * Configura el ComboBox "unidad_editarProducto" con las opciones de unidad.
     * @param url la URL de inicialización
     * @param rb  los recursos de inicialización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unidad_editarProducto.getItems().add("Kilogramos");
        unidad_editarProducto.getItems().add("Litros");
        unidad_editarProducto.getItems().add("Unidades");
    }

    /**
     * Inicializa los atributos del producto a editar.
     * @param a el producto a editar
     */
    void initAttributes(Productos a) {
        p = a;
        id_editarProducto.setText(a.getId() + "");
        nombre_editarProducto.setText(a.getNombre() + "");
        cantidad_editarProducto.setText(a.getCantidad() + "");
        unidad_editarProducto.setValue(a.getUnidad() + "");
        precio_editarProducto.setText(a.getPrecio() + "");
        cantidadMinima_editarProducto.setText(a.getCantidadMinima() + "");
    }

    /**
     * Maneja el evento del botón "aceptar_editarProducto".
     * Realiza la validación de los campos y actualiza los datos del producto en la base de datos.
     * @param event el evento de acción generado por el botón
     * @return true si la actualización es exitosa, false en caso contrario
     * @throws SQLException si ocurre un error en la consulta SQL
     */
    @FXML
    private boolean aceptar_editarProducto(ActionEvent event) throws SQLException {

        String id = id_editarProducto.getText();
        String nombre = nombre_editarProducto.getText();
        String cantidad = cantidad_editarProducto.getText();
        String unidad = unidad_editarProducto.getValue();
        String precio = precio_editarProducto.getText();
        String cantidadMin = cantidadMinima_editarProducto.getText();

        double cantidadI = 0;
        double cantidadM = 0;
        double cantidadComprobar = 0;
        double precioComprobar = 0;
        double cantidadMinComprobar = 0;
        try {
            cantidadComprobar = Double.parseDouble(cantidad);
            precioComprobar = Double.parseDouble(precio);
            cantidadMinComprobar = Double.parseDouble(cantidadMin);
            cantidadI = Double.parseDouble(cantidad);
            cantidadM = Double.parseDouble(cantidadMin);

            if (cantidadM >= cantidadI) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debes poner menos cantidad minima de la cantidad que hay");
                alert.showAndWait();
            } else {
                Conexion conexion = new Conexion();

                // Formo la consulta SQL para actualizar los datos del producto
                String SQL = "";
                SQL += "UPDATE productos SET ";
                SQL += "pro_nombre='" + nombre + "',";
                SQL += "pro_cantidad='" + cantidad + "',";
                SQL += "pro_unidad='" + unidad + "',";
                SQL += "pro_precio='" + precio + "',";
                SQL += "pro_cantidadMinima='" + cantidadMin + "'";
                SQL += " WHERE pro_id = '" + id + "'";

                // Ejecuto la consulta y obtengo el número de filas afectadas
                int filas = conexion.ejecutarInstruccion(SQL);

                conexion.cerrarConexion();
                MetodosVarios.cerrarVentanas(event);

                return filas > 0;
            }
        } catch (NumberFormatException e) {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setHeaderText(null);
            alertt.setTitle("Error");
            alertt.setContentText("Uno de los campos (cantidad, precio y/o cantidad minima no es un número");
            alertt.showAndWait();

        }
        return false;
    }

    /**
     * Maneja el evento del botón "restaurar_editarProducto".
     * Restaura los campos de texto del formulario a los valores originales del producto.
     * @param event el evento de acción generado por el botón
     */
    @FXML
    private void restaurar_editarProducto(ActionEvent event) {
        // Abre una ventana de confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("CONFIRMAR");
        alert.setContentText("¿Quieres restaurar el producto?");
        // Obtiene el resultado del botón seleccionado
        Optional<ButtonType> action = alert.showAndWait();

        // Si se ha pulsado el botón "Aceptar"
        if (action.get() == ButtonType.OK) {
            initAttributes(p);
        } else {
            Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setHeaderText(null);
            alertt.setTitle("Error");
            alertt.setContentText("No se ha restaurado el producto");
            alertt.showAndWait();
        }
    }

    /**
     * Maneja el evento del botón "cancelar_editarProducto".
     * Cierra la ventana actual.
     * @param event el evento de acción generado por el botón
     */
    @FXML
    private void cancelar_editarProducto(ActionEvent event) {
        MetodosVarios.cerrarVentanas(event);
    }

}