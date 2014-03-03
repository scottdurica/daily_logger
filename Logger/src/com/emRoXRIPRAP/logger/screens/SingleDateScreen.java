package com.emRoXRIPRAP.logger.screens;

import android.app.ListActivity;
import android.os.Bundle;

public class SingleDateScreen extends ListActivity{

	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		List<String> listForView = getDates();
//		
//		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listForView));
//		
//		ListView listView = getListView();
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//			    // When clicked, show a toast with the TextView text
//				String dateString = ((TextView)view).getText().toString();
//				Log.d("The value of the dateString is: ",dateString);
//				Date d = null;
//				try {
//					d = sdf.parse(dateString);
//					Log.d("The value of the d is: ","" + d);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				List<Entry> theList = db.getEntriesForDate(dateString);
//				int counter = 0;
//				for(Entry e: theList){
//					counter++;
//				}
//			    Toast.makeText(getApplicationContext(),
//				"The count for this date: " + counter, Toast.LENGTH_SHORT).show();
//			}
//		});
		
	}
}
