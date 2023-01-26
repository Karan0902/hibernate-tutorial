package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		
//		create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession();
		
		try {
//			start a transaction
			session.beginTransaction();
						
//			query students
			List<Student> students = session.createQuery("from Student").getResultList();
			displayStudents(students);
			
//			query students: lastName='Doe'
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.println("\nStudents who have last name of Doe ===>");
			displayStudents(students);
			
			
//			query students: lastName='Doe' OR firstName='Daffy'
			students = session.createQuery("from Student s where " + 
											"s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			System.out.println("\nStudents with lastName=Doe OR firstName=Daffy ===>");
			displayStudents(students);
			
			
//			query students: email ends LIKE %luv2code.com
			students = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
			System.out.println("\nStudents with email ===>");
			displayStudents(students);
			
//			commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for(Student student : students) {
			System.out.println("\nGet the student: " + student);
		}
	}

}
