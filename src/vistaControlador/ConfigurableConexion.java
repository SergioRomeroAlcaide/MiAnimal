/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vistaControlador;

import java.sql.Connection;

public interface ConfigurableConexion {
    /**
     * Método para configurar la conexión con la base de datos.
     * @param conexion Objeto Connection para interactuar con la base de datos.
     */
    void configurarConexion(Connection conexion);
}

