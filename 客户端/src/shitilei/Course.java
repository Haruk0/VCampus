package shitilei;

import java.io.Serializable;
/**
 * Course类信息封装.
 *
 */
public class Course  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2147176190475485005L;
	public String teacher;
	public String name;
	public int beginTime;
	public int weekday;
	public int lastTime=2;
	public int courseID;
}
