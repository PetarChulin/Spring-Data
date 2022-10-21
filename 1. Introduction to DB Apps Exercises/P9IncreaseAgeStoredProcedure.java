package IntroductiontoDBAppsExercises;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static IntroductiontoDBAppsExercises.Constants.*;

public class P9IncreaseAgeStoredProcedure {

    public static final String AGE_INCREASE_PROCEDURE = "CALL usp_get_older(?)";
    public static final String SELECT_MINION = "SELECT name, age FROM minions WHERE id = ?";

    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();

        properties.setProperty(USER_KEY, USER_VALUE);
        properties.setProperty(PASS_KEY, PASS_VALUE);

        Connection connection = DriverManager.getConnection(JDBC_URL, properties);

        Scanner sc = new Scanner(System.in);

        int id = sc.nextInt();

        PreparedStatement increaseAgeStmt = connection.prepareStatement(AGE_INCREASE_PROCEDURE);
        increaseAgeStmt.setInt(1, id);
        increaseAgeStmt.executeUpdate();

        PreparedStatement selectMinion = connection.prepareStatement(SELECT_MINION);
        selectMinion.setInt(1,id);
        ResultSet printMinion = selectMinion.executeQuery();
        printMinion.next();

        System.out.println(printMinion.getString("name") + " " + printMinion.getInt("age"));

        connection.close();
    }
}
