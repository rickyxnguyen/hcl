// package com.hcl;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;


// public class StudentJDBC {
// 	private static String jdbcURL = "jdbc:h2:mem:test";
// 	private static String jdbcUsername = "su";
// 	private static String jdbcPassword = "";
	
// 	private static final String CREATE_TABLE_SQL = "CREATE TABLE STUDENTS (" 
// 			+ "  id int primary key,"
// 			+ "  name varchar(20)," 
// 			+ "  age int) ";

// 	private static final String INSERT_STUDENTS_SQL = "INSERT INTO STUDENTS" 
// 			+ "  (id, name, age) VALUES "
// 			+ " (?, ?, ?);";

// 	private static final String SELECT_STUDENT_BY_ID = "select id,name,age from students where id =?";
// 	private static final String SELECT_ALL_STUDENTS = "select * from students";
// 	private static final String DELETE_STUDENTS_SQL = "delete from students where id = ?;";
// 	private static final String UPDATE_STUDENTS_SQL = "update students set name = ?,age = ? where id = ?;";

// 	public StudentJDBC() {
		
// 	}
	
// 	public static void main(String[] args) throws SQLException {
		
// 		StudentJDBC studentDao = new StudentJDBC();
// 		Scanner scanner = new Scanner(System.in);
		
// 		try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
// 			studentDao.createTable(connection);
// 			welcome();
// 			boolean cont = true;
// 			while(cont) {
// 				choices();
// 				int input = scanner.nextInt();
// 				scanner.nextLine();
// 				switch(input) {
// 					case 1:
// 						studentDao.insertStudent(connection, scanner);
// 						break;
// 					case 2:
// 						studentDao.searchStudent(connection, scanner);
// 						break;
// 					case 3:
// 						studentDao.updateStudent(connection, scanner);
// 						break;
// 					case 4:
// 						studentDao.deleteStudentById(connection, scanner);
// 						break;
// 					case 5:
// 						System.out.println(studentDao.selectAllStudents(connection));
// 						break;
// 					case 6:
// 						cont = false;
// 						goodbye();
// 						break;
// 					default: 
// 						invalidInput();
// 						break;
// 				}
// 			}

// 		}
		
// 	}
	
// 	private static void welcome() {
//         System.out.println("==========Welcome==========");
// 	}
	
// 	private static void goodbye() {
// 		System.out.println("\n==========Goodbye==========");
// 	}
	
// 	private static void invalidInput() {
// 		System.out.println("There was an error with your syntax, please check the command list and try again.");
// 	}

// 	private static void choices() {
// 		System.out.println("\n==========Main Menu==========");
//         System.out.println("Please select from the following (1,2,3,4 or 5):");
//         System.out.println("1. Insert a new student");
//         System.out.println("2. Find an existing student");
//         System.out.println("3. Update an existing student");
//         System.out.println("4. Delete an existing student");
//         System.out.println("5. Print list of all students");
//         System.out.println("6. Exit");
//         System.out.print("Enter your choice: ");
// 	}
	
// 	public boolean studentExists(Connection connection, int id) {
// 		List<Student> students = selectAllStudents(connection);
// 		return students.stream().anyMatch(i -> i.getID() == id);

// 	}
	
// 	public void createTable(Connection connection) throws SQLException {
// 		try(Statement statement = connection.createStatement()){
// 			statement.execute(CREATE_TABLE_SQL);
// 		}
// 	}
	
// 	public Student selectStudentById(Connection connection, int id) throws SQLException {
// 		Student student = null; 
// 		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
// 		preparedStatement.setInt(1, id);
// 		ResultSet rs = preparedStatement.executeQuery();

// 		while (rs.next()) {
// 			String name = rs.getString("name");
// 			int age = rs.getInt("age");
// 			student = new Student(id, name, age);
// 		}
// 		return student;
// 	}
	
// 	public void insertStudent(Connection connection, Scanner scanner) throws SQLException {
// 		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {		
// 			System.out.print("Please enter the Student's ID: ");
// 	        int id = scanner.nextInt();
// 	        System.out.print("Please enter the Student's name: ");
// 	        String name = scanner.next();
// 	        System.out.print("Please enter the Student's age: ");
// 	        int age = scanner.nextInt();
			
