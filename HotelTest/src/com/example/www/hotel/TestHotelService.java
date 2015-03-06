package com.example.www.hotel;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.axis2.AxisFault;

import com.example.www.hotel.HotelServiceStub.BookHotelRoom;
import com.example.www.hotel.HotelServiceStub.CheckHotelAvailability;

public class TestHotelService {
	private static HotelServiceStub stub;

	public static void main(String[] args) throws AxisFault{
		if(args.length==0)
			stub = new HotelServiceStub();
		else
			stub = new HotelServiceStub(args[0]);

		testService();
		
	}

	private static void testService() {
		
		try {
				//CheckHotelAvailability verschillende steden
				CheckHotelAvailability request = createCheckHotelAvailability("Amsterdam", 2);
				System.out.println("Testing CheckHotelAvailability for Amsterdam");
				int[] checkHotelresult = stub.checkHotelAvailability(request).getHotelCodeList().getHotelCode();
				assertTrue(checkHotelresult[0]==2&&checkHotelresult[1]==3, "HotelCodes returned equal to [2,3]", "HotelCodes returned not equal to [2,3]");

				System.out.println("Testing CheckHotelAvailability for Enschede");
				request.setCity("Enschede");
				checkHotelresult = stub.checkHotelAvailability(request).getHotelCodeList().getHotelCode();
				assertTrue(checkHotelresult[0]==0&&checkHotelresult[1]==1, "HotelCodes returned equal to [0,1]", "HotelCodes returned not equal to [0,1]");

				System.out.println("Testing CheckHotelAvailability for Utrecht");
				request.setCity("Utrecht");
				checkHotelresult = stub.checkHotelAvailability(request).getHotelCodeList().getHotelCode();
				assertTrue(checkHotelresult[0]==4&&checkHotelresult[1]==5, "HotelCodes returned equal to [4,5]", "HotelCodes returned not equal to [4,5]");
				
				
				//CheckHotelAvailability Niet bestaande stad
				System.out.println("Testing CheckHotelAvailability for non existing city abcd");
				request.setCity("abcd");
				checkHotelresult = stub.checkHotelAvailability(request).getHotelCodeList().getHotelCode();
				assertTrue(checkHotelresult==null, "HotelCodes returned equal to null", "HotelCodes returned not equal to null");
				
				//CheckHotelAvailability voor 3 personen
				System.out.println("Testing CheckHotelAvailability for non existing room capacity: 3");
				request.setCity("Amsterdam");
				request.setNumberOfGuests(3);
				checkHotelresult = stub.checkHotelAvailability(request).getHotelCodeList().getHotelCode();
				assertTrue(checkHotelresult==null, "HotelCodes returned equal to null", "HotelCodes returned not equal to null");
				
				//BookHotelRoom 3 keer voor AMS/2p/vandaag
				BookHotelRoom bookHotelRoomRequest = createBookHotelRoom(2,2);
				
				System.out.println("Testing BookHotelRoom: booking 3 standard rooms for today (3 max available)");
				boolean bookHotelRoomResult=stub.bookHotelRoom(bookHotelRoomRequest).getConfirmed();
				assertTrue(bookHotelRoomResult, "1st booking succes", "1st booking failed");

				 bookHotelRoomResult=stub.bookHotelRoom(bookHotelRoomRequest).getConfirmed();
				assertTrue(bookHotelRoomResult, "2nd booking succes", "2nd booking failed");

				 bookHotelRoomResult=stub.bookHotelRoom(bookHotelRoomRequest).getConfirmed();
				assertTrue(bookHotelRoomResult, "3rd booking succes", "3rd booking failed");
				
				//BookHotelRoom 4e keer lukt niet
				bookHotelRoomResult=stub.bookHotelRoom(bookHotelRoomRequest).getConfirmed();
				assertTrue(!bookHotelRoomResult, "4th booking was rejected", "4th booking was accepted (supposed to be rejected)");
			
				//Andere stad lukt wel
				System.out.println("Testing BookHotelRoom: booking a standard room in a hotel should succeed");
				bookHotelRoomRequest.setHotelCode(3);
				bookHotelRoomResult=stub.bookHotelRoom(bookHotelRoomRequest).getConfirmed();
				assertTrue(bookHotelRoomResult, "Booking room in different hotel succeeded", "Booking room in different hotel failed");
				
				//4 personen lukt wel
				System.out.println("Testing BookHotelRoom: booking a room with different amount of guests should succeed");
				bookHotelRoomRequest=createBookHotelRoom(2, 4);
				bookHotelRoomResult = stub.bookHotelRoom(bookHotelRoomRequest).getConfirmed();
				assertTrue(bookHotelRoomResult, "Booking room for a different number of guests succeeded", "Booking room for a different number of guests failed");
				
				
				
				//Andere datum lukt ook
				System.out.println("Testing BookHotelRoom: booking a room on a different date should succeed");
				bookHotelRoomRequest=createBookHotelRoom(2, 2);
				Calendar c = Calendar.getInstance();
				c.setTime(getSimpleDate());
				c.add(Calendar.DATE,1);
				Date newDate = c.getTime();
				bookHotelRoomRequest.setDate(newDate);
				
				bookHotelRoomResult = stub.bookHotelRoom(bookHotelRoomRequest).getConfirmed();
				assertTrue(bookHotelRoomResult, "Booking room on a different date succeeded", "Booking room on a different date failed");
				
				
			} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static BookHotelRoom createBookHotelRoom(int hotelCode, int numberOfPersons) {
		BookHotelRoom result = new BookHotelRoom();
		result.setDate(getSimpleDate());
		result.setHotelCode(hotelCode);
		result.setNumberOfGuests(numberOfPersons);
		return result;
	}

	private static Date getSimpleDate(){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date today = new Date();
		try {
			today = formatter.parse(formatter.format(today));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return today;
	}
	
	private static CheckHotelAvailability createCheckHotelAvailability(String city, int numberOfGuests){
		CheckHotelAvailability result = new CheckHotelAvailability();
		result.setDate(getSimpleDate());
		result.setNumberOfGuests(numberOfGuests);
		result.setCity(city);
		
		return result;
	}
	
	private static void assertTrue(Boolean toTest, String messageIfTrue, String messageIfFalse){
		if(toTest)
			System.out.println("Test passed: "+messageIfTrue);
		else
			System.out.println("Test failed: "+messageIfFalse);
			
	}
}
