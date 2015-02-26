package com.example.www.hotel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Hotel{
	private String city;
	
	private int code;
	
	private List<Room> rooms = new ArrayList<Room>();
	
	public Hotel(int code, String city){
		this.city=city;
		this.code=code;
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void addRooms(List<Room> rooms) {
		this.rooms.addAll(rooms);
	}
	
	public boolean checkRoomAvailable(int numberOfGuests, Date date){
		for(Room room:getRooms()){
			if(room.getCapacity()==numberOfGuests && !room.isOccupied(date))
				return true;
		}
		return false;
	}
	
}