package main;

import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static LinkedList<Student> allStudents;
	
	//main method is here		
	public static void main(String[] args) {

		int option;
		allStudents = new LinkedList<Student>();
		 //try to read file, launch menu if successful, otherwise throw an error
		boolean success = readFile("studentdata.txt");
		
		//this is the error
		if(!success){
			System.out.println("Error reading data from file.");
			return;
			}
		
			do {
				//launches the menu
				displayMenu();
		
				option = selectMenuOption();
		
				switch (option) {
					case 1:
						//this is for adding a new student
						inputNewStudent();
						break;
					case 2:
						//display all students marks
						displayReportByMarks();
						break;
					case 3:
						//display all students grades
						displayReportByGrades();
						break;
					case 4:
						//remove a student by ID
						removeStudent();
						break;
					case 5:
						//display an individual students marks
						displayStudentMark();
						break;
					case 6:
						//display an individual students grades
						displayStudentGrade();
						break;
					case 7:
						//confirms then exits program or returns to menu
						exitProgram();
						break;
					}
				} 
				while (option != 8);
					}
	//reads the file to access the data
	public static boolean readFile(String filename) { 
		 
		File file =new File(filename);
			//scanner parses each word on a line then, sets constructs a student using those words, then   moves onto the next line
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String[] words = scanner.nextLine().split(",");
					
				int id = Integer.parseInt(words[0]);
				
				String firstName = words[1];
				String lastName = words[2];
				
				int mathsMark1 = Integer.parseInt(words[3]);
				int mathsMark2 = Integer.parseInt(words[4]);
				int mathsMark3 = Integer.parseInt(words[5]);
				
				int englishMark1 = Integer.parseInt(words[6]);
				int englishMark2 = Integer.parseInt(words[7]);
				int englishMark3 = Integer.parseInt(words[8]);
						
				addNewStudent(id ,firstName, lastName,mathsMark1,mathsMark2 , mathsMark3,englishMark1 ,englishMark2 , englishMark3); 
				}
					
			scanner.close();
			return true;
			} 
			//throws an error if cannot read file
		catch (FileNotFoundException e) {
		
			System.out.println("File not found: " + filename);
			return false;
			}	
		}
	//creates a class of type student and adds it to the allStudents linked list
	private static void addNewStudent(int id , String firstName , String lastName , int mathsMark1, int mathsMark2 , int mathsMark3, int englishMark1 , int englishMark2 , int englishMark3) {
		
		Student astudent = new Student(id, firstName, lastName);
		//sends off individual marks to the assignmentMarks class to allow for further processing when accessed
		AssignmentMarks math = new AssignmentMarks("Maths", mathsMark1, mathsMark2 , mathsMark3 );
		AssignmentMarks english = new AssignmentMarks("English", englishMark1, englishMark2 , englishMark3 );
		
		astudent.mathMarks = math;
		astudent.englishMarks = english;
		
		allStudents.add(astudent);
		}
	//displays a table with the id name and marks of each student
	private static void displayReportByMarks() {
			//title for the table
			System.out.println("ID" + "\t" +"Name" + "\t \t" +"Maths" + "\t" +"A1" + "\t" +"A2" + "\t" +"A3" + "\t" +"Grade" + "\t" +"English" + "\t" + "A1" + "\t" + "A2" + "\t" +"A3" + "\t" +"Grade");
			//loops through each student in allStudents
			for (Student s : allStudents) {
			//inputs the correct information and spaces them accordingly, uses methods from the assignmentMarks class on the marks 
			System.out.println(s.id + "\t" + s.firstName + " " + s.lastName + "\t" + " " + "\t" + s.mathMarks.getMark(1) + "\t" + s.mathMarks.getMark(2) + "\t" + s.mathMarks.getMark(3) +"\t" +s.mathMarks.getAverageMark() + "\t" + "" + "\t" + s.englishMarks.getMark(1) + "\t" + s.englishMarks.getMark(2) + "\t" + s.englishMarks.getMark(3) +"\t" +s.englishMarks.getAverageMark() );
			
				}
		}
	//displays a table with the id, name and grade of each student
	private static void displayReportByGrades() {
		//title for the table
		System.out.println("ID" + "\t" +"Name" + "\t \t" +"Maths" + "\t" +"A1" + "\t" +"A2" + "\t" +"A3" + "\t" +"Grade" + "\t" +"English" + "\t" + "A1" + "\t" + "A2" + "\t" +"A3" + "\t" +"Grade");
		//loops through each student in allStudents
		for (Student s : allStudents) {
		//prints the correct information and spaces them accordingly, uses methods from the assignmentMarks class to calculate grades
		System.out.println(s.id + "\t" + s.firstName + " " + s.lastName + "\t" + "" + "\t" + s.mathMarks.getGrade(1) + "\t" + s.mathMarks.getGrade(2) + "\t" + s.mathMarks.getGrade(3) +"\t" +s.mathMarks.getAverageGrade() + "\t" + "" +  "\t" + s.englishMarks.getGrade(1) + "\t" + s.englishMarks.getGrade(2) + "\t" + s.englishMarks.getGrade(3) +"\t" +s.englishMarks.getAverageGrade() );
			}
		}
	//prints out the main menu
	private static void displayMenu() {
        
		System.out.println("Menu:");
        System.out.println("1. Add a new student");
        System.out.println("2. Display report by marks");
        System.out.println("3. Display report by grades");
        System.out.println("4. Remove a student");
        System.out.println("5. View a students Marks");
        System.out.println("6. View a students Grades");
        System.out.println("7. Exit");
		}
	
    //takes input to select options on the main menu
	private static int selectMenuOption() {
        
    	Scanner scanner = new Scanner(System.in);
        //initilisation of variables
    	int selectedOption = 0;
        boolean isValidInput = false;
        
        // Loop until a valid option is selected
        while (!isValidInput) {
            System.out.print("Enter your choice: ");
            //cecks to see if there is an integer
            if (scanner.hasNextInt()) {
                selectedOption = scanner.nextInt();
                //checks to see if a valid integer
                if (selectedOption >= 1 && selectedOption <= 7) {
                    isValidInput = true;
                	} 
                	//error message for invalid number
                	else {
                		System.out.println("Invalid option! Please enter a number between 1 and 7.");
                		}
            	} 
            	//error message for non integer input
            	else {
            		System.out.println("Invalid input! Please enter a number.");
            		scanner.next(); // consume the invalid input
            		}
        	}
        	//reads and returns the integer value
        	scanner.nextLine();
        	return selectedOption;
    	}

    
    private static void inputNewStudent() {
	    
    	Scanner scannerInput = new Scanner(System.in);
	    	
	    	
	    	// Prompt for ID
	        System.out.print("Enter Student ID: ");
	        int id = scannerInput.nextInt();
	        scannerInput.nextLine(); // Consume newline character
	        
	        // Prompt for First Name
	        System.out.print("Enter First Name: ");
	        String firstName = scannerInput.nextLine();
	        
	        // Prompt for Last Name
	        System.out.print("Enter Last Name: ");
	        String lastName = scannerInput.nextLine();
	        
	        // Prompt for Math Grades
	        System.out.print("Enter Math Grade 1: ");
	        int mathsMark1 = scannerInput.nextInt();
	        System.out.print("Enter Math Grade 2: ");
	        int mathsMark2 = scannerInput.nextInt();
	        System.out.print("Enter Math Grade 3: ");
	        int mathsMark3 = scannerInput.nextInt();
	        
	        // Prompt for English Grades
	        System.out.print("Enter English Grade 1: ");
	        int englishMark1 = scannerInput.nextInt();
	        System.out.print("Enter English Grade 2: ");
	        int englishMark2 = scannerInput.nextInt();
	        System.out.print("Enter English Grade 3: ");
	        int englishMark3 = scannerInput.nextInt();
	        
	        //displays information added 
	        System.out.print("New Student added\n");
	        System.out.println("ID" + "\t" +"Name" + "\t \t" +"Maths" + "\t" +"A1" + "\t" +"A2" + "\t" +"A3" + "\t" +"English" + "\t" + "A1" + "\t" + "A2" + "\t" +"A3");
	        System.out.println(id + "\t" + firstName + " " + lastName + "\t \t" + mathsMark1 + "\t" + mathsMark2 + "\t" + mathsMark3 + "\t \t" + englishMark1 +"\t" + englishMark2 +"\t" + englishMark3);
	        
	        //submits info to a new student class, to be added to allStudents
	        addNewStudent(id,firstName,lastName,mathsMark1,mathsMark2,mathsMark3,englishMark1,englishMark2,englishMark3);
	    }

	//removes a student class from the allStudents linked list
	private static void removeStudent() {
		//scanner to accept user input
		Scanner scannerRemove = new Scanner(System.in); 
		//retrieve id of student to be removed
		System.out.print("Enter ID of student to be removed: ");
	    int idToRemove = scannerRemove.nextInt();
	    scannerRemove.nextLine(); // Consume newline character
		
		// Iterate through the LinkedList to find and remove the student with the specified ID
        for (Student student : allStudents) {
            if (student.getId() == idToRemove) {
                
            	allStudents.remove(student);
                //display to user that the student was removed
            	System.out.println("Student with ID " + idToRemove + " removed.");
                return; // Exit method after removal
            	}
        	}
        // If no student with the given ID is found
        System.out.println("Student with ID " + idToRemove + " not found.");
        return;
    	}
	
	//display individual student's marks
	private static void displayStudentMark() {
		//scanner to accept user input
		Scanner scannerMark = new Scanner(System.in);
		//ask user which student they wish to see based on id
		System.out.print("Enter ID of Student to view marks: ");
			int idMarkFetch = scannerMark.nextInt();
			scannerMark.nextLine();
		//iterate through all student class in linked list allStudents	
		for (Student student : allStudents) {
			//when the id entered and the id of a class match, fetch the marks and display in a table the same as with the full list of student marks
			if (student.getId() == idMarkFetch) {
				System.out.println("ID" + "\t" +"Name" + "\t \t" +"Maths" + "\t" +"A1" + "\t" +"A2" + "\t" +"A3" + "\t" +"Grade" + "\t" +"English" + "\t" + "A1" + "\t" + "A2" + "\t" +"A3" + "\t" +"Grade");
				
				System.out.println(student.id + "\t" + student.firstName + " " + student.lastName + "\t" + " " + "\t" + student.mathMarks.getMark(1) + "\t" + student.mathMarks.getMark(2) + "\t" + student.mathMarks.getMark(3) +"\t" +student.mathMarks.getAverageMark() + "\t" + "" + "\t" + student.englishMarks.getMark(1) + "\t" + student.englishMarks.getMark(2) + "\t" + student.englishMarks.getMark(3) +"\t" +student.englishMarks.getAverageMark() );
				return;
				}
			}
			
		
		}
	
	//display individual student's grades
	private static void displayStudentGrade() {
		//scanner to accept user input
		Scanner scannerGrade = new Scanner(System.in);
		//ask user which student they wish to see based on id
		System.out.print("Enter ID of Student to view Grades: ");
			int idGradeFetch = scannerGrade.nextInt();
			scannerGrade.nextLine();
		
		//iterate through all student class in linked list allStudents		
		for (Student student : allStudents) {
			if (student.getId() == idGradeFetch) {
				//when the id entered and the id of a class match, fetch the marks and display in a table the same as with the full list of student marks
				System.out.println("ID" + "\t" +"Name" + "\t \t" +"Maths" + "\t" +"A1" + "\t" +"A2" + "\t" +"A3" + "\t" +"Grade" + "\t" +"English" + "\t" + "A1" + "\t" + "A2" + "\t" +"A3" + "\t" +"Grade");
				
				System.out.println(student.id + "\t" + student.firstName + " " + student.lastName + "\t" + " " + "\t" + student.mathMarks.getGrade(1) + "\t" + student.mathMarks.getGrade(2) + "\t" + student.mathMarks.getGrade(3) +"\t" +student.mathMarks.getAverageGrade() + "\t" + "" + "\t" + student.englishMarks.getGrade(1) + "\t" + student.englishMarks.getGrade(2) + "\t" + student.englishMarks.getGrade(3) +"\t" +student.englishMarks.getAverageGrade() );
				return;
				}
			}
			
		
		}
	
    public static void exitProgram() {
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Do you want to exit the program? (yes/no)");
    	//trims whitespace and converts to all lower case to ease user input
    	String input = scanner.nextLine().trim().toLowerCase();

    	if (input.equals("yes")) {
    		System.out.println("Exiting the program...");
	            // exits the program
    		System.exit(0);
	        }
    	else if (input.equals("no")) {
    		System.out.println("Returning to the program.");
    		//returns to menu
	        return;
	        } 
    	else {
    		System.out.println("Invalid input. Please enter 'yes' or 'no'.");
	        //calls recursively until correct input
	        exitProgram();
	        }
	    }
	}
	
	
