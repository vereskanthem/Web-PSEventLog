
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by nlare on 30.11.16.
 */
//@WebServlet(name = "/Test")
public class Test extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usernameParam = request.getParameter("username");
        String filenameParam = request.getParameter("filename");
        String firstDateParam = request.getParameter("firstDate");
        String lastDateParam = request.getParameter("lastDate");

        String connectionSuccess;

        EventsData selectParametres = new EventsData();

        Map<EventsData,Integer> listOfEvents = new HashMap<>();
        Map<EventsData,Integer> searchEvent = new HashMap<>();

        DBConnect dbConnection = new DBConnect();

        dbConnection.setConnectionURL("jdbc:oracle:thin:@localhost:1521:orcl");
        dbConnection.setConnectionUsername("system");
        dbConnection.setConnectionPassword("1");

        dbConnection.connectToDB();

        List<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add(usernameParam);
        listOfStrings.add(filenameParam);
        listOfStrings.add(firstDateParam);
        listOfStrings.add(lastDateParam);
        listOfStrings.add(lastDateParam);

        if(dbConnection.getStatusConnection() == 0) {

            listOfStrings.add("::connected!");

        }   else {

            listOfStrings.add("::NOT connected!");

        }

        if(dbConnection.getOracleConnectClassExist() == 0)  {

            listOfStrings.add("::Class EXIST!");

        }   else    {

            listOfStrings.add("::Class NOT Exist!");

        }
//        listOfEvents.put(listOfEvents);

        Gson gson = new Gson();

        response.setContentType("application/json");

        if(usernameParam != null) {response.getWriter().write(gson.toJson(listOfStrings));}
        else {response.getWriter().write(gson.toJson("something is null!"));}

//        if(firstDateParam != null) {response.getWriter().write(gson.toJson(firstDateParam));}
//        else {response.getWriter().write(gson.toJson("firstDate is null!"));}
//
//        if(lastDateParam != null) response.getWriter().write(gson.toJson(lastDateParam));
//        else response.getWriter().write(gson.toJson("lastDate is null!"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.getSession().setAttribute("key","value");
//            request.setAttribute("key", "value");


    }
}
