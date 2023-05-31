package Modelo;

import Utiles.Conexion;
import java.sql.SQLException;
/**
 * Clase que representa un producto.
 * 
 * @author David y Sergio
 */
public class Productos {

    private int id;
    private String nombre;
    private double cantidad;
    private String unidad;
    private double precio;
    private double cantidadMinima;
    Conexion conexion;

    /**
     * Constructor de la clase Productos.
     * 
     * @param id             El ID del producto.
     * @param nombre         El nombre del producto.
     * @param cantidad       La cantidad del producto.
     * @param unidad         La unidad de medida del producto.
     * @param precio         El precio del producto.
     * @param cantidadMinima La cantidad mínima del producto.
     */
    public Productos(int id, String nombre, double cantidad, String unidad, double precio, double cantidadMinima) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.precio = precio;
        this.cantidadMinima = cantidadMinima;
    }

    /**
     * Obtiene el ID del producto.
     * 
     * @return El ID del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     * 
     * @param id El ID del producto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre El nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad del producto.
     * 
     * @return La cantidad del producto.
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto.
     * 
     * @param cantidad La cantidad del producto.
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la unidad de medida del producto.
     * 
     * @return La unidad de medida del producto.
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * Establece la unidad de medida del producto.
     * 
     * @param unidad La unidad de medida del producto.
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio El precio del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad mínima del producto.
     * 
     * @return La cantidad mínima del producto.
     */
    public double getCantidadMinima() {
        return cantidadMinima;
    }

    /**
     * Establece la cantidad mínima del producto.
     * 
     * @param cantidadMinima La cantidad mínima del producto.
     */
    public void setCantidadMinima(double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    /**
     * Borra el producto de la base de datos.
     * 
     * @return true si el producto se borró correctamente, false en caso contrario.
     * @throws SQLException Si ocurre un error de SQL al ejecutar la instrucción de borrado.
     */
    public boolean borrarProducto() throws SQLException {

        Conexion conexion = new Conexion();

        String SQL = "DELETE FROM productos where pro_id = '" + this.id + "'";

        int filas = conexion.ejecutarInstruccion(SQL);

        conexion.cerrarConexion();

        return filas > 0;

    }

}