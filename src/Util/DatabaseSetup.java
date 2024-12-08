/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup implements ConfigurableConexion {

    @Override
    public Connection establecerConexion() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public void configurarBaseDeDatos(Connection connection) {
        System.out.println("Iniciando la configuración de la base de datos...");
        try {
            if (!existeBaseDeDatos(connection, "MiAnimal")) {
                System.out.println("La base de datos MiAnimal no existe. Creando la base de datos...");
                ejecutarScriptSQL(connection, "setup.sql"); // Se asume que el archivo está en resources
                System.out.println("Base de datos MiAnimal creada correctamente.");
            } else {
                System.out.println("La base de datos MiAnimal ya está creada.");
            }
        } catch (Exception e) {
            System.err.println("Error durante la configuración de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Verifica si la base de datos existe en el sistema.
     * 
     * @param connection Conexión de la base de datos
     * @param nombreBaseDatos Nombre de la base de datos
     * @return true si la base de datos existe, false de lo contrario
     * @throws SQLException 
     */
    private boolean existeBaseDeDatos(Connection connection, String nombreBaseDatos) throws SQLException {
        String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreBaseDatos);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Ejecuta el script SQL desde el archivo de recursos setup.sql
     * 
     * @param connection Conexión a la base de datos
     * @param nombreArchivoSQL Nombre del archivo SQL a ejecutar
     */
    private void ejecutarScriptSQL(Connection connection, String nombreArchivoSQL) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nombreArchivoSQL)) {
            if (inputStream == null) {
                System.err.println("El archivo SQL no se encontró: " + nombreArchivoSQL);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder sql = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    sql.append(linea).append("\n");
                    if (linea.trim().endsWith(";")) {
                        try (Statement stmt = connection.createStatement()) {
                            stmt.execute(sql.toString());
                        }
                        sql.setLength(0); // Reinicia el StringBuilder
                    }
                }
            }
        } catch (IOException | SQLException e) {
            System.err.println("Error al ejecutar el script SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Verifica si se puede establecer una conexión con la base de datos.
     * 
     * @return true si la conexión es válida, false en caso contrario
     */
    @Override
    public boolean verificarConexion() {
        try (Connection connection = establecerConexion()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("No se pudo verificar la conexión: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para verificar la existencia de la base de datos y configurarla si no existe.
     */
    public static void verificarBaseDeDatos() {
        try (Connection connection = new DatabaseSetup().establecerConexion()) {
            new DatabaseSetup().configurarBaseDeDatos(connection);
        } catch (SQLException e) {
            System.err.println("Error al verificar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
