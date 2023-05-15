package com.ykspring.mysqltest;

import java.util.List;

import org.hibernate.boot.TempTableDdlTransactionHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ykspring.mysqltest.dao.StudentDAO;
import com.ykspring.mysqltest.entity.Student;

@SpringBootApplication
public class MysqltestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqltestApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			readStudent(studentDAO);
			queryForStudent(studentDAO);
			updateStudent(studentDAO);
			deleteStudent(studentDAO);
		};

	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("creating student");
		Student newStudent = new Student("Yogesh2", "Khot2", "yk2@gmail.com");

		// save the object
		studentDAO.save(newStudent);

		// display the id of saved student
		System.out.println("created student with id " + newStudent.getId());

	}

	private void readStudent(StudentDAO studentDAO) {
		Student getStudent = studentDAO.findById(1);
		System.out.println("found student" + getStudent.toString());
	}

	private void queryForStudent(StudentDAO studentDAO) {
		// get list of students
		List<Student> allStudent = studentDAO.findAll();
		for (Student student : allStudent) {
			System.out.println("student detail - " + student);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		Student temStudent = studentDAO.findById(1);
		temStudent.setEmail("yk1@gmail.com");
		studentDAO.update(temStudent);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(4);
		queryForStudent(studentDAO);
	}

}
