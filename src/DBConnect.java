/**
 * Created by nlare on 08.12.16.
 */

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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

    public int addToDatabase(String username, String filename, String eventdate) throws SQLException, ParseException {

        Statement st = connection.createStatement();

        String timeTest = "Tue Dec 01 2016 00:00:00 GMT+0600 (+06)";
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z '('Z')'");

        Date currentDate = formatter.parse(timeTest);

        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

        // Need correction of datatype!
        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_EVENT)" + " VALUES ('" + username + "','" + filename + "', to_date('" + sqlDate + "', 'yyyy/mm/dd hh:mi:ss'))");
//        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_EVENT) VALUES ('testuser2','testfile2',null)");

        st.close();

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

        st.close();
        selectResult.close();

        return 0;

    }

}