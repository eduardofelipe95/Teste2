
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static String usuario="root";
    private static String senha="12345678";
	private static String banco="teste";
    private static String ip="localhost";
    private static String driver="com.mysql.jdbc.Driver";
    private static Connection conexao = null;
    
	public Connection getConnection() {
	     try {
	         return DriverManager.getConnection("jdbc:mysql://"+ip+"/"+banco+"",usuario, senha);
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }

}