package shitilei;

import java.io.Serializable;

public class Book implements Serializable{
/**
 * Book类内部包含变量"ID","名称","作者","ISBN","数量"
 */
	private static final long serialVersionUID = -3L;
	//"ID","名称","作者","ISBN","数量"
	public String ID;
	public String name;
	public String writer;
	public String ISBN;
	public String amount;
}
