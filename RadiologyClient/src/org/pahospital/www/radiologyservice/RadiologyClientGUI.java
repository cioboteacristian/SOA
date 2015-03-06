package org.pahospital.www.radiologyservice;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.pahospital.www.radiologycallbackservice.RadiologyCallbackServiceSkeletonInterface;
import org.pahospital.www.radiologycallbackservice.RadiologyReport;
import org.pahospital.www.radiologyservice.RadiologyServiceStub;

public class RadiologyClientGUI extends JFrame{

	public static final String[] types = {"XRAY", "CT"};
	public static final int[] DAYS = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	public static final int[] MONTHS = {1,2,3,4,5,6,7,8,9,10,11,12};
	public static final int[] YEARS = {2015,2016,2017,2018};

	private ButtonListener buttonListener = null;

	private JLabel urlLabel = null;
	private JTextField urlfield = null;
	private final String defaultUrl = 
			"http://localhost:8080/RadiologyServer/services/RadiologyService";
	private String url;
	
	private JLabel radOrderPatient = null;
	private JTextField patientField = null;
	private JLabel radOrderCase = null;
	private JTextField caseField = null;
	private JLabel radOrderType = null;
	private JComboBox<String> typeField = null;
	private JButton orderRadiologyButton = null;
	
	private JLabel appointmentOrderID = null;
	private JTextField appointmentIDField = null;
	private JLabel appointmentDate = null;
	private JComboBox<Integer> dayField = null;
	private JComboBox<Integer> monthField = null;
	private JComboBox<Integer> yearField = null;
	private JButton appointmentButton = null;
	
	private JLabel statusOrderID = null;
	private JTextField statusIDField = null;
	private JButton statusButton = null;
	
	private JLabel paymentOrderID = null;
	private JTextField paymentIDField = null;
	private JButton paymentButton = null;
	
	private JLabel result = null;
	private JLabel resultfield = null;
	
	public static void main(String[] args) throws AxisFault {
		new RadiologyClientGUI();
	}
	
	
	/**
	 * This is the default constructor
	 * @throws AxisFault 
	 */
	public RadiologyClientGUI() throws AxisFault {
		super("Radiology Client");
		wsInit(defaultUrl);
		initialize();
	}
	
	public RadiologyServiceStub stub;
	public void wsInit(String endpoint) throws AxisFault{
		stub = new RadiologyServiceStub(endpoint);
	}
	
	/**
	 * This method initializes this JFrame
	 * 
	 * @return void
	 */
	private void initialize() {
		Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        
        Container urlContainer = new Container();
        urlContainer.setLayout(new FlowLayout());
        
        Container radiologyOrder = new Container();
        radiologyOrder.setLayout(new FlowLayout());
        
        Container appointment = new Container();
        appointment.setLayout(new FlowLayout());
        
        Container status = new Container();
      	status.setLayout(new FlowLayout());
      	
        Container payment = new Container();
      	payment.setLayout(new FlowLayout());
        
        Container resultContainer = new Container();
        resultContainer.setLayout(new FlowLayout());
        
		
        buttonListener = new ButtonListener();

        
		urlLabel = new JLabel();
		urlLabel.setText("URL:");
		
		url = defaultUrl;
		
		urlfield = new JTextField(url);
		urlfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == urlfield) 
				{ 
					try {
						stub = new RadiologyServiceStub(url);
					} catch (AxisFault e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
		}); 
		
        
        radOrderPatient = new JLabel();
        radOrderPatient.setText("PatientID:");
        radOrderCase = new JLabel();
        radOrderCase.setText("Case ID:");
		patientField = new JTextField(10);
		caseField = new JTextField(10);
		radOrderType = new JLabel();
        radOrderType.setText("Examination type:");
		typeField = new JComboBox<String>();
		typeField.setEditable(false);
		for(String item: types){
			typeField.addItem(item);
		}
		orderRadiologyButton = new JButton();
		orderRadiologyButton.setText("Order radiology exam");

		orderRadiologyButton.addActionListener(buttonListener);
		
		
		result = new JLabel();
		result.setText("Response:");
		resultfield = new JLabel();
		
		appointmentOrderID = new JLabel();
		appointmentOrderID.setText("Radiology order ID");
		appointmentIDField = new JTextField(10);
		appointmentDate = new JLabel();
		appointmentDate.setText("Date (dd-mm-yyyy):");
		dayField = new JComboBox<Integer>();
		for(int item: DAYS){
			dayField.addItem(item);
		}
		monthField = new JComboBox<Integer>();
		for(int item: MONTHS){
			monthField.addItem(item);
		}
		yearField = new JComboBox<Integer>();
		for(int item: YEARS){
			yearField.addItem(item);
		}
		appointmentButton = new JButton();
		appointmentButton.setText("Request appointment");
		
		appointmentButton.addActionListener(buttonListener);

		
		statusOrderID = new JLabel();
		statusOrderID.setText("Radiology order ID");
		statusIDField = new JTextField(10);
		statusButton = new JButton();
		statusButton.setText("Request status");
		
