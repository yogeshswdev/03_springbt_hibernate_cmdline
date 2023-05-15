package com.ykspring.mysqltest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ykspring.mysqltest.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity mnager
    private EntityManager entityManager;

    // inject entity manager using const injec
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        // ((StudentDAOImpl) entityManager).save(theStudent);
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);

    }

    @Override
    public List<Student> findAll() {
        // cretae query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);

        // return result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrive student using id
        Student tempStudent = entityManager.find(Student.class, id);
        // delete
        entityManager.remove(tempStudent);
    }

}
