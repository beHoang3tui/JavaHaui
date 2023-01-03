package KTTX2Java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public class ViewHousing extends JFrame {
	
	public static void OutputFile(List<Housing> housings) throws IOException {
		try {
			// Xác định đối tượng tệp tin để xuất dữ liệu
			FileOutputStream outFile = new FileOutputStream("housing.bin");
			// Khai báo đối tượng thực hiện xuất
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			// Xuất dữ liệu ra file
			for (Housing hs : housings) {
				System.out.println(hs.getProduct_price());
				out.writeObject(hs);
			}

			out.close(); // Đóng đối tượng thực thi
		} catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static long OutputSortPriceFile(List<Housing> housings , boolean isINC) throws IOException, ClassNotFoundException {

		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileInputStream inFile = new FileInputStream("housing.bin");
		// Khai báo đối tượng thực hiện xuất
		ObjectInputStream in = new ObjectInputStream(inFile);
		
		List<Housing> housingsSort = new ArrayList<>();
		for (Housing hs : housings) {
			hs = (Housing) in.readObject();
			housingsSort.add(hs);
		}
		in.close();
		
		if(isINC) {
			Collections.sort(housings , new sortedByPrice());
		}
		else {
			Collections.sort(housings , new sortedByPrice().reversed());
		}

		// Xác định đối tượng tệp tin để cập nhật dữ liệu ngẫu nhiên
		RandomAccessFile raf = new RandomAccessFile("housing.bin", "rw");
		
		// Di chuyển xuống cuối file để thêm 
		raf.seek(raf.length());
		
		long pos = raf.length();

		// Xuất dữ liệu ra file
		for (Housing hs : housingsSort) {
			raf.writeUTF(hs.toString());
		}
		

		raf.close(); // Đóng đối tượng thực thi
		return pos;
	}
	
	static List<Housing> housings = new ArrayList<>();
	HousingManagerImpl housingImpl = new HousingManagerImpl();
	DefaultTableModel dtm ;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHousing frame = new ViewHousing();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void loadData() {
		dtm.setRowCount(0);
		for (Housing hs : housings) {
			Vector<Object> vec = new Vector<>();
			vec.add(hs.getProduct_id());
			vec.add(hs.getProduct_name());
			vec.add(hs.getProduct_price());
			vec.add(hs.getProduct_total());
			vec.add(hs.getFeatured());
			vec.add(hs.getArea());
			dtm.addRow(vec);
		}
	}
	/**
	 * Create the frame.
	 */
	public ViewHousing() {
		JFrame f = new JFrame();
        f.setTitle("Housing List");
		
        
        // input ID
        JLabel lbID = new JLabel("ID :");
        lbID.setBounds(20, 12, 144, 26);
        f.getContentPane().add(lbID);
        
        JTextField txt1 = new JTextField();
        txt1.setBounds(110, 12, 260, 23);
        f.getContentPane().add(txt1);
        
        // input name
        JLabel lbName = new JLabel("Name :");
        lbName.setBounds(20, 42, 144, 26);
        f.getContentPane().add(lbName);
        
        JTextField txt2 = new JTextField();
        txt2.setBounds(110, 42, 260, 23);
        f.getContentPane().add(txt2);
		
     // input price
        JLabel lbPrice = new JLabel("Price :");
        lbPrice.setBounds(20, 72, 144, 26);
        f.getContentPane().add(lbPrice);
        
        JTextField txt3 = new JTextField();
        txt3.setBounds(110, 72, 260, 23);
        f.getContentPane().add(txt3);
        
     // input total
        JLabel lbTotal = new JLabel("Total :");
        lbTotal.setBounds(382, 12, 144, 26);
        f.getContentPane().add(lbTotal);
        
        JTextField txt4 = new JTextField();
        txt4.setBounds(472, 12, 260, 23);
        f.getContentPane().add(txt4);
        
     // input Featured
        JLabel lbFeatured = new JLabel("Featured :");
        lbFeatured.setBounds(382, 42, 144, 26);
        f.getContentPane().add(lbFeatured);
        
        JTextField txt5 = new JTextField();
        txt5.setBounds(472, 42, 260, 23);
        f.getContentPane().add(txt5);
        
     // input Area
        JLabel lbArea = new JLabel("Area :");
        lbArea.setBounds(382, 72, 144, 26);
        f.getContentPane().add(lbArea);
        
        JTextField txt6 = new JTextField();
        txt6.setBounds(472, 72, 260, 23);
        f.getContentPane().add(txt6);

        
        // create table
        
        dtm = new DefaultTableModel();
        JTable table = new JTable(dtm);
        dtm.addColumn("ID");
        dtm.addColumn("Name");
        dtm.addColumn("Price");
        dtm.addColumn("Total");
        dtm.addColumn("Featured");
        dtm.addColumn( "Area");
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(453, 421, 71, 34);
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Housing hs = new Housing();
        		hs.setProduct_id(txt1.getText());
        		hs.setProduct_name(txt2.getText());
        		hs.setProduct_price(Double.parseDouble(txt3.getText()));
        		hs.setProduct_total(Integer.parseInt(txt4.getText()));
        		hs.setFeatured(txt5.getText());
        		hs.setArea(Double.parseDouble(txt6.getText()));
        		
        		housings.add(hs);
        		loadData();
        		try {
					OutputFile(housings);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        f.getContentPane().setLayout(null);
        f.getContentPane().add(btnAdd);  
        
        
        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int i = table.getSelectedRow();
        		dtm.setValueAt(txt1.getText(), i, 0);
        		dtm.setValueAt(txt2.getText(), i, 1);
        		dtm.setValueAt(txt3.getText(), i, 2);
        		dtm.setValueAt(txt4.getText(), i, 3);
        		dtm.setValueAt(txt5.getText(), i, 4);
        		dtm.setValueAt(txt6.getText(), i, 5);
        		housings.remove(i);
        		Housing hs = new Housing();
        		hs.setProduct_id(txt1.getText());
        		hs.setProduct_name(txt2.getText());
        		hs.setProduct_price(Double.parseDouble(txt3.getText()));
        		hs.setProduct_total(Integer.parseInt(txt4.getText()));
        		hs.setFeatured(txt5.getText());
        		hs.setArea(Double.parseDouble(txt6.getText()));
        		housings.add(i, hs);
        		try {
					OutputFile(housings);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnEdit.setBounds(548, 421, 84, 34);
        f.getContentPane().add(btnEdit);
        
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int row = table.getSelectedRow();
        		housings.remove(row);
        		loadData();
        		try {
					OutputFile(housings);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnDelete.setBounds(651, 421, 81, 34);
        f.getContentPane().add(btnDelete);
        
     // Name for search
        JLabel lbSBName = new JLabel("Search Housing By Name");
        lbSBName.setBounds(102, 420, 256, 25);
        f.getContentPane().add(lbSBName);
        JLabel lbSName = new JLabel("Input Name :");
        lbSName.setBounds(42, 465, 256, 25);
        f.getContentPane().add(lbSName);
        
        JTextField txt7 = new JTextField();
        txt7.setBounds(150, 465, 256, 25);
        f.getContentPane().add(txt7);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dtm.setRowCount(0);
        		List<Housing> searchHousings = new ArrayList<>();
        		for (Housing hs : housings) { 
        			if(hs.getProduct_name().toLowerCase().contains(txt7.getText().toLowerCase())) {
        				searchHousings.add(hs);
        			}
        		}
        		for (Housing hs : searchHousings) {
        			Vector<Object> vec = new Vector<>();
        			vec.add(hs.getProduct_id());
        			vec.add(hs.getProduct_name());
        			vec.add(hs.getProduct_price());
        			vec.add(hs.getProduct_total());
        			vec.add(hs.getFeatured());
        			vec.add(hs.getArea());
        			dtm.addRow(vec);
        		}
        	}
        });
        btnSearch.setBounds(150, 500, 117, 25);
        f.getContentPane().add(btnSearch);
        
        
        
        JButton btnNewButtonAscending = new JButton("Sort Housing By Price (Ascending)");
        btnNewButtonAscending.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		housingImpl.sortedHousing(housings, true);
        		loadData();
        		try {
					OutputSortPriceFile(housings, true);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButtonAscending.setBounds(453, 465, 280, 25);
        f.getContentPane().add(btnNewButtonAscending);
        
        JButton btnNewButtonDecrease = new JButton("Sort Housing By Price (Decrease)");
        btnNewButtonDecrease.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		housingImpl.sortedHousing(housings, false);
        		loadData();
        		try {
					OutputSortPriceFile(housings, false);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButtonDecrease.setBounds(453, 500, 280, 25);
        f.getContentPane().add(btnNewButtonDecrease);
        
        table.setBounds(30, 40, 200, 300);
        
        
        JScrollPane sc = new JScrollPane(table , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.setBounds(110, 126, 626, 249);
        f.getContentPane().add(sc);
        
        JPanel panel = new JPanel();
        sc.setColumnHeaderView(panel);
        panel.setLayout(new BorderLayout());
        
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int row = table.getSelectedRow();
        		txt1.setText(dtm.getValueAt(row, 0).toString());
        		txt2.setText(dtm.getValueAt(row, 1).toString());
        		txt3.setText(dtm.getValueAt(row, 2).toString());
        		txt4.setText(dtm.getValueAt(row, 3).toString());
        		txt5.setText(dtm.getValueAt(row, 4).toString());
        		txt6.setText(dtm.getValueAt(row, 5).toString());
        	}
		});
 
        f.setSize(800, 600);
        f.setVisible(true);
		
	}
}
