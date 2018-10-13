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
 * GUI��ť���õĺ����࣬ͬʱҲ�������˽�����Ϣ��������
 * @author �����,л�ٲ�,������,���﷫.
 * @since JDK1.7.
 */
public class Clientfangfa {
	/**
	 * ��̬����myconnectֵΪ{@value}.
	 */
	static connect myconnect=new connect();
	/**
	 * ��ȡ���пγ���Ϣ.
	 * @return Course����ArrayList.
	 */
	public ArrayList<Course> getAllCourse()
	{
		Message m=new Message("��ȡ���пγ�");
		ArrayList<Course> c=new ArrayList<Course>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleCourseMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	
	/**
	 * ��ȡѡ�пγ���Ϣ,�����������ѡ�����󲢽���.
	 * @param ID int����,���ݿγ�ID.
	 * @param number String����,�����û�ID.
	 * @return 1��0.
	 */
	public int selectCourse(int ID,String number) 
	{
		Message m=new Message();
		m.setSelectedID(ID);
		m.setSelectorID(number);
		m.setType("ѡ��");
		Message rm=new Message();
		rm=myconnect.connectServer(m);
		int result=rm.getStatusCode();
		if(1==result) 
		{return 1;}
		else
		return 0;
	}
	/**
	 * ��ȡѡ�пγ���Ϣ,������������˿����󲢽���.
	 * @param selectedID int����,���ݿγ�ID.
	 * @param number String����,�����û�ID.
	 * @return 1��0.
	 */
	public int deleteCourse(int selectedID,String number) 
	{
		Message m=new Message();
		m.setSelectedID(selectedID);
		m.setSelectorID(number);
		m.setType("�˿�");
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
	 * ��ȡ���������ݵ�Course��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return Course����ArrayList.
	 */
	public ArrayList<Course> handleCourseMessage(Message m)
	{
		ArrayList<Course> c=new ArrayList<Course>();
		c=m.getCourseArray();
		return c;
		
	}
	/**
	 * ��ȡ�α���Ϣ.
	 * @param number String���ͱ���,�����û�ID.
	 * @return CourseRecord����ArrayList.
	 */
	public ArrayList<CourseRecord> getKeBiao(String number)
	{
		Message m=new Message("��ȡ���˿α�");
		m.setSelectorID(number);
		ArrayList<CourseRecord> c=new ArrayList<CourseRecord>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleRecordMessage(CM);
		return c;
	}
	/**
	 * ��ȡ�ÿ�����ѧ��ID
	 * @param courseID Int���Ͷ���,���ݿγ�ID.
	 * @return CourseRecord����ArrayList.
	 */
	public ArrayList<CourseRecord> getAllStudents(int courseID)
	{
		Message m=new Message("��ȡѡ��ѧ��");
		m.setSelectedID(courseID);
		ArrayList<CourseRecord> c=new ArrayList<CourseRecord>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleRecordMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * ��ȡ���ѧ����Ϣ,�����˷��ʹ�����󲢽���.
	 * @param selectedID int���Ͷ���,���ݿγ�ID.
	 * @param selectorID String���Ͷ���,����ѧ��ID.
	 * @param score int���Ͷ���,���ݷ���.
	 * @return 1����0.
	 */
	public int score(int selectedID,String selectorID,int score)
	{
		Message m=new Message();
		m.setSelectorID(selectorID);
		m.setType("���");
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
	 * ��ȡ���������ݵ�CourseRecord��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return CourseRecord����ArrayList.
	 */
	private ArrayList<CourseRecord> handleRecordMessage(Message m) {
		ArrayList<CourseRecord> c=new ArrayList<CourseRecord>();
		c=m.getCourseRecordArray();
		return c;
	}
	/**
	 * ��ȡ�û�ID��Ϣ.
	 * @param user User���Ͷ���.
	 * @return Student����ArrayList.
	 */
	public ArrayList<Student> getInfo(Users user)
	{
		Message m=new Message("ѧ����Ϣ");
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
	 * ����ѧ����Ϣ.
	 * @param c Student����ArrayList.
	 */
	public void updateInfo(ArrayList<Student> c)
	{
		Message m=new Message("����ѧ����Ϣ");
		m.setStudentArray(c);
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleStudentMessage(CM);
		/*return handleMessage(CM);*/
		
	}
	/**
	 * ��ȡ���������ݵ�Student��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return Student����ArrayList.
	 */

	public ArrayList<Student> handleStudentMessage(Message m)
	{
		ArrayList<Student> c=new ArrayList<Student>();
		c=m.getStudentArray();
		return c;
		
	}
	/**
	 * ��ȡ�û�ID��Ϣ.
	 * @param user User���Ͷ���.
	 * @return Teacher����ArrayList.
	 */
	public ArrayList<Teacher> getTeacherInfo(Users user)
	{
		Message m=new Message("��ʦ��Ϣ");
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
	 * ���½�ʦ��Ϣ.
	 * @param c Teacher����ArrayList.
	 */
	public void updateTeacherInfo(ArrayList<Teacher> c)
	{
		Message m=new Message("���½�ʦ��Ϣ");
		m.setTeacherArray(c);
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleTeacherMessage(CM);
		/*return handleMessage(CM);*/
		
	}
	/**
	 * ��ȡ���������ݵ�Teacher��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return Teacher����ArrayList.
	 */

	public ArrayList<Teacher> handleTeacherMessage(Message m)
	{
		ArrayList<Teacher> c=new ArrayList<Teacher>();
		c=m.getTeacherArray();
		return c;
		
	}
	/**
	 *��ȡ�����鼮. 
	 * @return Book����ArrayList.
	 */
	public ArrayList<Book> getInfo()
	{
		Message m=new Message("�����鵥");
		ArrayList<Book> c=new ArrayList<Book>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * �����˷�����ȡ�û�ID��Ӧ�����¼.
	 * @param userID String���Ͷ���,�����û�ID.
	 * @return BookRecord����ArrayList.
	 */
	public ArrayList<BookRecord> getrecordInfo(String userID)
	{
		Message m=new Message("���ļ�¼");
		m.setSelectorID(userID);
		ArrayList<BookRecord> c=new ArrayList<BookRecord>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookRecordMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * ���½�����Ϣ.
	 * @param c Book����ArrayList.
	 */
	public void updateBorrowInfo(ArrayList<Book> c)
	{
		Message m=new Message("���½�����Ϣ");
		m.setBook(c);
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		
	}
	/**
	 * �����˷�����������������.
	 * @param n String���Ͷ���,��������.
	 * @return Book����ArrayList.
	 */
	public ArrayList<Book> searchName(String n)
	{
		Message m=new Message("��������");
		m.search = n;
		ArrayList<Book> c=new ArrayList<Book>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * �����˷����������߲��������.
	 * @param n String���Ͷ��󣬴�����������.
	 * @return Book����ArrayList.
	 */
	public ArrayList<Book> searchWriter(String n)
	{
		Message m=new Message("��������");
		m.search = n;
		ArrayList<Book> c=new ArrayList<Book>();
		Message CM=new Message();
		CM=myconnect.connectServer(m);
		c=handleBookMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * �����˷������������.
	 * @param userID String���Ͷ���,�����û�ID.
	 * @param bookid int���Ͷ���,�������.
	 * @return 1.
	 */
	public int addBook(String userID,int bookid)
	{
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(bookid);
		m.setType("����");
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
	 * �����˷�����������.
	 * @param userID String���Ͷ���,�����û�ID.
	 * @param bookid int���Ͷ���,�������.
	 * @return 1.
	 */
	public int returnbook(String userID,int bookid) {
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(bookid);
		m.setType("����");
		myconnect.connectServer(m);
		return 1;
	}
	/**
	 * ��ȡ���������ݵ�Book��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return Book����ArrayList.
	 */ 
	public ArrayList<Book> handleBookMessage(Message m)
	{
		ArrayList<Book> c=new ArrayList<Book>();
		c=m.getBook();
		return c;
		
	}
	/**
	 * ��ȡ���������ݵ�BookRecord��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return BookRecord����ArrayList.
	 */
	public ArrayList<BookRecord> handleBookRecordMessage(Message m)
	{
		ArrayList<BookRecord> c=new ArrayList<BookRecord>();
		c=m.getBookrecord();
		return c;
		
	}


	
}
