package com.amit.service;

import com.amit.dto.Student;

public interface IStudentService {
	// operations to be implemented
	public String addStudent(Student std);

	public Student searchStudent(Integer sid);

	public String updateStudent(Student std);

	public String deleteStudent(Integer sid);
}