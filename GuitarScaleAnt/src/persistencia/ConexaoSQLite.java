/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author iapereira
 */
import java.sql.*;

/**
 *
 * @author iapereira
 */
public class ConexaoSQLite {
    private String url;

    public Connection getMyConnection() {
        Connection conn = null;
        if (OSInfo.getOs() == OSInfo.OS.UNIX) {
            this.url = "jdbc:sqlite:database.db";
        } else {
            this.url = "jdbc:sqlite:database.db";
        }        
        try {
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}