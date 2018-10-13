package jiaowuGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

import connect.connect;
import shitilei.Users;
/**
 *客户端主体类.
 * @author 宋天昊,谢荣昌,潘振宇,周扬帆,白海云.
 *
 */
public class Client {
	
	static connect myconnect=new connect();
//	static FunctionPanel  frame = new FunctionPanel();
	static MyClien clien=null;
	Users customer=null;
	FunctionPanel  fp=null;
	static Client overAll=null;
	/**
	 * 构造函数.
	 * @param args 主方法参数.
	 */
	public static void main(String[] args) {
		overAll=new Client();
		clien = new MyClien("向服务器送数据",overAll,myconnect);	
	EventQueue.invokeLater(new Runnable() {
			public void run() {
			try {
			 JFrame.setDefaultLookAndFeelDecorated(true);
			 //JDialog.setDefaultLookAndFeelDecorated(true);
			 //UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
			 UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceCremeLookAndFeel");
			 //SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
			} catch (Exception e) {
			}
			}
			});
		
		
	}
	/**
	 * 
	 * @return FunctionPanel类对象
	 */
	FunctionPanel getFunction()
	{
		return fp;
	}
	/**
	 * 登陆界面.
	 */
	public void login() {
		customer=clien.getUser();
		fp = new FunctionPanel(overAll);
		fp.setCustomer(customer);

	}

}
