package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shitilei.Teacher;


public class TeacherDAO {
	
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
 * 获取教师信息
 * @param selectorID 教师id	
 * @return ALc 教师信息
 */
	
	public ArrayList<Teacher> getInfo(String selectorID) {
		ArrayList<Teacher> ALc=new ArrayList<Teacher>();
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_teacher where t_number = '" + selectorID + "'");
            while (rs.next()) 
            {
            	Teacher c=new Teacher();
            	String s;
            	
            	s=rs.getString("t_number");
            	c.number=s;
            	s=rs.getString("t_name");
            	c.name=s;
            	
            	s=rs.getString("t_sex");
            	c.sex=s;
            	s=rs.getString("t_address");
            	c.address=s;

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
     * @param a 教师A
     */
	public void updateInfo(ArrayList<Teacher> a) {
		String selectorID = a.get(0).number;
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_teacher where t_number = '" + selectorID + "'");

            	String name = a.get(0).name;
            
            	String address = a.get(0).address;
            	stmt.executeUpdate("update tb_teacher set t_name ='"+ name +"'where t_number = '" + selectorID +" '");
            
            	stmt.executeUpdate("update tb_teacher set t_address = '"+ address +"' where t_number = '" + selectorID +" '");
            	System.out.println("修改成功");
            
		}catch(Exception e) {
			e.printStackTrace();
		}// TODO 自动生成的方法存根
		
	}
}

	