package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDb {
    public static Connection objConnection = null;

    public static Connection openConnection(){


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/contratacion";
            String user = "root";
            String password = "MySQLDataBase*77";

            objConnection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>> " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERROR >>> " + e.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection(){

            try {
                if (objConnection != null) {
                    objConnection.close();
                } else {
                    JOptionPane.showMessageDialog(null,"No hay bases de datos abiertas");
                }
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null,e.getMessage());
            }

    }
}
