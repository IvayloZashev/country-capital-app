import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection getConnection () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Connection connection;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/users", "root", "admin");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            return connection;
        } else {
            return null;
        }
    }
}
