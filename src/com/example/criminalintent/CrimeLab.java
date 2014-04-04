package com.example.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {

	private static CrimeLab crimeLab;
	private Context appContext;

	private ArrayList<Crime> crimes;

	private CrimeLab(Context appContext) {
		this.appContext = appContext;
		crimes = new ArrayList<Crime>();
	}

	public static CrimeLab getInstance(Context appContext) {
		if (crimeLab == null) {
			crimeLab = new CrimeLab(appContext.getApplicationContext());
		}
		return crimeLab;

	}

	public ArrayList<Crime> getCrimes() {
		return crimes;
	}

	public void addCrime(Crime crime) {
		crimes.add(crime);
	}

	public Crime getCrime(UUID id) {
		Crime searchedCrime = null;
		for (Crime crime : crimes) {
			if (crime.getId().equals(id)) {
				searchedCrime = crime;
			}
		}
		return searchedCrime;
	}
}
