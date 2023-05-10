/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sergi
 */
public class Conexion {

    private Connection conexion;

    /**
     * Metodo para conectarse a la base de datos
     *
     * @throws SQLException -
     */
    public Conexion() throws SQLException {
        String host = "localhost";
        String baseDatos = "stockapp";
        String usuaio = "root";
        String password = "";

        //Conectamos a la base de datos
        String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos;

        //Conexion
        conexion = DriverManager.getConnection(cadenaConexion, usuaio, password);
        //System.out.println("Conexion establecida");

        conexion.setAutoCommit(true);

    }

    /**
     * Metodo para ejecutar la consulta
     *
     * @param SQL -
     * @return  -
     * @throws SQLException -
     */
    public ResultSet ejecutarConsulta(String SQL) throws SQLException {
        Statement statement = this.conexion.createStatement();
        return statement.executeQuery(SQL);
    }

    /**
     * Ejecuta una instrucción
     *
     * @param SQL -
     * @return -
     * @throws SQLException -
     */
    public int ejecutarInstruccion(String SQL) throws SQLException {
        Statement statement = this.conexion.createStatement();
        return statement.executeUpdate(SQL);
    }

    /**
     * Devuelve el ultimo ID insertado
     *
     * @return -
     * @throws SQLException -
     */
    public int ultimoID() throws SQLException {
        ResultSet rs = this.ejecutarConsulta("SELECT last_insert_id() as last_id;");
        rs.next();
        return rs.getInt("last_id");
    }

    /**
     * Cierra la conexión
     *
     * @throws SQLException -
     */
    public void cerrarConexion() throws SQLException {
        this.conexion.close();
    }

}
