package org.dpk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dpk.model.Circle;

public class JdbcDaoImpl {
	public Circle getCircle(int circleId)
	{
		Connection con=null;
		String driver = "org.apache.derby.jdbc.ClientDriver";
		
		try {
			Class.forName(driver).newInstance();
			con=DriverManager.getConnection("jdbc:derby://localhost:1527/db");
			PreparedStatement ps=con.prepareStatement("select * from circle where id=?");
			ps.setInt(1, circleId);
			
			Circle circle=null;
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				circle=new Circle(circleId, rs.getString("name"));
			}
			rs.close();
			ps.close();
			return circle;			
		} catch (Exception e) {
			throw new RuntimeException();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
