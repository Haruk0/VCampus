package jiaowuGUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import shitilei.Book;
/**
 * TableModelģ��.
 * 
 *
 */
public class BookModel extends AbstractTableModel{
	Vector rowData,columnNames;
	ArrayList<Book> a=new ArrayList<Book>();
	/**
	 * ���캯��.
	 * @param x Book����ArrayList.
	 */
    public BookModel(ArrayList<Book>x) {
    	columnNames=new Vector();
    	columnNames.add("ID");
    	columnNames.add("����");
    	columnNames.add("����");
    	columnNames.add("ISBN");
    	columnNames.add("����");
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
		// TODO �Զ����ɵķ������
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return this.rowData.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO �Զ����ɵķ������
		return (String) this.columnNames.get(column);
	}
	@Override
	public Object getValueAt(int row, int col) {
		// TODO �Զ����ɵķ������
		return ((Vector)this.rowData.get(row)).get(col);
	}
}
