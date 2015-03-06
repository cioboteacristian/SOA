package org.pahospital.www.radiologycallbackservice;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.apache.axis2.AxisFault;

public class callbackReportGUI extends JFrame {
	
	private static final String PATIENT_TEXT = "Patient ID: ";
	private static final String ORDER_TEXT = "Order ID: ";
	private static final String DATE_TEXT = "Date: ";
	private static final String REPORT_TEXT = "Report: \n";
	
	private JLabel patient = null;
	private JLabel orderID = null;
	private JLabel examinationDate = null;
	private JTextArea reportText = null;
	
	private RadiologyReport report;
	
	public callbackReportGUI(RadiologyReport radiologyReport){
		super("Radiology Report of Order "+radiologyReport.getRadiologyOrderID());
		report = radiologyReport;
		initialize();
	}
	
	private void initialize(){
		Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        
        patient = new JLabel();
        patient.setText(PATIENT_TEXT+report.getPatientID());
        
        orderID = new JLabel();
        orderID.setText(ORDER_TEXT+report.getRadiologyOrderID());
        
      	examinationDate = new JLabel();
      	examinationDate.setText(DATE_TEXT+report.getDateOfExamination().toString());
      	
      	reportText = new JTextArea(5,20);
      	reportText.setText(REPORT_TEXT+report.getReportText());
      	
      	c.add(patient);
      	c.add(orderID);
      	c.add(examinationDate);
      	c.add(reportText);
	
		this.setSize(350, 200);
		this.setVisible(true);
	}
}
