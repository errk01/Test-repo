package com.company;

public class Name {

	private String first;
	private String last;
	
	public Name(String first, String last) {
		if(first == null)
			throw new IllegalArgumentException("first name cannot be null");
		if(last == null)
			throw new IllegalArgumentException("last name cannot be null");
		this.first = first;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return first + " " + last;
	}
}
