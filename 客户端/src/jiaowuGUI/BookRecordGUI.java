package jiaowuGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import fangfalei.Clientfangfa;
import shitilei.Book;
import shitilei.BookRecord;
import shitilei.Users;
/**
 * 图书记录界面类
 * 
 */
public class BookRecordGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854159240980605041L;
	ArrayList<Book> mBookArray = null;
	ArrayList<BookRecord> mBookRecordArray = null;
	Clientfangfa client = new Clientfangfa();
	int selectedID;
	JTable bookrecordtable;
	Container c1;
	JScrollPane scrollPane;
	Users customer;
	ArrayList<Book> bookList=null;
/**
 * 构造函数.
 * @param tempBookList Book类型ArrayList.
 * @param user User类对象.
 */
	public BookRecordGUI(ArrayList<Book> tempBookList,Users user) {
		this.customer = user;
		bookList=tempBookList;
		JPanel jp1 = new JPanel();
		add(jp1);
		jp1.setBounds(0, 0, 584, 490);
		jp1.setLayout(null);

		JLabel lblNewLabel = new JLabel("借阅记录");
		lblNewLabel.setBounds(34, 31, 113, 34);
		lblNewLabel.setFont(new Font("宋体", 1, 20));
		jp1.add(lblNewLabel);

		final JButton huanshu = new JButton("还书");
		huanshu.setBounds(248, 370, 90, 25);
		huanshu.setFont(new Font("宋体", 1, 20));
		jp1.add(huanshu);

		huanshu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = 0;
				try {
					String SelectedISBN=new String();
					SelectedISBN=(String)bookrecordtable.getValueAt(bookrecordtable.getSelectedRow(), 0);
					//System.out.println("xxxx="+(bookrecordtable.getValueAt(bookrecordtable.getSelectedRow(), 1)));
					for(int i=0;i<bookList.size();i++) 
					{
						if(SelectedISBN.equals(bookList.get(i).ISBN))
						{selectedID = Integer.parseInt(bookList.get(i).ID);}
						System.out.println(i);
						System.out.println(Integer.parseInt(bookList.get(i).ID));
						System.out.println(SelectedISBN.equals(bookList.get(i).ISBN));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				System.out.println(selectedID);
				if (arg0.getSource() == huanshu) {
					result = client.returnbook(new String(customer.number), selectedID);
				}
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "已成功还书！");
				}
			}
		});

		/*
		 * JButton shuaxin = new JButton("刷新"); shuaxin.setFont(new
		 * Font("宋体",1,20));//设置字体 shuaxin.setVisible(true);
		 * shuaxin.setBackground(Color.white); p2.add(shuaxin);
		 */

		ArrayList<Book> book = new ArrayList<Book>(client.getInfo());
		ArrayList<BookRecord> bookrecord = new ArrayList<BookRecord>(client.getrecordInfo(new String(customer.number)));
  		Date date=new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = f.format(new Date());
		Date d1=null;
		try {
		d1=f.parse(d);}
		catch (Exception e)
		{
			 e.printStackTrace();
		}
		String[] columnNames=new String[] {"书号","名称","借书时间","逾期提醒"};
		Object[][] rowData = new Object[50][4];
		for (int i = 0; i <bookrecord.size(); i++)  
        {  
            for (int j = 0; j < 4; j++)  {
            	int k=0;
            	switch(j) {
            	case 0:
            		for(;k<book.size();k++) {
                    	if(book.get(k).ID.equals(bookrecord.get(i).bookID))
            			{
            				rowData[i][j] = book.get(k).ISBN;
            					break;
            			}                 	
            	}
            		break;
            	case 1:
            		for(;k<book.size();k++) {
                    	if(book.get(k).ID.equals(bookrecord.get(i).bookID))
            			{
            				rowData[i][j] = book.get(k).name;
            					break;
            			}
                    	
            	}break;
            	case 2:
            		rowData[i][j]=bookrecord.get(i).borrowerDate;
            		
            		break;
            	case 3:
            		try {
            		Date d2=f.parse(bookrecord.get(i).borrowerDate);
            		long diff =d1.getTime()-d2.getTime() ;
            		long days=diff/(1000*60*60*24);
            		if(days>=90) {
            			rowData[i][j]="已逾期！请尽快还书";
            		}
            		}catch (Exception e)
                    { e.printStackTrace();}
            }
        }
	}
		for (int i = 0; i < bookrecord.size(); i++)
			for (int j = 0; j < 3; j++) {
				System.out.println(rowData[i][j]);
			}

		bookrecordtable = new JTable(rowData, columnNames);
		bookrecordtable.setRowHeight(30);
		bookrecordtable.setSelectionBackground(Color.LIGHT_GRAY);
		bookrecordtable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);// 表格只能单选
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(bookrecordtable);
		scrollPane.setBounds(34, 94, 514, 260);
		JTableHeader bookrecordtableHeader = bookrecordtable.getTableHeader();
		jp1.add(bookrecordtableHeader);
		jp1.add(scrollPane);

		// 表格的鼠标事件监听器
		/*
		 * bookrecordtable.addMouseListener(new MouseAdapter() {
		 * 
		 * });
		 */
		jp1.setVisible(true);
	}
/**
 * 构造表.
 */
	public void creatTable() {
		ArrayList<Book> book = new ArrayList<Book>(client.getInfo());
		ArrayList<BookRecord> bookrecord = new ArrayList<BookRecord>(client.getrecordInfo(new String(customer.number)));

		// String[] columnNames=new String[] {"书号","名称","借书时间"};
		Object[][] rowData = new Object[50][3];
		for (int i = 0; i < bookrecord.size(); i++) {
			for (int j = 0; j < 3; j++) {
				int k = 0;
				switch (j) {
				case 0:
					for (; k < book.size(); k++) {
						if (book.get(k).ID.equals(bookrecord.get(i).bookID)) {
							rowData[i][j] = book.get(k).ISBN;
							break;
						}
					}
					break;
				case 1:
					for (; k < book.size(); k++) {
						if (book.get(k).ID.equals(bookrecord.get(i).bookID)) {
							rowData[i][j] = book.get(k).name;
							break;
						}
					}
					break;
				case 2:
					rowData[i][j] = bookrecord.get(i).borrowerDate;
					break;
				}
			}
		}
		for (int i = 0; i < bookrecord.size(); i++)
			for (int j = 0; j < 3; j++) {
				System.out.println(rowData[i][j]);
			}
	}
/**
 * 给数组赋值.
 * @param c Book类型ArrayList.
 */
	public void setBookArray(ArrayList<Book> c) {
		mBookArray = c;
	}
}
