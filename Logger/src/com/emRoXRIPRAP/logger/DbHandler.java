package com.emRoXRIPRAP.logger;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.emRoXRIPRAP.logger.DBContract.DataEntry;

public class DbHandler extends SQLiteOpenHelper {


	public DbHandler(Context context) {
		super(context, DBContract.DB_NAME, null, DBContract.DB_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {		
		db.execSQL(DataEntry.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//drop table if it exists
		db.execSQL("DROP TABLE IF EXISTS" + DataEntry.TABLE_NAME);		
		//create table again
		onCreate(db);
	}
	
	public void deleteAllEntries(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + DataEntry.TABLE_NAME);

	}
	
	public Boolean addEntry(Entry entry){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataEntry.COLUMN_NAME_DATE, entry.getDate());
		values.put(DataEntry.COLUMN_NAME_ADDRESS_MAIN, entry.getAddressMain());
		values.put(DataEntry.COLUMN_NAME_ADDRESS_SECONDARY,entry.getAddressSecondary());
		values.put(DataEntry.COLUMN_NAME_LABOR_HOURS, entry.getLaborHours());
		values.put(DataEntry.COLUMN_NAME_LABOR_RATE, entry.getLaborRate());
		values.put(DataEntry.COLUMN_NAME_MATERIAL_COST, entry.getMaterialCost());
		values.put(DataEntry.COLUMN_NAME_MATERIAL_MARKUP, entry.getMaterialMarkup());
		values.put(DataEntry.COLUMN_NAME_WORKER_NAME, entry.getWorkerName());
		values.put(DataEntry.COLUMN_NAME_TOTAL, entry.getTotal());
		
		long finished =db.insert(DataEntry.TABLE_NAME, null, values);
		db.close();
		if(finished != -1){
			return true;
		}else
			return false;
	}
	
	public Entry getEntry(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(DataEntry.TABLE_NAME, new String[] {String.valueOf(DataEntry.COLUMN_NAME_ENTRY_ID), 
																	DataEntry.COLUMN_NAME_ADDRESS_MAIN,
																	DataEntry.COLUMN_NAME_ADDRESS_SECONDARY,
																	DataEntry.COLUMN_NAME_LABOR_HOURS,
																	DataEntry.COLUMN_NAME_LABOR_RATE,
																	DataEntry.COLUMN_NAME_MATERIAL_COST,
																	DataEntry.COLUMN_NAME_MATERIAL_MARKUP,
																	DataEntry.COLUMN_NAME_WORKER_NAME,
																	DataEntry.COLUMN_NAME_TOTAL
																	}, 
																	DataEntry.COLUMN_NAME_ENTRY_ID + "=?",
																	new String[] {String.valueOf(id) },
																	null,null,null,null
																	);
		if(cursor !=null){
			cursor.moveToFirst();
		}
		Entry entry = new Entry(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
								cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
		entry.setId(cursor.getInt(0));
		return entry;
	}
	
	public List<Entry> getAllEntries(){
		   List<Entry> entryList = new ArrayList<Entry>();
		    // Select All Query
		    String selectQuery = "SELECT  * FROM " + DataEntry.TABLE_NAME;
		 
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);

		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
String lId = cursor.getString(cursor.getColumnIndex(DataEntry.COLUMN_NAME_ENTRY_ID));
Log.d("LOG LOG LOG lId Value STRINGVALUE: ","" + lId);
int i = Integer.parseInt(lId);

		            Entry entry = new Entry();
		            entry.setId(i);
		            entry.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.COLUMN_NAME_DATE)));
		            entry.setAddressMain(cursor.getString(2));
		            entry.setAddressSecondary(cursor.getString(3));
		            entry.setLaborHours(cursor.getString(4));
		            entry.setLaborRate(cursor.getString(5));
		            entry.setMaterialCost(cursor.getString(6));
		            entry.setMaterialMarkup(cursor.getString(7));
		            entry.setWorkerName(cursor.getString(8));
		            entry.setTotal(cursor.getString(9));
		            // Adding contact to list
		            entryList.add(entry);
		        } while (cursor.moveToNext());
		    }
		 
		    // return entry list
		    return entryList;
	}
	
	public int updateEntry(Entry entry){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataEntry.COLUMN_NAME_DATE, entry.getDate());
		values.put(DataEntry.COLUMN_NAME_ADDRESS_MAIN, entry.getAddressMain());
		values.put(DataEntry.COLUMN_NAME_ADDRESS_SECONDARY,entry.getAddressSecondary());
		values.put(DataEntry.COLUMN_NAME_LABOR_HOURS, entry.getLaborHours());
		values.put(DataEntry.COLUMN_NAME_LABOR_RATE, entry.getLaborRate());
		values.put(DataEntry.COLUMN_NAME_MATERIAL_COST, entry.getMaterialCost());
		values.put(DataEntry.COLUMN_NAME_MATERIAL_MARKUP, entry.getMaterialMarkup());
		values.put(DataEntry.COLUMN_NAME_WORKER_NAME, entry.getWorkerName());
		values.put(DataEntry.COLUMN_NAME_TOTAL, entry.getTotal());
		
		//updating row
		return db.update(DataEntry.TABLE_NAME, values, DataEntry.COLUMN_NAME_ENTRY_ID + " =?", new String[]{String.valueOf(entry.getId())});

	}
	
	public void deleteEntry( Entry entry){
		   SQLiteDatabase db = this.getWritableDatabase();
		   db.delete(DataEntry.TABLE_NAME, DataEntry.COLUMN_NAME_ENTRY_ID + " = ?",
		            new String[] {String.valueOf(entry.getId()) });
		   db.close();
	}
	

}
