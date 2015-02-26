
/**
 * HotelServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package com.example.www.hotel;


import java.util.List;

import com.example.www.hotel.model.HotelModel;
    /**
     *  HotelServiceSkeleton java skeleton for the axisService
     */
    public class HotelServiceSkeleton implements HotelServiceSkeletonInterface{
        
         public static final HotelModel MODEL=new HotelModel();
        /**
         * Auto generated method signature
         * 
                                     * @param checkHotelAvailability0 
             * @return checkHotelAvailabilityResponse1 
         */
        
                 public com.example.www.hotel.CheckHotelAvailabilityResponse checkHotelAvailability
                  (
                  com.example.www.hotel.CheckHotelAvailability checkHotelAvailability0
                  )
            {                	 
              CheckHotelAvailabilityResponse result = new CheckHotelAvailabilityResponse();
                           
              List<Integer> hotelNumbers = MODEL.checkCityHotelAvailability(
            		  checkHotelAvailability0.getCity(),
            		  checkHotelAvailability0.getNumberOfGuests(),
            		  checkHotelAvailability0.getDate());
              
              int[] hotelArray= new int[hotelNumbers.size()];
              for(int i=0;i<hotelNumbers.size();i++){
            	  hotelArray[i]=hotelNumbers.get(i);
              }
              
              AvailableHotelList hotelList = new AvailableHotelList();
              hotelList.setHotelCode(hotelArray);
              
              result.setHotelCodeList(hotelList);
              return result;               
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param bookHotelRoom2 
             * @return bookHotelRoomResponse3 
         */
        
                 public com.example.www.hotel.BookHotelRoomResponse bookHotelRoom
                  (
                  com.example.www.hotel.BookHotelRoom bookHotelRoom2
                  )
            {
                BookHotelRoomResponse result = new BookHotelRoomResponse();
                boolean succes= MODEL.bookHotelRoom(bookHotelRoom2.getHotelCode(), bookHotelRoom2.getNumberOfGuests(), bookHotelRoom2.getDate());
                result.setConfirmed(succes);
                return result;
            }
     
    }
    