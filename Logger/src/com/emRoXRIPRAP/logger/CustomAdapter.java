package com.emRoXRIPRAP.logger;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Entry>{
	private final Context context;
//	private final String[] values;
// 
//	public MobileArrayAdapter(Context context, String[] values) {
//		super(context, R.layout.list_mobile, values);
//		this.context = context;
//		this.values = values;
//	}
	private static List<Entry> entryList;
	
	public CustomAdapter(Context context, List<Entry> list){
		super(context, R.layout.listview_item, entryList);
		this.context = context;
		this.entryList = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entryList.size();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listview_item, parent,false);
		
		TextView addressMain = (TextView)rowView.findViewById(R.id.tv_li_address_main);
		TextView addressApt = (TextView)rowView.findViewById(R.id.tv_li_total);
		TextView hours = (TextView)rowView.findViewById(R.id.tv_li_hours_rate_total);
		TextView materials = (TextView)rowView.findViewById(R.id.tv_li_material_markup_total);
		TextView total = (TextView)rowView.findViewById(R.id.tv_li_total);
		
		Entry e = entryList.get(position);
		addressMain.setText(e.getAddressMain());
		addressApt.setText(e.getAddressSecondary());
		double h = Double.parseDouble(e.getLaborHours());
		double r = Double.parseDouble(e.getLaborRate());
		double tot = h * r;
		hours.setText(e.getLaborHours() + " / " + e.getLaborRate() + " / $" + tot );
		double mc = Double.parseDouble(e.getMaterialCost());
		double mu = Double.parseDouble(e.getMaterialMarkup());
		double mcmu = mc * mu + mc;
		materials.setText(e.getMaterialCost() + " / " + e.getMaterialMarkup() + " / $" + mcmu);
		total.setText("$" + mcmu + tot);
		return rowView;
		

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