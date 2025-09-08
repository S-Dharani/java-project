package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
	public int insert(Course c) throws Exception{
	    String sql = "INSERT INTO course(code, name, seats, cutoff) VALUES (?, ?, ?, ?)";
		try(Connection con = DataBase.get();PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			pstmt.setString(1, c.code);
	        pstmt.setString(2, c.name);
	        pstmt.setInt(3, c.seats);
	        pstmt.setDouble(4, c.cutoff);

			pstmt.executeUpdate();
			try(ResultSet rs = pstmt.getGeneratedKeys()){
				if(rs.next()) return rs.getInt(1);
			}
			
		}
		return -1;
	
	}
	
	public boolean update(Course c)throws Exception{
		String sql = "UPDATE course SET code=?,name=?,seats=?,cutoff=? WHERE id =?";
		try(Connection con= DataBase.get(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(2,c.code);
			pstmt.setString(3,c.name);
			pstmt.setInt(4,c.seats);
			pstmt.setDouble(5,c.cutoff);
			pstmt.setInt(1,c.id);
			return pstmt.executeUpdate()==1;
		}
				
	}
	
  public boolean delete(int id)throws Exception{
	  try(Connection con =DataBase.get(); PreparedStatement pstmt = con.prepareStatement("DELETE FROM course WHERE id=?")){
		  pstmt.setInt(1, id);
		  return pstmt.executeUpdate()==1;
	  }
  }
  
  public Course getById(int id) throws Exception {
      String sql = "SELECT * FROM course WHERE id=?";
      try (Connection con = DataBase.get(); PreparedStatement pstmt = con.prepareStatement(sql)) {
          pstmt.setInt(1, id);
          try (ResultSet rs = pstmt.executeQuery()) {
              if(rs.next()) return map(rs);
          }
      }
      return null;
  }
  
  public Course getBycode(String code)throws Exception{
	  String sql = "SELECT * FROM course WHERE code=?";
	  try(Connection con = DataBase.get();PreparedStatement pstmt = con.prepareStatement(sql)){
		  pstmt.setString(1, code);
		  try(ResultSet rs = pstmt.executeQuery()){
			  if(rs.next()) return map(rs);
		  }
	  }
	  return null;
	  
  }
  
  public List<Course> getAll()throws Exception{
	  List<Course> list = new ArrayList<>();
	  try(Connection con =  DataBase.get(); PreparedStatement pstmt = con.prepareStatement("SELECT * FROM course ORDER BY id"); ResultSet rs = pstmt.executeQuery()){
		  while(rs.next()) list.add(map(rs));
	  }
	  return list;
  }
  public boolean decreamentSeat(int courseId)throws Exception{
	  String sql="UPDATE course SET seats= seats-1 WHERE id=? AND seats>0";
	  try(Connection con= DataBase.get();PreparedStatement pstmt = con.prepareStatement(sql)){
		  pstmt.setInt(1,courseId);
		  return pstmt.executeUpdate()==1;
	  }
  }
  private Course map(ResultSet rs)throws Exception{
	  Course c = new Course();
	  c.id= rs.getInt("id");
	  c.code=rs.getString("code");
	  c.name=rs.getString("name");
	  c.seats=rs.getInt("seats");
	  c.cutoff=rs.getDouble("cutoff");
	  return c;
  }
}
