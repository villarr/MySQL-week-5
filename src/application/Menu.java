package application;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.EmployeeDao;

// menu application for accessing company databse using crud operations via dao methods.
public class Menu {
	private EmployeeDao employeeDao = new EmployeeDao();
	private Scanner scanner = new Scanner(System.in);
	
	private List<String> options = Arrays.asList(
			"Dislay Employees",
			"Create New Employee",
			"Delete Employee(s)",
			"Update Employee(s)"
			);
	
	public void start() throws SQLException {
		String selection = "";
	
		
		do {
			printMenu();
			selection = scanner.nextLine();
		if (selection.equals("1")) {
			employeeDao.getEmployees();
			System.out.println("\n");
			
		} else if (selection.equals("2")) {
			employeeDao.createEmployees();
			System.out.println("\n");
		
		} else if (selection.equals("3")) {
			employeeDao.deleteEmployees();
			System.out.println("\n");
			
		} else if (selection.equals("4")) {
			employeeDao.updateEmployees();
			System.out.println("\n");
		}
			
			
		} while (!selection.equals("-1"));
			System.out.println("Press enter to restart menu...");
			scanner.nextLine();
			start();
	}
	
private void printMenu () {
	System.out.println("Welcome to the new and improved Company Database! Please make a selection by entering in a number corresponding to a menu operation. Thank you!");
	for (int i = 0; i < options.size(); i ++) {
		System.out.println(i + 1 + ") " + options.get(i));
	}
}
}

