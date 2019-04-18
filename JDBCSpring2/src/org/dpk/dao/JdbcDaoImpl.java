package org.dpk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.dpk.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	/*public Circle getCircle(int circleId) {
		Circle circle = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from circle where id=?");
			ps.setInt(1, circleId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));
			}
			rs.close();
			ps.close();
			System.out.println("hi");
			return circle;
		} catch (Exception e) {
			System.out.println("exception");
			throw new RuntimeException();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/
	
	public int getCircleCount() {
		String sql = "select count(*) from circle";
		return jdbcTemplate.queryForInt(sql);
	}

	public String getCircleName(int id) {
		String query = "select name from circle where id=?";
		return jdbcTemplate.queryForObject(query, new Object[] { id }, String.class);
	}

	public Circle getCircleById(int id) {
		String query = "select * from circle where id=?";
		return jdbcTemplate.queryForObject(query, new Object[] { id }, new ClassMapper());
	}

	private static final class ClassMapper implements RowMapper<Circle> {
		@Override
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setCircleId(resultSet.getInt("id"));
			circle.setName(resultSet.getString("name"));
			return circle;
		}
	}

	public List<Circle> getAllCircle() {
		String query = "select * from circle";
		return jdbcTemplate.query(query, new ClassMapper());
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
