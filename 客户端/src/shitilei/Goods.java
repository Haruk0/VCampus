package shitilei;

import java.io.Serializable;
/**
 * Goods类信息封装.
 *
 */
public class Goods implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 537871713815360689L;

	public int price;
	  public int itemID;
	  public int itemamount;
	  public String itemname;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getItemamount() {
		return itemamount;
	}
	public void setItemamount(int itemamount) {
		this.itemamount = itemamount;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	  
}
