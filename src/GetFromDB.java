import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nlare on 15.12.16.
 */
//@WebServlet(name = "GetFromDB")
public class GetFromDB extends HttpServlet {

    private String selectedUsername;
    private String selectedFilename;
    private String selectedTimeEvent;

    public void setSelectedUsername(String selectedUsername)   {

        this.selectedUsername = selectedUsername;

    }

    public void setSelectedFilename(String selectedFilename)   {

        this.selectedFilename = selectedFilename;

    }

    public void setSelectedTimeEvent(String selectedTimeEvent)   {

        this.selectedTimeEvent = selectedTimeEvent;

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usernameParam  = request.getParameter("username");
        String filenameParam  = request.getParameter("filename");
        String firstDateParam = request.getParameter("firstDate");
        String lastDateParam  = request.getParameter("lastDate");

        String connectionSuccess;

        EventsData selectedParametres = new EventsData();

        List<String> listOfStrings = new ArrayList<String>();

        listOfStrings.add("Select test!");

        DBConnect dbConnection = new DBConnect();

        dbConnection.connectToDB();

        Gson gson = new Gson();
//
//        try {
//
//          dbConnection.selectFromDatabase(usernameParam, filenameParam, firstDateParam, lastDateParam);
//
//        } catch (SQLException e) {
//
//            listOfStrings.add("Cannot Select! ||");
//            e.printStackTrace();
//
//        } catch (NullTimeStringException e) {
//
//            e.printStackTrace();
//
//        }

        if(usernameParam != null) {response.getWriter().write(gson.toJson(listOfStrings));}
        else {response.getWriter().write(gson.toJson("something is null!"));}

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
