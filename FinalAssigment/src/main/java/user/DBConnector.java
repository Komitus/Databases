package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class DBConnector {

    private Connection connection;

    private PreparedStatement statement;

    private static DBConnector instance;
    private static int idKlienta;
    private DBConnector () {

    }

    public static DBConnector getInstance () {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }
    public static int getIdKlienta() {
    	return idKlienta;
    }
    public void setIdKlienta(int id) {
    	this.idKlienta = id;
    }
}
