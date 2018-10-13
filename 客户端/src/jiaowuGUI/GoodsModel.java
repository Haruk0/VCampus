package jiaowuGUI;
import java.util.ArrayList;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import shitilei.Goods;
/**
 * TableModel类模板
 *
 */
public class GoodsModel extends AbstractTableModel{
	Vector rowData,columnNames;
	ArrayList<Goods> a=new ArrayList<Goods>();
	/**
	 * 构造函数
	 * @param x  Goods类型ArrayList;
	 */
    public GoodsModel(ArrayList<Goods>x) {
    	columnNames=new Vector();
    	columnNames.add("ID");
    	columnNames.add("名称");
    	columnNames.add("价格");
    	columnNames.add("数量");
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
