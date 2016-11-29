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
@WebServlet(name = "GetJSON")
public class GetJSON extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jsonDataString = request.getParameter("jsonData");
        PrintWriter writer = response.getWriter();

        if(jsonDataString != null) {
            out.println(jsonDataString);
            writer.println(jsonDataString);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
