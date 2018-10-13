package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shitilei.Student;


public class StudentDAO {
	
	static public Connection conn = null;
	static public Statement stmt = null;
	static public PreparedStatement sql = null;
	private static ResultSetMetaData rsmd = null;
	/**
	 * 建立与数据库的链接
	 */
	public void openDb ()
	{
		try 
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conn = DriverManager.getConnection("jdbc:ucanaccess://.\\db.accdb");
			stmt = conn.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 查询学籍信息
	 * @param selectorID 学生Id
	 * @return ALc 学生学籍信息
	 */
	public ArrayList<Student> getInfo(String selectorID) {
		ArrayList<Student> ALc=new ArrayList<Student>();
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_student where s_number = '" + selectorID + "'");
            while (rs.next()) 
            {
            	Student c=new Student();
            	String s;
            	
            	s=rs.getString("s_number");
            	c.number=s;
            	s=rs.getString("s_name");
            	c.name=s;
            	s=rs.getString("s_age");
            	c.age=s;
            	s=rs.getString("s_sex");
            	c.sex=s;
            	s=rs.getString("s_address");
            	c.address=s;
            	s=rs.getString("s_studentclass");
            	c.studentclass=s;
            	s=rs.getString("s_major");
            	c.major=s;
            	s=rs.getString("s_status");
            	c.status=s;

            	ALc.add(c);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ALc;
	}
	/**
	 * 关闭数据库
	 */
    public void closeDb(){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


/**
 * 更新学籍信息
 * @param a 学生A
 */
	public void updateInfo(ArrayList<Student> a) {
		String selectorID = a.get(0).number;
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_student where s_number = '" + selectorID + "'");

            	String name = a.get(0).name;
            	String age = a.get(0).age;
            	String address = a.get(0).address;
            	stmt.executeUpdate("update tb_student set s_name ='"+ name +"'where s_number = '" + selectorID +" '");
            	stmt.executeUpdate("update tb_student set s_age ='"+ age +"'where s_number = '" + selectorID +" '");
            	stmt.executeUpdate("update tb_student set s_address = '"+ address +"' where s_number = '" + selectorID +" '");
            	System.out.println(name);
            	System.out.println(age);
            	System.out.println(address);
            	System.out.println("修改成功");
		}catch(Exception e) {
			e.printStackTrace();
		}// TODO 自动生成的方法存根
		
	}
}

	