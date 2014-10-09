package com.alejandrosoret.asmweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*************************************************************/
/*                                                           */ 
/* WeatherDbHelper                                           */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: WeatherDbHelper Class                        */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class WeatherDbHelper extends SQLiteOpenHelper
{
	public static final String	DATABASE_NAME		= "ASMWeather.db";
	public static final int		DATABASE_VERSION	= 1;
	
	/*********************************************************/
	/*                                                       */ 
	/* WeatherDbHelper.WeatherDbHelper()                     */ 
	/*                                                       */ 
	/*********************************************************/
	public WeatherDbHelper( Context context )
	{
		super( context, DATABASE_NAME, null, DATABASE_VERSION );
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* WeatherDbHelper.onCreate()                            */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public void onCreate( SQLiteDatabase db )
     {
		db.execSQL( SQL_ASMWEATHER_CREATE_CITY_TABLE_STMT );
		db.execSQL( SQL_ASMWEATHER_CREATE_CONDITION_TABLE_STMT );
		db.execSQL( SQL_JMFWEATHER_CREATE_FORECAST_TABLE_STMT );
     }

	/*********************************************************/
	/*                                                       */ 
	/* WeatherDbHelper.onUpgrade()                           */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
     {
		db.execSQL( SQL_ASMWEATHER_DROP_CITY_TABLE_STMT );
		db.execSQL( SQL_JMFWEATHER_DROP_CONDITION_TABLE_STMT );
		db.execSQL( SQL_JMFWEATHER_DROP_FORECAST_TABLE_STMT );
     }

	/*********************************************************/
	/*                                                       */ 
	/* Create table SQL statements                           */ 
	/*                                                       */ 
	/*********************************************************/
	private static final String SQL_ASMWEATHER_CREATE_CITY_TABLE_STMT = "CREATE TABLE " + 
			WeatherDbContract.CCityTable.TABLE_NAME + 
			" (" +
			WeatherDbContract.CCityTable._ID									+	" INTEGER PRIMARY KEY AUTOINCREMENT, " +
			WeatherDbContract.CCityTable.COLUMN_NAME_NAME						+ 	" TEXT, " +
			WeatherDbContract.CCityTable.COLUMN_NAME_COUNTRY						+ 	" TEXT, " +
			WeatherDbContract.CCityTable.COLUMN_NAME_LATITUDE						+ 	" TEXT, " +
			WeatherDbContract.CCityTable.COLUMN_NAME_LONGITUDE					+ 	" TEXT, " +
			WeatherDbContract.CCityTable.COLUMN_NAME_POPULATION					+ 	" LONG, " +
			WeatherDbContract.CCityTable.COLUMN_NAME_URL							+ 	" TEXT"   +
			" );";

	private static final String SQL_ASMWEATHER_CREATE_CONDITION_TABLE_STMT = "CREATE TABLE " + 
			WeatherDbContract.CConditionTable.TABLE_NAME + 
			" (" +
			WeatherDbContract.CConditionTable._ID								+	" INTEGER PRIMARY KEY AUTOINCREMENT, " +
			WeatherDbContract.CConditionTable.COLUMN_NAME_CITY_ID					+ 	" INTEGER, " 	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_CLOUD_COVERAGE			+ 	" INTEGER, " 	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_OBSERVATION_TIME			+ 	" LONG, " 	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_PRESSURE					+ 	" INTEGER, " 	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_TEMPERATURE_CELSIUS		+ 	" INTEGER, " 	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_VISIBILITY				+ 	" INTEGER, " 	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_TEMPERATURE_FAHRENHEIT		+ 	" INTEGER, "  	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_WIND_SPEED_MPH			+ 	" INTEGER, "  	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_PRECIPITATION				+ 	" REAL, "   	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_WIND_DIRECTION_DEGREES		+ 	" INTEGER, "  	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_WIND_DIRECTION_COMPASS		+ 	" TEXT, "   	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_ICON_URL					+ 	" TEXT, "   	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_HUMIDITY					+ 	" INTEGER, "  	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_WIND_SPEED_KMPH			+ 	" INTEGER, "  	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_WEATHER_CODE				+ 	" INTEGER, "  	+
			WeatherDbContract.CConditionTable.COLUMN_NAME_WEATHER_DESCRIPTION		+ 	" TEXT"   	+
			" );";

	private static final String SQL_JMFWEATHER_CREATE_FORECAST_TABLE_STMT = "CREATE TABLE " + 
			WeatherDbContract.CForecastTable.TABLE_NAME + 
			" (" +
			WeatherDbContract.CForecastTable._ID								+	" INTEGER PRIMARY KEY AUTOINCREMENT, " +
			WeatherDbContract.CForecastTable.COLUMN_NAME_CITY_ID					+ 	" INTEGER, " 	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_ICON_URL					+ 	" TEXT, "   	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_MIN_TEMPERATURE_CELSIUS		+ 	" INTEGER, " 	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_WIND_SPEED_MPH				+ 	" INTEGER, "  	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_WIND_SPEED_KMPH			+ 	" INTEGER, "  	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_WIND_DIRECTION				+ 	" TEXT, "   	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_MAX_TEMPERATURE_CELSIUS		+ 	" INTEGER, " 	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_FORECAST_DATE				+ 	" LONG, " 	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_WEATHER_CODE				+ 	" INTEGER, "  	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_MAX_TEMPERATURE_FAHRENHEIT	+ 	" INTEGER, "  	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_PRECIPITATION				+ 	" REAL, "   	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_WIND_DIRECTION_DEGREES		+ 	" INTEGER, "  	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_WIND_DIRECTION_COMPASS		+ 	" TEXT, "   	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_WEATHER_DESCRIPTION			+ 	" TEXT, "   	+
			WeatherDbContract.CForecastTable.COLUMN_NAME_MIN_TEMPERATURE_FAHRENHEIT	+ 	" INTEGER"   	+
			" );";

	/*********************************************************/
	/*                                                       */ 
	/* Drop table SQL statements                             */ 
	/*                                                       */ 
	/*********************************************************/
	private static final String SQL_ASMWEATHER_DROP_CITY_TABLE_STMT = 
			"DROP TABLE IF EXISTS " + WeatherDbContract.CCityTable.TABLE_NAME + ";";

	private static final String SQL_JMFWEATHER_DROP_CONDITION_TABLE_STMT = 
			"DROP TABLE IF EXISTS " + WeatherDbContract.CConditionTable.TABLE_NAME + ";";

	private static final String SQL_JMFWEATHER_DROP_FORECAST_TABLE_STMT = 
			"DROP TABLE IF EXISTS " + WeatherDbContract.CForecastTable.TABLE_NAME + ";";

}