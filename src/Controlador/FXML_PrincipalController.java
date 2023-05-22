/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Productos;
import Utiles.Conexion;
import Utiles.MetodosVarios;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class FXML_PrincipalController implements Initializable {

    @FXML
    private TextField principal_buscar;
    @FXML
    private TableColumn<?, ?> tabla_id;
    @FXML
    private TableColumn<?, ?> tabla_nombre;
    @FXML
    private TableColumn<Productos, Double> tabla_cantidad;
    @FXML
    private TableColumn<?, ?> tabla_unidad;
    @FXML
    private TableColumn<?, ?> tabla_precio;
    @FXML
    private TableColumn<Productos, Double> tabla_cantidadMinima;

    Conexion conexion;

    static ObservableList<Productos> lista_Producto = FXCollections.observableArrayList();
    @FXML
    private TableView<Productos> tabla_productos;
    @FXML
    private Button prin_añadirProducto_boton;
    @FXML
    private Button prin_eliminarProducto_boton;
    @FXML
    private TextField prin_id_GastoProducto;
    @FXML
    private TextField prin_CantMinima_GastoProducto;
    @FXML
    private TextField prin_precio_GastoProducto;
    @FXML
    private ComboBox<String> prin_unidad_GastoProducto;
    @FXML
    private TextField prin_cantidad_GastoProducto;
    @FXML
    private TextField prin_nombre_GastoProducto;
    @FXML
    private Button prin_aceptar_GastoProducto;
    @FXML
    private Button prin_cancelar_GastoProducto;
    @FXML
    private Button prin_editarProducto_boton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        prepararTabla();
        cargarTabla();

    }

    public static ObservableList<Productos> getProductList() {
        return lista_Producto;
    }

    public void prepararTabla() {

        /*      tabla_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabla_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tabla_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tabla_unidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tabla_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tabla_cantidadMinima.setCellValueFactory(new PropertyValueFactory<>("cantidadMinima"));
         */
        tabla_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabla_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tabla_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tabla_unidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tabla_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Configurar estilo de celda personalizada para la columna tabla_cantidad
        tabla_cantidad.setCellFactory(column -> new TableCell<Productos, Double>() {
            @Override
            protected void updateItem(Double cantidad, boolean empty) {
                super.updateItem(cantidad, empty);

                if (empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(cantidad));

                    // Obtener el objeto Producto asociado a esta celda
                    Productos producto = getTableView().getItems().get(getIndex());

                    // Comprobar si el producto tiene cantidad igual a la cantidad mínima
                    if (producto.getCantidad() <= producto.getCantidadMinima()) {
                        setStyle("-fx-background-color: red; -fx-text-fill: white;");

                    } else {
                        setStyle(""); // Estilo por defecto
                    }
                }
            }
        });

        tabla_cantidadMinima.setCellValueFactory(new PropertyValueFactory<>("cantidadMinima"));
    }

    public void cargarTabla() {

        try {
            conexion = new Conexion();

            ResultSet rs;
            rs = conexion.ejecutarConsulta("Select * from productos");

            lista_Producto.removeAll(lista_Producto);
            while (rs.next()) {
                int id = rs.getInt("pro_id");
                String nombre = rs.getString("pro_nombre");
                double cantidad = rs.getDouble("pro_cantidad");
                String unidad = rs.getString("pro_unidad");
                double precio = rs.getDouble("pro_precio");
                double cantidadMinima = rs.getDouble("pro_cantidadMinima");

                Productos producto = new Productos(id, nombre, cantidad, unidad, precio, cantidadMinima);
                lista_Producto.add(producto);
            }

            rs.close();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(FXML_PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tabla_productos.setItems(lista_Producto);

    }

    @FXML
    private void prin_añadirProducto(ActionEvent event) throws IOException {

        // carga la vista
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/FXML_AñadirProductos.fxml"));

        // Cargo el padre
        Parent root;
        root = loader.load();

        // Creo la scene
        Scene scene = new Scene(root);

        // Creo la stage
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("StockApp");
        stage.showAndWait();

        cargarTabla();

    }

    @FXML
    private void prin_borrarProducto(ActionEvent event) throws SQLException {
        // Obtengo el producto seleccionado
        Productos a = (Productos) this.tabla_productos.getSelectionModel().getSelectedItem();

        // Si es nulo, muestro error
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else {
            // Abrimos una ventana de confirmacion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("ACTUALIZAR");
            alert.setContentText("¿Seguro que quieres eliminar este producto?");
            // Cogemos el resultado del boton seleccionado
            Optional<ButtonType> action = alert.showAndWait();

            // Si hemos pulsado en aceptar
            if (action.get() == ButtonType.OK) {

                // Borramos el usuario
                if (a.borrarProducto()) {

                    Alert alertA = new Alert(Alert.AlertType.INFORMATION);
                    alertA.setHeaderText(null);
                    alertA.setTitle("Exito");
                    alertA.setContentText("Se ha borrado el producto");
                    alertA.showAndWait();

                }

            } else {
                Alert alertt = new Alert(Alert.AlertType.ERROR);
                alertt.setHeaderText(null);
                alertt.setTitle("Error");
                alertt.setContentText("No se ha eliminado el producto");
                alertt.showAndWait();
            }

            // Cargamos de nuevo
            this.cargarTabla();
            this.tabla_productos.refresh();

        }
        principal_buscar.setText("");
        MetodosVarios m = new MetodosVarios();
        m.cancelar(prin_id_GastoProducto, prin_nombre_GastoProducto, prin_cantidad_GastoProducto, prin_unidad_GastoProducto, prin_precio_GastoProducto, prin_CantMinima_GastoProducto);

    }

    @FXML
    private void prin_editarProducto(ActionEvent event) throws IOException {
        // Obtengo el producto seleccionado
        Productos a = (Productos) this.tabla_productos.getSelectionModel().getSelectedItem();

        // Si es nulo, muestro error
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else {
            try {
                // carga la vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/FXML_EditarProducto.fxml"));

                // Cargo el padre
                Parent root;
                root = loader.load();

                // Creo la scene
                Scene scene = new Scene(root);

                // Cargo el controlador asociado a la vista y le paso el producto seleccionado
                FXML_EditarProductoController controlador = loader.getController();
                controlador.initAttributes(a);
                // Creo la stage
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("StockApp");
                stage.showAndWait();

                cargarTabla();

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
                principal_buscar.setText("");
        MetodosVarios m = new MetodosVarios();
        m.cancelar(prin_id_GastoProducto, prin_nombre_GastoProducto, prin_cantidad_GastoProducto, prin_unidad_GastoProducto, prin_precio_GastoProducto, prin_CantMinima_GastoProducto);

    }

    @FXML
    private void prin_gastos(MouseEvent event) {
        // Obtengo el producto seleccionado
        Productos a = (Productos) this.tabla_productos.getSelectionModel().getSelectedItem();
        // Si es nulo, muestro error
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else {
            try {
                prin_id_GastoProducto.setText(a.getId() + "");
                prin_nombre_GastoProducto.setText(a.getNombre());
                prin_unidad_GastoProducto.setValue(a.getUnidad());
                prin_precio_GastoProducto.setText(a.getPrecio() + "");
                prin_CantMinima_GastoProducto.setText(a.getCantidadMinima() + "");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void prin_aceptarGasto(ActionEvent event) throws SQLException {
        // Obtengo el producto seleccionado
        Productos a = (Productos) this.tabla_productos.getSelectionModel().getSelectedItem();
        String cantidadRestarStr = prin_cantidad_GastoProducto.getText();

        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else if (cantidadRestarStr.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes poner una cantidad");
            alert.showAndWait();
        } else {
            double cantidadInicial = a.getCantidad();
            double cantidadRestar = Double.parseDouble(cantidadRestarStr);
            double cantidadFinal = 0;
            if (cantidadRestar > cantidadInicial) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debes poner menos cantidad de la que tienes");
                alert.showAndWait();
            } else {
                cantidadFinal = cantidadInicial - cantidadRestar;
                MetodosVarios m = new MetodosVarios();
                int id = Integer.parseInt(prin_id_GastoProducto.getText());
                m.actualizar(cantidadFinal, id);
                cargarTabla();
                double cantMinima = a.getCantidadMinima();
                if (cantidadFinal <= cantMinima) {
                    Alert alertA = new Alert(Alert.AlertType.INFORMATION);
                    alertA.setHeaderText(null);
                    alertA.setTitle("AVISO");
                    alertA.setContentText("Has llegado a la cantidad minima de este producto");
                    alertA.showAndWait();

                }
                m.cancelar(prin_id_GastoProducto, prin_nombre_GastoProducto, prin_cantidad_GastoProducto, prin_unidad_GastoProducto, prin_precio_GastoProducto, prin_CantMinima_GastoProducto);

            }
        }

    }

    @FXML
    private void prin_cancelarGastos(ActionEvent event
    ) {
        cargarTabla();
        MetodosVarios m = new MetodosVarios();
        m.cancelar(prin_id_GastoProducto, prin_nombre_GastoProducto, prin_cantidad_GastoProducto, prin_unidad_GastoProducto, prin_precio_GastoProducto, prin_CantMinima_GastoProducto);
    }

    
    @FXML
    private void BuscaPorNombre(KeyEvent event) throws SQLException {
        ObservableList<Productos> prod = FXCollections.observableArrayList();
        // Abro la conexion
        Conexion conexion = new Conexion();

        // realizo la consulta
        String SQL = "select *"
                + "from productos"
                + " where pro_nombre like '" + principal_buscar.getText().toLowerCase() + "%'";

        ResultSet rs = conexion.ejecutarConsulta(SQL);
        lista_Producto.removeAll(lista_Producto);

        while (rs.next()) {
            
            int id = rs.getInt("pro_id");
            String nombre = rs.getString("pro_nombre");
            double cantidad = rs.getDouble("pro_cantidad");
            String unidad = rs.getString("pro_unidad");
            double precio = rs.getDouble("pro_precio");
            double cantidadMinima = rs.getDouble("pro_cantidadMinima");

            Productos producto = new Productos(id, nombre, cantidad, unidad, precio, cantidadMinima);
            lista_Producto.add(producto);

        }

        rs.close();
        conexion.cerrarConexion();
    }

}
