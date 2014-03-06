package com.emRoXRIPRAP.logger.screens;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emRoXRIPRAP.logger.Constants;
import com.emRoXRIPRAP.logger.DbHandler;
import com.emRoXRIPRAP.logger.Entry;
import com.emRoXRIPRAP.logger.R;

public class DataEntryScreen extends Activity implements OnFocusChangeListener,Constants{

	private TextView date;
	private TextView tvTotal;
	private Button enterButton;
	private Button deleteButton;
	private CheckBox cbIsEntered;
	private AutoCompleteTextView etAddressMain;
	private EditText etHours;
	private EditText etLaborRate;
	private EditText etMaterialCost;
	private EditText etMaterialMarkup;
	private EditText etWork;
	private Boolean isUpdate=false;
	private Entry entryToUpdate;
	private int id;
	private boolean useLaborRatePref;
	private boolean useMarkupRatePref;
	private String laborRatePrefVal;
	private String markupRatePrefVal;
	DbHandler db = new DbHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_entry_screen);
		setupWidgets();
		loadPrefs();
		
		
	}
	private void loadPrefs() {
		SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		useLaborRatePref = myPrefs.getBoolean("auto_value_labor_rate", false);
		useMarkupRatePref = myPrefs.getBoolean("auto_value_markup_rate", false);
		if(useLaborRatePref && isUpdate == false){
			laborRatePrefVal = myPrefs.getString("labor_rate_value", "");
			etLaborRate.setText(laborRatePrefVal);
		}
		if(useMarkupRatePref && isUpdate == false){
			markupRatePrefVal = myPrefs.getString("markup_rate_value", "");
			etMaterialMarkup.setText(markupRatePrefVal);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void setupWidgets(){
		date= (TextView)findViewById(R.id.tv_date);
		Intent intent = getIntent();
		final String dateString = intent.getStringExtra("date");
		date.setText(dateString);
		isUpdate = intent.getBooleanExtra("update", false);
		
		etAddressMain = (AutoCompleteTextView)findViewById(R.id.et_address_main);
		getAddressesForAutoCompleteField();
		cbIsEntered = (CheckBox)findViewById(R.id.cb_entered_or_not);
		etHours = (EditText)findViewById(R.id.et_hours);
		etLaborRate = (EditText)findViewById(R.id.et_labor_rate);
		etMaterialCost = (EditText)findViewById(R.id.et_material_cost);
		etMaterialMarkup = (EditText)findViewById(R.id.et_material_markup);
		tvTotal = (TextView)findViewById(R.id.tv_total);
		etWork = (EditText)findViewById(R.id.et_description);
		
		etHours.setOnFocusChangeListener(this);
		etLaborRate.setOnFocusChangeListener(this);
		etMaterialCost.setOnFocusChangeListener(this);
		etMaterialMarkup.setOnFocusChangeListener(this);
		
		
		deleteButton = (Button)findViewById(R.id.b_delete_entry_from_db);
		enterButton = (Button)findViewById(R.id.b_enter_data_to_db);
		if(isUpdate){
			deleteButton.setVisibility(View.VISIBLE);
			final DbHandler db = new DbHandler(this);
			id =intent.getIntExtra("id", 0);
			if(id != 0){
				entryToUpdate =db.getEntry(id);
			}
			populateFieldsWithDbValues(entryToUpdate);
			enterButton.setText("Update");
			
			deleteButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {

					int result = db.deleteEntry(entryToUpdate);
					finish();
					if(result == 1){
						Toast.makeText(DataEntryScreen.this, "Entry deleted", Toast.LENGTH_LONG).show();
					}else
						Toast.makeText(DataEntryScreen.this, "Error deleting entry", Toast.LENGTH_LONG).show();
						
				}
				
			});
		}else{
			enterButton.setText("Enter");
		}
		
		enterButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(checkFields()){
					String total = tallyTotal();
					//create entry object
					Entry entry = new Entry(dateString,
											String.valueOf(cbIsEntered.isChecked()),
											etAddressMain.getText().toString(),
											etHours.getText().toString(),
											etLaborRate.getText().toString(),
											etMaterialCost.getText().toString(),
											etMaterialMarkup.getText().toString(),
											total,etWork.getText().toString());
					if(isUpdate){
						entry.setId(id);
						updateEntryInDb(entry);
					}else{
					enterDataToDb(entry);
					}
				}
			}




			
		});
	}
	private void getAddressesForAutoCompleteField() {
		List<Entry> completeList = db.getAllEntries();
		List<String>addressList = new ArrayList<String>();		
		for(Entry e: completeList){
			String s = e.getAddressMain();
			addressList.add(s);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addressList);
		etAddressMain.setAdapter(adapter);
	}
	private void populateFieldsWithDbValues(Entry entry) {
		etAddressMain.setText(entry.getAddressMain());
		etHours.setText(entry.getLaborHours());
		etLaborRate.setText(entry.getLaborRate());
		etMaterialCost.setText(entry.getMaterialCost());
		etMaterialMarkup.setText(entry.getMaterialMarkup());
		tvTotal.setText(entry.getTotal());
		etWork.setText(entry.getWork());
		if(entry.getIsEntered().equalsIgnoreCase("true")){
			cbIsEntered.setChecked(true);
		}else{
			cbIsEntered.setChecked(false);
		}
		
		
	}
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		Boolean check;
		check = validateField(etHours);
		check = validateField(etLaborRate);
		check = validateField(etMaterialCost);
		check = validateField(etMaterialMarkup);
		//all fields are filled...calculate total
		if(check){
			tvTotal.setText(tallyTotal());
		}
		else
			tvTotal.setText("NMI");
	
	}
	
	private String tallyTotal() {
		double hours = Double.parseDouble(etHours.getText().toString());
		double lr = Double.parseDouble(etLaborRate.getText().toString());
		double matCost = Double.parseDouble(etMaterialCost.getText().toString());
		double matMarkup = Double.parseDouble(etMaterialMarkup.getText().toString());
		double tot = hours * lr + matCost + matCost * matMarkup;
		String total = "$" +String.format("%.2f", tot);
		return total;
	}
	
	private Boolean checkFields(){
		
		if(validateField(etAddressMain) == false || validateField(etHours) == false || validateField(etLaborRate) == false || 
		   validateField(etMaterialCost) == false || validateField(etMaterialMarkup) == false){
			Toast.makeText(this, "Check Fields. One is empty dummy", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
	private Boolean validateField(EditText et){
		if(et.getText().toString().trim().matches("")){
			return false;
		}
		return true;
		
	}
	private void enterDataToDb(Entry entry){
		DbHandler db = new DbHandler(this);
		db.deleteAllEntries();
		testModeQuickEntryOfFieldsIntoDataBase(db);
		finish();
		if(db.addEntry(entry)){
			Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
			finish();
		}else
			Toast.makeText(this, "ERROR Saving Data", Toast.LENGTH_LONG).show();
			finish();
	}
	private void updateEntryInDb(Entry entry) {
		DbHandler db = new DbHandler(this);
		int result = db.updateEntry(entry);
		finish();
//		Log.d("VALUW ODF RESULT IS: ",""+ result);
		if(result > 0){
			Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();

		}else{
			Toast.makeText(this, "Error Saving Data", Toast.LENGTH_LONG).show();

		}
	}
	private void testModeQuickEntryOfFieldsIntoDataBase(DbHandler db) {
		Entry one = new Entry("Wednesday 02-12-2014","true","123 Main Street","8.0","35","12.90",".15", "120.00","winterization");
		Entry two = new Entry("Wednesday 02-12-2014","false","137 Ski Mobile","18.0","35","120.20",".15","120.00","anual ins");
		Entry thr = new Entry("Wednesday 02-12-2014","false","Settler's Green","5.5","35","87.25",".15","120.00","plumbing");
		Entry fou = new Entry("Saturday 04-12-2014","true","19 Prospect St","10.25","35","360.25",".10", "120.00","repair roof");
		Entry fiv = new Entry("Wednesday 02-19-2014","false","185 Pine Knoll Ter","8.0","35","12.90",".15", "120.00","plowing");
		db.addEntry(one);
		db.addEntry(two);
		db.addEntry(thr);
		db.addEntry(fou);
		db.addEntry(fiv);
		
	}
	


}