// 	        if(studentExists(connection, id)) {
// 	        	System.out.println("A student with that id already exists.");
// 	        	System.out.println();
// 	        }else {
// 	        	preparedStatement.setLong(1, id);
// 				preparedStatement.setString(2, name);
// 				preparedStatement.setInt(3, age);
// 				preparedStatement.executeUpdate();
// 	        }
// 		}
// 	}
	
// 	public void updateStudent(Connection connection, Scanner scanner) throws SQLException {
// 		try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS_SQL)){
// 			System.out.println("*******Update Student*******");
// 	        System.out.println("Please enter the ID of the student you want to update: ");
// 	        int id = scanner.nextInt();
// 	        if(studentExists(connection, id)) {
// 	        	Student student = selectStudentById(connection, id);
// 	        	System.out.printf("Student #%d's current name is: %s, and current age is: %d\n", id, student.getName(), student.getAge());
// 	        	System.out.println("Please enter new name: ");
// 	        	String newName = scanner.next();
// 	        	preparedStatement.setString(1, newName);
// 	        	System.out.println("Please enter new age: ");
// 	        	int newAge = scanner.nextInt();
// 	        	preparedStatement.setInt(2, newAge);
// 	        	preparedStatement.setInt(3, id);
// 	        	int update = preparedStatement.executeUpdate();
// 	        	if(update > 0) {
// 	        		System.out.printf("Successfully updated student #%d with name %s, and age %d", id, newName, newAge);
// 	        	}else {
// 	        		System.out.println("Could not update student.");
// 	        	}
// 	        }
// 		}
// 	}
	
// 	public List<Student> selectAllStudents(Connection connection) {
// 		List<Student> students = new ArrayList<>();
// 		try  {
// 			Statement statement = connection.createStatement();
// 			ResultSet rs = statement.executeQuery(SELECT_ALL_STUDENTS);
// 			while (rs.next()) {
// 				int id = rs.getInt("id");
// 				String name = rs.getString("name");
// 				int age = rs.getInt("age");
// 				students.add(new Student(id, name, age));
// 			}
// 		}catch(SQLException e) {
// 			throw new RuntimeException("Error retrieving all students.", e);
// 		}
// 		return students;
// 	}
	
// 	public void searchStudent(Connection connection, Scanner scanner) throws SQLException {
// 		System.out.println("*******Look up Student*******");
//         System.out.print("Please enter the ID of the student you want to select: ");
//         int id = scanner.nextInt();
// 		Student student = null; 
// 		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
// 		preparedStatement.setInt(1, id);
// 		ResultSet rs = preparedStatement.executeQuery();

// 		if(studentExists(connection, id)) {
// 			while (rs.next()) {
// 				String name = rs.getString("name");
// 				int age = rs.getInt("age");
// 				student = new Student(id, name, age);
// 			}
// 			System.out.printf("Student #%d is %s, and is %d years old.", id, student.getName(), student.getAge());
// 		}else {
// 			System.out.println("Student with that ID could not be found.");
// 		}
// 	}
	
// 	public void deleteStudentById(Connection connection, Scanner scanner) throws SQLException {
// 		try (PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
// 			System.out.println("*******Delete Student*******");
// 	        System.out.print("Please enter the ID of the student you would like to delete: ");
// 	        int id = scanner.nextInt();
// 	        if(studentExists(connection, id)) {
// 	        	System.out.printf("Are you sure you want to delete the student with the ID of %d? [y/n]\n", id);
// 	        	String input = scanner.next();
// 	        	if(input.equalsIgnoreCase("y")) {
// 	        		statement.setInt(1, id);
// 		        	int delete = statement.executeUpdate();
// 	        		if(delete > 0) {
// 	        			System.out.printf("Successfully removed student %d\n", id);
// 	        		}else {
// 	            		System.out.printf("Student %d was not deleted.\n", id);
// 	        		}
// 	        	}else if(input.equalsIgnoreCase("n")) {
// 	        		System.out.printf("Student %d was not deleted.\n", id);
// 	        	}
	        	
// 	        }else {
// 	        	System.out.println("Student does not exist.");
// 	        }
// 		}
// 	}
	
	
// }