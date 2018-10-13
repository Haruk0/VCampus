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
	 * ���������ݿ������
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
 * ��ȡ��ʦ��Ϣ
 * @param selectorID ��ʦid	
 * @return ALc ��ʦ��Ϣ
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
	 * �ر����ݿ�
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
     * ����ѧ����Ϣ
     * @param a ��ʦA
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
            	System.out.println("�޸ĳɹ�");
            
		}catch(Exception e) {
			e.printStackTrace();
		}// TODO �Զ����ɵķ������
		
	}
}

	