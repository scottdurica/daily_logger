package com.emRoXRIPRAP.logger.screens;

import java.util.List;

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

import com.emRoXRIPRAP.logger.DbHandler;
import com.emRoXRIPRAP.logger.Entry;
import com.emRoXRIPRAP.logger.R;

public class SingleDateScreen extends Activity{

	ArrayAdapter<Entry> adapter;
	ListView listView;
	private  List<Entry> entryList;
	DbHandler db = new DbHandler(this);
	private String date;
	@Override
	protected void onResume() {

		super.onResume();
		entryList.clear();
		entryList = db.getEntriesForDate(date);
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_date_list_screen);
		Intent i = getIntent();
		date = i.getStringExtra("date");
		entryList = db.getEntriesForDate(date);
		TextView tvDate = (TextView)findViewById(R.id.tv_sdls_date);
		tvDate.setText(date);
		
		adapter = new MyCustomAdapter();
		listView =(ListView)findViewById(R.id.lv_sdlv_listview);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {

				Entry e = entryList.get(pos);
				int dbId = e.getId();
				Intent i = new Intent(SingleDateScreen.this,DataEntryScreen.class);
				i.putExtra("id", dbId);
//				Log.d("VALUE OF ITEM ID IS: ",""+ dbId);
				i.putExtra("date", date);
				i.putExtra("update",true);
				startActivity(i);
				
				
			}
			
		});
	}
	
	private class MyCustomAdapter extends ArrayAdapter<Entry>{
		

		
		public MyCustomAdapter(){
			super(SingleDateScreen.this, R.layout.listview_item, entryList);
			
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return entryList.size();
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;
			if(rowView == null){
				rowView = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
			}
			TextView addressMain = (TextView)rowView.findViewById(R.id.tv_li_address_main);
			TextView hours = (TextView)rowView.findViewById(R.id.tv_li_hours_rate_total);
			TextView materials = (TextView)rowView.findViewById(R.id.tv_li_material_markup_total);
			TextView total = (TextView)rowView.findViewById(R.id.tv_li_total);
			ImageView image = (ImageView)rowView.findViewById(R.id.i_li_entered_or_not);
			
			Entry e = entryList.get(position);
			addressMain.setText(e.getAddressMain());
			Log.d("VALUE OF THE CHECKBVOSX: ",e.getIsEntered() );
			if(e.getIsEntered().equalsIgnoreCase("true")){
				image.setImageResource(R.drawable.entered_status_yes);
			}else image.setImageResource(R.drawable.entered_status_no);
			double h = Double.parseDouble(e.getLaborHours());
			double r = Double.parseDouble(e.getLaborRate());
			double tot = h * r;
			String labTot = String.format("%.2f",tot);
			hours.setText(e.getLaborHours() + "/" + e.getLaborRate() + "/ $" + labTot);
			double mc = Double.parseDouble(e.getMaterialCost());
			double mu = Double.parseDouble(e.getMaterialMarkup());
			double mcmu = mc * mu + mc;
			String matTot = String.format("%.2f", mcmu);
			materials.setText(e.getMaterialCost() + "/" + e.getMaterialMarkup() + "/ $" + matTot);
			total.setText("$" + String.format("%.2f",mcmu+tot));
			return rowView;

		}

	}
}
