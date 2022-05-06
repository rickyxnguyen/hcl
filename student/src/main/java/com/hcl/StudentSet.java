package com.hcl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StudentSet {
	// creates a treeset that will sort alphabetically
	private static final Set<Student> studentSet = new TreeSet<>((o1, o2) -> o1.getName().compareTo(o2.getName()));

	public static void main(String[] args) {
		// initialize scanner to accept user input
		Scanner scanner = new Scanner(System.in);

		// prepopulate the treeset with students
		studentSet.add(new Student(1, "Jake", 20));
		studentSet.add(new Student(2, "Logan", 48));
		studentSet.add(new Student(3, "Nathan", 12));
		studentSet.add(new Student(4, "Brandon", 23));
		studentSet.add(new Student(5, "Ricky", 22));

		welcome();
		// default value until user exits program
		boolean cont = true;
		while (cont) {
			choices();
			int input = scanner.nextInt();
			scanner.nextLine();
			switch (input) {
				case 1:
					addStudent(scanner);
					break;
				case 2:
					lookUpStudent(scanner);
					break;
				case 3:
					updateStudent(scanner);
					break;
				case 4:
					deleteStudent(scanner);
					break;
				case 5:
					listStudents();
					try {
						readFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					cont = false;
					goodbye();
					break;
				default:
					invalidInput();
					break;
			}
		}
	}

	private static void welcome() {
		System.out.println("==========Welcome==========");
	}

	private static void goodbye() {
		System.out.println("\n==========Goodbye==========");

	}

	private static void invalidInput() {
		System.out.println("There was an error with your syntax, please check the command list and try again.");
	}

	private static boolean studentExists(int id) {
		// stream goes through the set to check if any of the student's id matches the
		// user input
		return studentSet.stream().anyMatch(i -> i.getID() == id);
	}

	private static void addStudent(Scanner scanner) {
		System.out.print("Please enter the Student's ID: ");
		int id = scanner.nextInt();
		System.out.print("Please enter the Student's name: ");
		String name = scanner.next();
		System.out.print("Please enter the Student's age: ");
		int age = scanner.nextInt();

		if (studentExists(id)) {
			// will decline appending adding if student with id exists
			System.out.println("A student with that ID already exists.");
			System.out.println();
		} else {
			// appends if input will not generate a duplicate
			Student student = new Student(id, name, age);
			studentSet.add(student);
			System.out.printf("Successfully added new student: \n ID: %d, Name: %s, Age: %d\n", student.getID(),
					student.getName(), student.getAge());
		}
	}

	private static void choices() {
		System.out.println("\n==========Main Menu==========");
		System.out.println("Please select from the following (1,2,3,4,5):");
		System.out.println("1. Add a new student");
		System.out.println("2. Find an existing student");
		System.out.println("3. Update an existing student");
		System.out.println("4. Delete an existing student");
		System.out.println("5. Print list of all students");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");
	}

	private static Student getStudentById(int id) {
		// grabs student from treeset if id matches
		Student student = studentSet.stream().filter(p -> p.getID() == (id)).findAny().orElse(null);
		return student;
	}

	private static boolean isRemoved(int id) {
		// returns true or false if student exists with inputted id
		return studentExists(id);
	}

	private static void deleteStudent(Scanner scanner) {
		System.out.println("*******Delete Student*******");
		System.out.print("Please enter the ID of the student you would like to delete: ");
		int id = scanner.nextInt();
		// checks if student exists first
		if (studentExists(id)) {
			System.out.printf("Are you sure you want to delete the student with the ID of %d? [y/n]\n", id);
			String input = scanner.next();
			// if selected either Y or y student will be removed
			if (input.equalsIgnoreCase("y")) {
				studentSet.remove(getStudentById(id));
				if (isRemoved(id)) {
					System.out.printf("Successfully removed student %d\n", id);
				} else {
					System.out.printf("Student %d does not exist.\n", id);
				}
			} else if (input.equalsIgnoreCase("n")) {
				System.out.printf("Student %d was not deleted.\n", id);
			}
		}
	}

	private static void lookUpStudent(Scanner scanner) {
		System.out.println("*******Look up Student*******");
		System.out.print("Please enter the Roll Number of the student you want to look up: ");
		int id = scanner.nextInt();
		// grabs student only if student exists
		if (studentExists(id)) {
			Student student = getStudentById(id);
			System.out.printf("Student #%d is %s and is %d years old.\n", id, student.getName(), student.getAge());
		} else {
			System.out.println("Student with that ID number does not exist.");
		}
	}

	private static void updateStudent(Scanner scanner) {
		System.out.println("*******Update Student*******");
		System.out.println("Please enter the ID of the student you want to update: ");
		int id = scanner.nextInt();
		// checks if student exists and then will prompt changes to values
		if (studentExists(id)) {
			Student student = getStudentById(id);
			System.out.printf("Student #%d's current name is: %s, and current age is: %d\n", id, student.getName(),
					student.getAge());
			System.out.println("Please enter new name: ");
			student.setName(scanner.next());
			System.out.println("Please enter new age: ");
			student.setAge(scanner.nextInt());

			System.out.printf("Successfully updated student #%d with name %s, and age %d", id, student.getName(),
					student.getAge());
		} else {
			System.out.printf("Student with ID of: %d could not be found.\n", id);
		}
	}

	private static void listStudents() {
		// prints to the console the file information and treeset values
		System.out.println("*******Printing Students*******");
		try {
			writeToFile(studentSet);
			studentSet.forEach(student -> System.out.println(student.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void writeToFile(Set<Student> set) throws IOException {
		// writes to file what values the treeset has
		String data = studentSet.stream().map(e -> e.toString()).collect(Collectors.joining("\n"));

		try (FileWriter fw = new FileWriter("output.txt");) {

			data.chars()
					.forEach(i -> {
						try {
							fw.write(Character.toChars(i));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					});

			System.out.println("Writing successful");
		}
	}

	private static void readFile() throws IOException {
		// reads the lines from the file if exists
		System.out.println("Printing the file");
		try (FileReader fr = new FileReader("output.txt");
				BufferedReader br = new BufferedReader(fr)) {
			// read from FileReader till the end of file
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}