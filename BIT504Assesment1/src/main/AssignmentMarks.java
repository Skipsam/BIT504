package main;

public class AssignmentMarks {
	
	//Defining the Private fields
	private String courseName;
	private int assignment1;
	private int assignment2;
	private int assignment3;
	
	//constructor for generating objects
	public AssignmentMarks(String coursename, int assignment1, int assignment2, int assignment3) {
		this.courseName = coursename;
		this.assignment1 = assignment1;
		this.assignment2 = assignment2;
		this.assignment3 = assignment3;
	}
	
	//setters and getters
	public void setCourseName(String courseName) {
		this.courseName = courseName;
		
	}
	public String getCourseName() {
		return courseName;
	}
	// sets the mark for a specific assignment
	public void setMark(int assignmentNumber, int mark) {
		if (assignmentNumber == 1) {
			this.assignment1 = mark;
		} else if (assignmentNumber == 2) {
			this.assignment2 = mark;
		} else if (assignmentNumber == 3) {
			this.assignment3 = mark;
		} else {
			throw new IllegalArgumentException("Invalid assignment number");
		}
	}
	
	// gets the mark for a specific assignment
	public int getMark(int assignmentNumber) {
		if (assignmentNumber == 1) {
			return this.assignment1;
		} else if (assignmentNumber == 2) {
			return this.assignment2;
		} else if(assignmentNumber == 3) {
			return this.assignment3;
		} else {
			throw new IllegalArgumentException("Invalid assignment number");
		}	
	}
	
	// calculates average grade for the course
	public double getAverageMark() {
		return (assignment1 + assignment2 + assignment3) / 3;
	}
	
	//Method to retrieve a grade for an assignment based on the mark
	public String getGrade(int assignmentNumber) {
		int mark = getMark(assignmentNumber);
		return markToGrade(mark);
	}
	
	//method to calculate average grade for the course
	public String getAverageGrade() {
		double averageMark = getAverageMark();
		return markToGrade(averageMark);
	}
	//calculates a grade based on the mark, uses dobles to accomodate average grades.
	public String markToGrade(double mark) {
        if (mark >= 95 && mark <= 100) {
            return "A+";
        } else if (mark >= 90 && mark < 95) {
            return "A";
        } else if (mark >= 85 && mark < 90) {
            return "A-";
        } else if (mark >= 80 && mark < 85) {
            return "B+";
        } else if (mark >= 75 && mark < 80) {
            return "B";
        } else if (mark >= 70 && mark < 75) {
            return "B-";
        } else if (mark >= 60 && mark < 70) {
            return "C+";
        } else if (mark >= 50 && mark < 60) {
            return "C";
        } else if (mark >= 41 && mark < 50) {
            return "C-";
        } else if (mark >= 0 && mark < 41) {
            return "D";
        
        } else {
            throw new IllegalArgumentException("Invalid mark. Mark should be between 0 and 100.");
        }
    }
}
