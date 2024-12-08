/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConfigurableConexion {
    /**
     * Método para establecer la conexión a la base de datos.
     * @return Una conexión activa a la base de datos.
     * @throws SQLException Si ocurre un error en la conexión.
     */
    Connection establecerConexion() throws SQLException;

    /**
     * Método para configurar la base de datos (crear tablas, insertar registros, etc.).
     * @param connection La conexión activa a la base de datos.
     */
    void configurarBaseDeDatos(Connection connection);

    /**
     * Método para verificar si la conexión está activa.
     * @return True si la conexión está activa, de lo contrario False.
     */
    boolean verificarConexion();
}
