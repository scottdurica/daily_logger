package com.emRoXRIPRAP.logger;
 
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;

import com.emRoXRIPRAP.logger.screens.DataEntryScreen;
import com.emRoXRIPRAP.logger.screens.DataViewScreen;
import com.emRoXRIPRAP.logger.screens.SettingsActivity;

public class MainActivity extends Activity implements OnClickListener, Constants{
	




	Button viewData;
	Button enterData;
	CalendarView calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		loadPrefs();
		setupWidgets();
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case 0:
			startActivityForResult(new Intent(this,SettingsActivity.class), 0);
			return true;
		}
		return false;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
		menu.add(Menu.NONE, 0, 0, "Settings");
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
//		loadPrefs();
	}
//	private void loadPrefs() {
//		SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//		
//	}
	private void setupWidgets(){
		
		viewData = (Button)findViewById(R.id.b_view_data);
		enterData = (Button)findViewById(R.id.b_enter_data);
		calendar = (CalendarView)findViewById(R.id.calendar);
		
		viewData.setOnClickListener(this);
		enterData.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		Intent intent;
		switch (v.getId()) {
			case R.id.b_enter_data:
				intent = new Intent(MainActivity.this,DataEntryScreen.class);
				SimpleDateFormat sdf = new SimpleDateFormat("EEEE MM-dd-yyyy", Locale.US);
				String fDate = sdf.format(calendar.getDate());
				intent.putExtra("date", fDate);
				//Log.d(tag, "date value is: " + fDate );
				startActivity(intent);
				
				//test method
//				insertIntoDB();
				
				
				break;
			
			case R.id.b_view_data:
				intent = new Intent(MainActivity.this,DataViewScreen.class);
				startActivity(intent);
				break;
			
		}
		
	}
//	private void insertIntoDB(){
//		Entry one = new Entry("Jan 1", "123 Main st.","apt 1","50","35.00","187.50",".10","Scott","100");
//		Entry two = new Entry("Jan 2", "456 Main st.","apt 2","22","35.00","152.20",".10","Jim","200");
//		
//		Log.d(tag, "Test to push changes");
//		Log.d(tag, "" + one.getId());
//		Log.d(tag, one.getDate());
//		Log.d("LOG LOG LOG ENTRY address main: ", one.getAddressMain());
//		Log.d("LOG LOG LOG ENTRY apt-unit: ", one.getAddressSecondary());
//		Log.d("LOG LOG LOG ENTRY labor hours: ", one.getLaborHours());
//		Log.d("LOG LOG LOG ENTRY labor rate: ", one.getLaborRate());
//		Log.d("LOG LOG LOG ENTRY mat cost: ", one.getMaterialCost());
//		Log.d("LOG LOG LOG ENTRY mat markup: ", one.getMaterialMarkup());
//		Log.d("LOG LOG LOG ENTRY name: ", one.getWorkerName());
//		Log.d("LOG LOG LOG ENTRY total: ", one.getTotal());
//		DbHandler db = new DbHandler(this);
//		
//		db.deleteAllEntries();
//
//		Log.d("Insert: ", "Inserting .."); 
//		if(db.addEntry(one)){
//			Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
//		}else
//			Toast.makeText(this, "ERROR Saving Data", Toast.LENGTH_LONG).show();
//
//		//db.addEntry(two);
//		
//        // Reading all contacts
//        Log.d(tag, "Reading all contacts.."); 
//        List<Entry> entries = db.getAllEntries();       
//         
//        for (Entry e : entries) {
//            String log = "Id: "+e.getId()+" ,Date: " + e.getDate() + " ,Address: " + e.getAddressMain() + " ,APT: " + e.getAddressSecondary() +
//            		"Hours: "+e.getLaborHours()+" ,Labor Rate: " + e.getLaborRate() + " ,Material Cost: " + e.getMaterialCost() +
//            		"Material Markup: " + e.getMaterialMarkup() + "Worker: " + e.getWorkerName() + "Total: " + e.getTotal()	;
//                // Writing Contacts to log
//        Log.d("Name: ", log);
//    }
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
