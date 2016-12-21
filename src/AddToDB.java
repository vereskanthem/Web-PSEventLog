import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.out;

/**
 * Created by nlare on 30.11.16.
 */
//@WebServlet(name = "/AddToDB")
public class AddToDB extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usernameParam  = request.getParameter("username");
        String filenameParam  = request.getParameter("filename");
        String firstDateParam = request.getParameter("firstDate");
        String lastDateParam  = request.getParameter("lastDate");

        String connectionSuccess;

        EventsData selectedParametres = new EventsData();

        List<String> listOfStrings = new ArrayList<String>();

        DBConnect dbConnection = new DBConnect();

        dbConnection.connectToDB();

        try {

//            dbConnection.selectFromDatabase(firstDateParam,lastDateParam);
            dbConnection.addToDatabase(usernameParam, filenameParam, firstDateParam);

        } catch (SQLException e) {

            listOfStrings.add("|| Cannot Add! || ");
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        listOfStrings.add("<b>UN: </b>" + usernameParam + " | ");
        listOfStrings.add("<b>UN: </b>" + filenameParam + " | ");

        listOfStrings.add("<b>FD: </b>" + firstDateParam + " (ms) | ");
        listOfStrings.add("<b>FD: </b>" + lastDateParam + " (ms) | ");

        if(dbConnection.getOracleConnectClassExist() != 0) listOfStrings.add("::Class NOT Exist!::");
        if(dbConnection.getStatusConnection() != 0) listOfStrings.add("::NOT connected!::");

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
