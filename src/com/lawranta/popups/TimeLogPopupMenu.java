package com.lawranta.popups;
import java.awt.PointerInfo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import com.lawranta.containersObjects.employeeContainer;
import com.lawranta.panels.AdminPanel;
import com.lawranta.sqllite.connectDB;

public class TimeLogPopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4949948920218669784L;
	private Component contentPanel;;

	public TimeLogPopupMenu(AdminPanel adminPanel) {
		
		// TODO Auto-generated constructor stub
		this.contentPanel=adminPanel;
		Component thisMenu = this;
		setLabel("Filter");
		setBackground(Color.BLACK);
		JMenuItem m1=new JMenuItem("All");
		JMenuItem m2=new JMenuItem("Employees");
		JMenuItem m3=new JMenuItem("Penis");
		add(m1);
		add(m2);
		add(m3);
		
		
		
		
		 // addActionListener
        m1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Item 1 clicked.");
                ((AdminPanel) contentPanel).relistLogs(0,  null,  null);
            }
        });
 
        m2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	
            	
            	JPopupMenu subMenu = FilterByEmployee();
            	addPopup(thisMenu, subMenu, e);
            	subMenu.setVisible(true);
            	//subMenu.setLocationRelativeTo(null);
            	
            	int x=(int) ( MouseInfo.getPointerInfo().getLocation().getX()-contentPanel.getLocationOnScreen().getX()) ;
            	int y=(int) (MouseInfo.getPointerInfo().getLocation().getY()-contentPanel.getLocationOnScreen().getY());
            	subMenu.show(contentPanel,x,y);
            	 System.out.println("Item 2 clicked.");
            }
        });
 
        m3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	 System.out.println("Item 3 clicked.");
            }
        });
		
		
		
		
		
		
		
		
		
		
		
		
		
		pack();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	public JPopupMenu FilterByEmployee() {
		
		JPopupMenu m = new JPopupMenu();
		connectDB cDb = new connectDB();
		ArrayList<employeeContainer> employeeList = cDb.getEmployees();
		System.out.print("Employees:" + employeeList.size() + "\n");
		m.setLabel("Employee Filter");
		m.setBackground(Color.BLACK);
		
		
		
		
		
		
		for (int i = 0; i < employeeList.size(); i++) {
			
			JMenuItem e=new JMenuItem(employeeList.get(i).getLastName()+", "+employeeList.get(i).getFirstName());
			m.add(e);
			int num =i;
		   e.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	                System.out.println(employeeList.get(num).getLastName()+", "+employeeList.get(num).getFirstName() + " clicked.");
	                ((AdminPanel) contentPanel).relistLogs(+employeeList.get(num).getID(),  null,  null);
	              
	            }
	        });
	 
			
		}
		
		
	
		
		return m;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public final void addPopup(Component component, final JPopupMenu popup, ActionEvent e) {
		System.out.println("penis2");
		
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
					System.out.println("penis3");
			
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
					show(component, 0, 0);
					System.out.println("penis4");
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), 0, 0);
			
				System.out.println("penis5");
			}
		});
	}



	
	
	
	
	
}
