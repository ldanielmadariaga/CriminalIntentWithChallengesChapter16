package com.example.criminalintent.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.criminalintent.Crime;
import com.example.criminalintent.CrimeLab;
import com.example.criminalintent.activities.CrimeListActivity;
import com.example.criminalintent.activities.CrimePagerActivity;
import com.example.criminalintent.fragments.CrimeFragment;

class AddCrimeOnClickListener implements OnClickListener {

	Context context;

	private AddCrimeOnClickListener(Context context) {
		this.context = context;
	}

	public static AddCrimeOnClickListener getInstance(Context context) {
		return new AddCrimeOnClickListener(context);
	}

	@Override
	public void onClick(View arg0) {
		Crime crime = new Crime();
		CrimeListActivity crimeListActivity = (CrimeListActivity) context;
		CrimeLab.getInstance(crimeListActivity).addCrime(crime);
		Intent intent = new Intent(crimeListActivity, CrimePagerActivity.class);
		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
		crimeListActivity.startActivityForResult(intent, 0);
	}

}
