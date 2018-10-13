package shitilei;

import java.io.Serializable;

public class BookRecord implements Serializable{
    /**
	 * BookRecord类内部包含变量userID，bookID，borrowerDate
	 */
	private static final long serialVersionUID = -7453425519753489342L;
	public String userID;
    public String bookID;
    public String borrowerDate;
}
