package com.romero278.kernel.connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author volt
 */
public class ConnectionBD {

   private Connection connect = null;

    public Connection connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestor_rutas", "root", "root");
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, "Connection failed "+"\n"+error,"Message Error",JOptionPane.ERROR_MESSAGE);
        }
        
        return connect;
    }
}