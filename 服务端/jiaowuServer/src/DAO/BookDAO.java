package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import shitilei.Book;
import shitilei.BookRecord;


public class BookDAO {
	
	static public Connection conn = null;
	static public Statement stmt = null;
	static public PreparedStatement sql = null;
	private static ResultSetMetaData rsmd = null;
	public BookDAO(){
		openDb();
	}
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
	 * 查询所有书籍
	 * @return ALc 将所有数据库中book返回
	 */

	
	public ArrayList<Book> getInfo() {
		ArrayList<Book> ALc=new ArrayList<Book>();
		try 
		{
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_Book");
            while (rs.next()) 
            {
            	Book c=new Book();
            	String s;
            	int a;
            	s=rs.getString("b_ID");
            	c.ID=s;
            	s=rs.getString("b_name");
            	c.name=s;
            	s=rs.getString("b_ISBN");
            	c.ISBN=s;
            	s=rs.getString("b_writer");
            	c.writer=s;
            	s=rs.getString("b_Amount");
            	c.amount=s;
            	ALc.add(c);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ALc;
	}
	/**
	 * 按照书名查询
	 * @param n 查询的书名
	 * @return ALc 返回查询结果
	 */	

	public ArrayList<Book> SearchName(String n) {
		ArrayList<Book> ALc=new ArrayList<Book>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_Book where b_name  like '%"+n+ "%'");
			while(rs.next())
			{
				System.out.println("找到书籍");
			Book c=new Book();
        	String s;
        	int a;
        	s=rs.getString("b_ID");
        	c.ID=s;
        	s=rs.getString("b_name");
        	c.name=s;
        	s=rs.getString("b_ISBN");
        	c.ISBN=s;
        	s=rs.getString("b_writer");
        	c.writer=s;
        	s=rs.getString("b_Amount");
        	c.amount=s;
        	ALc.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ALc;
	}
	/**
	 * 按照作者查询
	 * @param n 查询的作者名
	 * @return ALc 返回查询结果
	 */
	public ArrayList<Book> SearchWriter(String n) {
		ArrayList<Book> ALc=new ArrayList<Book>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_Book where b_writer like '%"+n+ "%'");
			while(rs.next())
			{
				System.out.println("找到书籍");
			Book c=new Book();
        	String s;
        	int a;
        	s=rs.getString("b_ID");
        	c.ID=s;
        	s=rs.getString("b_name");
        	c.name=s;
        	s=rs.getString("b_ISBN");
        	c.ISBN=s;
        	s=rs.getString("b_writer");
        	c.writer=s;
        	s=rs.getString("b_Amount");
        	c.amount=s;
        	ALc.add(c);
			}
		} catch (SQLException e) {
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

    @SuppressWarnings("deprecation")
    /**
     * 执行借书操作
     * @param userID 用户ID	
     * @param bookID 所选书籍ID
     */
	public void Borrow(String userID,int bookID) {
    	try {
    		int result=0;
    		Date date=new Date();
    		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		 String d = f.format(new Date());
    		 System.out.println("date="+d);
    		result = stmt.executeUpdate("insert into tb_bookRecord(br_borrowerID,br_bookID,br_borrowDate) values('"+userID+"','"+bookID+"','"+d+"')");
        	if(result>0) {
        		System.out.println("借书成功");
        	}
        	String sqll="select*from tb_book where b_ID='"+bookID+"'";
        	ResultSet res=stmt.executeQuery(sqll);
        	
        	while(res.next()) {
            	String ress=null;
                ress=res.getString("b_Amount");
                try {
                    result = Integer.parseInt(ress);
                    System.out.println("reslut="+result);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                result=result-1;
            	String ssql="update tb_book set b_Amount='"+result+"' where b_ID='"+bookID+"'";
        		stmt.executeUpdate(ssql);}
        	}catch(Exception e) {
       		e.printStackTrace();
         	}
    }
    /**
     * 执行还书操作 
     * @param userID 用户ID
     * @param bookID 所选书籍ID
     */
    public void Returnbook(String userID,int bookID) {
    	try {
    		System.out.println("Id = "+bookID);
    		int result=0;
    		result=stmt.executeUpdate("delete from tb_bookRecord where br_borrowerID='"+userID+"' and br_bookID='"+bookID+"'");
    		if(result>0) {
    			System.out.println("还书成功");
    		}
    		String sqll="select*from tb_book where b_ID='"+bookID+"'";
        	ResultSet res=stmt.executeQuery(sqll);
        	
        	while(res.next()) {
        	String ress=null;
            ress=res.getString("b_Amount");
            try {
                result = Integer.parseInt(ress);
                System.out.println("reslut="+result);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            result=result+1;
        	String ssql="update tb_book set b_Amount='"+result+"' where b_ID='"+bookID+"'";
    		stmt.executeUpdate(ssql);}
    	}catch(Exception e) {
   		e.printStackTrace();
     	}
    }
    /**
     * 查看借书记录
     * @param userID 用户id
     * @return ALc 用户所借书籍
     */
    public ArrayList<BookRecord>getAllbookRecord(String userID){
    	ArrayList<BookRecord> ALc=new ArrayList<BookRecord>();
    	try {
    	  ResultSet res=stmt.executeQuery("select*from tb_bookRecord where br_borrowerID='"+userID+"'");
    	while(res.next()) {
    		BookRecord c=new BookRecord();
        	String s;
        	s=res.getString("br_borrowerID");
        	c.userID=s;
        	s=res.getString("br_bookID");
        	c.bookID=s;
        	s=res.getString("br_borrowDate");
        	c.borrowerDate=s;
        	ALc.add(c);
    	  }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		return ALc;

	/*public void updateInfo(ArrayList<Book> a) {
		String selectorID = a.get(0).number;
		try 
		{
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tb_Book where s_number = '" + selectorID + "'");

            	String name = a.get(0).name;
            	String age = a.get(0).age;
            	String address = a.get(0).address;
            	stmt.executeUpdate("update tb_Book set s_name ='"+ name +"'where s_number = '" + selectorID +" '");
            	stmt.executeUpdate("update tb_Book set s_age ='"+ age +"'where s_number = '" + selectorID +" '");
            	stmt.executeUpdate("update tb_Book set s_address = '"+ address +"' where s_number = '" + selectorID +" '");
            	System.out.println("xiugaichengguo");
            
		}catch(Exception e) {
			e.printStackTrace();
		}// TODO 自动生成的方法存根
		
	}*/
}
}

	
