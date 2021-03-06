package gui;

import gui.create.NoWalletFrame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.Controller;

public class Gui extends JFrame{

	private static final long serialVersionUID = 1L;

	public Gui() throws Exception
	{
		//USE SYSTEM STYLE
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.put("RadioButton.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
        UIManager.put("ComboBox.focus", new Color(0, 0, 0, 0));
		
        //CHECK IF WALLET EXISTS
        if(!Controller.getInstance().doesWalletExists())
        {
        	//OPEN WALLET CREATION SCREEN
        	new NoWalletFrame(this);
        }
        else
        {
        	new MainFrame();
        }
	}
	
	public void onWalletCreated()
	{
		new MainFrame();
	}

	public void onCancelCreateWallet() 
	{
		Controller.getInstance().stopAll();
		System.exit(0);
	}
	
	public static <T extends TableModel> JTable createSortableTable(T tableModel, int defaultSort)
	{
		//CREATE TABLE
		JTable table = new JTable(tableModel);
		
		//CREATE SORTER
		TableRowSorter<T> rowSorter = new TableRowSorter<T>(tableModel);
		//drowSorter.setSortsOnUpdates(true);
		
		//DEFAULT SORT DESCENDING
		rowSorter.toggleSortOrder(defaultSort);	
		rowSorter.toggleSortOrder(defaultSort);	
		
		//ADD TO TABLE
		table.setRowSorter(rowSorter);
		
		//RETURN
		return table;
	}

	public static <T extends TableModel> JTable createSortableTable(T tableModel, int defaultSort, RowFilter<T, Object> rowFilter)
	{
		//CREATE TABLE
		JTable table = new JTable(tableModel);
		
		//CREATE SORTER
		TableRowSorter<T> rowSorter = new TableRowSorter<T>(tableModel);
		//rowSorter.setSortsOnUpdates(true);
		rowSorter.setRowFilter(rowFilter);
		
		//DEFAULT SORT DESCENDING
		rowSorter.toggleSortOrder(defaultSort);	
		rowSorter.toggleSortOrder(defaultSort);	
		
		//ADD TO TABLE
		table.setRowSorter(rowSorter);
		
		//RETURN
		return table;
	}
	
}
