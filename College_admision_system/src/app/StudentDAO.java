package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
     public int insert(Student s) throws Exception{
    	 String sql = "INSERT INTO students (name, dob, email, gender, tenth_mark, twelth_mark, entrance_score) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	 try(Connection con = DataBase.get();
    			 PreparedStatement pstmt= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    		 pstmt.setString(1, s.name);
    		 pstmt.setDate(2, s.dob);  // Ensure s.dob is a valid Date
    		 pstmt.setString(3, s.email);
    		 pstmt.setString(4, s.gender);
    		 pstmt.setDouble(5, s.tenthMark);
    		 pstmt.setDouble(6, s.twelthMark);
    		 pstmt.setDouble(7, s.entranceScore);
    		 pstmt.executeUpdate();
    		 try(ResultSet rs= pstmt.getGeneratedKeys()){
    			 if(rs.next())return rs.getInt(1);
    		 }
    		 
    	 }
    	 return -1;
     }
     
     public boolean update(Student s)throws Exception{
    	 String sql = "UPDATE students SET name=?, email=?, dob=?, gender=?, tenth_mark=?, twelth_mark=?, entrance_score=? WHERE id=?";
    	 try(Connection con = DataBase.get();PreparedStatement pstmt = con.prepareStatement(sql)){
    		 pstmt.setString(1, s.name);
    		 pstmt.setString(2, s.email);
    		 pstmt.setDate(3, s.dob);
    		 pstmt.setString(4, s.gender);
    		 pstmt.setDouble(5, s.tenthMark);
    		 pstmt.setDouble(6, s.twelthMark);
    		 pstmt.setDouble(7, s.entranceScore);

    		 return pstmt.executeUpdate() ==1;

    	 }
    	 
     }
     
     public boolean delete(int id)throws Exception{
    	 try(Connection con = DataBase.get(); PreparedStatement pstmt = con.prepareStatement("DELETE FROM students WHERE id=?")){
    		 pstmt.setInt(1, id);
    		 return pstmt.executeUpdate()==1;
    	 }
     }
     
     public Student getById(int id) throws Exception {
         String sql = "SELECT * FROM students WHERE id=?";
         try (Connection con = DataBase.get(); PreparedStatement pstmt = con.prepareStatement(sql)) {
             pstmt.setInt(1, id);
             try (ResultSet rs = pstmt.executeQuery()) {
                 if (rs.next()) return map(rs);

    		 }
    		 }
    	 return null;
     }
     public List<Student> getAll() throws Exception {
         List<Student> list = new ArrayList<>();
         String sql = "SELECT * FROM students ORDER BY id";
         try (Connection con = DataBase.get(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
             while (rs.next()) list.add(map(rs));
         }
         return list;
     }

     private Student map(ResultSet rs) throws Exception {
         Student s = new Student();
         s.id = rs.getInt("id");
         s.name = rs.getString("name");
         s.email = rs.getString("email");
         s.dob = rs.getDate("dob");
         s.gender = rs.getString("gender");
         s.tenthMark = rs.getDouble("tenth_mark");
         s.twelthMark = rs.getDouble("twelth_mark");
         s.entranceScore = rs.getDouble("entrance_score");
         return s;
     }
 }


