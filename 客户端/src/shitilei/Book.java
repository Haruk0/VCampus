package shitilei;

import java.io.Serializable;
/**
 * Book����Ϣ��װ.
 *
 */
public class Book implements Serializable{
	private static final long serialVersionUID = -3L;
	//"ID","����","����","ISBN","����"
	public String ID;
	public String name;
	public String writer;
	public String ISBN;
	public String amount;
}
