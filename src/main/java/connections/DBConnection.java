package connections;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static configuration.ConfigProperties.*;

public class DBConnection {

	private static final Logger log = LogManager.getLogger(DBConnection.class);
	
	public static Connection openDBConnection() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Connection connection = null;
		try {

			//db driver read from properties file ex: "org.postgresql.Driver"
			Class.forName(dBClass());
			Properties properties = new Properties();
			properties.setProperty("user", dBuser());

			properties.setProperty("password", dBpassword());
			connection = DriverManager.getConnection(dBurl(), properties);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			log.info("exception :{}" , cnfe.getException().toString());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			log.info("exception :{}" , sqle.getMessage());

		}
		return connection;
	}

	// close connection
	public static void closeConnection(Connection connection)  {
		try {
			connection.close();
		}

		catch (SQLException e1) {
			log.info("connection is closed");
			log.info("exception :{}" , e1.getMessage());
		}
	}

	public static <T> T runQuery(String sql, RowMapper<T> rowMapper) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		Connection connection = openDBConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		T result = null;
		while (resultSet.next()) {
			result = rowMapper.mapRow(resultSet);
		}
		closeConnection(connection);
		return result;
	}

	public interface RowMapper<T> {
		T mapRow(ResultSet resultSet) throws SQLException;
	}

}
