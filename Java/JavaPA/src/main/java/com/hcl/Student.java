package com.hcl;

import javax.persistence.Column; //JPA -> Java Persistence API
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.collect.Tables;

/*
 * This POJO is Annotated with @Entity, that means this POJO knows how to persist and read from the database
 * CRUD operation. We dont need to write any SQL query to store or retrieve information from Database
 * this is mandatory
 */
@Entity(name = "Stu")
@Table(name="Student")
public class Student /* CamelCase */ {

	/*
	 * studentID is primary key of the database, because it is annotated with @Id annotation
	 * this is mandatory
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long studentId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "Age") //snake_case
	private int age; // pascalCase

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name
				+ ", age=" + age + "]";
	}
	
}