import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nlare on 15.12.16.
 */
public class DateConverter {

    public String ConvertMillisecToSQLDateString(String sourceDateInMillisec, String dateFormat)   {

        Long sourceDateLong = Long.parseLong(sourceDateInMillisec);
        DateFormat formatter = new SimpleDateFormat(dateFormat);
//        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sourceDateLong);

        String resultDateInSQLCompatibleFormat = formatter.format(calendar.getTime());

        return resultDateInSQLCompatibleFormat;

    }

}
