import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Capital dao object. Defines operations to db
 */
public class CapitalDAO {

    /**
     * Selects all records from table capitals
     *
     * @return list of capitals
     */
    public static List<Capital> getAllCapitals() {
        Connection connection = ConnectDB.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Capital> capitalList = new ArrayList<>();
        try {

            if (connection != null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM capitals");
                while (resultSet.next()) {
                    Capital capital = new Capital(resultSet.getInt("capital_id"),
                            resultSet.getString("country"),
                            resultSet.getString("capital"),
                            resultSet.getInt("user_id"));
                    capitalList.add(capital);
                }
            } else {
                System.out.println("Error connecting");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return capitalList;
    }

    /**
     * Inserts new record in table capital.
     *
     * @param country country
     * @param capital capital
     * @param userId  user id of the user performing the operation
     * @return true if insert succeeds otherwise false
     */
    public static boolean insertCapital(String country, String capital, Integer userId) {
        int result = 0;
        Connection connection = ConnectDB.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                preparedStatement =
                        connection.prepareStatement("INSERT INTO capitals (country, capital, user_id) values(?,?,?)");
                preparedStatement.setString(1, country);
                preparedStatement.setString(2, capital);
                preparedStatement.setInt(3, userId);
                result = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error connecting");
        }
        return (result > 0);
    }

    /**
     * Selects record from table users by specified first name and password. The first name is reckoned to participate
     * in authentication by username. This method is used to check if authentication succeeds.
     *
     * @param userName user name
     * @param password password
     * @return user record or null if no data found
     */
    public static User getUserByUNAndPassword(String userName, String password) {
        User user = null;
        Connection connection = ConnectDB.getConnection();
        PreparedStatement preparedStatement =
                null;
        ResultSet resultSet = null;
        if (connection != null) {

            try {
                preparedStatement =
                        connection.prepareStatement("SELECT * FROM users where firstName = ? and password = ?");
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setIsAdmin(resultSet.getBoolean("isAdmin"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error connecting");
        }
        return user;
    }

}
