package fangfalei;
import shitilei.Book;
import shitilei.BookRecord;
import shitilei.Course;
import shitilei.CourseRecord;
import shitilei.Student;
import shitilei.Teacher;
import shitilei.Users;

import java.util.ArrayList;

import connect.connect;
import messageLei.Message;
/**
 * GUI按钮调用的函数类，同时也是与服务端进行信息交互的类
 * @author 宋天昊,谢荣昌,潘振宇,周扬帆.
 * @since JDK1.7.
 */
public class Clientfangfa {
	/**
	 * 静态变量myconnect值为{@value}.
	 */
	static connect myconnect=new connect();
	/**
	 * 获取所有课程信息.
	 * @return Course类型ArrayList.
	 */
	public ArrayList<Course> getAllCourse()
	{
		Message m=new Message("获取所有课程");
		ArrayList<Course> c=new ArrayList<Course>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleCourseMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	
	/**
	 * 获取选中课程信息,向服务器发出选课请求并接收.
	 * @param ID int变量,传递课程ID.
	 * @param number String变量,传递用户ID.
	 * @return 1或0.
	 */
	public int selectCourse(int ID,String number) 
	{
		Message m=new Message();
		m.setSelectedID(ID);
		m.setSelectorID(number);
		m.setType("选课");
		Message rm=new Message();
		rm=myconnect.connectServer(m);
		int result=rm.getStatusCode();
		if(1==result) 
		{return 1;}
		else
		return 0;
	}
	/**
	 * 获取选中课程信息,向服务器发出退课请求并接收.
	 * @param selectedID int变量,传递课程ID.
	 * @param number String变量,传递用户ID.
	 * @return 1或0.
	 */
	public int deleteCourse(int selectedID,String number) 
	{
		Message m=new Message();
		m.setSelectedID(selectedID);
		m.setSelectorID(number);
		m.setType("退课");
		System.out.println(selectedID);
		Message rm=new Message();
		rm=myconnect.connectServer(m);
		int result=rm.getStatusCode();
		if(1==result) 
		{return 1;}
		else
		return 0;
	}
	/**
	 * 获取服务器传递的Course类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return Course类型ArrayList.
	 */
	public ArrayList<Course> handleCourseMessage(Message m)
	{
		ArrayList<Course> c=new ArrayList<Course>();
		c=m.getCourseArray();
		return c;
		
	}
	/**
	 * 获取课表信息.
	 * @param number String类型变量,传递用户ID.
	 * @return CourseRecord类型ArrayList.
	 */
	public ArrayList<CourseRecord> getKeBiao(String number)
	{
		Message m=new Message("获取个人课表");
		m.setSelectorID(number);
		ArrayList<CourseRecord> c=new ArrayList<CourseRecord>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleRecordMessage(CM);
		return c;
	}
	/**
	 * 获取该科所有学生ID
	 * @param courseID Int类型对象,传递课程ID.
	 * @return CourseRecord类型ArrayList.
	 */
	public ArrayList<CourseRecord> getAllStudents(int courseID)
	{
		Message m=new Message("获取选课学生");
		m.setSelectedID(courseID);
		ArrayList<CourseRecord> c=new ArrayList<CourseRecord>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleRecordMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * 获取打分学生信息,向服务端发送打分请求并接收.
	 * @param selectedID int类型对象,传递课程ID.
	 * @param selectorID String类型对象,传递学生ID.
	 * @param score int类型对象,传递分数.
	 * @return 1或者0.
	 */
	public int score(int selectedID,String selectorID,int score)
	{
		Message m=new Message();
		m.setSelectorID(selectorID);
		m.setType("打分");
		m.setSelectedID(selectedID);
		m.setScore(score);
		Message rm=new Message();
		rm=myconnect.connectServer(m);
		int result=rm.getStatusCode();
		if(result>0) 
		{return 1;}
		else
		return 0;
	}
	/**
	 * 获取服务器传递的CourseRecord类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return CourseRecord类型ArrayList.
	 */
	private ArrayList<CourseRecord> handleRecordMessage(Message m) {
		ArrayList<CourseRecord> c=new ArrayList<CourseRecord>();
		c=m.getCourseRecordArray();
		return c;
	}
	/**
	 * 获取用户ID信息.
	 * @param user User类型对象.
	 * @return Student类型ArrayList.
	 */
	public ArrayList<Student> getInfo(Users user)
	{
		Message m=new Message("学籍信息");
		m.setUser(user);
		ArrayList<Student> c=new ArrayList<Student>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleStudentMessage(CM);
		//System.out.println(m.getUser().number);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * 更新学籍信息.
	 * @param c Student类型ArrayList.
	 */
	public void updateInfo(ArrayList<Student> c)
	{
		Message m=new Message("更新学籍信息");
		m.setStudentArray(c);
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleStudentMessage(CM);
		/*return handleMessage(CM);*/
		
	}
	/**
	 * 获取服务器传递的Student类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return Student类型ArrayList.
	 */

	public ArrayList<Student> handleStudentMessage(Message m)
	{
		ArrayList<Student> c=new ArrayList<Student>();
		c=m.getStudentArray();
		return c;
		
	}
	/**
	 * 获取用户ID信息.
	 * @param user User类型对象.
	 * @return Teacher类型ArrayList.
	 */
	public ArrayList<Teacher> getTeacherInfo(Users user)
	{
		Message m=new Message("教师信息");
		m.setUser(user);
		ArrayList<Teacher> c=new ArrayList<Teacher>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleTeacherMessage(CM);
		/*return handleMessage(CM);*/
		int i=1;
		return c;
	}
	/**
	 * 更新教师信息.
	 * @param c Teacher类型ArrayList.
	 */
	public void updateTeacherInfo(ArrayList<Teacher> c)
	{
		Message m=new Message("更新教师信息");
		m.setTeacherArray(c);
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleTeacherMessage(CM);
		/*return handleMessage(CM);*/
		
	}
	/**
	 * 获取服务器传递的Teacher类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return Teacher类型ArrayList.
	 */

	public ArrayList<Teacher> handleTeacherMessage(Message m)
	{
		ArrayList<Teacher> c=new ArrayList<Teacher>();
		c=m.getTeacherArray();
		return c;
		
	}
	/**
	 *获取所有书籍. 
	 * @return Book类型ArrayList.
	 */
	public ArrayList<Book> getInfo()
	{
		Message m=new Message("查阅书单");
		ArrayList<Book> c=new ArrayList<Book>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * 向服务端发出获取用户ID相应借书记录.
	 * @param userID String类型对象,传递用户ID.
	 * @return BookRecord类型ArrayList.
	 */
	public ArrayList<BookRecord> getrecordInfo(String userID)
	{
		Message m=new Message("查阅记录");
		m.setSelectorID(userID);
		ArrayList<BookRecord> c=new ArrayList<BookRecord>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookRecordMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * 更新借书信息.
	 * @param c Book类型ArrayList.
	 */
	public void updateBorrowInfo(ArrayList<Book> c)
	{
		Message m=new Message("更新借书信息");
		m.setBook(c);
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		
	}
	/**
	 * 向服务端发出查阅书名的请求.
	 * @param n String类型对象,传递书名.
	 * @return Book类型ArrayList.
	 */
	public ArrayList<Book> searchName(String n)
	{
		Message m=new Message("查阅书名");
		m.search = n;
		ArrayList<Book> c=new ArrayList<Book>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * 向服务端发出根据作者查书的请求.
	 * @param n String类型对象，传递作者名字.
	 * @return Book类型ArrayList.
	 */
	public ArrayList<Book> searchWriter(String n)
	{
		Message m=new Message("查阅作者");
		m.search = n;
		ArrayList<Book> c=new ArrayList<Book>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * 向服务端发出借书的请求.
	 * @param userID String类型对象,传递用户ID.
	 * @param bookid int类型对象,传递书号.
	 * @return 1.
	 */
	public int addBook(String userID,int bookid)
	{
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(bookid);
		m.setType("借书");
		//Message CM=new Message();
		myconnect.connectServer(m);
		/*return handleMessage(CM);*/
		/*int result=CM.getStatusCode();
		if(1==result) 
		{return 1;}
		else
		return 0;*/
		return 1;
	}
	/**
	 * 向服务端发出还书请求.
	 * @param userID String类型对象,传递用户ID.
	 * @param bookid int类型对象,传递书号.
	 * @return 1.
	 */
	public int returnbook(String userID,int bookid) {
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(bookid);
		m.setType("还书");
		myconnect.connectServer(m);
		return 1;
	}
	/**
	 * 获取服务器传递的Book类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return Book类型ArrayList.
	 */ 
	public ArrayList<Book> handleBookMessage(Message m)
	{
		ArrayList<Book> c=new ArrayList<Book>();
		c=m.getBook();
		return c;
		
	}
	/**
	 * 获取服务器传递的BookRecord类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return BookRecord类型ArrayList.
	 */
	public ArrayList<BookRecord> handleBookRecordMessage(Message m)
	{
		ArrayList<BookRecord> c=new ArrayList<BookRecord>();
		c=m.getBookrecord();
		return c;
		
	}


	
}
