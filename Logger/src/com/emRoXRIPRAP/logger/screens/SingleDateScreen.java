package com.emRoXRIPRAP.logger.screens;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.emRoXRIPRAP.logger.DbHandler;
import com.emRoXRIPRAP.logger.Entry;
import com.emRoXRIPRAP.logger.R;

public class SingleDateScreen extends Activity{

	
	private  List<Entry> entryList;
	DbHandler db = new DbHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_date_list_screen);
		
		Intent i = getIntent();
		String date = i.getStringExtra("date");
		entryList = db.getEntriesForDate(date);
		
		ArrayAdapter<Entry> adapter = new MyCustomAdapter();
		ListView listView =(ListView)findViewById(R.id.lv_sdlv_listview);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {

				
				
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
			TextView addressApt = (TextView)rowView.findViewById(R.id.tv_li_apt);
			TextView hours = (TextView)rowView.findViewById(R.id.tv_li_hours_rate_total);
			TextView materials = (TextView)rowView.findViewById(R.id.tv_li_material_markup_total);
			TextView total = (TextView)rowView.findViewById(R.id.tv_li_total);
			
			Entry e = entryList.get(position);
			addressMain.setText(e.getAddressMain());
			addressApt.setText(e.getAddressSecondary());
			double h = Double.parseDouble(e.getLaborHours());
			double r = Double.parseDouble(e.getLaborRate());
			double tot = h * r;
			hours.setText(e.getLaborHours() + "/" + e.getLaborRate() + "/ $" + tot );
			double mc = Double.parseDouble(e.getMaterialCost());
			double mu = Double.parseDouble(e.getMaterialMarkup());
			double mcmu = mc * mu + mc;
			materials.setText(e.getMaterialCost() + "/" + e.getMaterialMarkup() + "/ $" + mcmu);
			total.setText("$" + String.format("%.2f",mcmu+tot));
			return rowView;

		}

	}
}
