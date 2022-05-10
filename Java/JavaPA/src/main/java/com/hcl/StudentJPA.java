package com.hcl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class StudentJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            emf = Persistence.createEntityManagerFactory("jbd-pu");
            entityManager = emf.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Student student = new Student();
            student.setName("Sumit");
            student.setAge(20);

            entityManager.persist(student);

            student = new Student();
            student.setName("Krishna");
            student.setAge(21);

            entityManager.persist(student);

            transaction.commit();
            /*
             * 'select s from Stu s' looks like SQL, but it is called HQL
             * Actual SQL statement likes like 'select * from Student'
             */
            Query q = entityManager.createQuery("select s from Stu s");

            List<Student> resultList = q.getResultList();
            System.out.println("Number of sudents:" + resultList.size());
            for (Student next : resultList) {
                System.out.println("Next student: " + next);
            }

        } catch (Exception e) {
            System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}