package com.example.www.hotel.model;

import java.util.Date;
import java.util.HashMap;

public class Room{
	private int capacity;
	
	private HashMap<Date, Boolean> occupied=new HashMap<Date, Boolean>();
	
	public Room(int cap){
		this.setCapacity(cap);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Boolean isOccupied(Date date) {
		return (occupied.get(date)==null?false:occupied.get(date));
	}

	public void setOccupied(Date date, Boolean occupied) {
		this.occupied.put(date, occupied);
	}
}