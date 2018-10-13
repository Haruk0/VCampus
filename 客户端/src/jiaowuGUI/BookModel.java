package jiaowuGUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import shitilei.Book;
/**
 * TableModel模板.
 * 
 *
 */
public class BookModel extends AbstractTableModel{
	Vector rowData,columnNames;
	ArrayList<Book> a=new ArrayList<Book>();
	/**
	 * 构造函数.
	 * @param x Book类型ArrayList.
	 */
    public BookModel(ArrayList<Book>x) {
    	columnNames=new Vector();
    	columnNames.add("ID");
    	columnNames.add("名称");
    	columnNames.add("作者");
    	columnNames.add("ISBN");
    	columnNames.add("数量");
    	rowData=new Vector<Vector>();
    	Vector hang=new Vector();
    	
    	for(int i=0;i<x.size();i++) {
    		hang=new Vector();
    		hang.add(x.get(i).ID);
    		hang.add(x.get(i).name);
    		hang.add(x.get(i).writer);
    		hang.add(x.get(i).ISBN);
    		hang.add(x.get(i).amount);
    		rowData.add(hang);
    	}
    }

	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String) this.columnNames.get(column);
	}
	@Override
	public Object getValueAt(int row, int col) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(row)).get(col);
	}
}
