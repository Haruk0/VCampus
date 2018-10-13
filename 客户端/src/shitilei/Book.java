package shitilei;

import java.io.Serializable;
/**
 * Book类信息封装.
 *
 */
public class Book implements Serializable{
	private static final long serialVersionUID = -3L;
	//"ID","名称","作者","ISBN","数量"
	public String ID;
	public String name;
	public String writer;
	public String ISBN;
	public String amount;
}
