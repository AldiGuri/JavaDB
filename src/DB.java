import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
public class DB {
   
	private Connection conn = null;
    private Statement stmt = null;
    private String sql = "";

	public DB(String url, String user, String pass) {
        
        try{
        	
        	//JDBC_DRIVER = "com.mysql.jdbc.Driver";
        	//Class.forName("com.mysql.jdbc.Driver");

        	conn = DriverManager.getConnection(url, user, pass);    	
        	stmt = conn.createStatement();
        	
        	// drop database (safety check nqs ka db existente)
        	sql = "DROP DATABASE president";
        	stmt.executeUpdate(sql);
        	
        	// create database president
        	sql = "CREATE DATABASE president";
        	stmt.executeUpdate(sql);
        	
        	// select database president
        	sql = "USE president";
        	stmt.executeUpdate(sql);
        	
        	// create table presidents
        	sql = "CREATE TABLE presidents (Pres_Name VARCHAR(30), Party VARCHAR(50) ,State_Name VARCHAR(50),Birth_Yr INTEGER, Yrs_Servic INTEGER ,Death_Age INTEGER)";
        	stmt.executeUpdate(sql);
        	
        	// insert data into presidents
        	BufferedReader br = new BufferedReader(new FileReader("query.txt"));
        	sql = "";
        	while ((sql=br.readLine()) !=null) {        
        		stmt.executeUpdate(sql);
        	}
        	
    // err handling     	
    }catch(SQLException se){
        
        se.printStackTrace();
    }catch(Exception e){
        
        e.printStackTrace();
    }
//    finally{
//        
//        try{
//            if(stmt!=null)
//                stmt.close();
//        }catch(SQLException se2){
//        	se2.printStackTrace();
//        }
//        try{
//            if(conn!=null)
//                conn.close();
//        }catch(SQLException se){
//            se.printStackTrace();
//        }
//    }

	}
	
	public String getPresident() {
		 sql = "SELECT Pres_Name, Party, State_Name, Birth_Yr, Yrs_Servic, Death_Age FROM presidents";
		 ResultSet rs;
		 String afishim = "";
		try {
			rs = stmt.executeQuery(sql);
		
			 while(rs.next()){
			 	
				 String Pres_Name = rs.getString("Pres_Name");
				 String Party = rs.getString("Party");
				 String State_Name = rs.getString("State_Name");
				 int Birth_Yr = rs.getInt("Birth_Yr");
				 int Yrs_Servic = rs.getInt("Yrs_Servic");
				 int Death_Age = rs.getInt("Death_Age");
			
				 afishim +="President Name: " + Pres_Name;
				 afishim +="\t, Party: " + Party;
				 afishim +="\t, State Name: " + State_Name;
				 afishim +="\t, Birth Year: " + Birth_Yr;
				 afishim +="\t, Years in Servic: " + Yrs_Servic;
				 afishim +="\t, Death Age: " + Death_Age + "\n";
			 
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return afishim;
	
	}
}