package dk.easv.easvticket.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBConnector {
    private static final String PROP_FILE = "config/config.settings";
    private SQLServerDataSource dataSource;

    public DBConnector() throws Exception{
        Properties databaseProperties = new Properties();
        try {
            databaseProperties.load(new FileInputStream(new File(PROP_FILE)));

            dataSource = new SQLServerDataSource();
            dataSource.setServerName(databaseProperties.getProperty("Server"));
            dataSource.setDatabaseName(databaseProperties.getProperty("Database"));
            dataSource.setUser(databaseProperties.getProperty("User"));
            dataSource.setPassword(databaseProperties.getProperty("Password"));
            dataSource.setPortNumber(1433);
            dataSource.setTrustServerCertificate(true);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    public static Connection getStaticConnection() throws Exception {
        DBConnector dbConnector = new DBConnector();
        return dbConnector.getConnection();
    }

    public static void main(String[] args) throws Exception {
        DBConnector databaseConnector = new DBConnector();
        try (Connection connection = databaseConnector.getConnection()) {
            System.out.println("Is it open? " + !connection.isClosed());
        }
    }
}
