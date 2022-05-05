package com.hcl;

//Plain old java objects (POJO)
public class Student {
	private int rollnumber;
	private String name;
	private int age;

	public Student(int rollnumber, String name, int age) {
		this.rollnumber = rollnumber;
		this.name = name;
		this.age = age;
	}

	public int getRollNumber() {
		return rollnumber;
	}

	public void setRollNumber(int rollnumber) {
		this.rollnumber = rollnumber;
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
		return this.rollnumber + this.name.hashCode() + this.age;
	}

	@Override
	public boolean equals(Object obj) {
		Student s = (Student) obj;
		return s.age == age && s.rollnumber == rollnumber && s.name.equals(name);
	}

}