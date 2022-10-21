package IntroductiontoDBAppsExercises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static IntroductiontoDBAppsExercises.Constants.*;

public class P7PrintAllMinionNames {

    public static final String MINION_NAMES = "SELECT name FROM minions";

    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();

        properties.setProperty(USER_KEY, USER_VALUE);
        properties.setProperty(PASS_KEY, PASS_VALUE);

        Connection connection = DriverManager.getConnection(JDBC_URL, properties);

        PreparedStatement statement = connection.prepareStatement(MINION_NAMES);
        ResultSet resultSet = statement.executeQuery();
        List<String> names = new ArrayList<>();

        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }

        for (int i = 0; i < names.size() / 2; i++) {

            System.out.println(names.get(i));
            System.out.println(names.get(names.size() - 1 - i));

        }
    }
}
