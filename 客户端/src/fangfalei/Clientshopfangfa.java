package fangfalei;
import shitilei.Goods;
import shitilei.GoodsRecord;

import java.util.ArrayList;

import connect.connect;
import messageLei.Message;
/**
 * GUI按钮调用的商店函数类，同时也是与服务端进行信息交互的类
 * @author 宋天昊,谢荣昌,潘振宇,周扬帆.
 * @since JDK1.7.
 */
public class Clientshopfangfa {
	/**
	 * 静态变量myconnect值为{@value}.
	 */
	static connect myconnect=new connect();
	/**
	 * 获取所有商品信息.
	 * @return Goods类型ArrayList.
	 */
	public ArrayList<Goods> getAllGoods()
	{
		Message m=new Message("获取商品列表");
		ArrayList<Goods> c=new ArrayList<Goods>();
		Message CM=new Message();
	    CM=myconnect.connectServer(m);
		c=handleGoodsMessage(CM);
		/*return handleMessage(CM);*/
		return c;
	}
	/**
	 * 获取购物车信息.
	 * @param userID String类型对象,传递用户ID.
	 * @return GoodsRecord类型ArrayList.
	 */
	public ArrayList<GoodsRecord> getAllGoodsRecord(String userID)
	{
		Message m=new Message("获取购物车列表");
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
	 * 搜素商品信息.
	 * @param goodsname String类型对象,传递商品名称.
	 * @return Goods类型ArrayList.
	 */
	public ArrayList<Goods> searchGoods(String goodsname) 
	{
		ArrayList<Goods> c=new ArrayList<Goods>();
		Message m=new Message();
		m.setGoodsname(goodsname);
		m.setType("选商品");
		Message rm=new Message();
		rm=myconnect.connectServer(m);
		c=handleGoodsMessage(rm);
		return c;
	}
	/**
	 * 将选中商品加入购物车.
	 * @param userID String类型对象,传递用户ID.
	 * @param itemid int类型对象,传递商品ID.
	 * @param number int类型对象,传递商品数量.
	 * @return 1.
	 */
	public int addGoods(String userID,int itemid,int number)
	{
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(itemid);
		m.setGoodsnumber(number);
		m.setType("加入购物车");
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
	 * 向服务器发出购买商品信息.
	 * @param userID String类型对象,传递用户ID.
	 * @param itemid int类型对象,传递商品ID.
	 * @param money int类型对象,传递交易金额.
	 * @return 1或0.
	 */
	public int selectGoods(String userID,int itemid,int money) {
		Message m=new Message();
		m.setSelectorID(userID);
		m.setSelectedID(itemid);
		m.setMoney(money);
		Message rm=new Message();
		m.setType("购买");
        rm=myconnect.connectServer(m);
        int result=rm.getStatusCode();
		if(1==result) 
		{return 1;}
		else
		return 0;
	}
	/**
	 * 更新账户余额.
	 * @param userID String类型对象,传递用户ID.
	 * @return int 余额.
	 */
	public int userMoney(String userID) {
		Message m=new Message();
		m.setSelectorID(userID);
		m.setType("更新金额");
		Message M=new Message();
		M=myconnect.connectServer(m);
		int reslut=M.getMoney();
		return reslut;
	}
	/**
	 * 获取服务器传递的Goods类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return Goods类型ArrayList.
	 */
	public ArrayList<Goods> handleGoodsMessage(Message m)
	{
		ArrayList<Goods> c=new ArrayList<Goods>();
		c=m.getGoods();
		return c;
		
	}
	/**
	 * 获取服务器传递的GoodsRecord类型数组.
	 * @param m Message类型对象,内部包括各种信息.
	 * @return GoodsRecord类型ArrayList.
	 */
	public ArrayList<GoodsRecord> handleGoodsRecordMessage(Message m)
	{
		ArrayList<GoodsRecord> c=new ArrayList<GoodsRecord>();
		c=m.getGoodsrecord();
		return c;
		
	}
}
