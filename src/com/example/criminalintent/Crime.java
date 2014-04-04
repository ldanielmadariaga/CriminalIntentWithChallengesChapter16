package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {

	private UUID id;
	private String title;
	private Date date;
	private boolean isSolved;

	public Crime() {
		this.id = UUID.randomUUID();
		this.date = new Date();
	}

	@Override
	public String toString() {
		return title;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSolved() {
		return isSolved;
	}

	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}

}