		statusButton.addActionListener(buttonListener);
		
		
		paymentOrderID = new JLabel();
		paymentOrderID.setText("Radiology order ID");
		paymentIDField = new JTextField(10);
		paymentButton = new JButton();
		paymentButton.setText("Pay radiology exam");
		
		paymentButton.addActionListener(buttonListener);
		
		
		urlContainer.add(urlLabel);
		urlContainer.add(urlfield);
		
		radiologyOrder.add(radOrderPatient);
		radiologyOrder.add(patientField);
		radiologyOrder.add(radOrderCase);
		radiologyOrder.add(caseField);
		radiologyOrder.add(radOrderType);
		radiologyOrder.add(typeField);
		radiologyOrder.add(orderRadiologyButton);
		
		appointment.add(appointmentOrderID);
		appointment.add(appointmentIDField);
		appointment.add(appointmentDate);
		appointment.add(dayField);
		appointment.add(monthField);
		appointment.add(yearField);
		appointment.add(appointmentButton);
		
		status.add(statusOrderID);
		status.add(statusIDField);
		status.add(statusButton);
		
		payment.add(paymentOrderID);
		payment.add(paymentIDField);
		payment.add(paymentButton);
		
		resultContainer.add(result);
		resultContainer.add(resultfield);
		
		
		c.add(urlContainer);
		c.add(radiologyOrder);
		c.add(appointment);
		c.add(status);
		c.add(payment);
		c.add(resultContainer);
		
		this.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
            	System.exit(0); 				
            	}
	    });
	
		this.setSize(800, 300);
		this.setVisible(true);
	}
	
	private void setResult(String text){
		resultfield.setText("\""+text+"\"");
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//update endpoint URL with the value of urlfield
			url = urlfield.getText();
			try {
				stub = new RadiologyServiceStub(url);
				
				if(e.getSource()==orderRadiologyButton){
					RadiologyServiceStub.RadiologyOrder request = 
						new RadiologyServiceStub.RadiologyOrder();
					RadiologyServiceStub.RadiologyOrderID response;
					
					request.setPatientID(patientField.getText());
					request.setCaseID(caseField.getText());
					String type = (String) typeField.getSelectedItem();
					RadiologyServiceStub.ExaminationType_type1 examination = 
							new RadiologyServiceStub.ExaminationType_type1(type, true);
					request.setExaminationType(examination);
						
					response = stub.orderRadiologyExamination(request);
					
					setResult("ID: "+String.valueOf(response.getRadiologyOrderID()));
					
					/*RadiologyServiceStub.Appointment appointment = new RadiologyServiceStub.Appointment();
					appointment.setOrderID("0");
					appointment.setDate(new Date());
					stub.requestAppointment(appointment);
					
					RadiologyServiceStub.RadiologyOrderIDForPayment payment = new RadiologyServiceStub.RadiologyOrderIDForPayment();
					payment.setRadiologyOrderIDForPayment("0");
					stub.makePayment(payment);*/
				}
				else if(e.getSource() == appointmentButton){
					RadiologyServiceStub.Appointment appointment = new RadiologyServiceStub.Appointment();
					RadiologyServiceStub.Appointment response;
					
					appointment.setOrderID(appointmentIDField.getText());
					int day = (int) dayField.getSelectedItem();
					int month = (int) monthField.getSelectedItem();
					int year = (int) yearField.getSelectedItem();
					Calendar date = new GregorianCalendar();
					date.set(year, month-1, day);
					appointment.setDate(date.getTime());
					
					response = stub.requestAppointment(appointment);
					
					setResult("Appointment made on "+response.getDate().toString()+" for radiology order "+response.getOrderID());
					
				}
				else if(e.getSource() == statusButton){
					RadiologyServiceStub.RadiologyOrderID order = new RadiologyServiceStub.RadiologyOrderID();
					RadiologyServiceStub.OrderStatus response;
					
					order.setRadiologyOrderID(statusIDField.getText());
					
					response = stub.getOrderStatus(order);
					
					setResult("Status for order "+response.getOrderID()+": "+response.getOrderStatus());
					
				}
				else if(e.getSource() == paymentButton){
					RadiologyServiceStub.RadiologyOrderIDForPayment order = new RadiologyServiceStub.RadiologyOrderIDForPayment();
					RadiologyServiceStub.OrderStatus response;
					
					order.setRadiologyOrderIDForPayment(statusIDField.getText());
					
					stub.makePayment(order);
					
					setResult("Paid for order "+order.getRadiologyOrderIDForPayment());
					
				}
			} catch (AxisFault e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				setResult(e1.getMessage());
			} 
			catch (RemoteException e1) {
				// TODO Auto-generated catch block
				setResult(e1.getMessage());
			} 
			catch (ClassCastException e1) {
				resultfield.setText("A dropdown was not selected correctly");
			}
		}
		
	}
}
