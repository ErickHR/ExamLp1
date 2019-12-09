/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rivas
 */
public class Conexion {
    
    public static Connection conectar(){
        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/arnold";
        String user = "root";
        String pass = "";
        try {
            Class.forName(driver);
            Connection cn = DriverManager.getConnection(url, user, pass);
            return cn;
        } catch (Exception e) {
            System.out.println("error en conectar");
            return null;
        }
        
    }
    
}
