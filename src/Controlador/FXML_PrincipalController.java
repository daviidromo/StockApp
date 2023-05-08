/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Productos;
import Utiles.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class FXML_PrincipalController implements Initializable {

    @FXML
    private MenuItem principal_añadirProducto;
    @FXML
    private MenuItem principal_eliminarProducto;
    @FXML
    private MenuItem principal_editarProducto;
    @FXML
    private TextField principal_buscar;
    @FXML
    private TableColumn<?, ?> tabla_id;
    @FXML
    private TableColumn<?, ?> tabla_nombre;
    @FXML
    private TableColumn<?, ?> tabla_cantidad;
    @FXML
    private TableColumn<?, ?> tabla_unidad;
    @FXML
    private TableColumn<?, ?> tabla_precio;
    @FXML
    private TableColumn<?, ?> tabla_cantidadMinima;

    Conexion conexion;

    ObservableList<Productos> lista_Producto = FXCollections.observableArrayList();
    @FXML
    private TableView<Productos> tabla_productos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepararTabla();
        cargarTabla();
    }    
    
    public void prepararTabla() {

        tabla_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabla_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tabla_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tabla_unidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tabla_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
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
                int cantidad = rs.getInt("pro_cantidad");
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
}
