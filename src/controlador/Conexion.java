
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
      
    private final String URL = "jdbc:mysql://localhost:3306/tienda";
    private final String USERNAME = "root";
    private final String PASSWORD = "leon12345";
    private Connection con = null;
  
    public  Connection getConection() {
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            //JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (SQLException e) {
            System.out.println(e);
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
