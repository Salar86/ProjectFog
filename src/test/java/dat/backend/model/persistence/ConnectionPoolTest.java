package dat.backend.model.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    private  static ConnectionPool connectionpool;
    private final static String USER = "root";
    private final static String PASSWORD = "Salar0108";
    private final static String URL = "jdbc:mysql://localhost:3306/project_fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    @BeforeAll
    public static void setUpClass() {
        connectionpool = new ConnectionPool();
    }

    @Test
    void getConnection() throws SQLException {
        Connection connection = connectionpool.getConnection();
        assertNotNull(connection);
        if(connection != null){
            connection.close();
        }
    }
}