package messageLei;
import shitilei.Book;
import java.io.Serializable;
import java.util.ArrayList;

import shitilei.BookRecord;
import shitilei.Course;
import shitilei.CourseRecord;
import shitilei.Goods;
import shitilei.GoodsRecord;
import shitilei.Student;
import shitilei.Teacher;
import shitilei.Users;

public class Message implements Serializable{
	
	/**
	 * 规定了message类内部的变量及其set与get函数
	 */
	private static final long serialVersionUID = -3596756118026748548L;
	private String type;
	protected int selectedID;
	protected String selectorID;
	protected ArrayList<Course> cal=null;
	protected ArrayList<CourseRecord> cral=null;
	protected Course c;
	protected int statusCode;
	protected int score;
	//protected int tag;
	protected ArrayList<Student> sal=null;
	protected Users u;
	protected ArrayList<Teacher> teaal=null;
	protected Teacher tea;
	protected String goodsname;
	protected int goodsnumber;
    protected ArrayList<Goods> goods=null;
	protected ArrayList<GoodsRecord> goodsrecord=null;
	protected ArrayList<Book> book=null;
	protected ArrayList<BookRecord> bookrecord=null;
    public String search = null;
	protected String time;
	
	
	public Message()
	{

	}
	
	public Message(Message u)
	{
	this.type=u.type;
	}
	
	public Message(String type)
	{
		this.type=type;

	}
	

	public ArrayList<Course> getCourseArray() 
	{
		return cal;
	}

	public void setCourseRecordArray(ArrayList<CourseRecord> c) 
	{
		this.cral = c;
	}
	
	public ArrayList<CourseRecord> getCourseRecordArray() 
	{
		return cral;
	}

	public ArrayList<Teacher> getTeacherArray() 
	{
		return teaal;
	}


	public void setTeacherArray(ArrayList<Teacher> c) 
	{
		teaal = c;
	}
	public void setCourseArray(ArrayList<Course> c) 
	{
		this.cal = c;
	}
	
	public Course getCourse() 
	{
		return c;
	}

	public void setCourse(Course co) 
	{
		this.c = co;
	}

	public Users getUser() 
	{
		return u;
	}

	public void setUser(Users user) 
	{
		u = user;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}


	/*public int getTag() 
	{
		return tag;
	}

	public void setTag(int t) 
	{
		tag = t;
	}*/
	
	public int getStatusCode() 
	{
		return statusCode;
	}

	public void setStatusCode(int a) 
	{
		statusCode = a;
	}
	
	public int getSelectedID() 
	{
		return selectedID;
	}

	public void setSelectedID(int a) 
	{
		selectedID = a;
	}

	public int getScore() 
	{
		return score;
	}

	public void setScore(int a) 
	{
		score = a;
	}
	
	public String getSelectorID() 
	{
		return selectorID;
	}

	public void setSelectorID(String a) 
	{
		selectorID=a;
	}

	public void setStudentArray(ArrayList<Student> a) {
		sal=a;
		
	}

	public ArrayList<Student> getStudentArray() {

		return sal;
	}

	public Teacher getTeacher() {
		return tea;
		
	}
	
	public void  setTeacher(Teacher t) {
		tea=t;
		
	}
	
	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public int getGoodsnumber() {
		return goodsnumber;
	}

	public void setGoodsnumber(int goodsnumber) {
		this.goodsnumber = goodsnumber;
	}

	public ArrayList<Goods> getGoods() {
		return goods;
	}

	public void setGoods(ArrayList<Goods> goods) {
		this.goods = goods;
	}

	public ArrayList<GoodsRecord> getGoodsrecord() {
		return goodsrecord;
	}

	public void setGoodsrecord(ArrayList<GoodsRecord> goodsrecord) {
		this.goodsrecord = goodsrecord;
	}

	public ArrayList<Book> getBook() {
		return book;
	}

	public void setBook(ArrayList<Book> book) {
		this.book = book;
	}

	public ArrayList<BookRecord> getBookrecord() {
		return bookrecord;
	}

	public void setBookrecord(ArrayList<BookRecord> bookrecord) {
		this.bookrecord = bookrecord;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}