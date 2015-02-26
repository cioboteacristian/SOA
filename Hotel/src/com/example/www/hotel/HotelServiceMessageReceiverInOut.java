
/**
 * HotelServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
        package com.example.www.hotel;

        /**
        *  HotelServiceMessageReceiverInOut message receiver
        */

        public class HotelServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        HotelServiceSkeletonInterface skel = (HotelServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("checkHotelAvailability".equals(methodName)){
                
                com.example.www.hotel.CheckHotelAvailabilityResponse checkHotelAvailabilityResponse5 = null;
	                        com.example.www.hotel.CheckHotelAvailability wrappedParam =
                                                             (com.example.www.hotel.CheckHotelAvailability)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.example.www.hotel.CheckHotelAvailability.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               checkHotelAvailabilityResponse5 =
                                                   
                                                   
                                                         skel.checkHotelAvailability(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), checkHotelAvailabilityResponse5, false, new javax.xml.namespace.QName("http://www.example.com/Hotel",
                                                    "checkHotelAvailability"));
                                    } else 

            if("bookHotelRoom".equals(methodName)){
                
                com.example.www.hotel.BookHotelRoomResponse bookHotelRoomResponse7 = null;
	                        com.example.www.hotel.BookHotelRoom wrappedParam =
                                                             (com.example.www.hotel.BookHotelRoom)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.example.www.hotel.BookHotelRoom.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               bookHotelRoomResponse7 =
                                                   
                                                   
                                                         skel.bookHotelRoom(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), bookHotelRoomResponse7, false, new javax.xml.namespace.QName("http://www.example.com/Hotel",
                                                    "bookHotelRoom"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(com.example.www.hotel.CheckHotelAvailability param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.example.www.hotel.CheckHotelAvailability.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.example.www.hotel.CheckHotelAvailabilityResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.example.www.hotel.CheckHotelAvailabilityResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.example.www.hotel.BookHotelRoom param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.example.www.hotel.BookHotelRoom.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.example.www.hotel.BookHotelRoomResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.example.www.hotel.BookHotelRoomResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.example.www.hotel.CheckHotelAvailabilityResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.example.www.hotel.CheckHotelAvailabilityResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.example.www.hotel.CheckHotelAvailabilityResponse wrapcheckHotelAvailability(){
                                com.example.www.hotel.CheckHotelAvailabilityResponse wrappedElement = new com.example.www.hotel.CheckHotelAvailabilityResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.example.www.hotel.BookHotelRoomResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.example.www.hotel.BookHotelRoomResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.example.www.hotel.BookHotelRoomResponse wrapbookHotelRoom(){
                                com.example.www.hotel.BookHotelRoomResponse wrappedElement = new com.example.www.hotel.BookHotelRoomResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (com.example.www.hotel.CheckHotelAvailability.class.equals(type)){
                
                           return com.example.www.hotel.CheckHotelAvailability.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.example.www.hotel.CheckHotelAvailabilityResponse.class.equals(type)){
                
                           return com.example.www.hotel.CheckHotelAvailabilityResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.example.www.hotel.BookHotelRoom.class.equals(type)){
                
                           return com.example.www.hotel.BookHotelRoom.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.example.www.hotel.BookHotelRoomResponse.class.equals(type)){
                
                           return com.example.www.hotel.BookHotelRoomResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    