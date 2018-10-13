package jiaowuGUI;

import java.awt.Color;
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
import javax.swing.table.JTableHeader;

import fangfalei.Clientshopfangfa;
import shitilei.Goods;
import shitilei.GoodsRecord;
import shitilei.Users;
/**
 * 购物车界面.
 *
 */
public class ShopRecordGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String YUE = null;
	Clientshopfangfa clint = new Clientshopfangfa();
	ArrayList<Goods> overAllGoodsArray = null;
	String selectorID;
	String[] columnName = null;
	Object[][] rowData = null;
	JTable table;
	int selectedID;
	JPanel jp2;
	int sum = 0;
	String Sum = null;
	Users customer;
	FunctionPanel p=null;
	/**
	 * Create the frame.
	 * @param f FunctionPanel.
	 * @param user Users.
	 */
	public ShopRecordGui(FunctionPanel f,Users user) {
		this.customer = user;
		p=f;
		selectorID = new String(customer.number);

		jp2 = new JPanel();
		jp2.setBounds(0, 0, 700, 500);
		jp2.setLayout(null);
		add(jp2);
		
		createTable();

		

		JLabel lblNewLabel = new JLabel("总计");
		lblNewLabel.setFont(new Font("宋体", 1, 20));
		lblNewLabel.setBounds(30, 400, 55, 25);
		jp2.add(lblNewLabel);

		JLabel summ = new JLabel(Sum);
		summ.setBounds(100, 400, 150, 25);
		summ.setFont(new Font("宋体", 1, 20));
		jp2.add(summ);
		
		JLabel lblNewLabe2 = new JLabel("余额");
		lblNewLabe2.setFont(new Font("宋体",1,20));
		lblNewLabe2.setBounds(145, 400, 55, 25);
		jp2.add(lblNewLabe2);
		
		
		JLabel kehuyue=new JLabel(YUE);
		kehuyue.setBounds(200, 400, 100, 25);
		kehuyue.setFont(new Font("宋体",1,20));
		jp2.add(kehuyue);
		
		JButton queren = new JButton("确认购买");
		queren.setBounds(330, 400, 130, 25);
		queren.setFont(new Font("宋体", 1, 20));
		jp2.add(queren);
		queren.addActionListener(new order());
/*		
		JButton deleteItem = new JButton("删除商品");
		deleteItem.setBounds(480, 400, 130, 25);
		deleteItem.setFont(new Font("宋体", 1, 20));
		jp2.add(deleteItem);
		deleteItem.addActionListener(new delete());
*/		
		jp2.setVisible(true);
	}
/**
 * 初始化数组.
 * @param c Goods类型ArrayList.
 */
	private void setOverAllGoodsArray(ArrayList<Goods> c) {
		overAllGoodsArray = c;

	}
/**
 * 监听确认购买按钮,发出请求.
 *
 */
	class order implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int i = 0;
			int Result = 0;
			for (; i < 50; i++) {
				if (rowData[i][0] == null) {
					break;
				} 
					else {
					try {
						selectedID = (int) table.getValueAt(i, 0);
					}catch(Exception e1) {
			    		e1.printStackTrace();
			    	}
					if(rowData[i][4].equals("未发货")) 
					 Result=clint.selectGoods(selectorID, selectedID,(int) rowData[i][2]);
					 }
				}
			if (Result == 1) {
				JOptionPane.showMessageDialog(null, "购买成功！");
			}
		}
		
/*		class delete implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				int Result = 0;
				for (; i < 50; i++) {
					if (rowData[i][0] == null) {
						break;
					} 
						else {
						try {
							selectedID = (int) table.getValueAt(i, 0);
						}catch(Exception e1) {
				    		e1.printStackTrace();
				    	}
						if(rowData[i][4].equals("未发货")) 
						 Result=clint.selectGoods(selectorID, selectedID,(int) rowData[i][2]);
						 }
					}
				if (Result == 1) {
					JOptionPane.showMessageDialog(null, "购买成功！");
				}
			}
	*/	
	}
/**
 * 构造表格.
 */
	public void createTable() {
		ArrayList<GoodsRecord> cc = new ArrayList<GoodsRecord>(clint.getAllGoodsRecord(selectorID));
		ArrayList<Goods> goods = new ArrayList<Goods>(clint.getAllGoods());
		setOverAllGoodsArray(goods);

		columnName = new String[] { "ID", "名称", "价格", "数量", "状态" };
		rowData = new Object[50][5];
		customer.money=clint.userMoney(selectorID);
		p.setCustomer(customer);

		for (int i = 0; i < cc.size(); i++) {
			int k = 0;
			for (int j = 0; j < 5; j++) {
				switch (j) {
				case 0:
					rowData[i][j] = cc.get(i).selectedID;
					break;
				case 1:
					for (; k < goods.size(); k++) {
						if (goods.get(k).itemID == cc.get(i).selectedID) {
							rowData[i][j] = goods.get(k).itemname;
							break;
						}
					}
					break;
				case 2:
					for (; k < goods.size(); k++) {
						if (goods.get(k).itemID == cc.get(i).selectedID) {
							rowData[i][j] = goods.get(k).price;
							if (cc.get(i).status.equals("未发货") )
								sum += goods.get(k).price;
							System.out.println("sum="+sum);
							break;
						}
					}
					break;
				case 3:
					rowData[i][j] = 1;
					break;
				case 4:
					rowData[i][j] = cc.get(i).status;
				}
			}
		}
		Sum = " " + sum;
		YUE=""+clint.userMoney(selectorID);
		table = new JTable(rowData, columnName);
		table.setRowHeight(30);
		table.setRowMargin(1);
		table.setSelectionBackground(Color.ORANGE);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 500, 300);
		JTableHeader tableHeader = table.getTableHeader();
		jp2.add(tableHeader);
		jp2.add(scrollPane);
		scrollPane.setViewportView(table);
		table.setVisible(true);
	}

}
