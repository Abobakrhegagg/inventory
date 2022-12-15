/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventoryjava;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class Inventoryjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {   
            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myinventory","root",""))
            {
            System.out.println("Connection established");
        }
        }
        catch (SQLException e)
        {
            System.out.println("Error connection to the database");
            
        }
    }
    
}
