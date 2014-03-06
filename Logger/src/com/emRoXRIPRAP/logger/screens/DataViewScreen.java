package com.emRoXRIPRAP.logger.screens;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.emRoXRIPRAP.logger.Constants;
import com.emRoXRIPRAP.logger.DbHandler;
import com.emRoXRIPRAP.logger.Entry;
import com.emRoXRIPRAP.logger.R;

public class DataViewScreen extends Activity implements Constants{
	
	private List<Entry> entryList;
	private List<Date> newList;
	private List<String> listForView;
	private MyCustomAdapter adapter;
	private ListView listView;
	private DbHandler db = new DbHandler(this);
	private SimpleDateFormat sdf = new SimpleDateFormat("EEEE MM-dd-yyyy");

	@Override
	protected void onResume() {
		super.onResume();
		entryList.clear();
		listForView.clear();
		listForView = getDates();
		adapter.notifyDataSetChanged();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dates_listview_screen);
		listForView = getDates();
		adapter = new MyCustomAdapter();
		listView =(ListView)findViewById(R.id.lv_dls_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String dateString = listForView.get(position);			
				Intent i = new Intent(DataViewScreen.this, SingleDateScreen.class);
				i.putExtra("date", dateString);
				startActivity(i);
				Log.d(tag, dateString);
			}
		});
		
	}

	private List<String> getDates(){
	
		entryList= db.getAllEntries();
		newList = new ArrayList<Date>();
		
		Set<String> set = new HashSet<String>();
		for(Entry entry: entryList){
			set.add(entry.getDate());			
		}
		
		for(String d: set){
			try {
				Date date = sdf.parse(d);
//				String s = sdf.format(date);
//				Log.d("LOG LOG SSSSSS ",s);
//				Log.d("LOG LOG DDDDDD ",""+date);	
				
				newList.add(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(newList);
		List<String> stringList = new ArrayList<String>();
		for(Date d: newList){
			String s = sdf.format(d);
			stringList.add(s);
		}
		return stringList;
	}
	
	private class MyCustomAdapter extends ArrayAdapter<String>{
		

		
		public MyCustomAdapter(){
			super(DataViewScreen.this, R.layout.listview_item, listForView);
			
		}
		
		@Override
		public int getCount() {
			return listForView.size();
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;
			if(rowView == null){
				rowView = getLayoutInflater().inflate(R.layout.dates_listview_item, parent, false);
			}
			TextView tvDate = (TextView)rowView.findViewById(R.id.tv_dli_date);
			ImageView image = (ImageView)rowView.findViewById(R.id.i_dli_image);
			
			
			String d = listForView.get(position);
			
			List<Entry> list = db.getEntriesForDate(d);
			Boolean dateHasUnenteredVals = false;
			
			for(Entry e: list){
				
//				Log.d("Log Log Log LOg LOg ","item is entered: " + e.getIsEntered() + " Date: " + d);
				if(e.getIsEntered().equalsIgnoreCase("false")){
					dateHasUnenteredVals = true;
					break;
				}
			}
			
			tvDate.setText(listForView.get(position));
			if(dateHasUnenteredVals){
				image.setImageResource(R.drawable.entered_status_no);
			}else{
				image.setImageResource(R.drawable.entered_status_yes);
			}
			
//			Entry e = entryList.get(position);
//			addressMain.setText(e.getAddressMain());
//			Log.d("VALUE OF THE CHECKBVOSX: ",e.getIsEntered() );
//			if(e.getIsEntered().equalsIgnoreCase("true")){
//				image.setImageResource(R.drawable.entered_status_yes);
//			}else image.setImageResource(R.drawable.entered_status_no);
//			double h = Double.parseDouble(e.getLaborHours());
//			double r = Double.parseDouble(e.getLaborRate());
//			double tot = h * r;
//			String labTot = String.format("%.2f",tot);
//			hours.setText(e.getLaborHours() + "/" + e.getLaborRate() + "/ $" + labTot);
//			double mc = Double.parseDouble(e.getMaterialCost());
//			double mu = Double.parseDouble(e.getMaterialMarkup());
//			double mcmu = mc * mu + mc;
//			String matTot = String.format("%.2f", mcmu);
//			materials.setText(e.getMaterialCost() + "/" + e.getMaterialMarkup() + "/ $" + matTot);
//			total.setText("$" + String.format("%.2f",mcmu+tot));
			return rowView;

		}

	}
}
