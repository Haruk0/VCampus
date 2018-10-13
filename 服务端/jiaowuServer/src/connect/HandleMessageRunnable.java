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
* ��Ӧ�ͻ�������
* @author �����,л�ٲ�,������,���﷫.
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
 * ��Ӧ�ͻ��˵���������
 * @param socket socket���������ݿͻ���socket
 * @param CD Connection����
 */
public HandleMessageRunnable(Socket socket,CourseDAO CD) {
    this.socket = socket;
    this.connection=CourseDAO.conn;
}
//�߳�ִ�еĲ�������Ӧ�ͻ��˵�����
/**
 * ���������������������ر�socket
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
      System.out.println("�ѶϿ�����");  
	  //e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally{
        //�ر���Դ
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
}

/*public void run(){  
	System.out.println("�߳̿�ʼ");
    try {
    	while(socket.isConnected()) {while(true) {
    		System.out.println("������Ϣ��ʼ");	
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
 * ���ݿͻ�������������Ӧ��������Ӧ�Ĳ�������
 * @param message �ͻ��˷��͵���������
 * 
 */
public void handleMessage(Message message){
	
    switch (message.getType()){
    /**
     * ����ѡ�ε���ز���
     * @see CourseDAO.java 
     * 
     */
        case "��ȡ���пγ�":
            CourseDAO DAO1=new CourseDAO();
            ArrayList<Course> a=new ArrayList<Course>();
            a=DAO1.getAllCourseA();
            //String name=new String();
            //name=a.get(0).name;
            //System.out.println(name);
            CM=new Message();
            CM.setCourseArray(a);
            CM.setType("�������пγ�");
            //ArrayList<Course> alc=new ArrayList<Course>();
            //alc=CM.getCourseArray();
            //System.out.println(alc.get(0).name);
            break;
            
        case"ѡ��":
        	CourseDAO DAO2=new CourseDAO();
        	int returnNumber=DAO2.addCourse(String.valueOf(message.getSelectorID()),message.getSelectedID());
        	CM=new Message();
        	CM.setStatusCode(returnNumber);
        	CM.setType("���ز������");
        	//System.out.println(CM.getStatusCode());
        	break;
        case"�˿�":
        	CourseDAO DAO0=new CourseDAO();
        	int resNumber=DAO0.delCourse(String.valueOf(message.getSelectorID()),message.getSelectedID());
        	CM=new Message();
        	CM.setStatusCode(resNumber);
        	CM.setType("�����˿ν��");
        	//System.out.println(CM.getStatusCode());
        	break;

        case "��ȡ���˿α�":
            CourseDAO DAO3=new CourseDAO();
            ArrayList<CourseRecord> cral=new ArrayList<CourseRecord>();
            cral=DAO3.searchKeBiao(message.getSelectorID());
            //String name=new String();
            //name=a.get(0).name;
            //System.out.println(name);
            CM=new Message();
            CM.setCourseRecordArray(cral);
            CM.setType("���ظ��˿α�");
            break;
            
        case"��ȡѡ��ѧ��":
        	CourseDAO DAO4=new CourseDAO();
        	ArrayList<CourseRecord> cral1=new ArrayList<CourseRecord>();
            cral1=DAO4.getAllStudentsA(message.getSelectedID());
            CM=new Message();
            CM.setCourseRecordArray(cral1);
            CM.setType("����ѡ��ѧ��");
            break;
            
        case"���":
        	CourseDAO DAO5=new CourseDAO();
        	int returnNumber1=DAO5.updateCourse(message.getScore(),message.getSelectorID(),message.getSelectedID());
        	CM=new Message();
        	CM.setStatusCode(returnNumber1);
        	CM.setType("���ز������");
        	break;
            /**
             * ����ѧ������ز���
             * @see StudentDAO.java
             * @see TeacherDAO.java
             */       	
        case "ѧ����Ϣ":
            System.out.println(message.getType());
            StudentDAO DAO6=new StudentDAO();
            ArrayList<Student> a1=new ArrayList<Student>();
            String selectorID = new String (message.getUser().number);
            a1=DAO6.getInfo( selectorID);

            CM=new Message();
            CM.setStudentArray(a1);
            CM.setType("����ѧ����Ϣ");
            break;
            
     case"����ѧ����Ϣ":
    	 StudentDAO DAO7=new StudentDAO();
    	 System.out.println(message.getType());
    	 DAO7.updateInfo( message.getStudentArray());
    	 ArrayList<Student> b=new ArrayList<Student>();
    	 String selectorID1 = message.getStudentArray().get(0).number;
         b=DAO7.getInfo( selectorID1);
         CM=new Message();
         CM.setStudentArray(b);
         CM.setType("����ѧ����Ϣ");
         break;
     case "��ʦ��Ϣ":
         TeacherDAO DAO8=new TeacherDAO();
         DAO8.openDb();
         ArrayList<Teacher> a8=new ArrayList<Teacher>();
         String selectorID8 = new String (message.getUser().number);
         System.out.println(selectorID8);
         a8=DAO8.getInfo( selectorID8);
         CM=new Message();
         CM.setTeacherArray(a8);
         CM.setType("���ؽ�ʦ��Ϣ");
         break;
         
     case"���½�ʦ��Ϣ":
    	TeacherDAO DAO9=new TeacherDAO();
        DAO9.openDb();
   	 	System.out.println(message.getType());
   	 	DAO9.updateInfo( message.getTeacherArray());
   	 	ArrayList<Teacher> b9=new ArrayList<Teacher>();
   	 	String selectorID9 = message.getTeacherArray().get(0).number;
        b9=DAO9.getInfo( selectorID9);
        CM=new Message();
        CM.setTeacherArray(b9);
        CM.setType("�����ʦ��Ϣ");
        break;
         /**
          * ���е�½����ز���
          * @see UserDAO.java
          */ 
     case "�����½":
     	System.out.println("�û������½");
        String n = new String(message.getUser().number);
		String p = new String(message.getUser().pwd);
        UserDAO ud = new UserDAO();
        Users backUser = ud.signJudge(n, p);
        System.out.println(backUser.name);
	    if (backUser.tag == 1) {
	    	
		    CM= new Message("��½�ɹ�"); 
		    CM.setUser(backUser);
		    
	       } 
	    
	     if (backUser.tag == 2) {
	 		 CM= new Message("��½�ɹ�");
	 		 CM.setUser(backUser);

	 	   }
	     if(backUser.tag==0) {
		     CM= new Message("�û������������");
		     }
	       
	        break;
	        
     case "�����޸�����":
     	System.out.println("�û������޸�����");
        CM=new Message();
        String n1 = new String(message.getUser().number);
		String p1 = new String(message.getUser().pwd);
        UserDAO ud1 = new UserDAO();
        Users backUser1 = ud1.signJudge(n1, p1);
        	if (backUser1.tag!=0) {
        		CM= new Message("�����޸�����");    		
 
	        } else {
		        CM= new Message("�û������������");

	          }  
	        break;
	        
     case "�޸�����":
     	UserDAO ud2 = new UserDAO();
     	String num = new String(message.getUser().number);
     	String np = new String(message.getUser().pwd);
		try {
			ud2.changePwd(num,np );
		} catch (Exception e) {
			e.printStackTrace();
		}
     	CM= new Message("�޸��������");
	    System.out.println("CM.type = "+CM.getType());  
	    break;
         
 /*    case "��ʦ��Ϣ":
         
         TeacherDAO DAO8=new TeacherDAO();
         ArrayList<Teacher> a8=new ArrayList<Teacher>();
         String selectorID8 = message.getTeacher().number;
         a8=DAO8.getInfo( selectorID8);

         CM=new Message();
         CM.setTeacherArray(a8);
         CM.setType("���ؽ�ʦ��Ϣ");
        // System.out.println(CM.getTeacherArray().get(0).name);
         break;
         
  case"���½�ʦ��Ϣ":
 	 TeacherDAO DAO9=new TeacherDAO();
 	 System.out.println(message.getType());
 	 DAO9.updateInfo( message.getTeacherArray());
 	 ArrayList<Teacher> b9=new ArrayList<Teacher>();
 	 String selectorID9 = message.getTeacher().number;
      b9=DAO9.getInfo( selectorID9);
      CM=new Message();
      CM.setTeacherArray(b9);
      CM.setType("�����ʦ��Ϣ");
      break;*/
	    
	    /**
         * �����̵����ز���
         * @see GoodsDAO.java
         * @see	GoodsRecordDAO.JAVA
         */ 
   
case "��ȡ��Ʒ�б�":
	//System.out.println('1');
    GoodsDao myDAO1=new GoodsDao();
    ArrayList<Goods> goods=new ArrayList<Goods>();
    goods=myDAO1.getAllgoods();
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    CM=new Message();
    CM.setGoods(goods);
    CM.setType("������Ʒ�б�");
    
    break;
case "���빺�ﳵ":
    GoodsRecordDao myDAO2=new GoodsRecordDao();
    myDAO2.addGoods(message.getSelectorID(), message.getSelectedID(), message.getGoodsnumber());
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    break;
case "����":
    GoodsRecordDao myDAO=new GoodsRecordDao();
    int result=myDAO.updateGoodsRecord(message.getSelectorID(), message.getSelectedID());
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    CM=new Message();
	CM.setStatusCode(result);
	CM.setType("���ز������");
    break;
case "��ȡ���ﳵ�б�":
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
    CM.setType("���ع��ﳵ�б�");
    break;
case "ѡ��Ʒ":
    GoodsDao myDAO4=new GoodsDao();
    ArrayList<Goods> searchgoods=new ArrayList<Goods>();
   searchgoods=myDAO4.seachGoods(message.getGoodsname());
    /*String name=new String();
    name=a.get(0).name;
    System.out.println(name);*/
    CM=new Message();
    CM.setGoods(searchgoods);
    CM.setType("���ع��ﳵ�б�");
    break;
    /**
     * ����ͼ��ݵ���ز���
     * @see BookDAO.java
     */ 
case "�����鵥":
    
    BookDAO DAO11=new BookDAO();
    ArrayList<Book> a11=new ArrayList<Book>();
    a11=DAO11.getInfo();

    CM=new Message();
    CM.setBook(a11);
    CM.setType("�����鵥");
    break;
case "���ļ�¼":
 
 BookDAO mDAO=new BookDAO();
 ArrayList<BookRecord> record=new ArrayList<BookRecord>();
 record=mDAO.getAllbookRecord(message.getSelectorID());
 System.out.println("record = "+record);
 CM=new Message();
 CM.setBookrecord(record);
 CM.setType("�����鵥");
 break;
/*case"����ѧ����Ϣ":
 BookDAO DAO2=new BookDAO();
 System.out.println(message.getType());
 DAO2.updateInfo( message.getBookArray());
 ArrayList<Book> b=new ArrayList<Book>();
 String selectorID1 = "09016224";
 b=DAO2.getInfo( selectorID1);
 CM=new Message();
 CM.setBookArray(b);
 CM.setType("����ѧ����Ϣ");
 break;*/
case "��������":
 BookDAO DAO21=new BookDAO();
 ArrayList<Book> b1=new ArrayList<Book>();
 b1=DAO21.SearchName(message.search);
 CM=new Message();
 CM.setBook(b1);
 CM.setType("���������������");
 break;
case "��������":
 BookDAO DAO31=new BookDAO();
 ArrayList<Book> c=new ArrayList<Book>();
 c=DAO31.SearchWriter(message.search);
 CM=new Message();
 CM.setBook(c);
 CM.setType("���������������");
 break;
case "����":
BookDAO myDAO21=new BookDAO();
 myDAO21.Borrow(message.getSelectorID(), message.getSelectedID());
 System.out.println("message.getSelectedID()"+message.getSelectedID());
 /*String name=new String();
 name=a.get(0).name;
 System.out.println(name);*/
 break;
case "����":
 System.out.println("���л������");
 BookDAO myDAO31=new BookDAO();
 myDAO31.Returnbook(message.getSelectorID(), message.getSelectedID());
 
 break;
     
        default:
            System.out.println("Never arrive here!");
            break;

    }
}

}