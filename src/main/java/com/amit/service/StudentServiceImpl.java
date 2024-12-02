package com.amit.service;

import com.amit.daofactory.StudentDaoFactory;
import com.amit.dto.Student;
import com.amit.dao.IStudentDao;
import com.amit.dto.Student;
import com.amit.dao.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao stdDao;

	@Override
	public String addStudent(Student std) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.addStudent(std);
	}

	@Override
	public Student searchStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.searchStudent(sid);
	}

	@Override
	public String updateStudent(Student std) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.updateStudent(std);
	}

	@Override
	public String deleteStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.deleteStudent(sid);
	}

}
