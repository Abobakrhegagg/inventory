package Product;



import java.sql.*;
import java.util.*;

public class Files
{
    private final String dbName;
    private final String dbUsr;
    private final String dbPw;
    private final String serverName;
    private String port = "1433";
    /*
        there is no empty contructor because everyone have different server name
        so, this contructor takes the server name, db name, username and the pa-
        ssword
    
        the defualt port for sql server is 1433 but, if it's different that this
        you can change it by calling setPort function
    */
    public Files(String sName, String dbName, String dbUsr, String dbPw)
    {
        this.serverName = sName;
        this.dbName = dbName;
        this.dbUsr = dbUsr;
        this.dbPw = dbPw;
    }
    /*
        The connection is taking the server name, database name, port number, username
        and password for the accessing the server and it's DBs and gives all the
        access to the server
        this function is returning the connection to let the rest of the function
        access the server to edit data
    */
    private Connection connect()
    {
        String connectionUrl = "jdbc:mysql://localhost:3306/Myinventory?" + "user=root";
        Connection con = null;
        try
        {
            con = DriverManager.getConnection(connectionUrl);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
    
