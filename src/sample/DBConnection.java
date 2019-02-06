package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class is responsible for creating connection with SQLServer
 * by given username and password
 * @author Monika Regula
 * @version 1.0
 */
public class DBConnection {

    /**
     * Method makes connection with SqlServer
     * @return con
     */
    public static Connection kosmetykiConnection(){

        Connection con = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Kosmetyki;user=Monia;password=beverly90210";
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
