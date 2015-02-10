
/**
 * BookPriceServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */
package com.example.www.bookquote;

import java.util.*;
    /**
     *  BookPriceServiceSkeleton java skeleton for the axisService
     */
    public class BookPriceServiceSkeleton implements BookPriceServiceSkeletonInterface {
        
    	private Map<String,Double> books;
    	
        public BookPriceServiceSkeleton () {
        	books = new HashMap<String,Double>();
        	books.put("1", 10.0);
        	books.put("2", 45.0);
        	books.put("3", 20.0);
        	books.put("4", 35.0);
        	books.put("5", 50.0);
        }
        /**
         * Auto generated method signature
         * 
                                     * @param getBookPrice
         */
        
                 public com.example.www.bookquote.GetBookPriceResponse getBookPrice
                  (
                  com.example.www.bookquote.GetBookPrice getBookPrice
                  )
            {
                //TODO : fill this with the necessary business logic
                	 GetBookPriceResponse response = new GetBookPriceResponse();
                	 response.setPrice(books.get(getBookPrice.getIsbn()));
                	 return response;
            }
     
    }
    