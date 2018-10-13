package DAO;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shitilei.Users;
public class UserDAO {
	Statement stmt;
	Connection con;
	/**
	 * ���������ݿ������
	 */
public UserDAO(){
	try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		con = DriverManager.getConnection("jdbc:ucanaccess://.\\db.accdb");
		stmt = con.createStatement();	
	} catch (Exception e) {	
		e.printStackTrace();
	}	
}
/**
 * �����û�������
 * @param n �û���
 * @param p ����
 * @return user �����û���
 */
public Users signJudge(String n,String p) {	
	Users user=new Users();
	user.tag=0;
	try {
		ResultSet rs = stmt.executeQuery("select * from tb_user");
		while (rs.next()) {
			//System.out.println("�������ݿ�Ա�");
			String uname = new String(rs.getString("u_number"));		
			String upwd = new String(rs.getString("u_pwd"));
			String username=new String(rs.getString("u_name"));
			int mon = rs.getInt("u_money");
			if (uname.equals(n) && upwd.equals(p)) {			
				String userType = new String(rs.getString("u_type"));
				//System.out.println("��½�����Ϊ"+tag);
				//System.out.println(userType);
				if (userType.equals("student"))
					user.tag= 1;	
				if (userType.equals("teacher"))
					user.tag= 2;	
				if (userType.equals("admin"))
					user.tag= 2;
				user.name=username;
				user.number=uname.toCharArray();
				user.money = mon;
				//System.out.println(user.number);
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} 			
//	System.out.println(user.tag);
    return user;	
}
/**
 * ��������
 * @param num �û�id
 * @param npwd �û�������
 * @throws Exception �޸��쳣
 */
public void changePwd(String num,String npwd) throws Exception {
	String sql="update tb_user set u_pwd=? where u_number=? ";//����һ��mysql���
	PreparedStatement ps=con.prepareStatement(sql);//����PreparedStatement����
	ps.setString(1, npwd);//Ϊ��һ���ʺŸ�ֵ
	ps.setString(2, num);//Ϊ�ڶ����ʺŸ�ֵ
    int result = ps.executeUpdate();
    if(result == 1)
    	System.out.println("�޸��������");
    System.out.println("������Ϊ"+npwd);
}


}
