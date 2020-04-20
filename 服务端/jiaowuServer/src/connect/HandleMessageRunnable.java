package connect;

import DAO.BookDAO;
import DAO.CourseDAO;
import DAO.GoodsDao;
import DAO.GoodsRecordDao;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import DAO.UserDAO;
import messageLei.*;
import shitilei.Book;
import shitilei.BookRecord;
import shitilei.Course;
import shitilei.CourseRecord;
import shitilei.Goods;
import shitilei.GoodsRecord;
import shitilei.Student;
import shitilei.Teacher;
import shitilei.Users;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
/**
* 相应客户端请求
* @author 宋天昊,谢荣昌,潘振宇,周扬帆.
* @since JDK1.7.
*/

public class HandleMessageRunnable implements Runnable {
    private Socket socket;
    private Connection connection;
    private Message ansMessage=null;
    private Message CM=null;
    ObjectInputStream ins = null;
    ObjectOutputStream ous = null;

 /**
 * 响应客户端的链接请求
 * @param socket socket变量，传递客户端socket
 * @param CD Connection变量
 */
public HandleMessageRunnable(Socket socket,CourseDAO CD) {
    this.socket = socket;
    this.connection=CourseDAO.conn;
}
//线程执行的操作，响应客户端的请求
/**
 * 建立输入输出流，若无则关闭socket
 */
public void run(){
    ObjectInputStream ins = null;
    ObjectOutputStream ous = null;
     
    try { while(socket.isConnected()) {while(true) {
		ins = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	    ous = new ObjectOutputStream(socket.getOutputStream());
	
        Message message=(Message)ins.readObject();
        handleMessage(message);
        ous.writeObject(CM);
        ous.flush();
  } }}catch (IOException e) {
        // TODO Auto-generated catch block
      System.out.println("流操作失败！");  
	  //e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally{
        //关闭资源
            try {
               ins.close();
            } catch(Exception ex) {ex.printStackTrace();}
            try {
                ous.close();
            } catch(Exception ex) {ex.printStackTrace();}
	    try {
                socket.close();
            } catch(Exception ex) {ex.printStackTrace();}
        }
}

/*public void run(){  
	System.out.println("线程开始");
    try {
    	while(socket.isConnected()) {while(true) {
    		System.out.println("传输信息开始");	
    	ins = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        ous = new ObjectOutputStream(socket.getOutputStream());
        Message message=(Message)ins.readObject();
        
        System.out.println("message = "+message.getType()+' ');
        
        handleMessage(message);       
        ous.writeObject(CM);
        ous.flush();
    	}}
    } catch (IOException e) {        
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally{
            try {
                ins.close();
            } catch(Exception ex) {}
            try {
                ous.close();
            } catch(Exception ex) {}
            try {
                socket.close();
            } catch(Exception ex) {}
    
        }
}*/
/**
 * 根据客户端请求做出响应，调用相应的操作函数
 * @param message 客户端发送的请求数据
 * 
 */
public void handleMessage(Message message){
	
    switch (message.getType()){
    /**
     * 进行选课的相关操作
     * @see CourseDAO.java 
     * 
     */
        case "获取所有课程":
            CourseDAO DAO1=new CourseDAO();
            ArrayList<Course> a=new ArrayList<Course>();
            a=DAO1.getAllCourseA();
            //String name=new String();
            //name=a.get(0).name;
            //System.out.println(name);
            CM=new Message();
            CM.setCourseArray(a);
            CM.setType("返回所有课程");
            //ArrayList<Course> alc=new ArrayList<Course>();
            //alc=CM.getCourseArray();
            //System.out.println(alc.get(0).name);
            break;
            
        case"选课":
        	CourseDAO DAO2=new CourseDAO();
        	int returnNumber=DAO2.addCourse(String.valueOf(message.getSelectorID()),message.getSelectedID());
        	CM=new Message();
        	CM.setStatusCode(returnNumber);
        	CM.setType("返回操作结果");
        	//System.out.println(CM.getStatusCode());
        	break;
        case"退课":
        	CourseDAO DAO0=new CourseDAO();
        	int resNumber=DAO0.delCourse(String.valueOf(message.getSelectorID()),message.getSelectedID());
        	CM=new Message();
        	CM.setStatusCode(resNumber);
        	CM.setType("返回退课结果");
        	//System.out.println(CM.getStatusCode());
        	break;

        case "获取个人课表":
            CourseDAO DAO3=new CourseDAO();
            ArrayList<CourseRecord> cral=new ArrayList<CourseRecord>();
            cral=DAO3.searchKeBiao(message.getSelectorID());
            //String name=new String();
            //name=a.get(0).name;
            //System.out.println(name);
            CM=new Message();
            CM.setCourseRecordArray(cral);
            CM.setType("返回个人课表");
            break;
            
        case"获取选课学生":
        	CourseDAO DAO4=new CourseDAO();
        	ArrayList<CourseRecord> cral1=new ArrayList<CourseRecord>();
            cral1=DAO4.getAllStudentsA(message.getSelectedID());
            CM=new Message();
            CM.setCourseRecordArray(cral1);
            CM.setType("返回选课学生");
            break;
            
        case"打分":
        	CourseDAO DAO5=new CourseDAO();
        	int returnNumber1=DAO5.updateCourse(message.getScore(),message.getSelectorID(),message.getSelectedID());
        	CM=new Message();
        	CM.setStatusCode(returnNumber1);
        	CM.setType("返回操作结果");
        	break;
            /**
             * 进行学籍的相关操作
             * @see StudentDAO.java
             * @see TeacherDAO.java
             */       	
        case "学籍信息":
            System.out.println(message.getType());
            StudentDAO DAO6=new StudentDAO();
            ArrayList<Student> a1=new ArrayList<Student>();
            String selectorID = new String (message.getUser().number);
            a1=DAO6.getInfo( selectorID);

            CM=new Message();
            CM.setStudentArray(a1);
            CM.setType("返回学籍信息");
            break;
            
     case"更新学籍信息":
    	 StudentDAO DAO7=new StudentDAO();
    	 System.out.println(message.getType());
    	 DAO7.updateInfo( message.getStudentArray());
    	 ArrayList<Student> b=new ArrayList<Student>();
    	 String selectorID1 = message.getStudentArray().get(0).number;
         b=DAO7.getInfo( selectorID1);
         CM=new Message();
         CM.setStudentArray(b);
         CM.setType("保存学籍信息");
         break;
     case "教师信息":
         TeacherDAO DAO8=new TeacherDAO();
         DAO8.openDb();
         ArrayList<Teacher> a8=new ArrayList<Teacher>();
         String selectorID8 = new String (message.getUser().number);
         System.out.println(selectorID8);
         a8=DAO8.getInfo( selectorID8);
         CM=new Message();
         CM.setTeacherArray(a8);
         CM.setType("返回教师信息");
         break;
         
     case"更新教师信息":
    	TeacherDAO DAO9=new TeacherDAO();
        DAO9.openDb();
   	 	System.out.println(message.getType());
   	 	DAO9.updateInfo( message.getTeacherArray());
   	 	ArrayList<Teacher> b9=new ArrayList<Teacher>();
   	 	String selectorID9 = message.getTeacherArray().get(0).number;
        b9=DAO9.getInfo( selectorID9);
        CM=new Message();
        CM.setTeacherArray(b9);
        CM.setType("保存教师信息");
        break;
         /**
          * 进行登陆的相关操作
          * @see UserDAO.java
          */ 
     case "请求登陆":
     	System.out.println("用户请求登陆");
        String n = new String(message.getUser().number);
		String p = new String(message.getUser().pwd);
        UserDAO ud = new UserDAO();
        Users backUser = ud.signJudge(n, p);
        System.out.println(backUser.name);
	    if (backUser.tag == 1) {
	    	
		    CM= new Message("登陆成功"); 
		    CM.setUser(backUser);
		    
	       } 
	    
	     if (backUser.tag == 2) {
	 		 CM= new Message("登陆成功");
	 		 CM.setUser(backUser);

	 	   }
	     if(backUser.tag==0) {
		     CM= new Message("用户名或密码错误");
		     }
	       
	        break;
	        
     case "请求修改密码":
     	System.out.println("用户请求修改密码");
        CM=new Message();
        String n1 = new String(message.getUser().number);
		String p1 = new String(message.getUser().pwd);
        UserDAO ud1 = new UserDAO();
        Users backUser1 = ud1.signJudge(n1, p1);
        	if (backUser1.tag!=0) {
        		CM= new Message("可以修改密码");    		
 
	        } else {
		        CM= new Message("用户名或密码错误");

	          }  
	        break;
	        
     case "修改密码":
     	UserDAO ud2 = new UserDAO();
     	String num = new String(message.getUser().number);
     	String np = new String(message.getUser().pwd);
		try {
			ud2.changePwd(num,np );
		} catch (Exception e) {
			e.printStackTrace();
		}
     	CM= new Message("修改密码完成");
	    System.out.println("CM.type = "+CM.getType());  
	    break;
         
 /*    case "教师信息":
         
         TeacherDAO DAO8=new TeacherDAO();
         ArrayList<Teacher> a8=new ArrayList<Teacher>();
         String selectorID8 = message.getTeacher().number;
         a8=DAO8.getInfo( selectorID8);

         CM=new Message();
         CM.setTeacherArray(a8);
         CM.setType("返回教师信息");
        // System.out.println(CM.getTeacherArray().get(0).name);
         break;
         
  case"更新教师信息":
 	 TeacherDAO DAO9=new TeacherDAO();
 	 System.out.println(message.getType());
 	 DAO9.updateInfo( message.getTeacherArray());
 	 ArrayList<Teacher> b9=new ArrayList<Teacher>();
 	 String selectorID9 = message.getTeacher().number;
      b9=DAO9.getInfo( selectorID9);
      CM=new Message();
      CM.setTeacherArray(b9);
      CM.setType("保存教师信息");
      break;*/
	    
	    /**
         * 进行商店的相关操作
         * @see GoodsDAO.java
         * @see	GoodsRecordDAO.JAVA
         */ 
   
case "获取商品列表":
	//System.out.println('1');
    GoodsDao myDAO1=new GoodsDao();
    ArrayList<Goods> goods=new ArrayList<Goods>();
    goods=myDAO1.getAllgoods();
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    CM=new Message();
    CM.setGoods(goods);
    CM.setType("返回商品列表");
    
    break;
case "加入购物车":
    GoodsRecordDao myDAO2=new GoodsRecordDao();
    myDAO2.addGoods(message.getSelectorID(), message.getSelectedID(), message.getGoodsnumber());
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    break;
case "购买":
    GoodsRecordDao myDAO=new GoodsRecordDao();
    int result=myDAO.updateGoodsRecord(message.getSelectorID(), message.getSelectedID());
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    CM=new Message();
	CM.setStatusCode(result);
	CM.setType("返回操作结果");
    break;
case "获取购物车列表":
    GoodsRecordDao myDAO3=new GoodsRecordDao();
    ArrayList<GoodsRecord> goodsrecord=new ArrayList<GoodsRecord>();
    goodsrecord=myDAO3.getAllgoodsrecrod(message.getSelectorID());
    System.out.println(goodsrecord);
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    CM=new Message();
    CM.setGoodsrecord(goodsrecord);
    System.out.println("CM = "+CM.getGoodsrecord());
    CM.setType("返回购物车列表");
    break;
case "选商品":
    GoodsDao myDAO4=new GoodsDao();
    ArrayList<Goods> searchgoods=new ArrayList<Goods>();
   searchgoods=myDAO4.seachGoods(message.getGoodsname());
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    CM=new Message();
    CM.setGoods(searchgoods);
    CM.setType("返回购物车列表");
    break;
    /**
     * 进行图书馆的相关操作
     * @see BookDAO.java
     */ 
case "查阅书单":
    
    BookDAO DAO11=new BookDAO();
    ArrayList<Book> a11=new ArrayList<Book>();
    a11=DAO11.getInfo();

    CM=new Message();
    CM.setBook(a11);
    CM.setType("返回书单");
    break;
case "查阅记录":
 
 BookDAO mDAO=new BookDAO();
 ArrayList<BookRecord> record=new ArrayList<BookRecord>();
 record=mDAO.getAllbookRecord(message.getSelectorID());
 System.out.println("record = "+record);
 CM=new Message();
 CM.setBookrecord(record);
 CM.setType("返回书单");
 break;
/*case"更新学籍信息":
 BookDAO DAO2=new BookDAO();
 System.out.println(message.getType());
 DAO2.updateInfo( message.getBookArray());
 ArrayList<Book> b=new ArrayList<Book>();
 String selectorID1 = "09016224";
 b=DAO2.getInfo( selectorID1);
 CM=new Message();
 CM.setBookArray(b);
 CM.setType("保存学籍信息");
 break;*/
case "查阅书名":
 BookDAO DAO21=new BookDAO();
 ArrayList<Book> b1=new ArrayList<Book>();
 b1=DAO21.SearchName(message.search);
 CM=new Message();
 CM.setBook(b1);
 CM.setType("返回书名搜索结果");
 break;
case "查阅作者":
 BookDAO DAO31=new BookDAO();
 ArrayList<Book> c=new ArrayList<Book>();
 c=DAO31.SearchWriter(message.search);
 CM=new Message();
 CM.setBook(c);
 CM.setType("返回作者搜索结果");
 break;
case "借书":
BookDAO myDAO21=new BookDAO();
 myDAO21.Borrow(message.getSelectorID(), message.getSelectedID());
 System.out.println("message.getSelectedID()"+message.getSelectedID());
 /*String name=new String();
 name=a.get(0).name;
 System.out.println(name);*/
 break;
case "还书":
 System.out.println("进行还书操作");
 BookDAO myDAO31=new BookDAO();
 myDAO31.Returnbook(message.getSelectorID(), message.getSelectedID());
 
 break;
     
        default:
            System.out.println("Never arrive here!");
            break;

    }
}

}
