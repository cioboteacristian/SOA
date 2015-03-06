
/**
 * RadiologyServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package org.pahospital.www.radiologyservice;
    /**
     *  RadiologyServiceSkeleton java skeleton for the axisService
     */
    
    import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

    import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.pahospital.www.radiologycallbackservice.RadiologyCallbackServiceStub;
    
    public class RadiologyServiceSkeleton implements RadiologyServiceSkeletonInterface{
        
    	public static int MINUTE = 60000;
    	
    	private static List<RadiologyOrder> orders = new ArrayList<RadiologyOrder>();
    	private static List<Appointment> appointments = new ArrayList<Appointment>();
    	private static List<RadiologyReport> reports = new ArrayList<RadiologyReport>();
    	private static List<Boolean> reportsSent = new ArrayList<Boolean>();
    	private static List<Boolean> examsPaid = new ArrayList<Boolean>();
    	
    	private final String eclipseUrl = 
    			"http://localhost:8080/RadiologyClient/services/RadiologyCallbackService";
    	private final String externalUrl = 
    			"http://localhost:8080/axis2/services/RadiologyCallbackService";
       
        /**
         * Auto generated method signature
         * 
                                     * @param appointment2 
             * @return appointment3 
         */
        
                 public org.pahospital.www.radiologyservice.Appointment requestAppointment
                  (
                  org.pahospital.www.radiologyservice.Appointment appointment2
                  )
            {
                	 String orderID = appointment2.getOrderID();
                     try{
                     	int ID = Integer.parseInt(orderID);
                     	if(ID<0 || ID>=orders.size())
                     		throw new  java.lang.UnsupportedOperationException("OrderID " + orderID + " does not point to an existing order");
                     	else{
                     		if(appointments.get(ID)==null){
                     			appointments.set(ID,appointment2);
                     			//Set Date on report
                     			reports.get(ID).setDateOfExamination(appointment2.getDate());
                     			
                     			//Try to send the report;
                     			(new Thread(){        
                     				private int ID;
                     				
                     				public void run(){
                     					try {
                     						//Sleeps before sending the report between 1 and 2 minutes.
											Thread.sleep((long) (Math.random()*MINUTE/10)+(MINUTE/10));
											sendReport(ID);
										} catch (InterruptedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
                     				}
                     				
                     				public Thread init(int ID){
                     					this.ID = ID;
                     					return this;
                     				}
                     			}.init(ID)).start();
                     		}
                     		else{
                     			//How to solve whether an appointment already has been made
                     			throw new  java.lang.UnsupportedOperationException("OrderID " + orderID + " already  made an appointment");
                     		}
                     	}
                     }
                     catch(NumberFormatException e){
                     	throw new  java.lang.UnsupportedOperationException("OrderID " + orderID + " for request appointment does not have a valid format (int)");
                     }
                     
                     
                     return appointment2;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param radiologyOrder4 
             * @return radiologyOrderID5 
         */
        
                 public org.pahospital.www.radiologyservice.RadiologyOrderID orderRadiologyExamination
                  (
                  org.pahospital.www.radiologyservice.RadiologyOrder radiologyOrder4
                  )
            {
                	 
                	 //The current size is the ID since that's where the new Order will be put.
                     int orderID = orders.size();
                     //The order is put into the list so it can be looked up later.
                     orders.add(radiologyOrder4);
                     //Put an empty appointment into the list so it can be looked up later
                     appointments.add(null);
                     //Initialize report
                     RadiologyReport report = new RadiologyReport();
                     report.setPatientID(radiologyOrder4.getPatientID());
                     report.setRadiologyOrderID(Integer.toString(orderID));
                     report.setReportText("Ik ben klaar");
                     reports.add(report);
                     //Initialize others
                     reportsSent.add(false);
                     examsPaid.add(false);
                     
                     RadiologyOrderID result = new RadiologyOrderID();
                     result.setRadiologyOrderID(String.valueOf(orderID));
                     return result;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param radiologyOrderID6 
             * @return orderStatus7 
         */
        
                 public org.pahospital.www.radiologyservice.OrderStatus getOrderStatus
                  (
                  org.pahospital.www.radiologyservice.RadiologyOrderID radiologyOrderID6
                  )
            {
                	 //retrieve the current status of a radiology order (e.g., 
                		//	 ordered, appointment made, report sent)
                	 String orderID = radiologyOrderID6.getRadiologyOrderID();
                	 OrderStatus result = new OrderStatus();
              		 result.setOrderID(orderID);
                     try{
                     	int ID = Integer.parseInt(orderID);
                     	if(ID<0 || ID>=orders.size())
                     		throw new  java.lang.UnsupportedOperationException("OrderID " + orderID + " does not point to an existing order");
                     	else{
                     		if(appointments.get(ID)==null){
                         		result.setOrderStatus("Ordered");
                     		}
                     		else{
                     			result.setOrderStatus(reportsSent.get(ID) ? "Report sent" : "Appointment made");
                     		}
                     		if(examsPaid.get(ID)){
                     			result.setOrderStatus(result.getOrderStatus()+" & Payment made");
                     		}
                     	}
                     }
                     catch(NumberFormatException e){
                     	throw new  java.lang.UnsupportedOperationException("OrderID " + orderID + " for request appointment does not have a valid format (int)");
                     }
                     
                     return result;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param radiologyOrderIDForPayment8 
             * @return  
         */
        
                 public void makePayment
                  (
                  org.pahospital.www.radiologyservice.RadiologyOrderIDForPayment radiologyOrderIDForPayment8
                  )
            {
                	 String orderID = radiologyOrderIDForPayment8.getRadiologyOrderIDForPayment();
                     try{
                     	int ID = Integer.parseInt(orderID);
                     	if(ID<0 || ID>=orders.size())
                     		throw new  java.lang.UnsupportedOperationException("OrderID " + orderID + " does not point to an existing order");
                     	else{
                     		examsPaid.set(ID, true);
                     	}
                     }
                     catch(NumberFormatException e){
                     	throw new  java.lang.UnsupportedOperationException("OrderID " + orderID + " for paying does not have a valid format (int)");
                     }
                     
                     
                
        }
                 
                 /**
                  * Tries to send the report
                  * @param ID The ID of the report to be sent
                  */
                 public void sendReport(int ID){
                	 try{
                		RadiologyCallbackServiceStub sendReport = new RadiologyCallbackServiceStub(externalUrl);
                		sendReport.sendRadiologyReport(reports.get(ID));
						reportsSent.set(ID, true);
                	 }
                	 catch(AxisFault e){
                		try {
                  			RadiologyCallbackServiceStub sendReport = new RadiologyCallbackServiceStub(eclipseUrl);
                       		sendReport.sendRadiologyReport(reports.get(ID));
       						reportsSent.set(ID, true);
  						} catch (AxisFault e1) {
  							e1.printStackTrace();
  						} catch (RemoteException e1) {
 							e1.printStackTrace();
 						}
                		e.printStackTrace();
                	 } catch (RemoteException e) {
                		 
						e.printStackTrace();
					} 
                 }
     
    }
    