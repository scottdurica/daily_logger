package com.emRoXRIPRAP.logger.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emRoXRIPRAP.logger.DbHandler;
import com.emRoXRIPRAP.logger.Entry;
import com.emRoXRIPRAP.logger.R;

public class DataEntryScreen extends Activity implements OnFocusChangeListener{

	private TextView date;
	private TextView tvTotal;
	private Button enterButton;

	private EditText etAddressMain;
	private EditText etAddressSecondary;
	private EditText etHours;
	private EditText etLaborRate;
	private EditText etMaterialCost;
	private EditText etMaterialMarkup;
	private EditText etWorkerName;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_entry_screen);
		
		setupWidgets();
		
		
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
		
		etAddressMain = (EditText)findViewById(R.id.et_address_main);
		etAddressSecondary = (EditText)findViewById(R.id.et_address_secondary);
		etHours = (EditText)findViewById(R.id.et_hours);
		etLaborRate = (EditText)findViewById(R.id.et_labor_rate);
		etMaterialCost = (EditText)findViewById(R.id.et_material_cost);
		etMaterialMarkup = (EditText)findViewById(R.id.et_material_markup);
		etWorkerName = (EditText)findViewById(R.id.et_worker_name);
		tvTotal = (TextView)findViewById(R.id.tv_total);
		
		etHours.setOnFocusChangeListener(this);
		etLaborRate.setOnFocusChangeListener(this);
		etMaterialCost.setOnFocusChangeListener(this);
		etMaterialMarkup.setOnFocusChangeListener(this);
		
		enterButton = (Button)findViewById(R.id.b_enter_data_to_db);
		
		enterButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(checkFields()){
					String total = tallyTotal();
					//create entry object
					Entry entry = new Entry(dateString,
											etAddressMain.getText().toString(),
											etAddressSecondary.getText().toString(),
											etHours.getText().toString(),
											etLaborRate.getText().toString(),
											etMaterialCost.getText().toString(),
											etMaterialMarkup.getText().toString(),
											etWorkerName.getText().toString(),
											total);
					enterDataToDb(entry);

				}
			}
			
		});
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
		Boolean check;
		check = validateField(etAddressMain);
		check = validateField(etAddressSecondary);
		check = validateField(etHours);
		check = validateField(etLaborRate);
		check = validateField(etMaterialCost);
		check = validateField(etMaterialMarkup);
		check = validateField(etWorkerName);
		
		if(check) return true;
		else{
			Toast.makeText(this, "Check Fields. One is empty dummy", Toast.LENGTH_LONG).show();
			return false;
		}
		
		
	}
	private Boolean validateField(EditText et){
		if(et.getText().toString().trim().matches("")){
			return false;
		}
		return true;
		
	}
	private void enterDataToDb(Entry entry){
		DbHandler db = new DbHandler(this);
//		db.deleteAllEntries();
//		testModeQuickEntryOfFieldsIntoDataBase(db);
//		finish();
		if(db.addEntry(entry)){
			Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
			finish();
		}else
			Toast.makeText(this, "ERROR Saving Data", Toast.LENGTH_LONG).show();
			finish();
	}

	private void testModeQuickEntryOfFieldsIntoDataBase(DbHandler db) {
		Entry one = new Entry("Wednesday 02-12-2014","123 Main Street","Apt 3","8.0","35","12.90",".15","Scott", "120.00");
		Entry two = new Entry("Wednesday 02-12-2014","137 Ski Mobile","Apt 1","18.0","35","120.20",".15","Scott", "120.00");
		Entry thr = new Entry("Wednesday 02-12-2014","Settler's Green","Unit 29","5.5","35","87.25",".15","Scott", "120.00");
		Entry fou = new Entry("Saturday 04-12-2014","19 Prospect St","Lancaster","10.25","35","360.25",".10","Scott", "120.00");
		Entry fiv = new Entry("Wednesday 02-19-2014","185 Pine Knoll Ter","Lisbon","8.0","35","12.90",".15","Scott", "120.00");
		db.addEntry(one);
		db.addEntry(two);
		db.addEntry(thr);
		db.addEntry(fou);
		db.addEntry(fiv);
		
	}
	


}
