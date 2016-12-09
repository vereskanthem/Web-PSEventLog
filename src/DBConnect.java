/**
 * Created by nlare on 08.12.16.
 */

//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.SQLException;

import java.sql.*;

public class DBConnect {

    private String connectionURL;
    private String connectionUsername;
    private String connectionPassword;

    private int statusConnection;
    private int oracleConnectClassExist;

    public void setConnectionURL(String connectionURL) {

        this.connectionURL = connectionURL;

    }

    public void setConnectionUsername(String connectionUsername) {

        this.connectionUsername = connectionUsername;
    }

    public void setConnectionPassword(String connectionPassword)    {

        this.connectionPassword = connectionPassword;

    }

    private void setStatusConnection(int statusConnection)   {

        this.statusConnection = statusConnection;

    }

    private void setOracleConnectClassExist(int classExist)  {

        this.oracleConnectClassExist = oracleConnectClassExist;

    }

    public String getConnectionURL()  {

        return this.connectionURL;

    }

    public String getConnectionUsername()   {

        return  this.connectionUsername;

    }

    public String getConnectionPassword()   {

        return this.connectionPassword;

    }

    public int getStatusConnection()    {

        return this.statusConnection;

    }

    public int getOracleConnectClassExist() {

        return this.oracleConnectClassExist;

    }

    public int connectToDB() {

        setOracleConnectClassExist(0);

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

        }   catch(ClassNotFoundException cne)   {

            System.out.println("Cannot connect Oracle thought JDBC! Class not found!");

            setOracleConnectClassExist(1);

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        }

//        try {
//            Driver oracleDriver = new oracle.jdbc.driver.OracleDriver();
//        }   catch (ClassNotFoundException e)    {
//            System.out.println();
//        }

        Connection connection = null;

        try {

//            connection = DriverManager.getConnection(connectionURL,connectionUsername,connectionPassword);
            connection = DriverManager.getConnection("jdbc:oracle:thin:pseventlog/1@localhost:1521:orcl");

        }   catch(SQLException sqle) {

            System.out.println("Error in getConnection!");

            setStatusConnection(1);

        }
//
//        if(connection == null)  {
//
//            System.out.println("Cannot connect to the current URL!");
//
//            setStatusConnection(1);
//
//        }   else    {
//
//            System.out.println("Connected!");
//
//            setStatusConnection(0);
//
//        }

        return 0;

    }

    public int addToDatabase(String username, String filename, String firstDate, String lastDate) {

        return 0;

    }

}
