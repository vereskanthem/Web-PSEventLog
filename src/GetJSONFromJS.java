/**
 * Created by nlare on 28.11.16.
 */

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.http.HttpServlet;

public class GetJSONFromJS extends HttpServlet {

    String jsonString;
    protected JSONObject jsonObject;

    int getJSON()   {
//
//        jsonString = getParameter("jsonData");
//        jsonObject = (JSONObject) JSONValue.parse(jsonString);


        return 1;
    }

}