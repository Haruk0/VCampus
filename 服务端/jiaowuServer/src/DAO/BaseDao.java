package DAO;

import java.sql.*;
/**
* 设置打开服务器端口，同时允许进行数据库访问，无请求时关闭数据库。
* @author 宋天昊,谢荣昌,潘振宇,周扬帆.
* @since JDK1.7.
*/
public class BaseDao {
	   public static Connection con=null;
	   public static PreparedStatement stt=null;
	   public static ResultSet res=null;
	   /**
	    * 建立与数据库的链接
	    * @return con 返回数据库借口con
	    */
	    public Connection getConnection() {
	    	try 
			{
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				con = DriverManager.getConnection("jdbc:ucanaccess://.\\db.accdb");
			}catch(Exception e) {
				e.printStackTrace();
			}
	    	return con;
	    }
	    /**
	     * 执行关闭数据库访问的操作
	     */
	    public void closesql() {
	    	try {
	    	   if(res!=null)
	    	   {
	    			res.close();
	    	   }
	    		if(stt!=null)
	        	{
	        		stt.close();
	        	}
	    		if(con!=null)
	        	{
	        			con.close();
	        	}
	    	}catch(SQLException e){
	    			e.printStackTrace();
	    	}
	    }
}
