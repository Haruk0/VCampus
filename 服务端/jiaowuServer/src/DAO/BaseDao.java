package DAO;

import java.sql.*;
/**
* ���ô򿪷������˿ڣ�ͬʱ����������ݿ���ʣ�������ʱ�ر����ݿ⡣
* @author �����,л�ٲ�,������,���﷫.
* @since JDK1.7.
*/
public class BaseDao {
	   public static Connection con=null;
	   public static PreparedStatement stt=null;
	   public static ResultSet res=null;
	   /**
	    * ���������ݿ������
	    * @return con �������ݿ���con
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
	     * ִ�йر����ݿ���ʵĲ���
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
