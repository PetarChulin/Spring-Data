package IntroductiontoDBAppsExercises;

import java.sql.*;
import java.util.Properties;

import static IntroductiontoDBAppsExercises.Constants.*;

public class P2GetVillainsNames {

    public static final String COUNT_VILLAINS_MINIONS = "SELECT v.name," +
            " COUNT(DISTINCT mv.minion_id) AS count FROM villains v\n" +
            " JOIN minions_villains mv ON mv.villain_id = v.id" +
            " GROUP BY v.name HAVING count > 15  ORDER BY count DESC";


    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();

        properties.setProperty(USER_KEY, USER_VALUE);
        properties.setProperty(PASS_KEY, PASS_VALUE);

        Connection connection = DriverManager.getConnection(JDBC_URL, properties);

        PreparedStatement selectVillain = connection.prepareStatement(COUNT_VILLAINS_MINIONS);
        ResultSet resultSet = selectVillain.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString("v.name");
            int countMinions = resultSet.getInt("count");

            System.out.println(villainName + " " + countMinions);
        }
    }
}
