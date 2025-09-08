package app;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AdminConsole {
	private static final Scanner sc = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final CourseDAO courseDAO = new CourseDAO();
    private static final AdmissionService service = new AdmissionService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== College Admission Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Add Course");
            System.out.println("4. List Courses");
            System.out.println("5. Apply Student to Course");
            System.out.println("6. Allocate Seats (Generate Approvals/Rejections)");
            System.out.println("7. Show Merit List for a Course");
            System.out.println("8. Export Merit List (CSV)");
            System.out.println("9. Exit");
            System.out.print("Choose: ");

            int ch = readInt();
            try {
                switch (ch) {
                    case 1 -> addStudent();
                    case 2 -> listStudents();
                    case 3 -> addCourse();
                    case 4 -> listCourses();
                    case 5 -> applyStudent();
                    case 6 -> allocateSeats();
                    case 7 -> showMeritList();
                    case 8 -> exportMeritCSV();
                    case 9 -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent() throws Exception {
        Student s = new Student();
        System.out.print("Name: ");
        s.name = sc.nextLine().trim();
        System.out.print("Email: ");
        s.email = sc.nextLine().trim();
        s.dob = readDate();  // Validated date input
        System.out.print("Gender: ");
        s.gender = sc.nextLine().trim();
        System.out.print("10th Mark (0-100): ");
        s.tenthMark = readDouble();
        System.out.print("12th Mark (0-100): ");
        s.twelthMark = readDouble();
        System.out.print("Entrance Score (0-100): ");
        s.entranceScore = readDouble();

        int id = studentDAO.insert(s);
        System.out.println("Student inserted with id: " + id);
    }

    private static void listStudents() throws Exception {
        List<Student> list = studentDAO.getAll();
        System.out.println("\n-- Students --");
        for (Student s : list) {
            System.out.printf("%d | %s | %s | %s | %s | 10th: %.2f | 12th: %.2f | Entrance: %.2f%n",
                    s.id, s.name, s.email, s.dob, s.gender, s.tenthMark, s.twelthMark, s.entranceScore);
        }
    }

    private static void addCourse() throws Exception {
        Course c = new Course();
        System.out.print("Code: ");
        c.code = sc.nextLine().trim();
        System.out.print("Name: ");
        c.name = sc.nextLine().trim();
        System.out.print("Seats: ");
        c.seats = readInt();
        System.out.print("Cutoff (merit >= cutoff): ");
        c.cutoff = readDouble();
        int id = courseDAO.insert(c);
        System.out.println("Course inserted with id: " + id);
    }

    private static void listCourses() throws Exception {
        List<Course> list = courseDAO.getAll();
        System.out.println("\n-- Courses --");
        for (Course c : list) {
            System.out.printf("%d | %s | %s | seats=%d | cutoff=%.2f%n", c.id, c.code, c.name, c.seats, c.cutoff);
        }
    }

    private static void applyStudent() throws Exception {
        System.out.print("Student Id: ");
        int sId = readInt();
        System.out.print("Course Id: ");
        int cId = readInt();
        int appId = service.applyForCourse(sId, cId);
        System.out.println("Application created with id: " + appId);
    }

    private static void allocateSeats() throws Exception {
        System.out.print("Course Id: ");
        int cId = readInt();
        service.allocateSeats(cId);
        System.out.println("Allocation complete.");
    }

    private static void showMeritList() throws Exception {
        System.out.print("Course Id: ");
        int cId = readInt();
        List<Application> apps = service.meritList(cId);
        System.out.println("\n-- Merit List (DESC) --");
        for (Application a : apps) {
            System.out.printf("appId=%d | studentId=%s | courseId=%s | merit=%.2f | %s | %s%n",
                    a.id, a.studentId, a.courseId, a.meritScore, a.status, a.remarks);
        }
    }

    private static void exportMeritCSV() throws Exception {
        System.out.print("Course Id: ");
        int cId = readInt();
        List<Application> apps = service.meritList(cId);
        System.out.print("Output path (e.g., C:/temp/merit.csv or ./merit.csv): ");
        String path = sc.nextLine().trim();
        CSVExporter.exportMeritList(path, apps);
        System.out.println("Saved: " + path);
    }

    private static int readInt() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Double.parseDouble(s);
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private static Date readDate() {
        while (true) {
            try {
                System.out.print("DOB (yyyy-mm-dd): ");
                String input = sc.nextLine().trim();
                return Date.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter DOB in yyyy-mm-dd format.");
            }
        }
    }
}
