package com.lawranta.panels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lawranta.DatabaseModels.AttendanceModel;
import com.lawranta.Globals.Global;
import com.lawranta.Globals.PropertiesCFG;
import com.lawranta.SubPanels.AdminSubPanel;
import com.lawranta.SubPanels.currentSessionPanel;
import com.lawranta.containersObjects.attendanceContainer;
import com.lawranta.frames.*;
import com.lawranta.popups.DateChooserDialog;
import com.lawranta.popups.TimeLogPopupMenu;
import com.lawranta.popups.adminNewPinDialog;
import com.lawranta.popups.newEmployeeDialog;
import com.lawranta.services.AttendanceService;
import com.lawranta.sqllite.AttendanceDAO;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class AdminPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 323277068294353532L;
	public static AdminPanel owner;
	protected AdminPanel INITIALIZE;
	public AdminSubPanel asp;
	public JPanel employeeContent;
	public JPanel contents;
	public PanelContainerFrame frame;
	public String [] fromToDates = new String[2];
	public int logID=0;
	public float totalHours;
	public String parsedTotal;
	public boolean toggleTotal;
	JPanel totalPanel = new JPanel();
	JLabel totalHoursText = new JLabel("Total: ");
	public String timeLogFilter = "all";
	
	/**
	 * Create the frame.
	 */
	public AdminPanel(PanelContainerFrame frame) {
		this.frame=frame;
		System.out.println("Passing " + this + " panel as owner");
		owner=this;
		setVisible(true);
		setBounds(400, 400, 550, 800);
		setBackground(new Color(0, 0, 0));

		
		

		
		
		
		
		
		JPopupMenu pm = new TimeLogPopupMenu(this);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel lblNewLabel = new JLabel("Admins Only");
		lblNewLabel.setVisible(false);
		lblNewLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setBounds(0, 0, 0, 0);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(Global.header2Font);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		add(lblNewLabel);
		
		JPanel buttonContainer = new JPanel();
		buttonContainer.setForeground(new Color(255, 255, 255));
		buttonContainer.setBackground(new Color(70, 70, 70));
		buttonContainer.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Aministrative Tools", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		add(buttonContainer);
		buttonContainer.setLayout(new GridLayout(0, 4, 4, 4));
		
		
		
		final JButton btnClear = new JButton("Clear Dates"); 
		btnClear.setVisible(false);
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fromToDates[0]=null;
				fromToDates[1]=null;
				relistLogs(logID, fromToDates[0], 	fromToDates[1]);
				btnClear.setVisible(false);
				
			}
		});
		btnClear.setBounds(139, 280, 119, 23);

		JButton btnLogOut = new JButton("Log Out");
		buttonContainer.add(btnLogOut);
		btnLogOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogOut.setBounds(0, 0, 0, 0);
		
		
				JButton btnListEmployees = new JButton("Employees");
				buttonContainer.add(btnListEmployees);
				btnListEmployees.setAlignmentX(Component.CENTER_ALIGNMENT);
				btnListEmployees.setBounds(0, 0, 0, 0);
				
						JButton btnAdminPin = new JButton("Admin PIN");
						btnAdminPin.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								addNewPin();
								
								
								
							}});
						buttonContainer.add(btnAdminPin);
						btnAdminPin.setAlignmentX(Component.CENTER_ALIGNMENT);
						btnAdminPin.setBounds(0, 0, 0, 0);
						
								JButton btnTimeLogs = new JButton("Time Logs");
								buttonContainer.add(btnTimeLogs);
								btnTimeLogs.setAlignmentX(Component.CENTER_ALIGNMENT);
								btnTimeLogs.setBounds(0, 0, 0, 0);
								
								JButton btnNewEmployee = new JButton("New Employee");
										buttonContainer.add(btnNewEmployee);
										btnNewEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);
										btnNewEmployee.setBounds(0, 0, 0, 0);
										
												JButton btnDates = new JButton("Dates");
												buttonContainer.add(btnDates);
												btnDates.setAlignmentX(Component.CENTER_ALIGNMENT);
												btnDates.setBounds(0, 0, 0, 0);
												
												JButton btnExportAll = new JButton("Export");
												buttonContainer.add(btnExportAll);
												btnExportAll.setAlignmentX(Component.CENTER_ALIGNMENT);
												btnExportAll.setBounds(0, 0, 550, 800);
												btnExportAll.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {

														if(asp!=null) {
														asp.exportList(totalHours, parsedTotal);}
														
													}
												});
												btnDates.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {

														openDateChooserDialog();
														btnClear.setVisible(true);
														buttonContainer.add(btnClear);
													
														
													}
												});
										btnNewEmployee.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {

												openNewEmployeeDialog();

											}
										});
								btnTimeLogs.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										pm.setVisible(true);
										((TimeLogPopupMenu) pm).addPopup(owner, pm, e);
										pm.setPopupSize(btnTimeLogs.getSize().width, 100);
										pm.show(owner, btnTimeLogs.getLocation().x, btnTimeLogs.getLocation().y+20);
										

									}
									});
				btnListEmployees.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						relistEmployees();
					}
				});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.PanelChange(new PinPanel(frame));
				setVisible(false);
			}
		});
		
		
		
		employeeContent = new JPanel();
		employeeContent.setBounds(0, 0, 0, 0);
		add(employeeContent);
		totalPanel.setVisible(false);
		add(totalPanel);
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.X_AXIS));
		totalHoursText.setAlignmentX(Component.CENTER_ALIGNMENT);
		

		totalHoursText.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		totalHoursText.setHorizontalTextPosition(SwingConstants.CENTER);
		totalHoursText.setFont(Global.analogFont16f);
		totalPanel.add(totalHoursText);

		
		
		
		
		
		
		
		
	
		

	}
	
	
	
	
	
	






	protected void addNewPin() {
		// TODO Auto-generated method stub
		
		
		String s[]= {"Enter new Pin.", "Must be 6 numerical digits."};
		
		adminNewPinDialog newPinDialog = new adminNewPinDialog(owner,s);
		
	
		newPinDialog.addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				String newPin = newPinDialog.getPin();
				System.out.println("New PIN: "+newPin);
				
				if (newPin!=null) {
					//confirm
					s[0]="Confirm Pin";s[1]="Re-enter the same exact pin.";
					adminNewPinDialog confirmPinDialog = new adminNewPinDialog(owner,s);
					
					
					
					
					confirmPinDialog.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							
							
							
							
						}

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub

							if(newPin.equals(confirmPinDialog.getPin())){
								String suc="Sucess! New Admin pin: " + newPin;
								Global.showSuccess(suc);
								System.out.println(suc);
								try {
									updatePin(newPin);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}else {
								String er="Pins do not match: " + newPin + " and " + confirmPinDialog.getPin();
								Global.showError(er);
								System.out.println(er);
								
							}
							;
						}

					

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						
						
						
						
					});
		
					confirmPinDialog.setVisible(true);
					
		
					
				}
				
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}		
			
		});
		
		
		
		
		
		
		newPinDialog.setVisible(true);

		
		
		
		
		
		
		
		
	}












	protected void openDateChooserDialog() {
		// TODO Auto-generated method stub
		JDialog dateChooser = new DateChooserDialog(owner);
		dateChooser.setModalityType(ModalityType.APPLICATION_MODAL);
		dateChooser.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dateChooser.setVisible(true);
		
		relistLogs(logID, fromToDates[0].toString(), fromToDates[1].toString());
		
	}












	public void openNewEmployeeDialog() {
		
		
		
		
		
		
					JDialog newEmployee = new newEmployeeDialog(owner, this, frame);
					newEmployee.setModalityType(ModalityType.APPLICATION_MODAL);
					newEmployee.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					newEmployee.setVisible(true);
					System.out.println("Passing " + this + " panel as owner");
					asp=(AdminSubPanel) new AdminSubPanel(frame).employeeList(this);
					subPanelChange(asp);
		
		
		
		
		
	}
	
	
	
	public void relistLogs(int id, String firstDate, String secondDate) {
		logID=id;
		if (contents!=null) {contents.removeAll();}
		System.out.println("Passing " + owner + " panel as owner");
		 asp=(AdminSubPanel) new AdminSubPanel(frame).employeeList(owner);
		System.out.println("Trying to refresh employee list...");
		System.out.println("ID: " + id + " firstDate: " + firstDate + " secondDate: " + secondDate);
		
		
		ArrayList<AttendanceModel> logList = AttendanceService.pullTimeData(id, firstDate, secondDate);
		asp= (AdminSubPanel) new AdminSubPanel(frame).logList(this, logList, frame);
		subPanelChange(asp);
		
		parseTotal();
		
		if(!toggleTotal)
		{
			totalToggle();
			
		}
		
		
		
	}
	
	
	
	
	
	public void relistEmployees(){

		if (contents!=null) {contents.removeAll();}
		System.out.println("Passing " + owner + " panel as owner");
		asp=(AdminSubPanel) new AdminSubPanel(frame).employeeList(owner);
		System.out.println("Trying to refresh employee list...");
		subPanelChange(asp);
		
		if(toggleTotal)
		{
			totalToggle();
			
		}
		
	}
	
	
	
	
	
	
	
	

	public void subPanelChange(JPanel c) {
		
		
		
		
		if (contents!=null) {contents.removeAll();}
		contents = c;
		
		
		
	//	contents.setBounds(0, 0, 514, 367);
		employeeContent.removeAll();
		employeeContent.revalidate();
		employeeContent.repaint();
		employeeContent.add(contents);
		setVisible(true);
	
		
		
	}
	

	private void updatePin(String newPin) throws IOException {
		// TODO Auto-generated method stub
		PropertiesCFG.consistPin(newPin);
		
	}
	
	
	public void totalToggle(){
	
		if(toggleTotal) {
			totalPanel.setVisible(false);
			toggleTotal=false;
			
		}else {
			totalPanel.setVisible(true);
			toggleTotal=true;
			
			
			
		}
		
		
	}
	
	public void parseTotal() {
		//format decimals to hh:mm:ss
		String formatedTotalHours;
		{
				

				int hour =(int) totalHours;
				
				int min = (int) ((totalHours - hour) * 60);
				
				int sec = (int) (((totalHours - hour) * 60 - min) * 60);
				
				
				
			       String formattedHours = String.format("%02d", hour);
			        String formattedMinutes = String.format("%02d", min);
			        String formattedSeconds = String.format("%02d", sec);

				
				parsedTotal = formattedHours + ":" + formattedMinutes + ":" + formattedSeconds;
		}
		
		
		
		
		
		
		
		totalHoursText.setText("Calculated Total: " + totalHours + "hrs; " +" [" + parsedTotal + "] " );
		
	}
	
	
}
