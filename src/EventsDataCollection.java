import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nlare on 12.12.16.
 */
public class EventsDataCollection {

//    private HashMap<EventsData,Integer> EventDataCollection = new HashMap<EventsData,Integer>();
    private List<EventsData> EventDataCollection = new ArrayList<EventsData>();
    private EventsData EventData = new EventsData();


    public List<EventsData> getAllFromCollection()  {

        return this.EventDataCollection;

    }

    // Using for READ data FROM DataBase and out into JSP
    public void addToCollection(String username, String filename, Date event_time)   {

//        if((username.equals("")))  {
//
//            EventData.setUsername("");
//
//        }   else    {
//
//            EventData.setUsername(username);
//
//        }
//
//        if((filename.equals("")))  {
//
//            EventData.setFilename("");
//
//        }   else    {
//
//            EventData.setFilename(filename);
//
//        }
//
//        EventData.setEventTime(event_time);

        EventData.setUsername(username);
        EventData.setFilename(filename);
        EventData.setEventTime(event_time);

        EventDataCollection.add(EventData);

    }

}
