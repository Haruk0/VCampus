package jiaowuGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import fangfalei.Clientfangfa;
import shitilei.CourseRecord;
import shitilei.Users;
/**
 * 教务教师界面.
 *
 */
public class Teacherjiaowu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 660156047218524435L;
	public TableModel tableModel = null;
	Users customer = null;
/**
 * 构造函数.
 * @param customer Users.
 */
	public Teacherjiaowu(Users customer) {
		this.customer = customer;
		final Clientfangfa client = new Clientfangfa();

		JPanel p4 = new JPanel();
		add(p4);
		p4.setBounds(0, 0, 700, 500);
		p4.setLayout(null);

		JLabel jl1 = new JLabel("欢迎  老师");
		jl1.setFont(new Font("宋体", 1, 20));
		jl1.setBounds(30, 30, 200, 25);
		// URL url=Teacherjiaowu.class.getResource("123.jpg");
		// Icon icon=new ImageIcon(url);
		// jl1.setIcon(icon);
		p4.add(jl1);

		JLabel jl2 = new JLabel("您开设的课程为： 现代摸鱼技术");
		jl2.setFont(new Font("宋体", 1, 20));
		jl2.setBounds(30, 60, 500, 25);
		p4.add(jl2);

		JButton jb3 = new JButton("修改分数");
		jb3.setBounds(400, 30, 100, 25);
		p4.add(jb3);

		ArrayList<CourseRecord> cr = new ArrayList<CourseRecord>(client.getAllStudents(2));
		String[] columnNames = new String[] { "学号", "分数" };
		Object[][] rowData = new Object[50][2];
		for (int i = 0; i < cr.size(); i++) {
			for (int j = 0; j < 3; j++) {
				switch (j) {
				case 0:
					rowData[i][j] = cr.get(i).selectorNumber;
					break;

				case 1:
					rowData[i][j] = cr.get(i).score;
					break;

				}
			}
		}
		final JTable dafentable = new JTable(rowData, columnNames);
		// int arrayID = dafentable.getSelectedRow();
		tableModel = (TableModel) dafentable.getModel();
		dafentable.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(dafentable);
		scrollPane.setBounds(30, 90, 500, 300);
		p4.add(scrollPane);

		jb3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int arrayID = dafentable.getSelectedRow();
				// System.out.println(arrayID);
				// String s=((String)tableModel.getValueAt(arrayID, 0 ));
				// System.out.println(s);
				// System.out.println(i);
				int resultCode = client.score(2, ((String) tableModel.getValueAt(arrayID, 0)),
						Integer.valueOf((String) tableModel.getValueAt(arrayID, 1)).intValue());
				if (resultCode == 1)
					JOptionPane.showMessageDialog(null, "已成功打分！");
				else
					JOptionPane.showMessageDialog(null, "打分失败！");

			}
		});

	}

	/*
	 * class jb3Aciton implements ActionListener { Clientfangfa overAllClient=new
	 * Clientfangfa(); TableModel tableModel=null; int arrayID; jb3Aciton
	 * (Clientfangfa c,TableModel t,int i) { overAllClient=c; tableModel=t;
	 * arrayID=i; } public void actionPerformed(ActionEvent arg0) { int
	 * arrayID=dafentable.getSelectedRow(); int
	 * resultCode=overAllClient.score(2,(String)tableModel.getValueAt(arrayID, 0
	 * ),(int)tableModel.getValueAt(arrayID, 1)); if(resultCode==1)
	 * JOptionPane.showMessageDialog(null, "已成功打分！"); else
	 * JOptionPane.showMessageDialog(null, "打分失败！");
	 * 
	 * }
	 */

	/*
	 * void set(Clientfangfa c,TableModel t,int i) { overAllClient=c; tableModel=t;
	 * arrayID=i; }
	 */

	// public static void main(String args[]) {
	// new Teacherjiaowu();
	// }

}
