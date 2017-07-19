/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DEV1
 */
public class Conection {

    Connection conexion;
    DatabaseMetaData metaDatos;
    ResultSet rs;

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/mantis", "root", "");
            this.metaDatos = conexion.getMetaData();
            //this.rs = metaDatos.getTables(null, null, "%", null);
            //this.rs = metaDatos.getColumns("mantis", null, "mantis_project_table", null);
            this.rs = metaDatos.getImportedKeys("mantis", null, "mantis_project_table");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getDataBd() {
        this.init();
        try {

            while (this.rs.next()) {
                // El contenido de cada columna del ResultSet se puede ver
                // en la API, en el metodo getTables() de DataBaseMetaData.
                // La columna 1 es TABLE_CAT
                // y la 3 es TABLE_NAME
                String catalogo = rs.getString(1);
                String tabla = rs.getString(3);
                System.out.println("TABLA=" + catalogo + "." + tabla);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDataTable() {
        this.init();
    
        try {
            while (rs.next()) {
                // El contenido de cada columna del ResultSet se puede ver en
                // la API de java, en el metodo getColumns() de DataBaseMetaData
                // La 4 corresponde al TABLE_NAME
                // y la 6 al TYPE_NAME
                String nombreColumna = rs.getString(4);
                String tipoColumna = rs.getString(6);
                System.out.println(" COLUMNA, nombre=" + nombreColumna
                        + " tipo = " + tipoColumna);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        public void getDataTableFk() {
        this.init();
    
        try {
            while (rs.next()) {
                // El contenido de cada columna del ResultSet se puede ver en
                // la API de java, en el metodo getColumns() de DataBaseMetaData
                // La 4 corresponde al TABLE_NAME
                // y la 6 al TYPE_NAME
                String nombreColumnaFk = rs.getString(8);
               
                System.out.println(" COLUMNA, nombre=" + nombreColumnaFk       );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
