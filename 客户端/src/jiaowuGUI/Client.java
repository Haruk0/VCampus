package jiaowuGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

import connect.connect;
import shitilei.Users;
/**
 *�ͻ���������.
 * @author �����,л�ٲ�,������,���﷫,�׺���.
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
	 * ���캯��.
	 * @param args ����������.
	 */
	public static void main(String[] args) {
		overAll=new Client();
		clien = new MyClien("�������������",overAll,myconnect);	
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
	 * @return FunctionPanel�����
	 */
	FunctionPanel getFunction()
	{
		return fp;
	}
	/**
	 * ��½����.
	 */
	public void login() {
		customer=clien.getUser();
		fp = new FunctionPanel(overAll);
		fp.setCustomer(customer);

	}

}
