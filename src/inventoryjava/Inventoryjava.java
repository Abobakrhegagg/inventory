/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventoryjava;
import java.sql.*;
import java.sql.SQLException;
import java.io.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.beans.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.awt.EventQueue;
import javax.swing.*;
import java.sql.*;

import java.beans.Statement;



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
