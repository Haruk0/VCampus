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
	 * 建立与数据库的链接
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
 * 检验用户名密码
 * @param n 用户名
 * @param p 密码
 * @return user 返回用户类
 */
public Users signJudge(String n,String p) {	
	Users user=new Users();
	user.tag=0;
	try {
		ResultSet rs = stmt.executeQuery("select * from tb_user");
		while (rs.next()) {
			//System.out.println("进入数据库对比");
			String uname = new String(rs.getString("u_number"));		
			String upwd = new String(rs.getString("u_pwd"));
			String username=new String(rs.getString("u_name"));
			int mon = rs.getInt("u_money");
			if (uname.equals(n) && upwd.equals(p)) {			
				String userType = new String(rs.getString("u_type"));
				//System.out.println("登陆者身份为"+tag);
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
 * 更改密码
 * @param num 用户id
 * @param npwd 用户新密码
 * @throws Exception 修改异常
 */
public void changePwd(String num,String npwd) throws Exception {
	String sql="update tb_user set u_pwd=? where u_number=? ";//生成一条mysql语句
	PreparedStatement ps=con.prepareStatement(sql);//创建PreparedStatement对象
	ps.setString(1, npwd);//为第一个问号赋值
	ps.setString(2, num);//为第二个问号赋值
    int result = ps.executeUpdate();
    if(result == 1)
    	System.out.println("修改密码完成");
    System.out.println("新密码为"+npwd);
}


}
