package com.emRoXRIPRAP.logger;

import android.provider.BaseColumns;

public final class DBContract implements Constants {
	
	public static final String DB_NAME = "my_logger_db";
	public static final int DB_VERSION = 1;

	//empty constructor to prevent instantiation of this class!
	public DBContract(){}
	
	//Inner classes that define the table contents
	
	public static abstract class DataEntry implements BaseColumns{
		
		public static final String TABLE_NAME = "log_data";
		
        public static String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_ADDRESS_MAIN = "address_main";
        public static final String COLUMN_NAME_IS_ENTERED = "is_entered";
        public static final String COLUMN_NAME_LABOR_HOURS = "labor_hours";
        public static final String COLUMN_NAME_LABOR_RATE = "labor_rate";
        public static final String COLUMN_NAME_MATERIAL_COST = "material_cost";
        public static final String COLUMN_NAME_MATERIAL_MARKUP = "material_markup";
        public static final String COLUMN_NAME_TOTAL = "total";
        public static final String COLUMN_NAME_WORK = "work";

		public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                								+ COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
                								+ COLUMN_NAME_DATE + " TEXT,"
                								+ COLUMN_NAME_IS_ENTERED + " TEXT,"
                								+ COLUMN_NAME_ADDRESS_MAIN + " TEXT," 
                								+ COLUMN_NAME_LABOR_HOURS + " TEXT,"
                								+ COLUMN_NAME_LABOR_RATE + " TEXT," 
                								+ COLUMN_NAME_MATERIAL_COST + " TEXT,"
                								+ COLUMN_NAME_MATERIAL_MARKUP + " TEXT," 
                								+ COLUMN_NAME_TOTAL + " TEXT,"
                								+ COLUMN_NAME_WORK + " TEXT"
                								+ ")";
	}

}
