package com.ykspring.mysqltest.dao;

import java.util.List;

import com.ykspring.mysqltest.entity.Student;

public interface StudentDAO {
    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    void update(Student student);

    void delete(Integer id);
}
