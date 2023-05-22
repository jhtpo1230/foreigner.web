package JCYB.foreigner.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;

@Controller
public class ConnectionController {

    @PostMapping("/end")
    public void performSearch(@RequestParam("ADDR") String ADDR) {
        try (Connection connection = createConnection()) {
            if (connection != null) {
                System.out.println("Success to connect to the database!");
                searchEntities(connection, ADDR);
            } else {
                System.out.println("Failed to connect to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/foreigner?serverTimezone=UTC";
        String username = "root";
        String password = "4112";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        return connection;
    }
    public void searchEntities(Connection connection, String ADDR) {
        try {
            String sql = "SELECT * FROM cultural_space_info WHERE addr LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + ADDR + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String fac_name = resultSet.getString("fac_name");
                String address = resultSet.getString("addr");
                String subjcode = resultSet.getString("subjcode");
                // 추가적인 필드 가져오기

                // 검색 결과 처리 로직
                System.out.println("Name: " + fac_name);
                System.out.println("Address: " + address);
                System.out.println("Subjcode: " + subjcode);
                // 추가적인 필드 출력

                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    /* @PostMapping("/end")
    public Connection createConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/foreigner?serverTimezone=UTC";
        String username = "root";
        String password = "4112";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Database Connection Successful!");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return connection;
    }
    public void searchEntities(String ADDR) {
        try (Connection connection = createConnection()) {
            String sql = "SELECT * FROM cultural_space_info WHERE addr LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + ADDR + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String fac_name = resultSet.getString("fac_name");
                String address = resultSet.getString("addr");
                String subjcode = resultSet.getString("subjcode");
                // 추가적인 필드 가져오기

                // 검색 결과 처리 로직
                System.out.println("Name: " + fac_name);
                System.out.println("Address: " + address);
                System.out.println("Subjcode: " + subjcode);
                // 추가적인 필드 출력

                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.out.println("검색 결과 없음");
            e.printStackTrace();
        }
    }
} */
