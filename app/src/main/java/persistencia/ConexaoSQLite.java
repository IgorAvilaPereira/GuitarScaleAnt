/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.File;
import java.net.URISyntaxException;
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

    private static final Connection connection;

    private ConexaoSQLite() { /* não é para ser instanciada */}

    static {
        try {
            final String path = Thread.currentThread().getContextClassLoader().getResource("database.db").getPath();
            System.out.println(path);
            final String connectionString = "jdbc:sqlite:%s/database.db".formatted(path);
            connection = DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection get() {
      return connection;
    }
}
