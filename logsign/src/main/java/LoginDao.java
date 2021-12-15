import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginDao {
	private String jdbcUrl = "jdbc:postgresql://localhost:2020/signupdb";
	private String name="postgres";
	private String password="root123";
	private String dbdriver="org.postgresql.Driver";
	
	
	public void loadDriver(String dbdriver)
	{
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection c=null;
		try {
			c=DriverManager.getConnection(jdbcUrl, name, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;
	}
	
	
	public boolean validate(LoginBean loginBean) {
		loadDriver(dbdriver);
		Connection c = getConnection();
		boolean status=false;
		String sql= "select from member where uname= ? and password= ?";
		PreparedStatement ps;
		try {
			ps=c.prepareStatement(sql);
			ps.setString(1, loginBean.getUname());
			ps.setString(2, loginBean.getPassword());
			
			ResultSet rs=ps.executeQuery();
			status=rs.next();
		   } catch (SQLException e) {
			
			e.printStackTrace();
		}
		return status;
		
	}
	

}
