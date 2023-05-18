/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utiles.Conexion;
import java.sql.SQLException;

/**
 *
 * @author sergi
 */
public class Productos {

    private int id;
    private String nombre;
    private double cantidad;
    private String unidad;
    private double precio;
    private double cantidadMinima;
    Conexion conexion;

    public Productos(int id, String nombre, double cantidad, String unidad, double precio, double cantidadMinima) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.precio = precio;
        this.cantidadMinima = cantidadMinima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(double cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public boolean borrarProducto() throws SQLException {

        Conexion conexion = new Conexion();

        String SQL = "DELETE FROM productos where pro_id = '" + this.id + "'";

        int filas = conexion.ejecutarInstruccion(SQL);

        conexion.cerrarConexion();

        return filas > 0;

    }

}
