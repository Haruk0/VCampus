package DAO;
import java.sql.*;
import java.util.ArrayList;

import DAO.*;
import shitilei.Goods;
import shitilei.GoodsRecord;
public class GoodsRecordDao extends BaseDao {
	public static Connection con=null;
    public static PreparedStatement stt=null;
    public static ResultSet res=null;
    /**
     * 执行添加购物车操作
     * @param userID 用户ID
     * @param itemid 商品ID
     * @param number 商品数量
     */
    public void addGoods(String userID,int itemid,int number) {
    	try {
    		int result=0;
    		con=getConnection();
    		String sql="insert into tb_goodsRecord(gr_buyerID,gr_goodsID,gr_status,gr_amount) values(?,?,'未发货',?)";
    		stt=con.prepareStatement(sql);
    		stt.setString(1, userID);
    		stt.setInt(2, itemid);
    		stt.setInt(3, number);
        	result=stt.executeUpdate();
        	if(result>0) {
        		System.out.println("添加成功");
        	}
        	String sqll="select*from tb_goods where g_ID=?";
        	stt=con.prepareStatement(sqll);
        	stt.setInt(1, itemid);
        	res=stt.executeQuery();
        	
        	res.next();
            result=res.getInt("g_amount");
            result=result-1;
        	String ssql="update tb_goods set g_amount=? where g_ID=?";
        	stt=con.prepareStatement(ssql);
    		stt.setInt(1, result);
    		stt.setInt(2, itemid);
    		stt.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	closesql();
    }
    /**
     * 查看购物车
     * @param userID 用户id
     * @return ALc 购物车商品
     */
    public ArrayList<GoodsRecord>getAllgoodsrecrod(String userID){
    	ArrayList<GoodsRecord> ALc=new ArrayList<GoodsRecord>();
    	try {
    	con=getConnection();
		String sql="select*from tb_goodsRecord where gr_buyerID=?";
		stt=con.prepareStatement(sql);
		stt.setString(1, userID);
    	res=stt.executeQuery();
    	while(res.next()) {
    		GoodsRecord c=new GoodsRecord();
        	String s;
        	int i;
        	s=res.getString("gr_buyerID");
        	c.selectorNumber=s;
        	s=res.getString("gr_status");
        	c.status=s;
        	i=res.getInt("gr_goodsID");
        	c.selectedID=i;
        	i=res.getInt("gr_amount");
        	c.selectedNumber=i;
        	ALc.add(c);
        	//System.out.println("拿到了");
    	  }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	closesql();
		return ALc;
    }
    /**
     * 更新商品状态
     * @param userID 用户Id
     * @param itemid 商品id
     * @return 0/1 操作结果
     */
    public int updateGoodsRecord(String userID,int itemid) {
    	int result=0;
    	try {
    		
    		con=getConnection();
    		String sql="update tb_goodsRecord set gr_status=? where gr_buyerID=? and gr_goodsID=?";
    		stt=con.prepareStatement(sql);
    		stt.setString(1, "发货");
    		stt.setString(2, userID);
    		stt.setInt(3, itemid);
    		result=stt.executeUpdate();
    		if(result>0) {
    			System.out.println("修改成功");
        	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}closesql();
    	if(result>0)
    		return 1;
    	else
    		return 0;
    	
    }
}
