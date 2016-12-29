import java.util.Date;

/**
 * Created by nlare on 06.12.16.
 */
public class EventsData {

    private String username;
    private String filename;
    private String eventTime;

    public void setUsername(String username)  {

        this.username = username;

    }

    public void setFilename(String filename)  {

        this.filename = filename;

    }

    public void setEventTime(String eventTime) {

        this.eventTime = eventTime;

    }

    public String getUsername() {

        return this.username;

    }

    public String getFilename() {

        return this.filename;

    }

    public String getEventTime()    {

        return this.eventTime;

    }

}
