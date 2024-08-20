package main;

public class Student {
	
	//defining private variables that compose the object
	public int id;
	public String firstName;
	public String lastName;
	public AssignmentMarks mathMarks;
	public AssignmentMarks englishMarks;


	//constructor
	public Student(int id,String firstName, String lastName) {
		//signing variables to the new student class
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
		}
	//method to create a full name from students first and last name
	public String getFullName() {
		
		return firstName + " " + lastName;
		}
	//getter for id
	public int getId() {
		return this.id;
		}

	}