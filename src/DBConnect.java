/**
 * Created by nlare on 08.12.16.
 */

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
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

    private void setOracleConnectClassExist(int oracleConnectClassExist)  {

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

        DateConverter converter = new DateConverter();

        String sqlDate = converter.ConvertMillisecToSQLDateString(eventdate);

        // Need correction of datatype!

        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_EVENT)" + " VALUES ('" + username + "','" + filename + "', to_date('" + sqlDate + "', 'dd/MM/yyyy'))");
//        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_STRING)" + " VALUES ('" + username + "','" + filename + "','" + dateString + "')");
//        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_EVENT) VALUES ('testuser2','testfile2',null)");

        st.close();

        return 0;

    }

    public List<EventsData> getListFromDatabase(String _username, String _filename, String firstTime, String lastTime) throws SQLException, NullTimeStringException {

        Statement st = connection.createStatement();

        int columnsCount;

//        EventsDataCollection collection = new EventsDataCollection();

        EventsData eventData = new EventsData();
        List<EventsData> eventsDataArray = new ArrayList<>();

//        DateConverter converter = new DateConverter();

//        if(firstTime.equals("")) throw new NullTimeStringException("Begin of Time Interval is NULL!");
//        if(lastTime.equals("")) throw new NullTimeStringException("End of Time Interval is NULL!");

//        sqlFirstTime = converter.ConvertMillisecToSQLDateString(firstTime);
//        sqlLastTime = converter.ConvertMillisecToSQLDateString(lastTime);

        // Need to add where statement
        String sql = "SELECT USERNAME,FILENAME,TIME_EVENT from PSEVENTLOG.EVENTSLOG";

        ResultSet selectResult = st.executeQuery(sql);

//        columnsCount = selectResult.getMetaData().getColumnCount();

        while (selectResult.next()) {

            String username = selectResult.getString("USERNAME");
            String filename = selectResult.getString("FILENAME");
            Date time_event = selectResult.getDate("TIME_EVENT");

            eventData.setUsername(username);
            eventData.setFilename(filename);
            eventData.setEventTime(time_event);

            eventsDataArray.add(eventData);

        }

        selectResult.close();
        st.close();

        return eventsDataArray;

    }

}
