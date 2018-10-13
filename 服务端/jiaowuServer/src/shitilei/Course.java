package shitilei;

import java.io.Serializable;

public class Course  implements Serializable {

	/**
	 * Course类内部包含变量teacher，name，beginTime，weekday，lastTime，courseID
	 */
	private static final long serialVersionUID = -2147176190475485005L;
	public String teacher;
	public String name;
	public int beginTime;
	public int weekday;
	public int lastTime=2;
	public int courseID;
}
