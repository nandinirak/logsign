import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao extends Member{
	
	private String jdbcUrl = "jdbc:postgresql://localhost:2020/signupdb";
	private String name="postgres";
	private String dbpassword="root123";
	private String dbdriver="org.postgresql.Driver";
	
	public void loadDriver(String dbdriver)
	{
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection c = null;
		try {
			c=DriverManager.getConnection(jdbcUrl, name, dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
	public String insert(Member member)
	{
		loadDriver(dbdriver);
		Connection con = getConnection();
		String result = "Data entered successfully";
		String sql = "insert into member values(?,?,?,?)";
		
		PreparedStatement ps;
		
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getUname());
		ps.setString(2, member.getPassword());
		ps.setString(3, member.getEmail());
		ps.setString(4, member.getPhone());
		ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Data not entered";
		}
		return result;
	}

}