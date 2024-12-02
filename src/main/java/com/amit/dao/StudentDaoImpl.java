package com.amit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amit.dto.Student;

//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmtd = null;
	PreparedStatement pstmtu = null;
	ResultSet resultSet = null;

	@Override
	public String addStudent(Student std) {
		String sqlInsertQuery = "insert into student(`id`,`name`,`age`, `address`) values(?,?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {
				pstmt.setInt(1, std.getSid());
				pstmt.setString(2, std.getSname());
				pstmt.setInt(3, std.getSage());
				pstmt.setString(4, std.getAddress());
				int rowAffected = pstmt.executeUpdate();

				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {

		String sqlSearchQuery = "select id,name,age,address from student where id=?";
		Student student = null;
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlSearchQuery);

			if (pstmt != null) {
				pstmt.setInt(1, sid);
				resultSet = pstmt.executeQuery();

				if (resultSet != null) {
					if (resultSet.next()) {
						student = new Student();

						// copy resultSet data to student object
						student.setSid(resultSet.getInt(1));
						student.setSname(resultSet.getString(2));
						student.setSage(resultSet.getInt(3));
						student.setAddress(resultSet.getString(4));

						return student;
					}

				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return student;

	}

	@Override
	public String updateStudent(Student std) {
		String sqlUpdateQuery = "update student set name=?,age=?,address=? where id=?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmtu = connection.prepareStatement(sqlUpdateQuery);

			if (pstmtu != null) {
				pstmtu.setString(1, std.getSname());
				pstmtu.setInt(2, std.getSage());
				pstmtu.setString(3, std.getAddress());
				pstmtu.setInt(4, std.getSid());
				int rowAffected = pstmtu.executeUpdate();

				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "delete from student where id=?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmtd = connection.prepareStatement(sqlDeleteQuery);

			if (pstmtd != null) {
				pstmtd.setInt(1, sid);
				int rowAffected = pstmtd.executeUpdate();

				if (rowAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

}
