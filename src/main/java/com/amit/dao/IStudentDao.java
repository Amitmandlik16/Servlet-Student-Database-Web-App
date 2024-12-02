package com.amit.dao;

import com.amit.dto.Student;

//Need of DTO in projects
//DTO -> It stands for Data Transfer Object.
//This object is used for transferring the data from one layer to another layer in realtime applications.

public interface IStudentDao {
	Object StudentDaoFactory = null;

	// operations to be implemented
	public String addStudent(Student std);

	public Student searchStudent(Integer sid);

	public String updateStudent(Student std);

	public String deleteStudent(Integer sid);
}
