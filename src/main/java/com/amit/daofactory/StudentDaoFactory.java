package com.amit.daofactory;

import com.amit.dao.IStudentDao;
import com.amit.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private StudentDaoFactory() {
	}

	private static IStudentDao studentDao = null;

	public static IStudentDao getStudentDao() {
		if (studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
	}

}