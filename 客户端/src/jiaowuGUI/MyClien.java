package jiaowuGUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connect.connect;
import messageLei.Message;
import shitilei.Users;
/**
 * ��½����GUI.
 *
 */
public class MyClien extends JFrame {

	private static final long serialVersionUID = 5083347601496340068L;
	connect myconnect = null;
	char[] number = new char[] {};
	char[] pwd = new char[] {};
	String npwd;
	String num;
	Message ansMessage = new Message("��ʼ������");
	Socket socket;
	Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
	int wid = scrSize.width;
	int hei = scrSize.height;
	String type;
	Login frame;

	Client client = null;
	static Users customer = new Users(0);
/**
 * ���캯��.
 * @param title String���Ͷ���.
 * @param cli ���������.
 * @param con connect����.
 */
	public MyClien(String title, Client cli, connect con) {
		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// try {
		myconnect = con;
		client = cli;
		frame = new Login();
		frame.setVisible(true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });
	}

	// public static void main(String[] args) {
	// MyClien clien = new MyClien("�������������");
	// }
/**
 * GUI.
 *
 */
	class Login extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JPasswordField passwordField;
/**
 * ���캯��.
 */
		public Login() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setTitle("��¼����");
			setContentPane(contentPane);
			contentPane.setLayout(null);
			// contentPane.setBackground(Color.LIGHT_GRAY);

			final JTextField name = new JTextField();
			name.setBounds(170, 120, 170, 25);
			contentPane.add(name);

			JButton login = new JButton("��¼");
			login.setBounds(100, 220, 93, 25);
			login.setFont(new Font("����", 1, 20));
			contentPane.add(login);
			// �س�������½
			this.getRootPane().setDefaultButton(login);

			login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					number = name.getText().toCharArray();
					customer.number = number;
					pwd = passwordField.getPassword();
					String p = new String(pwd);

					String np = encryption(p);

					Users d = new Users(0, "�����", number, np.toCharArray());
					Message u = new Message("�����½");
					u.setUser(d);
					Message u1 = myconnect.connectServer(u);
					System.out.println("u1 = " + u1.getType());
					dealServerMessage(u1);
				}
			});

			JLabel label = new JLabel("�û���");
			label.setBounds(70, 120, 90, 25);
			label.setFont(new Font("����", 1, 20));
			contentPane.add(label);

			JLabel label_1 = new JLabel("\u5BC6\u7801"); // ����
			label_1.setBounds(70, 165, 90, 25);
			label_1.setFont(new Font("����", 1, 20));
			contentPane.add(label_1);

			JButton xiugaimima = new JButton("�޸�����");
			xiugaimima.setBounds(250, 220, 125, 25);
			xiugaimima.setFont(new Font("����", 1, 20));
			contentPane.add(xiugaimima);
			xiugaimima.addActionListener(new xiugaimimaAction());

			passwordField = new JPasswordField(); // �����
			passwordField.setBounds(170, 165, 170, 25);
			contentPane.add(passwordField);

		}

		// �޸�������Ķ���������
		/**
		 * �����޸����밴ť,��������.
		 *
		 */
		class xiugaimimaAction implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				new Xiugai(Login.this).setVisible(true);
			}
		}

	}

	// �޸�����Ľ���
	/**
	 * �޸��������.
	 *
	 */
	class Xiugai extends JDialog implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
