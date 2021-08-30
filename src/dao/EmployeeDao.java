package dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// Employee Dao class containing queries and methods for working with Company Database. 

public class EmployeeDao {

private Connection connection;
private final String GET_EMPLOYEES_QUERY = "SELECT * FROM employee";
private final String CREATE_EMPLOYEES_QUERY = "INSERT INTO employee (id, shirt_size, company_name, dept_name, job_title, university, linkedin_skill)\n"
+ " VALUES(?,?,?,?,?,?,?)";
private final String DELETE_EMPLOYEES_QUERY = "DELETE from employee WHERE id = ?"; 
private final String UPDATE_EMPLOYEES_QUERY = "UPDATE employee SET job_title = ? WHERE id = ?";
int x = 0;

private Scanner sc = new Scanner(System.in);


public EmployeeDao() {
	connection = DBConnection.getConnection();
	
}
// Method for creating employees - thank you Nick for mentioning the Integer.parse method!

public void createEmployees () throws SQLException {

int n = 0; 

PreparedStatement ps = connection.prepareStatement(CREATE_EMPLOYEES_QUERY);

System.out.println("Enter no. of employees to insert"); 
n = Integer.parseInt(sc.nextLine());

for (int i = 1; i <= n; i++) {
	
	System.out.println("Enter a new employee id > 1000 (int)");
	Integer emp_id = Integer.parseInt(sc.nextLine());
	
	System.out.println("Enter a shirt size. L, M, S, XL, XS");		
	String shirt = sc.nextLine();
	
	System.out.println("Enter a new company name.");
	String company = sc.nextLine();

	System.out.println("Enter the department name.");
	String dept = sc.nextLine();

	System.out.println("Enter their job title.");
	String job = sc.nextLine();

	System.out.println("Enter the name of their last college/university attended.(limit 150)");
	String uni = sc.nextLine();

	System.out.println("Enter the employee's linkedin skill.(limit 50)");
	String skill = sc.nextLine();


ps.setInt(1,emp_id);
ps.setString(2,shirt);
ps.setString(3,company);
ps.setString(4,dept);
ps.setString(5,job);
ps.setString(6,uni);
ps.setString(7,skill);
x = ps.executeUpdate();
}
int row = x;
if (row > 0) {
	System.out.println("New information has been added to the employee table." + "\n");
} else System.out.println("No records were added.");
}

// Method for deleting employees by employee id.

public void deleteEmployees() throws SQLException {
	ResultSet rs = connection.prepareStatement(GET_EMPLOYEES_QUERY).executeQuery();
	while (rs.next()) {
	System.out.println("ID = " + rs.getInt(1) + "/ COMPANY = " + rs.getString(3)
	+ "/ Job = " + rs.getString(5));
	}
	System.out.println("\n");
	int n = 0; 

	PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEES_QUERY);

	System.out.println("Enter no. of employees to delete"); 
	n = sc.nextInt();

	for (int i = 1; i <= n; i++) {
		System.out.println("Enter an id to delete 1 - 1000 (int)");
		Integer id = sc.nextInt();
		ps.setInt(1,id);
		x = ps.executeUpdate();
	}
	int row = x;
	if (row > 0) {
		System.out.println(n + " Records have been deleted from the employee table." + "\n");
	} else System.out.println("No records were updated.");		
	
} 

// method for updating job title based on employee id. 

public void updateEmployees() throws SQLException {
	ResultSet rs = connection.prepareStatement(GET_EMPLOYEES_QUERY).executeQuery();
	while (rs.next()) {
	System.out.println("Id = " + rs.getInt(1) + "/ Company = " + rs.getString(3)
	+ "/ Job = " + rs.getString(5));
	}
	System.out.println("\n");
	
	int n = 0; 

	PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEES_QUERY);

	System.out.println("Enter no. of employees to update");
	
	n = Integer.parseInt(sc.nextLine());

	for (int i = 1; i <= n; i++) {
		
		System.out.println("Enter the updated job title.(String)");
		String data = sc.nextLine();
		ps.setString(1,data);
		
		System.out.println("Enter the id of the employee you would like to be updated.(String)");
		Integer id = Integer.parseInt(sc.nextLine());
		ps.setInt(2,id);
		x = ps.executeUpdate();
	}
	
	int row = x;
	if (row > 0) {
		System.out.println(n + " records have been updated in the employee table." + "\n");
	} else System.out.println("No records were updated.");		
	
}

//Method for reading all rows in the company database. 

public void getEmployees() throws SQLException {
	ResultSet rs = connection.prepareStatement(GET_EMPLOYEES_QUERY).executeQuery();
	while (rs.next()) {
	System.out.println(" Id = " + rs.getInt(1) + "/ Shirt Size = " + rs.getString(2)
	 + "/ Company = " + rs.getString(3) + "/ Department = " + rs.getString(4)
	 + "/ Job = " + rs.getString(5) + "/ University = " + rs.getString(6) + "/ Skill = " + rs.getString(7) 
	 + "\n");
}

}
}

