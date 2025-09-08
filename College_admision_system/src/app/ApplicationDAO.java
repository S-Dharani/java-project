package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
	public int insert(Application a) throws Exception {
        String sql = "INSERT INTO application(students_id, course_id, Merit_score, status, remarks) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DataBase.get();
             PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, Integer.parseInt(a.studentId));
            pstmt.setInt(2, Integer.parseInt(a.courseId));
            pstmt.setDouble(3, a.meritScore);
            pstmt.setString(4, a.status);
            pstmt.setString(5, a.remarks);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public boolean updateStatus(int appId, String status, String remarks) throws Exception {
        String sql = "UPDATE application SET status=?, remarks=? WHERE id=?";
        try (Connection con = DataBase.get(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, remarks);
            ps.setInt(3, appId);
            return ps.executeUpdate() == 1;
        }
    }

    public Application getById(int id) throws Exception {
        String sql = "SELECT * FROM application WHERE id=?";
        try (Connection con = DataBase.get(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<Application> getByCourse(int courseId) throws Exception {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM application WHERE course_id=? ORDER BY Merit_score DESC";
        try (Connection con = DataBase.get(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public List<Application> getAll() throws Exception {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM application ORDER BY id";
        try (Connection con = DataBase.get(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    private Application map(ResultSet rs) throws Exception {
        Application a = new Application();
        a.id = rs.getInt("id");
        a.studentId = String.valueOf(rs.getInt("students_id"));  // Fixed column name
        a.courseId = String.valueOf(rs.getInt("course_id"));
        a.meritScore = rs.getDouble("Merit_score");             // Capital M as in DB
        a.status = rs.getString("status");
        a.remarks = rs.getString("remarks");
        return a;
    }
}
