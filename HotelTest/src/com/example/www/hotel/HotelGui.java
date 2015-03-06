package com.example.www.hotel;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.axis2.AxisFault;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class HotelGui extends JFrame{
	
	private JLabel dateLabel;
	
	private JDatePickerImpl datePicker;
	
	private JLabel numberOfGuestsLabel;
	
	private JTextField numberOfGuestsField;
	
	private JLabel cityLabel;
	
	private JTextField cityField;
	
	private JLabel hotelCodeLabel;
	
	private JTextField hotelCodeField;	
	
	private JTextField responseField;
	
	private JButton checkHotelAvailabilityButton;
	
	private JButton bookHotelButton;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String defaultUrl = "http://localhost:8080/Hotel/services/HotelService";
	
	public HotelGui() throws AxisFault{
		super("HotelGui");
		wsInit(defaultUrl);
		initialize();
	}


	public HotelServiceStub stub;
	public void wsInit(String endpoint) throws AxisFault{
		stub = new HotelServiceStub(endpoint);
	}
	
	
	private void initialize() {
		Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        
        cityLabel = new JLabel();
        cityLabel.setText("City: ");
        
        cityField = new JTextField(15);
        
        numberOfGuestsLabel = new JLabel();
        numberOfGuestsLabel.setText("Number of guests: ");
        
        numberOfGuestsField = new JTextField(3);
       
        dateLabel = new JLabel();
        dateLabel.setText("Date: ");
        dateLabel.setBounds(100,350,100,20);

        

        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        p.put("text.day", "Day");
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel,  new DateLabelFormatter());
      
        
        
        hotelCodeLabel = new JLabel();
        hotelCodeLabel.setText("Hotel code: ");
        
        hotelCodeField = new JTextField(3);
        
        checkHotelAvailabilityButton = new JButton("Check Hotel");
        checkHotelAvailabilityButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HotelServiceStub.CheckHotelAvailability request = new HotelServiceStub.CheckHotelAvailability();
				HotelServiceStub.CheckHotelAvailabilityResponse response;
				
				request.setCity(cityField.getText());
				request.setDate((Date) datePicker.getModel().getValue());
				try{
					request.setNumberOfGuests(Integer.parseInt(numberOfGuestsField.getText()));
					

					response = stub.checkHotelAvailability(request);
					response("Hotels available: "+Arrays.toString(response.getHotelCodeList().getHotelCode()));
				}catch(NumberFormatException e0){
					response("Number of guests should be a number");
				}catch (RemoteException e1) {
					response("connection issues... :(");
					e1.printStackTrace();
				}
			}
		});
        
        bookHotelButton = new JButton("Book Hotel");
        bookHotelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HotelServiceStub.BookHotelRoom request = new HotelServiceStub.BookHotelRoom();
				HotelServiceStub.BookHotelRoomResponse response;
				
				request.setDate((Date) datePicker.getModel().getValue());
				try{
					request.setHotelCode(Integer.parseInt(hotelCodeField.getText()));
					request.setNumberOfGuests(Integer.parseInt(numberOfGuestsField.getText()));
					

					response = stub.bookHotelRoom(request);
					response("Hotels booking "+(response.getConfirmed()?"succeeded":"failed"));
				}catch(NumberFormatException e0){
					response("Number of guests and hotel code should be numbers");
				}catch (RemoteException e1) {
					response("connection issues... :(");
					e1.printStackTrace();
				}
			}
		});
        
        responseField = new JTextField(30);
        responseField.setEditable(false);
        
        c.add(cityField);
        c.add(cityLabel);
        c.add(numberOfGuestsLabel);
        c.add(numberOfGuestsField);
        c.add(dateLabel);
        c.add(datePicker);
        c.add(hotelCodeLabel);
        c.add(hotelCodeField);
		c.add(checkHotelAvailabilityButton);
		c.add(bookHotelButton);
		c.add(responseField);        
        
    	this.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
            	System.exit(0); 				
            	}
	    });
	
		this.setSize(420, 150);
		this.setVisible(true);
	}
	
	private void response(String response){
		responseField.setText(response);
	}
	
	public static void main(String[] args)  throws AxisFault{
		new HotelGui();

	}

}