/**
 * ���캯��.
 * @param contentPane Login����.
 */
		public Xiugai(Login contentPane) {
			super(contentPane, "�޸�����", true);
			Container container = getContentPane();
			setBounds(100, 100, 450, 300);
			setLayout(new GridLayout(4, 1, 0, 0));
			setTitle("�޸��������");
			JPanel jp1 = new JPanel();
			JPanel jp2 = new JPanel();
			JPanel jp3 = new JPanel();
			JPanel jp4 = new JPanel();

			container.add(jp1);
			container.add(jp2);
			container.add(jp3);
			container.add(jp4);

			jp1.setLayout(new GridLayout(1, 2, 5, 5));
			jp2.setLayout(new GridLayout(1, 2, 5, 5));
			jp3.setLayout(new GridLayout(1, 2, 5, 5));

			JLabel label = new JLabel("�û���");
			label.setFont(new Font("����", 1, 20));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			jp1.add(label);
			final JTextField name = new JTextField();
			name.setFont(new Font("����", 1, 20));
			jp1.add(name);

			JLabel label1 = new JLabel("������");
			label1.setFont(new Font("����", 1, 20));
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			jp2.add(label1);
			final JPasswordField password = new JPasswordField();
			password.setFont(new Font("����", 1, 20));
			jp2.add(password);

			JLabel label2 = new JLabel("������");
			label2.setFont(new Font("����", 1, 20));
			label2.setHorizontalAlignment(SwingConstants.CENTER);
			jp3.add(label2);
			final JPasswordField newpassword = new JPasswordField();
			newpassword.setFont(new Font("����", 1, 20));
			jp3.add(newpassword);

			JButton ok = new JButton("ȷ��");
			ok.setFont(new Font("����", 1, 20));
			jp4.add(ok);
			// �س�����ȷ��
			this.getRootPane().setDefaultButton(ok);
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					number = name.getText().toCharArray();
					num = name.getText();
					pwd = password.getPassword();
					String p = new String(pwd);
					// System.out.println("p = "+p);
					String np = encryption(p);
					npwd = new String(newpassword.getPassword());
					Users d = new Users(0, "�����", number, np.toCharArray());
					Message u = new Message("�����޸�����");
					u.setUser(d);
					Message u1 = myconnect.connectServer(u);
					System.out.println("u1 = " + u1.getType());
					dealServerMessage(u1);

				}
			});
		}

		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������

		}

	}

	class ErrorPwd extends JDialog {

		private static final long serialVersionUID = -2285746480484622569L;

		ErrorPwd(JPanel contentPane) {
			JOptionPane.showMessageDialog(contentPane, "�û������������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
		}
	}

	class ChangeSuccess extends JDialog {

		private static final long serialVersionUID = 8274464999367060545L;

		ChangeSuccess(JPanel contentPane) {
			JOptionPane.showMessageDialog(contentPane, "�޸�����ɹ�");
		}
	}
/**
 * ���
 * @param um Message�����.
 */
	void dealServerMessage(Message um) {
		switch (um.getType()) {
		case "��½�ɹ�":
			frame.setVisible(false);// ������½��ҳ��
			if (um.getUser().tag == 1) {
				System.out.println("��ѧ����ݵ�½");
			}
			;
			if (um.getUser().tag == 2) {
				System.out.println("�Խ�ʦ��ݵ�½");
			}
			;
			customer = um.getUser();
			client.login();
			break;

		case "�û������������":
			// ����������ʾ
			ErrorPwd ep = new ErrorPwd(frame.contentPane);
			ep.setVisible(true);
			break;
		case "�����޸�����":
			/* UserDAO ud = new UserDAO(); */
			// System.out.println("npwd = "+npwd);
			try {
				String np = encryption(npwd);
				// System.out.println("np = "+np);

				Users d = new Users(0, "�����", number, np.toCharArray());
				Message u = new Message("�޸�����");
				u.setUser(d);
				Message u1 = new Message(myconnect.connectServer(u));
				System.out.println("u1 = " + u1.getType());
				dealServerMessage(u1);
				// ud.changePwd(num, np);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "�޸��������":
			ChangeSuccess cs = new ChangeSuccess(frame.contentPane);
			cs.setVisible(true);
			break;
		}
	}

	public static String stringToAscii(String value) {
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (i != chars.length - 1) {
				sbu.append((int) chars[i]).append(",");
			} else {
				sbu.append((int) chars[i]);
			}
		}
		return sbu.toString();
	}

	public static String asciiToString(String value) {
		StringBuffer sbu = new StringBuffer();
		String[] chars = value.split(",");
		for (int i = 0; i < chars.length; i++) {
			sbu.append((char) Integer.parseInt(chars[i]) + 8);
		}
		return sbu.toString();
	}

	String encryption(String pwd) {
		String pwdA = stringToAscii(pwd);
		return asciiToString(pwdA);
	}

	Users getUser() {
		return customer;
	}
}