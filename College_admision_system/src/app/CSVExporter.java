package app;

import java.io.PrintWriter;
import java.util.List;

public class CSVExporter {
	public static void exportMeritList(String filePath, List<Application> apps) throws Exception {
		try (PrintWriter pw = new PrintWriter("F:\\Elevate_lab Task1\\College_admision_system\\merit.csv")) {
            pw.println("application_id,student_id,course_id,merit_score,status,remarks");
            for (Application a : apps) {
                pw.printf("%d,%s,%s,%.2f,%s,%s%n", a.id, a.studentId, a.courseId, a.meritScore, a.status,
                        a.remarks == null ? "" : a.remarks.replace(',', ';'));
            }
        }
    }

}
