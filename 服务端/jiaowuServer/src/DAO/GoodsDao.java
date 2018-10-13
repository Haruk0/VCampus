package DAO;
import java.sql.*;
import java.util.ArrayList;

import DAO.*;
import shitilei.Goods;
import shitilei.GoodsRecord;
public class GoodsDao extends BaseDao{
	public static Connection con=null;
    public static PreparedStatement stt=null;
    public static ResultSet res=null;
    
    /**
     * ��ѯ��Ʒ�б�
     * @return ALc ����������Ʒ
     */
    public ArrayList<Goods>getAllgoods(){
    	ArrayList<Goods> ALc=new ArrayList<Goods>();
    	try {
    	con=getConnection();
    	String sql="select*from tb_goods";
    	stt=con.prepareStatement(sql);
    	res=stt.executeQuery();
    	while(res.next()) {
    		Goods c=new Goods();
        	String s;
        	int i;
        	s=res.getString("g_name");
        	c.setItemname(s);
        	i=res.getInt("g_ID");
        	c.setItemID(i);
        	i=res.getInt("g_amount");
        	c.setItemamount(i);
        	i=res.getInt("g_price");
        	c.setPrice(i);
        	
        	ALc.add(c);
        	
    	  }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	closesql();
		return ALc;
    }
    /**
     * ��ѯ��Ʒ
     * @param name ��Ʒ��
     * @return ALc ��ѯ���
     */
    public  ArrayList<Goods>seachGoods(String name) {
    	ArrayList<Goods> ALc=new ArrayList<Goods>();
    	try {
    	con=getConnection();
		String sql="select*from tb_goods where g_name like = '%"+name+"%'";
		stt=con.prepareStatement(sql);
		res=stt.executeQuery();
		while(res.next()) {
			Goods c=new Goods();
        	c.itemname=res.getString("g_name");
        	c.itemID=res.getInt("g_ID");
        	c.price=res.getInt("g_price");
        	c.itemamount=res.getInt("g_amount");
        	ALc.add(c);
		}
       }catch(Exception e) {
   		e.printStackTrace();
     	}
    	closesql();
		return ALc;
}
}
