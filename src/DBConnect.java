/**
 * Created by nlare on 08.12.16.
 */

import java.sql.*;
import java.util.*;

//import java.sql.*;

public class DBConnect {

    private String connectionURL;
    private String connectionUsername;
    private String connectionPassword;

    private int statusConnection;
    private int oracleConnectClassExist;
    private Connection connection = null;

    EventsData data = new EventsData();

//    List<EventsData> dataList = new List<EventsData>();

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

        try {

//            connection = DriverManager.getConnection(connectionURL,connectionUsername,connectionPassword);
            connection = DriverManager.getConnection("jdbc:oracle:thin:pseventlog/1@localhost:1521:orcl");

        }   catch(SQLException sqle) {

            System.out.println("Error in getConnection!");

        }

        if(connection == null)  {

            System.out.println("Cannot connect to the current URL!");

            setStatusConnection(1);

        }   else    {

            System.out.println("Connected!");

            setStatusConnection(0);

        }

        return 0;

    }

    public int addToDatabase(String username, String filename, java.util.Date eventdate) throws SQLException {

        Statement st = connection.createStatement();

        java.sql.Date sqlDate = new java.sql.Date(eventdate.getTime());

        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_EVENT)" + " VALUES (" + username + "," + filename + "," + sqlDate + ")");

        return 0;

    }

    public int selectFromDatabase(String firstTime, String lastTime) throws SQLException {

        Statement st = connection.createStatement();

        String sql = "SELECT ID_EVENT,USERNAME,FILENAME,TIME_EVENT from PSEVENTLOG.EVENTSLOG";

        ResultSet selectResult = st.executeQuery(sql);

        while(selectResult.next())  {

            int id_event  = selectResult.getInt("ID_EVENT");
            String username = selectResult.getString("USERNAME");
            String filename = selectResult.getString("FILENAME");
            String time_event = selectResult.getString("TIME_EVENT");

        }

        selectResult.close();

        return 0;

    }

}