package app;

import java.util.List;

public class AdmissionService {
	private final StudentDAO studentDAO = new StudentDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private final ApplicationDAO applicationDAO = new ApplicationDAO();

    // Merit formula (20% 10th + 30% 12th + 50% entrance)
    public double calculateMerit(Student s) {
        return (0.20 * s.tenthMark) + (0.30 * s.twelthMark) + (0.50 * s.entranceScore);
    }

    public int applyForCourse(int studentId, int courseId) throws Exception {
        Student s = studentDAO.getById(studentId);
        if (s == null) throw new IllegalArgumentException("Student not found: " + studentId);
        Course c = courseDAO.getById(courseId);
        if (c == null) throw new IllegalArgumentException("Course not found: " + courseId);

        double merit = calculateMerit(s);
        Application a = new Application();
        a.studentId = String.valueOf(studentId);
        a.courseId = String.valueOf(courseId);
        a.meritScore = merit;
        a.status = "PENDING";
        a.remarks = "Applied";
        return applicationDAO.insert(a);
    }

    // Allocate seats: approve top applicants who meet cutoff until seats exhausted
    public void allocateSeats(int courseId) throws Exception {
        Course c = courseDAO.getById(courseId);
        if (c == null) throw new IllegalArgumentException("Course not found: " + courseId);

        List<Application> apps = applicationDAO.getByCourse(courseId);
        int seats = c.seats;
        int approved = 0;
        for (Application a : apps) {
            if (approved < seats && a.meritScore >= c.cutoff) {
                if (courseDAO.decreamentSeat(courseId)) {
                    applicationDAO.updateStatus(a.id, "APPROVED", "Meets cutoff & within seats");
                    approved++;
                } else {
                    applicationDAO.updateStatus(a.id, "REJECTED", "No seats left");
                }
            } else {
                String reason = (a.meritScore < c.cutoff) ? "Below cutoff" : "No seats left";
                applicationDAO.updateStatus(a.id, "REJECTED", reason);
            }
        }
    }

    public List<Application> meritList(int courseId) throws Exception {
        return applicationDAO.getByCourse(courseId);
    }
}



