package IntroductiontoDBAppsExercises;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static IntroductiontoDBAppsExercises.Constants.*;

public class P3GetMinionNames {

    public static final String SELECT_VILLAIN = "SELECT name FROM villains WHERE id = ?";
    public static final String SELECT_MINIONS = """
            SELECT m.name, m.age FROM minions m
            JOIN minions_villains mv ON m.id = mv.minion_id
            WHERE mv.villain_id = ?""";
    public static final String VILLAIN_NOT_EXISTS = "No villain with ID %d exists in the database.";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties properties = new Properties();

        properties.setProperty(USER_KEY, USER_VALUE);
        properties.setProperty(PASS_KEY, PASS_VALUE);

        Connection connection = DriverManager.getConnection(JDBC_URL, properties);

        PreparedStatement villainStmt = connection.prepareStatement(SELECT_VILLAIN);
        System.out.println("Enter id...");

        int id = Integer.parseInt(sc.nextLine());
        villainStmt.setInt(1, id);

        ResultSet villainSet = villainStmt.executeQuery();

        if (villainSet.next()) {
            System.out.println("Villain: " + (villainSet.getString("name")));
        } else {
            System.out.printf(VILLAIN_NOT_EXISTS, id);
        }

        PreparedStatement minionStmt =
                connection.prepareStatement(SELECT_MINIONS);

        minionStmt.setInt(1, id);

        ResultSet minionSet = minionStmt.executeQuery();
        int count = 0;
        while (minionSet.next()) {
            String minionName = minionSet.getString("m.name");
            String minionAge = minionSet.getString("m.age");
            count++;
            System.out.println(count + ". " + minionName + " " + minionAge);
        }
        connection.close();
    }
}
