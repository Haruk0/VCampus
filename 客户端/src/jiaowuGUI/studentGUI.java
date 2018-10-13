package jiaowuGUI;
/*import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import shitilei.Course;
import shitilei.CourseRecord;
import fangfalei.Clientfangfa;

public class studentGUI {

	static JFrame app;
	
	static void paintGUI()
	{
		app=new JFrame("学生选课");
		app.setSize(600, 500);
		
		ArrayList<Course> c=new ArrayList<Course>(); 
		c=new Clientfangfa().getAllCourse();
		
		JPanel northP=new JPanel();
		JPanel centerP=new JPanel();
		JPanel southP=new JPanel();
		JLabel AllCourseL=new JLabel("所有课程");
		northP.add(AllCourseL);
		
		String[] courseArray = new String[c.size()];
		 for(int i=0;i<c.size();i++){  
			 Course temp=c.get(i);
			 courseArray[i]=temp.name;  
	        }  
		JList<String> CourseL=new JList<String>();
		CourseL.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		CourseL.setListData(courseArray);
		JScrollPane scrollPane = new JScrollPane(
                CourseL,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
		CourseL.setPreferredSize(new Dimension(350, 400));
		centerP.add(scrollPane);

/*		 String[] columnNames =  
		        { "姓名", "学号", "性别", "工期", "学院", "学校" };  
		  
		        Object[][] obj = new Object[2][6];  
		        for (int i = 0; i < 2; i++)  
		        {  
		            for (int j = 0; j < 6; j++)  
		            {  
		                switch (j)  
		                {  
		                case 0:  
		                    obj[i][j] = "赵匡义";  
		                    break;  
		                case 1:  
		                    obj[i][j] = "123215";  
		                    break;  
		                case 2:  
		                    obj[i][j] = "男";  
		                    break;  
		                case 3:  
		                    obj[i][j] = "两年";  
		                    break;  
		                case 4:  
		                    obj[i][j] = "计算机技术学院";  
		                    break;  
		                case 5:  
		                    obj[i][j] = "北京理工大学";  
		                    break;  
		                }  
		            }  
		        }  
		          
		        JTable table = new JTable(obj, columnNames);  
		        centerP.add(table);
	        
		JButton select=new JButton("选课");
		select.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
						"选课成功！", "系统提示", JOptionPane.INFORMATION_MESSAGE); 
			}
			
		});
		southP.add(select);
		
		
		app.add(northP,"North");
		app.add(centerP,"Center");
		app.add(southP,"South");
		
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
	
		paintGUI();
	}

}

package gui;*/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import fangfalei.Clientfangfa;
import shitilei.Course;
import shitilei.CourseRecord;
import shitilei.Users;
/**
 * 教务学生界面.
 *
 */
public class studentGUI extends JPanel {
	private static final long serialVersionUID = 3876348482396028510L;
	JButton btn_choose, btn_find, btn_tuike;// 面板控件
	int selectedID;
	FunctionPanel p = null;
	String selectorID = null;
	Clientfangfa client = new Clientfangfa();
	static JTable xuanketable;
	ArrayList<Course> overAllCourseArray = null;
	Users customer;
	JPanel jp1;
/**
 * 构造函数
 * @param customer User.
 */
	public studentGUI(Users customer) {
		this.customer = customer;
		selectorID = new String(customer.number);

		jp1 = new JPanel();
		add(jp1);
		jp1.setBounds(0, 0, 700, 500);
		jp1.setLayout(null);

		btn_choose = new JButton("选择该课程");
		btn_choose.setFont(new Font("宋体", 1, 20));
		btn_choose.setBounds(70, 406, 150, 39);
		jp1.add(btn_choose);
		btn_choose.addActionListener(new btn_chooseAction());

		/*btn_tuike = new JButton("退选该课程");
		btn_tuike.setFont(new Font("宋体", 1, 20));
		btn_tuike.setBounds(250, 406, 150, 39);
		jp1.add(btn_tuike);
		btn_tuike.addActionListener(new btn_tuikeAction());
*/
		btn_find = new JButton("查看课表");
		btn_find.setFont(new Font("宋体", 1, 20));
		btn_find.setBounds(420, 406, 150, 39);
		jp1.add(btn_find);
		btn_find.addActionListener(new btn_findAction());

		// ArrayList<Course> c=new ArrayList<Course>();
		// Clientfangfa temple=new Clientfangfa();
		// c=temple.getAllCourse();
		ArrayList<Course> c = new ArrayList<Course>(client.getAllCourse());
		setOverAllCourseArray(c);

		// 选课表格
		String[] columnNames = new String[] { "课程ID", "课程名称", "任课教师", "开始时间", "结束时间" };
		Object[][] rowData = new Object[50][5];
		for (int i = 0; i <c.size(); i++)  
        {  
            for (int j = 0; j < 5; j++)  
            {  
                switch (j)  
                {  
                case 0:  
                	rowData[i][j] = c.get(i).courseID;  
                    break;  
                case 1:  
                	rowData[i][j] = c.get(i).name;  
                    break;  
                case 2:  
                	rowData[i][j] = c.get(i).teacher;  
                    break;  
                case 3:
                	String beginTimeS=Integer.toString(c.get(i).beginTime);
                	String beginWeekS=Integer.toString(c.get(i).weekday);
                	rowData[i][j] = "周"+beginWeekS+"第"+beginTimeS+"节课";  
                    break;  
                case 4:  
                	String endTimeS=Integer.toString(c.get(i).beginTime+2);
                	String endWeekS=Integer.toString(c.get(i).weekday);
                	rowData[i][j] = "周"+endWeekS+"第"+endTimeS+"节课";    
                    break;  
                }  
            }  
        }  
		xuanketable = new JTable(rowData, columnNames);
		xuanketable.setRowHeight(30);
		xuanketable.setRowMargin(1);
		xuanketable.setSelectionBackground(Color.ORANGE);
		JScrollPane scrollPane = new JScrollPane(xuanketable);
		scrollPane.setBounds(30, 30, 600, 350);
		JTableHeader tableHeader = xuanketable.getTableHeader();
		jp1.add(tableHeader);
		jp1.add(scrollPane);
		scrollPane.setViewportView(xuanketable);
	}
/**
 * 初始化数组.
 * @param c Course类型ArrayList.
 */
	public void setOverAllCourseArray(ArrayList<Course> c) {
		overAllCourseArray = c;
	}
/**
 * 监听选课按钮.
 *
 */
	class btn_chooseAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			selectedID = xuanketable.getSelectedRow();
			int ResultCode = 0;
			if (selectedID != -1) {
				ResultCode = client.selectCourse(selectedID, selectorID);
			}
			if (ResultCode == 1) {
				JOptionPane.showMessageDialog(null, "已成功选择该课程！");
			} else {
				JOptionPane.showMessageDialog(null, "您已选择该课程！");
			}
		}
	}

