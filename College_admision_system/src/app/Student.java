package app;

import java.sql.Date;

public class Student {
	int id;
	String name;
	String email;
	Date dob;
	String gender;
	double tenthMark;
	double twelthMark;
	double entranceScore;
	
	public Student(){
		
	}
	public Student(int id, String name, String email, Date dob, String gender, double tenthMark, double twelthMark,
			double entranceScore) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.tenthMark = tenthMark;
		this.twelthMark = twelthMark;
		this.entranceScore = entranceScore;
	}

}
