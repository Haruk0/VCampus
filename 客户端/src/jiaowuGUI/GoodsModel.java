package jiaowuGUI;
import java.util.ArrayList;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import shitilei.Goods;
/**
 * TableModel��ģ��
 *
 */
public class GoodsModel extends AbstractTableModel{
	Vector rowData,columnNames;
	ArrayList<Goods> a=new ArrayList<Goods>();
	/**
	 * ���캯��
	 * @param x  Goods����ArrayList;
	 */
    public GoodsModel(ArrayList<Goods>x) {
    	columnNames=new Vector();
    	columnNames.add("ID");
    	columnNames.add("����");
    	columnNames.add("�۸�");
    	columnNames.add("����");
    	rowData=new Vector<Vector>();
    	Vector hang=new Vector();
    	
    	for(int i=0;i<x.size();i++) {
    		hang=new Vector();
    		hang.add(x.get(i).itemID);
    		hang.add(x.get(i).itemname);
    		hang.add(x.get(i).price);
    		hang.add(x.get(i).itemamount);
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
