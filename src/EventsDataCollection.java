import org.w3c.dom.events.Event;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by nlare on 12.12.16.
 */
public class EventsDataCollection {

    private HashMap<EventsData,Integer> EventDataCollection = new HashMap<EventsData,Integer>();
    private EventsData EventData = new EventsData();

    private int mapCount;

//    EventsDataCollection()  {
//
//        mapCount = 0;
//
//    }

    // Using for READ data FROM DataBase and out into JSP
    public void addToCollection(String username, String filename, String event_time)   {

        mapCount += 1;

        EventData.setUsername(username);
        EventData.setFilename(filename);
        EventData.setEventTime(event_time);

        EventDataCollection.put(EventData,mapCount);

//        DBConnect dbConnection = new DBConnect();
//
//        dbConnection.connectToDB();
//
//        try {
//
//            dbConnection.addToDatabase(username,filename,event_time);
//
//        }   catch (SQLException e)    {
//
//            e.printStackTrace();
//
//        }

    }

//    public void getFromCollection(String grepBy, String something) {
//
//        if(Objects.equals(grepBy, "grep_by_username")) {
//
//            if()    {
//
//
//
//            }
//
//        }
//
//    }

}
