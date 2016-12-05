import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.*;

/**
 * Created by nlare on 29.11.16.
 */
//@WebServlet(name = "GetJSON")
public class GetJSON extends HttpServlet {

    String jsonDataString;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        PrintWriter writer = response.getWriter();


//        jsonDataString = request.getParameter("jsonData");
//
//        if(jsonDataString != null) {
//            out.println(jsonDataString);
//            writer.println(jsonDataString);
//        }   else    {
//
//            out.println("jsonDataString is NULL");
//
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        response.setContentType("text/html");
//        response.sendRedirect("index.jsp?test=stTSst");

    }




}
