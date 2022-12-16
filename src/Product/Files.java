package Product;



import java.sql.*;
import java.util.*;

public class Files
{
    private final String dbName;
    private final String dbUsr;
    private final String dbPw;
    private final String serverName;
    private String port = "3306";
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
        String connectionUrl = "jdbc:mysql://localhost:3306/myinventory" + "user=root";
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
    
    
    public void createTable(String tableName, String attributes)
    {
        String sqlStt = "use " + dbName +
                "\n create table " + tableName + " ( " + attributes + " );";
        Connection n = connect();
        try
        {
            Statement st = n.createStatement();
            st.execute(sqlStt);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
    }
    public void deleteTable(String tableName)
    {
        Connection n = connect();
        String sqlStt = "drop table " + tableName;
        try
        {
            Statement st = n.createStatement();
            st.execute(sqlStt);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
    }
    public void clearTable(String tableName)
    {
        Connection n = connect();
        String sqlStt = "delete from " + tableName;
        try
        {
            Statement st = n.createStatement();
            st.execute(sqlStt);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
    }
    
    
    public void addRow(String tableName, String values)
    {
        Connection n = connect();
        String sqlStt = "insert into " + tableName + " values ( ";
        String []data = values.split("@");
        for(int i = 0; i < data.length-1; i++)
        {
            sqlStt += "?, ";
        }
        sqlStt += "? );";
        try
        {
            PreparedStatement st = n.prepareStatement(sqlStt);
            switch(tableName)
            {
                case "Product":
                    //id@name@price@quantity@category
                    st.setString(1, data[0]);
                    st.setString(2, data[1].trim());
                    st.setFloat(3, Float.parseFloat(data[2].trim()));
                    st.setInt(4, Integer.parseInt(data[3].trim()));
                    st.setString(5, data[4]);
                    break;
                case "Login":
                    //UserName@Password@Case
                    st.setString(1, data[0]);
                    st.setString(2, data[1]);
                    st.setInt(3, Integer.parseInt(data[2].trim()));
                    break;
                case "projects":
                    //ID@Name
                    st.setInt(1, Integer.parseInt(data[0].trim()));
                    st.setString(2, data[1]);
            }
            st.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
    }
    
    public void deleteRow(String tableName, String condition)
    {
        Connection n = connect();
        String sqlStt = " delete from " + tableName +  " where " + condition +';';
        try
        {
            Statement st = n.createStatement();
            st.execute(sqlStt);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
    }
    
    
    public void updateRow(String tableName, String oldValue, String newValue)
    {
        deleteRow(tableName, oldValue);
        addRow(tableName, newValue);
    }
    
    
    
    public ArrayList<Object> read(String tableName)
    {
        String sqlStt = "select * from " + tableName;
        Connection n = connect();
        ResultSet rs;
        ArrayList<Object> readedData = new ArrayList<>();
        int i=0;
        try
        {
            Statement st = n.createStatement();
            rs = st.executeQuery(sqlStt);
            switch(tableName)
            {
                case "projects":
                    //id@name@price@quantity@category
                    while(rs.next())
                    {
                      /*  Product newP = new Product();
                        newP.setId(rs.getString(1));
                        newP.setName(rs.getString(2));
                        newP.setPrice(rs.getDouble(3));
                        newP.setQuantity(rs.getInt(4));
                        newP.setCategory(rs.getString(5));
                        readedData.add(newP);
                        */
                         
                    }
                    break;
                case "Login":
                    //UserName@Password@Case
                  /*  Login newLog = new Login();
                    newLog.setUserName(rs.getString(1));
                    newLog.setPassword(rs.getString(2));
                    newLog.setCase(rs.getInt(3));
                    readedData.add(newLog);
                    break;
                    */
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
        return readedData;
    }
    
    
    public ArrayList<Object> read(String tableName, String sortBy, boolean ascending)
    {
        String sortType = "asc";
        if(!ascending)
        {
            sortType = "desc";
        }
        String sqlStt = "select * from " + tableName + " order by " + sortBy + sortType;
        Connection n = connect();
        ResultSet rs;
        ArrayList<Object> readedData = new ArrayList<>();
        try
        {
            Statement st = n.createStatement();
            rs = st.executeQuery(sqlStt);
            switch(tableName)
            {
                case "Product":
                    //id@name@price@quantity@category
                    while(rs.next())
                    {
                     /*   Product newP = new Product();
                        newP.setId(rs.getString(1));
                        newP.setName(rs.getString(2));
                        newP.setPrice(rs.getDouble(3));
                        newP.setQuantity(rs.getInt(4));
                        newP.setCategory(rs.getString(5));
                        readedData.add(newP);
*/
                    }
                    break;
                case "Login":
                    //UserName@Password@Case
                   /* Login newLog = new Login();
                    newLog.setUserName(rs.getString(1));
                    newLog.setPassword(rs.getString(2));
                    newLog.setCase(rs.getInt(3));
                    readedData.add(newLog);
                    break;
                */
                    break;
                case "projects":
                     while(rs.next())
                    {
                     /*   Product newP = new Product();
                        newP.setId(rs.getString(1));
                        newP.setName(rs.getString(2));
                        newP.setPrice(rs.getDouble(3));
                        newP.setQuantity(rs.getInt(4));
                        newP.setCategory(rs.getString(5));
                        readedData.add(newP);
*/
                    }
                     break;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
        return readedData;
    }
    public void setPort(String port)
    {
        this.port = port;
    }
    public void execute(String statement)
    {
        Connection n = connect();
        try
        {
            Statement sqlStt = n.createStatement();
            sqlStt.execute(statement);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException nullE)
        {
            System.out.println("Null Pointer Exception, con variable is NULL, check the connect method");
        }
    }

}



