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
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import fangfalei.Clientshopfangfa;
import shitilei.Goods;
import shitilei.Users;
/**
 * 商店主界面.
 *
 */
public class ShopGui extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2938723590300979826L;
	static JTable goodstable;
	Clientshopfangfa clint = new Clientshopfangfa();
	int selectedID;
	String selectorID;
	ArrayList<Goods> overAllGoodsArray = null;
	static JTable gouwu;
	JButton gouwuche, addgoods, search;
	JTextField jt1;
	String name = null;
	Users customer;
	JPanel jp1 = null;
	FunctionPanel p=null;

	/**
	 * Create the frame.
	 */
	public ShopGui(FunctionPanel frame,Users customer) {

		p=frame;
		this.customer = customer;
		selectorID = new String(this.customer.number);
		// JFrame jf1=new JFrame("商店界面");//jf1
		// jf1.setBounds(100,100,600,500);
		// jf1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// jf1.setVisible(true);
		// jf1.getContentPane().setLayout(null);

		jp1 = new JPanel();
		jp1.setBounds(0, 0, 584, 461);
		add(jp1);
		jp1.setLayout(null);

		// jf1.addWindowListener(new WindowAdapter()
		// {
		// public void windowClosing(WindowEvent e)//窗口正处在关闭过程中时调用
		// {
		// p.setVisibleTrue();
		// }
		// });

		JLabel jl1 = new JLabel("商品名称");
		jl1.setBounds(20, 20, 120, 30);
		jp1.add(jl1);
		jl1.setFont(new Font("宋体", 1, 20));

		search = new JButton("搜索");
		search.setBounds(400, 20, 150, 25);
		search.setFont(new Font("宋体", 1, 20));
		jp1.add(search);
		search.addActionListener(new searchgoods());

		addgoods = new JButton("加入购物车");
		addgoods.setBounds(200, 400, 150, 25);
		addgoods.setFont(new Font("宋体", 1, 20));
		jp1.add(addgoods);
		addgoods.addActionListener(new addrecord());

		gouwuche = new JButton("购物车");
		gouwuche.setBounds(400, 400, 150, 25);
		gouwuche.setFont(new Font("宋体", 1, 20));
		jp1.add(gouwuche);
		gouwuche.addActionListener(this);

		jt1 = new JTextField();
		jt1.setBounds(150, 20, 190, 30);
		jp1.add(jt1);
		jt1.setFont(new Font("宋体", 1, 20));

		ArrayList<Goods> c = new ArrayList<Goods>(clint.getAllGoods());
		setOverAllGoodsArray(c);

		String[] columnNames = new String[] { "ID", "名称", "价钱", "数量" };
		Object[][] rowData = new Object[50][4];
		for (int i = 0; i < c.size(); i++) {
			for (int j = 0; j < 4; j++) {
				switch (j) {
				case 0:
					rowData[i][j] = c.get(i).itemID;
					break;
				case 1:
					rowData[i][j] = c.get(i).itemname;
					break;
				case 2:
					rowData[i][j] = c.get(i).price;
					break;
				case 3:
					rowData[i][j] = c.get(i).itemamount;
					break;
				}
			}
		}
		goodstable = new JTable(rowData, columnNames);
		goodstable.setRowHeight(30);
		goodstable.setRowMargin(1);
		JScrollPane scrollPane = new JScrollPane(goodstable);
		scrollPane.setViewportView(goodstable);
		scrollPane.setBounds(30, 80, 500, 300);
		goodstable.setSelectionBackground(Color.ORANGE);
		JTableHeader tableHeader = goodstable.getTableHeader();
		jp1.add(tableHeader);
		jp1.add(scrollPane);

		/*
		 * gouwu=new JTable(rowData,columnNames); gouwu.setRowHeight(30);
		 * gouwu.setRowMargin(10); JScrollPane scrollPane1=new JScrollPane(gouwu);
		 * getContentPane().add(scrollPane1); jf1.add(scrollPane1,BorderLayout.CENTER);
		 * scrollPane1.setViewportView(gouwu);
		 */

	}
/**
 * 初始化数组.
 * @param c Goods类型ArrayList.
 */
	public void setOverAllGoodsArray(ArrayList<Goods> c) {
		overAllGoodsArray = c;
	}
/**
 * 监听加入购物车按钮,发出请求.
 *
 */
	class addrecord implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			selectedID = (int) goodstable.getValueAt(goodstable.getSelectedRow(), 0);
			int ResultCode = 0;
			if (selectedID != 0) {
				if (goodstable.getValueAt(goodstable.getSelectedRow(), 1) == null)
					JOptionPane.showMessageDialog(null, "请选择商品！");
				else {
					if(overAllGoodsArray.get(selectedID-1).itemamount>=1) {
					ResultCode = clint.addGoods(selectorID, selectedID, 1);
					}
					else {
						JOptionPane.showMessageDialog(null, "库存不够，添加购物车失败");
					}
				}}else {
				JOptionPane.showMessageDialog(null, "请选择商品！");
			 }
				
			if (ResultCode == 1) 
				JOptionPane.showMessageDialog(null, "已成功加入购物车！");
			
		}
	}
	/**
	 * 监听搜索按钮,发出请求.
	 *
	 */
	class searchgoods implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == search) {
				if(jt1.getText()=="") {
					JOptionPane.showMessageDialog(null, "请输入搜索内容！");
				}
				else {
				name = jt1.getText().trim();

				ArrayList<Goods> c = new ArrayList<Goods>();
				c = clint.searchGoods(name);
				GoodsModel goods = new GoodsModel(c);
				goodstable.setModel(goods);
				goodstable.setEnabled(true);}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == gouwuche) {

			remove(jp1);
			repaint();
			ShopRecordGui br = new ShopRecordGui(p,customer);
			add(br);
			br.setBounds(0, 0, 700, 500);
		}
	}
}
