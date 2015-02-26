package com.example.www.hotel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HotelModel {
	
	private HashMap<Integer,Hotel> hotels=new HashMap<Integer,Hotel> ();
	
	
	
	public HotelModel(){
		init();
	}
	
	private void init() {
		int[] roomnumbers={2,2,2,4,4,4,6,6};
		String[] cityNames={"Enschede", "Amsterdam", "Utrecht"};
		int hotelCounter=0;
		//each city
		for(int c=0;c<cityNames.length;c++){
			//2 hotels per city
			for(int h=0;h<2;h++){
				ArrayList<Room> rooms=new ArrayList<Room>();
				for(int n=0;n<roomnumbers.length;n++){
					Room room = new Room(roomnumbers[n]);
					rooms.add(room);
				}
				Hotel hotel = new Hotel(hotelCounter,cityNames[c]);
				hotel.addRooms(rooms);
				hotels.put(hotelCounter, hotel);
				hotelCounter++;
			}
		}
	}

	public List<Integer> checkCityHotelAvailability(String city, int numberOfGuests, Date date){
		List<Integer> result= new ArrayList<Integer>();
		for(Hotel hotel:hotels.values()){
			if(hotel.getCity().equals(city)&&
				checkHotelAvailability(hotel.getCode(),numberOfGuests,date)){							
					result.add(hotel.getCode());																					
			}
		}
		return result;
	}
	
	public boolean checkHotelAvailability(int hotelCode, int numberOfGuests, Date date){
		return hotels.get(hotelCode).checkRoomAvailable(numberOfGuests, date);
	}
	
	public boolean bookHotelRoom(int hotelCode, int numberOfGuests, Date date){
		for(Room room: hotels.get(hotelCode).getRooms()){
			if(room.getCapacity()==numberOfGuests && 
					!room.isOccupied(date)){
				room.setOccupied(date, true);
				return true;
			}
		}
		
		return false;
		
	}
	
}
