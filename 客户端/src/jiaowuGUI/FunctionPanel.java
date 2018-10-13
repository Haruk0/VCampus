package jiaowuGUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import shitilei.Users;
/**
 * 主界面GUI
 *
 */
public class FunctionPanel {

	private JPanel contentPane;
	JButton btn_course = null;
	JButton btn_persion = null;
	JButton btn_library = null;
	JButton btn_store = null;
	Users customer = null;
	JPanel overAllJPanel = null;
	Client overAllClient=null;
	JLabel tupian;
	Icon icon;
	String skinprefix = "org.jvnet.substance.skin.";
	String[] skin = { "SubstanceAutumnLookAndFeel.class", "SubstanceBusinessBlackSteelLookAndFeel.class",
			"SubsusinessBlueSteelLookAndFeel.class", "SubstanceBusinessLookAndFeel.class",
			"SubstanceChallengerDeepLookAndFeel.class", "SubstanceCremeLookAndFeel.class",
			"SubstanceEmeraldDuskLookAndFeel.class", "SubstanceFieldOfWheatLookAndFeel.class",
			"SubstanceGreenMagicLookAndFeel.class", "SubstanceMagmaLookAndFeel.class",
			"SubstanceMangoLookAndFeel.class", "SubstanceMistAquaLookAndFeel.class",
			"SubstanceMistSilverLookAndFeel.class", "SubstanceModerateLookAndFeel.class",
			"SubstanceNebulaBrickWallLookAndFeel.class", "SubstanceNebulaLookAndFeel.class",
			"SubstanceOfficeBlue2007LookAndFeel.class", "SubstanceOfficeSilver2007LookAndFeel.class",
			"SubstanceRavenGraphiteGlassLookAndFeel.class", "SubstanceRavenGraphiteLookAndFeel.class",
			"SubstanceRavenLookAndFeel.class", "SubstanceSaharaLookAndFeel.class" };
	
	@SuppressWarnings("rawtypes")
	JComboBox comboBox = new JComboBox<>(new String[] { "SubstanceAutumnLookAndFeel",
			"SubstanceBusinessBlackSteelLookAndFeel", "SubstanceBusinessBlueSteelLookAndFeel",
			"SubstanceBusinessLookAndFeel", "SubstanceChallengerDeepLookAndFeel", "SubstanceCremeLookAndFeel",
			"SubstanceEmeraldDuskLookAndFeel", "SubstanceFieldOfWheatLookAndFeel", "SubstanceGreenMagicLookAndFeel",
			"SubstanceMagmaLookAndFeel", "SubstanceMangoLookAndFeel", "SubstanceMistAquaLookAndFeel",
			"SubstanceMistSilverLookAndFeel", "SubstanceModerateLookAndFeel", "SubstanceNebulaBrickWallLookAndFeel",
			"SubstanceNebulaLookAndFeel", "SubstanceOfficeBlue2007LookAndFeel",
			"SubstanceOfficeSilver2007LookAndFeel", "SubstanceRavenGraphiteGlassLookAndFeel",
			"SubstanceRavenGraphiteLookAndFeel", "SubstanceRavenLookAndFeel", "SubstanceSaharaLookAndFeel" });
	
	String skinstr;
/**
 * 构造函数.
 * @param c 客户端对象.
 */
	public FunctionPanel(Client c) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					//UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
					UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceCremeLookAndFeel");
				} catch (Exception e) {
				}
			}
		});
		
		

		overAllClient=c;
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setSize(800, 530);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1, 3, 3));
		panel.setBounds(0, 0, 90, 500);
		contentPane.add(panel);

		btn_persion = new JButton("信息管理");
		// btn_persion.setIcon(new ImageIcon("library.png"));
		panel.add(btn_persion);
		btn_persion.setBorderPainted(false);

		btn_course = new JButton("教务管理");
		// btn_course.setFont(new Font("宋体",1,20));//设置字体
		btn_course.setBorderPainted(false);// 无边框
		// btn_course.setIcon(new ImageIcon("1.png"));
		// 设置按钮与图片大小相同btn_course.setMaximumSize(new Dimension(90,30));
		panel.add(btn_course);

		btn_library = new JButton("网上图书");
		btn_library.setBorderPainted(false);
		// btn_library.setFont(new Font("宋体",1,20));
		// btn_library.setIcon(new ImageIcon("2.png"));
		panel.add(btn_library);

		btn_store = new JButton("网上商店");
		btn_store.setBorderPainted(false);
		// btn_store.setFont(new Font("宋体",1,20));
		// btn_store.setIcon(new ImageIcon("2.png"));
		panel.add(btn_store);

		JLabel label = new JLabel("皮肤:");
		label.setFont(new Font("宋体", 1, 18));
		label.setBounds(0, 520, 50, 25);
		contentPane.add(label);
		
//		tupian=new JLabel();
//		icon=new ImageIcon("1.jpg");
//		tupian.setBounds(100, 0, icon.getIconWidth(),icon.getIconHeight());
//		contentPane.add(tupian, new Integer(Integer.MIN_VALUE));

		
		comboBox.setBounds(50, 520, 150, 25);
		contentPane.add(comboBox);

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					System.out.println("选择的值：" + comboBox.getSelectedItem());
					skinstr = skinprefix + comboBox.getSelectedItem();
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							try {
								JFrame.setDefaultLookAndFeelDecorated(true);
								UIManager.setLookAndFeel(skinstr);

							} catch (Exception e) {
							}
						}
					});
				}
			}
		});

		btn_persion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (overAllJPanel != null)
					contentPane.remove(overAllJPanel);
				if (customer.tag == 1) {
					studentXueJi s = new studentXueJi(customer);
					overAllJPanel = s;
					contentPane.add(s);
					s.setBounds(100, 0, 700, 500);
				}
				if (customer.tag == 2) {
					teacherXueJi t = new teacherXueJi(customer);
					overAllJPanel = t;
					contentPane.add(t);
					t.setBounds(100, 0, 700, 500);
				}
			}
		});

		btn_library.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (overAllJPanel != null)
					contentPane.remove(overAllJPanel);
				BookSearch bs = new BookSearch(customer);
				overAllJPanel = bs;
				contentPane.add(bs);
				bs.setBounds(100, 0, 700, 500);

			}
		});

		btn_store.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (overAllJPanel != null)
					contentPane.remove(overAllJPanel);
				ShopGui sh = new ShopGui(overAllClient.fp,customer);
				overAllJPanel = sh;
				contentPane.add(sh);
				sh.setBounds(100, 0, 700, 500);

			}
		});

		btn_course.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (overAllJPanel != null)
					contentPane.remove(overAllJPanel);
				if (customer.tag == 1) {
					studentGUI s = new studentGUI(customer);
					overAllJPanel = s;
					contentPane.add(s);
					s.setBounds(100, 0, 700, 500);
				}
				if (customer.tag == 2) {
					Teacherjiaowu t = new Teacherjiaowu(customer);
					overAllJPanel = t;
					contentPane.add(t);
					t.setBounds(100, 0, 700, 500);
				}
			}
		});

		frame.setVisible(true);
	}
/**
 * 返回更新User
 * @param u User类对象.
 */
	void setCustomer(Users u) {
		customer = u;
	}
}
