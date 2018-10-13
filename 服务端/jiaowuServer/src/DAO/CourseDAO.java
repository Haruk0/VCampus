package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shitilei.Course;
import shitilei.CourseRecord;

public class CourseDAO {
	
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
	 * 执行选课操作
	 * @param ID 用户id
	 * @param courseID 所选课程id
	 * @return 0/1 操作结果
	 */
	public int addCourse(String ID,int courseID) {
		int result=1;
		courseID+=1;
		//System.out.println(ID+"  "+courseID);
		try{
		ResultSet rs = stmt.executeQuery("SELECT * FROM tb_courseRecord where cs_selectorID = '" + ID + "'");
		while (rs.next()) 
        {
			int i=-1;
        	i=rs.getInt("cs_courseID");
        	if(i==courseID)
        	{
        		result=0;
        		break;
        	}
        		
        }
	}catch(Exception e) {
		e.printStackTrace();
	}
		if(result==1){try {
			sql = conn.prepareStatement("insert into tb_courseRecord(cs_selectorID,cs_courseID,cs_score) values(?,?,?)");
    		sql.setString(1,ID);
    		sql.setInt(2, courseID);
    		sql.setInt(3,-1);
        	sql.executeUpdate();

    	}catch(Exception e) {
    		e.printStackTrace();
    	}}
    	if(result>0)
    		return 1;
    	else
    		return 0;
    }

    	
    
	/*public int score(String ID,int courseID,int score) {
		int result=1;
		courseID+=1;
		
		try {
			ResultSet rs=null;
			sql = conn.prepareStatement("SELECT * FROM tb_courseRecord where cs_selectorID = '" + ID + "'", ResultSet.TYPE_SCROLL_INSENSITIVE
									, ResultSet.CONCUR_UPDATABLE,rs = sql.executeQuery());

			while (rs.next()) 
	        {
				int i=-1;
	        	i=rs.getInt("cs_courseID");
	        	if(i==courseID)
	        	{
	        		result=0;
	        		break;
	        	}

    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(result>0)
    		return 1;
    	else
    		return 0;
    }*/
	/**
	 * 执行打分操作
	 * @param score 成绩
	 * @param userID 学生id
	 * @param courseID 课程Id
	 * @return result 操作结果
	 */
	public int updateCourse(int score,String userID,int courseID) {
		int result=0;
		//int sscore=80;
		try {
    		String sqlstring="update tb_courseRecord set cs_score=? where cs_selectorID=? and cs_courseID=?";
    		sql=conn.prepareStatement(sqlstring);
    		sql.setInt(1, score);
    		sql.setString(2, userID);
    		sql.setInt(3, courseID);
    		result=sql.executeUpdate();
    		System.out.println(result);
    		if(result>0) {
    			System.out.println("修改成功");
    		}
    	}catch(Exception e) {
   		e.printStackTrace();
     	}
    	return result;
    }
/**
 * 查看所有课程
 * @return ALc 返回所有课程
 */
	public ArrayList<Course> getAllCourseA() {
		ArrayList<Course> ALc=new ArrayList<Course>();
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_course");
            while (rs.next()) 
            {
            	Course c=new Course();
            	String s;
            	int i;
            	
            	s=rs.getString("c_name");
            	c.name=s;
            	s=rs.getString("c_teacher");
            	c.teacher=s;
            	i=rs.getInt("c_ID");
            	c.courseID=i;
            	i=rs.getInt("c_beginTime");
            	c.beginTime=i;
            	i=rs.getInt("c_weekday");
            	c.weekday=i;
            	ALc.add(c);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ALc;
	}
	/**
	 * 查看课程所有学生
	 * @param ID 课程ID
	 * @return ALc 返回学生及成绩
	 */
	public ArrayList<CourseRecord> getAllStudentsA(int ID) {
		ArrayList<CourseRecord> ALc=new ArrayList<CourseRecord>();
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_courseRecord where cs_courseID = '"+ID+"'");
            while (rs.next()) 
            {
            	CourseRecord c=new CourseRecord();
            	String s;
            	int i;
            	
            	s=rs.getString("cs_selectorID");
            	c.selectorNumber=s;
            	i=rs.getInt("cs_courseID");
            	c.selectedcourseID=i;
            	i=rs.getInt("cs_score");
            	c.score=i;
  
            	ALc.add(c);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ALc;
	}
	
    public void closeDb(){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/**
 * 查看课表
 * @param selectorID 学生id
 * @return  ALcr 所有已选课程
 */
	public ArrayList<CourseRecord> searchKeBiao(String selectorID) {
		ArrayList<CourseRecord> ALcr=new ArrayList<CourseRecord>();
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_courseRecord where cs_selectorID = '" + selectorID + "'");
			while (rs.next()) 
            {
            	CourseRecord c=new CourseRecord();
            	String s;
            	int i;
            	
            	s=rs.getString("cs_selectorID");
            	c.selectorNumber=s;
            	i=rs.getInt("cs_courseID");
            	c.selectedcourseID=i;
            	i=rs.getInt("cs_score");
            	c.score=i;
            	ALcr.add(c);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
	return ALcr;
	}
/**
 * 进行退课操作
 * @param ID 学生ID
 * @param courseID 选择课程ID
 * @return 0/1 操作结果
 */
	public int delCourse(String ID,int courseID) {
		
		try{
			
		
			if (stmt.executeUpdate("delete FROM tb_courseRecord where cs_selectorID = '" + ID +"' and cs_courseID = '" + courseID +"'") > 0) {
				System.out.println("删除成功!!!");
				return 1;
			}}
			
        		
        
	catch(Exception e) {
		e.printStackTrace();
	}
		return 0;
	}
	
}
