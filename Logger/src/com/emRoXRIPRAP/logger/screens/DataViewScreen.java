package com.emRoXRIPRAP.logger.screens;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.emRoXRIPRAP.logger.DbHandler;
import com.emRoXRIPRAP.logger.Entry;

public class DataViewScreen extends ListActivity{
	
	LayoutInflater inflater;
	List<Entry> entryList;
	List<Date> newList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<String> listForView = getDates();
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listForView));
		
		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	private List<String> getDates(){
		DbHandler db = new DbHandler(this);
		entryList= db.getAllEntries();
		newList = new ArrayList<Date>();
		
		Set<String> set = new HashSet<String>();
		for(Entry entry: entryList){
			set.add(entry.getDate());			
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE MM-dd-yyyy");
		for(String d: set){
			try {
				Date date = sdf.parse(d);
				String s = sdf.format(date);
//				Log.d("LOG LOG SSSSSS ",s);
//				Log.d("LOG LOG DDDDDD ",""+date);	
				
				newList.add(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
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
	
}
