package fangfalei;
import shitilei.Goods;
import shitilei.GoodsRecord;

import java.util.ArrayList;

import connect.connect;
import messageLei.Message;
/**
 * GUI��ť���õ��̵꺯���࣬ͬʱҲ�������˽�����Ϣ��������
 * @author �����,л�ٲ�,������,���﷫.
 * @since JDK1.7.
 */
public class Clientshopfangfa {
	/**
	 * ��̬����myconnectֵΪ{@value}.
	 */
	static connect myconnect=new connect();
	/**
	 * ��ȡ������Ʒ��Ϣ.
	 * @return Goods����ArrayList.
	 */
	public ArrayList<Goods> getAllGoods()
	{
		Message m=new Message("��ȡ��Ʒ�б�");
		ArrayList<Goods> c=new ArrayList<Goods>();
		Message CM=new Message();
	    CM=myconnect.connectServer(m);
		c=handleGoodsMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * ��ȡ���ﳵ��Ϣ.
	 * @param userID String���Ͷ���,�����û�ID.
	 * @return GoodsRecord����ArrayList.
	 */
	public ArrayList<GoodsRecord> getAllGoodsRecord(String userID)
	{
		Message m=new Message("��ȡ���ﳵ�б�");
		m.setSelectorID(userID);
		ArrayList<GoodsRecord> c=new ArrayList<GoodsRecord>();
		Message CM=new Message();
	    CM=myconnect.connectServer(m);
		c=handleGoodsRecordMessage(CM);
		System.out.println("CM = "+CM.getGoodsrecord());
		System.out.println("C = "+c);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * ������Ʒ��Ϣ.
	 * @param goodsname String���Ͷ���,������Ʒ����.
	 * @return Goods����ArrayList.
	 */
	public ArrayList<Goods> searchGoods(String goodsname) 
	{
		ArrayList<Goods> c=new ArrayList<Goods>();
		Message m=new Message();
		m.setGoodsname(goodsname);
		m.setType("ѡ��Ʒ");
		Message rm=new Message();
		rm=myconnect.connectServer(m);
		c=handleGoodsMessage(rm);
		return c;
	}
	/**
	 * ��ѡ����Ʒ���빺�ﳵ.
	 * @param userID String���Ͷ���,�����û�ID.
	 * @param itemid int���Ͷ���,������ƷID.
	 * @param number int���Ͷ���,������Ʒ����.
	 * @return 1.
	 */
	public int addGoods(String userID,int itemid,int number)
	{
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(itemid);
		m.setGoodsnumber(number);
		m.setType("���빺�ﳵ");
		//Message CM=new Message();
		myconnect.connectServer(m);
		/*return handleMessage(CM);*/
		/*int result=CM.getStatusCode();
		if(1==result) 
		{return 1;}
		else
		return 0;*/
		return 1;
	}
	/**
	 * �����������������Ʒ��Ϣ.
	 * @param userID String���Ͷ���,�����û�ID.
	 * @param itemid int���Ͷ���,������ƷID.
	 * @param money int���Ͷ���,���ݽ��׽��.
	 * @return 1��0.
	 */
	public int selectGoods(String userID,int itemid,int money) {
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(itemid);
		m.setMoney(money);
		Message rm=new Message();
		m.setType("����");
        rm=myconnect.connectServer(m);
        int result=rm.getStatusCode();
		if(1==result) 
		{return 1;}
		else
		return 0;
	}
	/**
	 * �����˻����.
	 * @param userID String���Ͷ���,�����û�ID.
	 * @return int ���.
	 */
	public int userMoney(String userID) {
		Message m=new Message();
		m.setSelectorID(userID);
		m.setType("���½��");
		Message M=new Message();
		M=myconnect.connectServer(m);
		int reslut=M.getMoney();
		return reslut;
	}
	/**
	 * ��ȡ���������ݵ�Goods��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return Goods����ArrayList.
	 */
	public ArrayList<Goods> handleGoodsMessage(Message m)
	{
		ArrayList<Goods> c=new ArrayList<Goods>();
		c=m.getGoods();
		return c;
		
	}
	/**
	 * ��ȡ���������ݵ�GoodsRecord��������.
	 * @param m Message���Ͷ���,�ڲ�����������Ϣ.
	 * @return GoodsRecord����ArrayList.
	 */
	public ArrayList<GoodsRecord> handleGoodsRecordMessage(Message m)
	{
		ArrayList<GoodsRecord> c=new ArrayList<GoodsRecord>();
		c=m.getGoodsrecord();
		return c;
		
	}
}