/*	class btn_tuikeAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// if(choseNumber%2==0)
			// {selectorID=selectorID1;
			// choseNumber+=1;}
			JOptionPane.showMessageDialog(null, "已成功退选该课程！");
			// else
			// {selectorID=selectorID2;
			// choseNumber+=1;}
			// JOptionPane.showMessageDialog(null, "ID改为"+selectorID);
		}
	}
*/
	/**
	 * 监听课表按钮,发出请求.
	 *
	 */
	class btn_findAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			remove(jp1);
			repaint();
			Formkebiao mykebiao = new Formkebiao(selectorID, client, overAllCourseArray);
			mykebiao.setBounds(0, 0, 700, 500);
			add(mykebiao);
			//add(btn_tuike);
			mykebiao.setVisible(true);
		}
	}

}

// 课表（点 查询课表 按钮，会弹出这个课表界面）
/**
 * 课表界面.
 *
 */
class Formkebiao extends JPanel {
	private static final long serialVersionUID = 1L;
	public static String number;
	public static Clientfangfa client = null;
	studentGUI jf = null;
	static JTable kebiao = null;
	public int selectedID = -1;
	DefaultTableModel tableModel;
	
/**
 * 构造函数
 * @param selectorID String类对象,用户ID.
 * @param mclient 方法类对象.
 * @param overAllCourseArray Course类型ArrayList.
 */
	public Formkebiao(String selectorID, Clientfangfa mclient, ArrayList<Course> overAllCourseArray) {
		number = selectorID;
		client = mclient;
		ArrayList<CourseRecord> c = new ArrayList<CourseRecord>(client.getKeBiao(number));

		String[] columnNames=new String[] {"ID","课程名称","任课教师","开始时间","结束时间","成绩"};
		Object[][] rowData = new Object[50][6];
		for (int i = 0; i <c.size(); i++)  
        {  	int k=0;
            for (int j = 0; j < 6; j++)  
            {   
            	
                switch (j)  
                { 
                
                case 0:  
                	for(;k<overAllCourseArray.size();k++)
                	{
                		if(overAllCourseArray.get(k).courseID==c.get(i).selectedcourseID)
                			{
                				rowData[i][j] = overAllCourseArray.get(k).courseID;
                					break;
                			}
                	}
                    break;
                case 1:  
                	for(;k<overAllCourseArray.size();k++)
                	{
                		if(overAllCourseArray.get(k).courseID==c.get(i).selectedcourseID)
                			{
                				rowData[i][j] = overAllCourseArray.get(k).name;
                					break;
                			}
                	}
                    break;  
                case 2:  
                	rowData[i][j] = overAllCourseArray.get(k).teacher;  
                    break;  
                case 3:  
                	String beginTimeS=Integer.toString(overAllCourseArray.get(k).beginTime);
                	String beginWeekS=Integer.toString(overAllCourseArray.get(k).weekday);
                	rowData[i][j] = "周"+beginWeekS+"第"+beginTimeS+"节课";  
                    break;  
                case 4:
                	String endTimeS=Integer.toString(overAllCourseArray.get(k).beginTime+2);
                	String endWeekS=Integer.toString(overAllCourseArray.get(k).weekday);
                	rowData[i][j] = "周"+endWeekS+"第"+endTimeS+"节课";    
                    break;  
                case 5:
                	if(c.get(i).score>0)
                	rowData[i][j] = c.get(i).score;
                	else
                		rowData[i][j] = "成绩未公布";
                	break;
                
                
                }  
            }  
        }  
		JTable kebiaotable = new JTable(rowData, columnNames);
		kebiaotable.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(kebiaotable);
		add(scrollPane);
		scrollPane.setBounds(50, 50, 500, 300);

		JButton btn_tuike=new JButton("退选该课程");
		btn_tuike.setFont(new Font("宋体",1,20));
		btn_tuike.setBackground(Color.white);
		btn_tuike.setBounds(350, 400, 150, 25);
		add(btn_tuike);
		btn_tuike.addActionListener(new btn_tuikeAction());
		btn_tuike.setVisible(true);
		kebiao = kebiaotable;
	}
/**
 * 监听退课按钮,发出请求.
 *
 */
	class btn_tuikeAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			selectedID = (int) kebiao.getValueAt(kebiao.getSelectedRow(), 0);
			if(client.deleteCourse(selectedID, number)==1) {
				
				JOptionPane.showMessageDialog(null, "退选该课程成功！");
			}
			else {
				JOptionPane.showMessageDialog(null, "退选该课程失败！");
			}
			
		}
	} 
	
}