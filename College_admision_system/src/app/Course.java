package app;

public class Course {
	int id;
	String code;
	String name;
	int seats;
	double cutoff;
	
	public Course() {
		
	}
	public Course(int id, String code, String name, int seats, double cutoff) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.seats = seats;
		this.cutoff = cutoff;
	}

}
