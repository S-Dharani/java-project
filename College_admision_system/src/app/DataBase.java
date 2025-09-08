package app;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
     private static final String url ="jdbc:Mysql://localhost:3306/collegeadmision";
     private static final String userName ="root";
     private static final String password ="root";
     
     public static Connection get() throws Exception{
    	 return DriverManager.getConnection(url, userName, password); 
    	 
     }

}
