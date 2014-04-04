package com.example.criminalintent.fragments;

import java.util.Date;
import java.util.UUID;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.criminalintent.Crime;
import com.example.criminalintent.CrimeLab;
import com.example.criminalintent.R;
import com.example.criminalintent.listeners.ListenerFactory;

public class CrimeFragment extends Fragment {

	public static final String EXTRA_CRIME_ID = "com.example.criminalintent.crime_id";
	public static final int REQUEST_DATE = 0;

	private Crime crime;
	private EditText titleField;
	private Button dateButton;
	private CheckBox solvedCheckBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		crime = CrimeLab.getInstance(getActivity()).getCrime(crimeId);
	}

	public static CrimeFragment newInstance(UUID crimeId) {
		Bundle arguments = new Bundle();
		arguments.putSerializable(EXTRA_CRIME_ID, crimeId);

		CrimeFragment crimeFragment = new CrimeFragment();
		crimeFragment.setArguments(arguments);

		return crimeFragment;
	}

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_crime_, parent, false);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}
		setHasOptionsMenu(true);

		titleField = (EditText) view.findViewById(R.id.crime_title);
		titleField.setText(crime.getTitle());
		titleField.addTextChangedListener(ListenerFactory.getCrimeTextChangeListener(crime));

		dateButton = (Button) view.findViewById(R.id.crime_date);
		updateDate();
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		OnClickListener onClickListener = ListenerFactory.getCrimeDateOnClickListener(fragmentManager,
				CrimeFragment.this, crime.getDate());
		dateButton.setOnClickListener(onClickListener);

		solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
		solvedCheckBox.setChecked(crime.isSolved());
		solvedCheckBox.setOnCheckedChangeListener(ListenerFactory.getCrimeSolvedCheckboxListener(crime));

		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == REQUEST_DATE) {
				Date crimeDate = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
				crime.setDate(crimeDate);
				updateDate();
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean optionsItemSelected;
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			optionsItemSelected = true;
			break;

		default:
			optionsItemSelected = super.onOptionsItemSelected(item);
			break;
		}
		return optionsItemSelected;
	}

	private void updateDate() {
		dateButton.setText(crime.getDate().toString());
	}
}
