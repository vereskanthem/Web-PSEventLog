/**
 * Created by nlare on 08.12.16.
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnect {

    private String connectionURL;
    private String DBlogin;
    private String DBpassword;

    public void setConnectionURL(String connectionURL) {

        this.connectionURL = connectionURL;

    }

    public static int addToDatabase(String username, String filename, String firstDate, String lastDate) {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        }   catch(ClassNotFoundException cne)   {

            System.out.println("Cannot connect Oracle thought JDBC! Class not found!");

        }

        Connection connection = null;

        try {

//            connection = DriverManager.getConnection("");

        }   catch() {


        }

    }

}
