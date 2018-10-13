package shitilei;
import java.io.Serializable;
/**
 * user类信息封装.
 *
 */
public class Users implements Serializable{
	private static final long serialVersionUID = -4034120172421752969L;
	public String name=" ";
	public char[] number= {' '};
	public int tag=0;
	public char[] pwd={' '};
	public int money = 0;
public Users(){
	
};
public Users(int t){	
	tag=t;	
}

Users(int t,String n,String num,String p){
	name = n;
	number = num.toCharArray();
	tag = t;
	pwd = p.toCharArray();
	
}
public Users(int t,String n,char[] num,char[] p){
	tag = t;
	name = n;
	number = num;
	pwd = p;
	
}
Users(Users u)
{
this.tag=u.tag;	
this.name=u.name;
this.number=u.number;
this.pwd=u.pwd;
this.money=u.money;
}

}

