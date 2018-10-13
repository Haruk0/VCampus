package jiaowuGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import fangfalei.Clientfangfa;
import shitilei.Book;
import shitilei.BookRecord;
import shitilei.Users;
/**
 * 图书借阅记录界面.
 *
 */
public class BookSearch extends JPanel {
	private static final long serialVersionUID = 1L;
	JButton btn_name, btn_author, btn_borrow, btn_record;// 面板控件
	Container con;
	int selectedID;
	ArrayList<BookRecord> mBookRecordArray = null;
	Clientfangfa client = new Clientfangfa();
	JTable table;
	String selectorID;
	Users customer;
	// ArrayList<Book> c=new ArrayList<Book>(client.getInfo());
	ArrayList<Book> c;
	ArrayList<Book> d;
	JPanel jp1 = null;
/**
 * 构造函数.
 * @param customer Users对象.
 */
	public BookSearch(final Users customer) {

		this.customer = customer;
		selectorID = new String(this.customer.number);

		jp1 = new JPanel();
		jp1.setLayout(null);
		add(jp1);
		jp1.setBounds(0, 0, 700, 500);

		JLabel jl1 = new JLabel("查询图书");
		jl1.setFont(new Font("宋体", 1, 20));
		jl1.setSize(95, 25);
		jl1.setLocation(30, 30);
		// jp1.setLayout(null);
		jp1.add(jl1);

		final JTextField jt1 = new JTextField();
		// jt1.setBounds();
		jt1.setSize(136, 25);
		jt1.setLocation(146, 32);
		jp1.add(jt1);

		// 设置按钮
		JButton btn_name = new JButton("查书名");
		btn_name.setBounds(308, 30, 108, 25);
		btn_name.setFont(new Font("宋体", 1, 20));
		btn_name.setVisible(true);
		// btn_name.setIcon(i1);
		jp1.add(btn_name);
		btn_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = jt1.getText();
				c = client.searchName(name);
				BookModel books = new BookModel(c);
				table.setModel(books);
				table.setEnabled(true);
			}
		});

		JButton btn_author = new JButton("查作者");
		btn_author.setFont(new Font("宋体", 1, 20));// 设置字体
		btn_author.setVisible(true);
		btn_author.setBackground(Color.white);
		btn_author.setBounds(442, 30, 106, 25);
		jp1.add(btn_author);

		btn_author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = jt1.getText();
				c = client.searchWriter(name);
				BookModel books = new BookModel(c);
				table.setModel(books);
				table.setEnabled(true);
			}
		});

		JButton btn_borrow = new JButton("借阅此书"); // 已设置动作监听器
		btn_borrow.setFont(new Font("宋体", 1, 20));
		btn_borrow.setBounds(300, 400, 120, 25);
		jp1.add(btn_borrow);

		btn_record = new JButton("借书记录"); // 已设置动作监听器
		btn_record.setFont(new Font("宋体", 1, 20));
		btn_record.setBounds(450, 400, 120, 25);
		jp1.add(btn_record);

		btn_record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == btn_record) {

					remove(jp1);
					repaint();
					BookRecordGUI br = new BookRecordGUI(d,customer);
					System.out.println("jilu");
					add(br);
					br.setBounds(0, 0, 700, 500);
				}
			}
		});

		d = client.getInfo();
		String[] columnNames = { "ID", "名称", "作者", "ISBN", "数量" };
		// Object[][] rowData = new Object[50][5];
		/*
		 * JTable booktable=new JTable(rowData,columnNames); booktable.setRowHeight(30);
		 * booktable.setRowMargin(10); JScrollPane scrollPane=new
		 * JScrollPane(booktable); con.add(scrollPane,BorderLayout.SOUTH);
		 * scrollPane.setViewportView(booktable);
		 */

		Vector<String> columnNameV = new Vector<>();
		for (int column = 0; column < columnNames.length; column++) {
			columnNameV.add(columnNames[column]);
		}
		Vector<Vector<String>> tableValues = new Vector<>();
		for (int row = 0; row < d.size(); row++) {
			Vector<String> rowV = new Vector<String>();
			for (int column = 0; column < columnNames.length; column++) {

				switch (column) {
				case 0:
					rowV.add(d.get(row).ID);
					break;
				case 1:
					rowV.add(d.get(row).name);
					break;
				case 2:
					rowV.add(d.get(row).writer);
					break;
				case 3:
					rowV.add(d.get(row).ISBN);
					break;

				case 4:
					rowV.add(d.get(row).amount);
					break;
				}
			}
			tableValues.add(rowV);
		} // 这里调用数据库函数给表格内容赋值
		btn_borrow.addActionListener(new borrow());
		table = new JTable(tableValues, columnNameV);
		table.setRowHeight(30);
		table.setSelectionBackground(Color.ORANGE);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 强制显示滚动条
		scrollPane.setViewportView(table);
		scrollPane.setBounds(30, 100, 600, 300);
		JTableHeader booktableHeader = table.getTableHeader();
		jp1.add(booktableHeader);
		jp1.add(scrollPane);
		System.out.println("建表完成");
		jp1.setVisible(true);
	}
/**
 * 监听借书按钮，发出请求.
 * 
 *
 */
	class borrow implements ActionListener {
		/**
		 * 监听事件.
		 */
		public void actionPerformed(ActionEvent e) {
			selectedID = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
			System.out.println("ID = "+selectedID);
			int ResultCode = 0;
			if (selectedID != -1) {
				String amount=d.get(selectedID-1).amount;
				int bookamount=0;
				try {
                    bookamount = Integer.parseInt(amount);
                   
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                }
				if(bookamount>=1)
				ResultCode = client.addBook(selectorID, selectedID);
				else
					JOptionPane.showMessageDialog(null, "库存不足，借书失败");
			
			}
			if (ResultCode == 1) {
				JOptionPane.showMessageDialog(null, "已成功借书！");
			}
		}
	}

}