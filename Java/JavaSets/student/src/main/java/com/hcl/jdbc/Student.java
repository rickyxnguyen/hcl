package com.hcl.jdbc;
//Plain old java objects (POJO)
public class Student {
	private int id;
	private String name;
	private int age;

	public Student(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
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
	public int hashCode() {
		return this.id + this.name.hashCode() + this.age;
	}

	@Override
	public boolean equals(Object obj) {
		Student s = (Student) obj;
		return s.age == age && s.id == id && s.name.equals(name);
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + ", id=" + id + "]";
	}

	
}