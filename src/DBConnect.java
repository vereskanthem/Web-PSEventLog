/**
 * Created by nlare on 08.12.16.
 */

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import java.sql.*;

public class DBConnect {

    private String connectionURL;
    private String connectionUsername;
    private String connectionPassword;

    private String inputUsername;
    private String inputFilename;

    private String inputFirstDate;
    private String inputLastDate;

    private int statusConnection;
    private int oracleConnectClassExist;
    private Connection connection;

    String sqlDateFormat;

    EventsData data = new EventsData();

    private String[] excludeNames = new String[10];

    public DBConnect() {

        sqlDateFormat = "yyyy.MM.dd";
//        sqlDateFormat = "yyyy-MM-dd'T'HH:mm:ssz";
        connection = null;

    }

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


    public void setInputUsername(String username)    {

        this.inputUsername = username;

    }

    public void setInputFilename(String filename)    {

        this.inputFilename = filename;

    }

    public void setInputFirstDate(String FirstDate)    {

        this.inputFirstDate = FirstDate;

    }

    public void setInputLastDate(String LastDate)    {

        this.inputLastDate = LastDate;

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
            connection = DriverManager.getConnection("jdbc:oracle:thin:pseventlog/pwdfromouterspace@cisarch:1521:cisarch");

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

        String sqlDate = converter.ConvertMillisecToSQLDateString(eventdate,sqlDateFormat);

        // Need correction of date type!

        String sql = new String();

        sql = "INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_EVENT) VALUES ('" + username + "','" + filename + "', to_date('" + sqlDate + "','" + sqlDateFormat + "'))";

        st.executeUpdate(sql);
//        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_STRING)" + " VALUES ('" + username + "','" + filename + "','" + dateString + "')");
//        st.executeUpdate("INSERT into PSEVENTLOG.EventsLog (USERNAME,FILENAME,TIME_EVENT) VALUES ('testuser2','testfile2',null)");

        st.close();

        return 0;

    }

    public List<Map<String, Object>> getListFromDatabase(String _username, String _filename, String firstTime, String lastTime, String nocache) throws SQLException, NullTimeStringException {

        Statement st = connection.createStatement();

        int columnsCount;

        String sqlFirstTime = new String();
        String sqlLastTime = new String();

        EventsData eventData = new EventsData();
        List<Map<String, Object>> eventsRow = new ArrayList<>();

        DateConverter converter = new DateConverter();

//        if(firstTime.equals("")) throw new NullTimeStringException("Begin of Time Interval is NULL!");
//        if(lastTime.equals("")) throw new NullTimeStringException("End of Time Interval is NULL!");

        if(!firstTime.isEmpty())  sqlFirstTime = converter.ConvertMillisecToSQLDateString(firstTime, sqlDateFormat);
        else    System.out.println("Variable firstTime is empty!");
        if(!lastTime.isEmpty())  sqlLastTime = converter.ConvertMillisecToSQLDateString(lastTime, sqlDateFormat);
        else    System.out.println("Variable lastTime is empty!");

//        if(_username == "" || _username == "Enter username please...") {
//
//            setInputUsername("");
//
//        }
//
//        if(_filename == "" || _username == "Enter filename please...") {
//
//            setInputFilename("");
//
//        }

//        System.out.println("UserName: " + _username);
//        System.out.println("FileName: " + _filename);
        System.out.println("FirstDate: " + sqlFirstTime);
        System.out.println("LastDate: " + sqlLastTime);
        System.out.println("nocache: " + nocache);

        // Need to add where statement
        String sql;

        excludeNames[0] = "AJakovchitc";
        excludeNames[1] = "VLozhnikov";
        excludeNames[2] = "VVelichko";
        excludeNames[3] = "AMamonov";

        sql = "SELECT USERNAME,FILENAME,TIME_EVENT from PSEVENTLOG.EVENTSLOG WHERE TIME_EVENT >= TO_TIMESTAMP('" + sqlFirstTime + "','" + sqlDateFormat + "') and TIME_EVENT <= TO_TIMESTAMP('" + sqlLastTime + "','" + sqlDateFormat + "') and USERNAME != " + "'" + excludeNames[0] + "'";

            if(_username.isEmpty() && !(_filename.isEmpty())) {
                sql = "SELECT USERNAME,FILENAME,TIME_EVENT from PSEVENTLOG.EVENTSLOG WHERE LOWER(FILENAME) LIKE TRIM(BOTH ' ' FROM LOWER('%" + _filename + "%')) and TIME_EVENT >= TO_TIMESTAMP('" + sqlFirstTime + "','" + sqlDateFormat + "') and TIME_EVENT <= TO_TIMESTAMP('" + sqlLastTime + "','" + sqlDateFormat + "')";
            }

            if(_filename.isEmpty() && !(_username.isEmpty())) {
                sql = "SELECT USERNAME,FILENAME,TIME_EVENT from PSEVENTLOG.EVENTSLOG WHERE LOWER(USERNAME) LIKE TRIM(BOTH ' ' FROM LOWER('%" + _username + "%')) and TIME_EVENT >= TO_TIMESTAMP('" + sqlFirstTime + "','" + sqlDateFormat + "') and TIME_EVENT <= TO_TIMESTAMP('" + sqlLastTime + "','" + sqlDateFormat + "')";
            }

            if(!(_username.isEmpty()) && !(_filename.isEmpty()))  {
                sql = "SELECT USERNAME,FILENAME,TIME_EVENT from PSEVENTLOG.EVENTSLOG WHERE LOWER(USERNAME) LIKE TRIM(BOTH ' ' FROM LOWER('%" + _username + "%')) and LOWER(FILENAME) LIKE TRIM(BOTH ' ' FROM LOWER('%" + _filename + "%')) and TIME_EVENT >= TO_DATE('" + sqlFirstTime + ",'" + sqlDateFormat + "') and TIME_EVENT <= TO_DATE('" + sqlLastTime + ",'" + sqlDateFormat + "')";
            }

        ResultSet selectResult = st.executeQuery(sql);
        ResultSetMetaData metaData = selectResult.getMetaData();

        columnsCount = metaData.getColumnCount();

        while (selectResult.next()) {

            Map<String, Object> eventsColumns = new HashMap<>();

            String username = selectResult.getString("USERNAME");
            String filename = selectResult.getString("FILENAME");
            Date time_event = selectResult.getDate("TIME_EVENT");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
            String converted_time = formatter.format(time_event);

//            System.out.println("time_event = " + converted_time);

            eventData.setUsername(username);
            eventData.setFilename(filename);
            eventData.setEventTime(converted_time);

            for(int i = 1; i <= columnsCount; i++) {

//                eventsColumns.put(metaData.getColumnLabel(i), selectResult.getObject(i));
                if (metaData.getColumnLabel(i).equals("USERNAME")) {

                    eventsColumns.put(metaData.getColumnLabel(i), eventData.getUsername());

                }

                if (metaData.getColumnLabel(i).equals("FILENAME")) {

                    eventsColumns.put(metaData.getColumnLabel(i), eventData.getFilename());

                }

                if (metaData.getColumnLabel(i).equals("TIME_EVENT")) {

                    eventsColumns.put(metaData.getColumnLabel(i), eventData.getEventTime());

                }

            }

            eventsRow.add(eventsColumns);

        }

//        for(int i = 0; i < eventsRow.size(); i++) {
//            for(Map.Entry entry : eventsRow.get(i).entrySet())   {
//                System.out.println(entry.getKey() + " :: " + entry.getValue());
//            }
//            System.out.println("\n");
//        }

        selectResult.close();
        st.close();

        return eventsRow;

    }

}
