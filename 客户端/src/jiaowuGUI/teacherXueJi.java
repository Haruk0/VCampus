package jiaowuGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import fangfalei.Clientfangfa;
import shitilei.Teacher;
import shitilei.Users;
/**
 * 学籍教师界面.
 *
 */
public class teacherXueJi extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String selectorID;
	ArrayList<Teacher> mTeacherArray = null;
	Clientfangfa client = new Clientfangfa();
	FunctionPanel p = null;
	ArrayList<Teacher> c = null;
	JButton btn_choose;
	Users customer = null;
	JPanel jp1;
	public DefaultTableModel tableModel;
/**
 * 构造函数.
 * @param customer Users.
 */
	public teacherXueJi(Users customer) {
		c = new ArrayList<Teacher>(client.getTeacherInfo(customer));
		this.customer = customer;
		this.selectorID = new String(this.customer.number);
		// setTitle("教师信息");
		// setBounds(100,100,1000,200);
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// this.setVisible(true);
		// JPanel jp1 = new JPanel();
		JPanel jp1 = new JPanel();
		add(jp1);
		jp1.setBounds(0, 0, 700, 500);
		jp1.setLayout(null);
		// jp1.setLayout(new BorderLayout());
		//
		// addWindowListener(new WindowAdapter()
		// {
		// public void windowClosing(WindowEvent e)//窗口正处在关闭过程中时调用
		// {
		// p.setVisibleTrue();
		// }
		// });

		String[] columnNames = { "工号", "姓名", "性别", "住址","余额" };
		Vector<String> columnNameV = new Vector<>();
		for (int column = 0; column < columnNames.length; column++) {
			columnNameV.add(columnNames[column]);
		}
		setTeacherArray(c);
		Vector<Vector<String>> tableValues = new Vector<>();
		for (int row = 1; row < 2; row++) {
			Vector<String> rowV = new Vector<String>();
			for (int column = 0; column < columnNames.length; column++) {

				switch (column) {
				case 0:
					rowV.add(c.get(0).number);
					break;
				case 1:
					rowV.add(c.get(0).name);
					break;
				case 2:
					rowV.add(c.get(0).sex);
					break;
				case 3:
					rowV.add(c.get(0).address);
					break;
				case 4:
					rowV.add(""+customer.money);
					break;
				}
			}
			tableValues.add(rowV);
		} // 这里调用数据库函数给表格内容赋值
		JTable table = new MTable(tableValues, columnNameV);
		tableModel = (DefaultTableModel) table.getModel();
		final JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table);
		jp1.add(scroll);
		scroll.setBounds(0, 50, 500, 300);
		table.setAutoResizeMode(1);
		table.setSelectionMode(1);
		table.setSelectionBackground(Color.gray);
		table.setSelectionForeground(Color.white);
		table.setRowHeight(30);
		scroll.setVisible(true);

		btn_choose = new JButton("保存");
		btn_choose.setFont(new Font("宋体", 1, 20));
		btn_choose.setBackground(Color.white);
		jp1.add(btn_choose);
		btn_choose.setBounds(400, 400, 100, 25);
		btn_choose.addActionListener(new btn_chooseAction());

		jp1.setVisible(true);
	}
/**
 * 初始化数组.
 * @param c Teacher类型ArrayList.
 */
	public void setTeacherArray(ArrayList<Teacher> c) {
		mTeacherArray = c;
	}
/**
 * 监听保存按钮,发出请求.
 *
 */
	public class btn_chooseAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			c.get(0).name = ((String) tableModel.getValueAt(0, 1));
			c.get(0).address = ((String) tableModel.getValueAt(0, 3));
			client.updateTeacherInfo(c);
		}

	}
/**
 * 修改表格.
 *
 */
	private class MTable extends JTable {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;
/**
 * 构造函数.
 */
		public MTable(Vector<Vector<String>> tableValues, Vector<String> columnNames) {
			super(tableValues, columnNames);
		}

		/*@Override
		public JTableHeader getTableHeader() {
			JTableHeader tableHeader = super.getTableHeader();
			tableHeader.setReorderingAllowed(false);
			DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
			hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			return tableHeader;

		}

		@Override
		public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
			DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);
			cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			return cr;
		}*/

		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 1 || column == 3) {
				return true;
			} else {
				return false;
			}

		}

	}

}
