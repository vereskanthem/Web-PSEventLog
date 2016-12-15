import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nlare on 15.12.16.
 */
@WebServlet(name = "GetFromDB")
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




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
