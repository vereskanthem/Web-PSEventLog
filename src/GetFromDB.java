import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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

        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");

        String usernameParam  = request.getParameter("username");
        String filenameParam  = request.getParameter("filename");
        String firstDateParam = request.getParameter("firstDate");
        String lastDateParam  = request.getParameter("lastDate");
        String nocacheParam  = request.getParameter("nocache");

        if(usernameParam == null)   {usernameParam = "";}
        if(filenameParam == null)   {filenameParam = "";}

        if(usernameParam.contains("Enter username please...") || (usernameParam == null) || usernameParam.equals(""))
        {usernameParam = "";}
        if(filenameParam.contains("Enter filename please...") || (filenameParam == null) || filenameParam.equals(""))
        {filenameParam = "";}
        if(firstDateParam.isEmpty())     {firstDateParam = "";}
        if(lastDateParam.isEmpty())     {lastDateParam = "";}

        System.out.println("username: " + usernameParam);
        System.out.println("filename: " + filenameParam);
        System.out.println("firstDate: " + firstDateParam);
        System.out.println("lastDate: " + lastDateParam);
        System.out.println("nocache: " + nocacheParam);

        DBConnect dbConnection = new DBConnect();

        dbConnection.connectToDB();

        Gson gson = new Gson();

        response.setContentType("application/json");

        try {

            response.getWriter().write(gson.toJson(dbConnection.getListFromDatabase(usernameParam,filenameParam,firstDateParam,lastDateParam,nocacheParam)));

        } catch (Exception e)   {

            e.printStackTrace();

        } catch (NullTimeStringException e) {

            e.printStackTrace();

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
