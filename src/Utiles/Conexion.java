package Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Clase que representa una conexión a la base de datos.
 * Esta clase se encarga de establecer y cerrar la conexión, así como ejecutar consultas e instrucciones SQL.
 * 
 * @author David y Sergio
 */
public class Conexion {

    private Connection conexion;

    /**
     * Constructor de la clase Conexion.
     * Establece la conexión a la base de datos utilizando los parámetros proporcionados.
     * 
     * @throws SQLException Si ocurre un error al establecer la conexión.
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
     * Ejecuta una consulta SQL y devuelve el conjunto de resultados.
     * 
     * @param SQL La consulta SQL a ejecutar.
     * @return El conjunto de resultados de la consulta.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */
    public ResultSet ejecutarConsulta(String SQL) throws SQLException {
        Statement statement = this.conexion.createStatement();
        return statement.executeQuery(SQL);
    }

    /**
     * Ejecuta una instrucción SQL y devuelve el número de filas afectadas.
     * 
     * @param SQL La instrucción SQL a ejecutar.
     * @return El número de filas afectadas.
     * @throws SQLException Si ocurre un error al ejecutar la instrucción.
     */
    public int ejecutarInstruccion(String SQL) throws SQLException {
        Statement statement = this.conexion.createStatement();
        return statement.executeUpdate(SQL);
    }

    /**
     * Devuelve el último ID insertado en la base de datos.
     * 
     * @return El último ID insertado.
     * @throws SQLException Si ocurre un error al obtener el último ID.
     */
    public int ultimoID() throws SQLException {
        ResultSet rs = this.ejecutarConsulta("SELECT last_insert_id() as last_id;");
        rs.next();
        return rs.getInt("last_id");
    }

    /**
     * Cierra la conexión a la base de datos.
     * 
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    public void cerrarConexion() throws SQLException {
        this.conexion.close();
    }

}