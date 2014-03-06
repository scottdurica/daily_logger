package com.emRoXRIPRAP.logger.screens;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.emRoXRIPRAP.logger.MyPreferenceFragment;
import com.emRoXRIPRAP.logger.R;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		 getFragmentManager().beginTransaction().replace(android.R.id.content,
	                new MyPreferenceFragment()).commit();
	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}
