package com.emRoXRIPRAP.logger;

import java.util.ArrayList;
import java.util.List;

import com.emRoXRIPRAP.logger.screens.DataViewScreen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomAdapter extends BaseAdapter{

	List<Entry> entryList;
	
	public CustomAdapter(List<Entry> list){
		this.entryList = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entryList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
   
}
//package com.mkyong.android.adaptor;
//
//import com.mkyong.android.R;
// 
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
// 
//public class MobileArrayAdapter extends ArrayAdapter<String> {
//	private final Context context;
//	private final String[] values;
// 
//	public MobileArrayAdapter(Context context, String[] values) {
//		super(context, R.layout.list_mobile, values);
//		this.context = context;
//		this.values = values;
//	}
// 
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		LayoutInflater inflater = (LayoutInflater) context
//			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
// 
//		View rowView = inflater.inflate(R.layout.list_mobile, parent, false);
//		TextView textView = (TextView) rowView.findViewById(R.id.label);
//		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
//		textView.setText(values[position]);
// 
//		// Change icon based on name
//		String s = values[position];
// 
//		System.out.println(s);
// 
//		if (s.equals("WindowsMobile")) {
//			imageView.setImageResource(R.drawable.windowsmobile_logo);
//		} else if (s.equals("iOS")) {
//			imageView.setImageResource(R.drawable.ios_logo);
//		} else if (s.equals("Blackberry")) {
//			imageView.setImageResource(R.drawable.blackberry_logo);
//		} else {
//			imageView.setImageResource(R.drawable.android_logo);
//		}
// 
//		return rowView;
//	}
//}