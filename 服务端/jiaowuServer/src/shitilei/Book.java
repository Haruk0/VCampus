package shitilei;

import java.io.Serializable;

public class Book implements Serializable{
/**
 * Book���ڲ���������"ID","����","����","ISBN","����"
 */
	private static final long serialVersionUID = -3L;
	//"ID","����","����","ISBN","����"
	public String ID;
	public String name;
	public String writer;
	public String ISBN;
	public String amount;
}
