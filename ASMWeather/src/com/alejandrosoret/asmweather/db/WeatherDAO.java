package com.alejandrosoret.asmweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alejandrosoret.asmweather.model.City;
import com.alejandrosoret.asmweather.model.Forecast;

/*************************************************************/
/*                                                           */ 
/* WeatherDAO                                                */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: WeatherDAO Class                             */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class WeatherDAO
{
	private WeatherDbHelper 	dbHelper;
	private SQLiteDatabase	db;
	
	/*********************************************************/
	/*                                                       */ 
	/* WeatherDAO.WeatherDAO()                               */ 
	/*                                                       */ 
	/*********************************************************/
	public WeatherDAO( Context context )
     {
		dbHelper = new WeatherDbHelper( context );
     }
	
	/*********************************************************/
	/*                                                       */ 
	/* WeatherDAO.insert()                                   */ 
	/*                                                       */ 
	/*********************************************************/
	public long insert( City city ) 
	{
		if( db == null || db.isReadOnly() )
		{
			this.close();
			db = dbHelper.getWritableDatabase();
		}
		
		long recordId = -1;
		db.beginTransaction();
		
		ContentValues cityRecord = new ContentValues();
		cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_NAME, 			city.getName() );
		cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_COUNTRY, 		city.getCountry() );
		cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_LATITUDE, 		city.getLatitude() );
		cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_LONGITUDE,		city.getLongitude() );
		cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_POPULATION,		city.getPopulation() );
		cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_URL, 			city.getWeatherUrl() );

		try
		{
			recordId = db.insert( WeatherDbContract.CityTable.TABLE_NAME, null, cityRecord );
			if( recordId == -1 )
			{
				ContentValues ConditionRecord = new ContentValues();
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_CITY_ID, 				recordId );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_CLOUD_COVERAGE, 			city.getCurrentCondition().getCloudCoverPercentage() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_OBSERVATION_TIME,			city.getCurrentCondition().getObservationTime() == null ? null : city.getCurrentCondition().getObservationTime().getTime() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_PRESSURE, 				city.getCurrentCondition().getPressure() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_TEMPERATURE_CELSIUS, 		city.getCurrentCondition().getTemperatureCelsius() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_VISIBILITY, 				city.getCurrentCondition().getVisibility() );			
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_TEMPERATURE_FAHRENHEIT,	city.getCurrentCondition().getTemperatureFahrenheit() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_SPEED_MPH,			city.getCurrentCondition().getWindSpeedMph() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_PRECIPITATION, 			city.getCurrentCondition().getPrecipitation() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_DIRECTION_DEGREES, 	city.getCurrentCondition().getWindDirectionDegrees() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_DIRECTION_COMPASS, 	city.getCurrentCondition().getWindDirectionCompass() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_ICON_URL, 				city.getCurrentCondition().getIconUrl() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_HUMIDITY, 				city.getCurrentCondition().getHumidity() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_SPEED_KMPH, 			city.getCurrentCondition().getWindSpeedKmph() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WEATHER_CODE, 			city.getCurrentCondition().getWeatherCode() );
				ConditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WEATHER_DESCRIPTION, 		city.getCurrentCondition().getWeatherDescription() );
	
				db.insert( WeatherDbContract.ConditionTable.TABLE_NAME, null, ConditionRecord );
				db.setTransactionSuccessful();
			}
		}
		finally
		{
			db.endTransaction();
		}

		return recordId;
	}

	/********************************************************/
	/*                                                      */ 
	/* CWeatherDAO.insert()                                 */ 
	/*                                                      */ 
	/********************************************************/
	public long insert( Forecast forecast )
	{
		if( db == null || db.isReadOnly() )
		{
			this.close();
			db = dbHelper.getWritableDatabase();
		}
		
		ContentValues forecastRecord = new ContentValues();
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_CITY_ID, 					forecast.getCityId() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_ICON_URL, 				forecast.getIconUrl() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_MIN_TEMPERATURE_CELSIUS, 	forecast.getMinTemperatureCelsius() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_WIND_SPEED_MPH,			forecast.getWindSpeedMph() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_WIND_SPEED_KMPH,			forecast.getWindSpeedKmph() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_WIND_DIRECTION, 			forecast.getWindDirection() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_MAX_TEMPERATURE_CELSIUS,		forecast.getMaxTemperatureCelsius() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_FORECAST_DATE, 			forecast.getForecastDate() == null ? null : forecast.getForecastDate().getTime() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_WEATHER_CODE,				forecast.getWeatherCode() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_MAX_TEMPERATURE_FAHRENHEIT,	forecast.getMaxTemperatureFahrenheit() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_PRECIPITATION,				forecast.getPrecipitation() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_WIND_DIRECTION_DEGREES, 		forecast.getWindDirectionDegrees() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_WIND_DIRECTION_COMPASS,		forecast.getWindDirectionCompass() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_WEATHER_DESCRIPTION, 		forecast.getWeatherDescription() );
		forecastRecord.put( WeatherDbContract.ForecastTable.COLUMN_NAME_MIN_TEMPERATURE_FAHRENHEIT, 	forecast.getMinTemperatureFahrenheit() );

		return db.insert( WeatherDbContract.ForecastTable.TABLE_NAME, null, forecastRecord );
	}

	/*********************************************************/
	/*                                                       */ 
	/* CWeatherDAO.selectAllCities()                         */ 
	/*                                                       */ 
	/*********************************************************/
	public Cursor selectAllCities()
	{
		if( db == null ) db = dbHelper.getReadableDatabase();
		
		String[] projection = { "*" };
		
		String orderBy = WeatherDbContract.CityTable.COLUMN_NAME_NAME + " ASC";
		return db.query( WeatherDbContract.CityTable.TABLE_NAME, projection, null, null, null, null, orderBy );                               
	}

	/*********************************************************/
	/*                                                       */ 
	/* CWeatherDAO.selectCityForecast()                      */ 
	/*                                                       */ 
	/*********************************************************/
	public Cursor selectCityForecast( City city )
	{
		if( db == null ) db = dbHelper.getReadableDatabase();

		String[] projection = { "*" };
		String Where = WeatherDbContract.ForecastTable.COLUMN_NAME_CITY_ID + " = ?";
		String[] whereArgs = new String[] { "" + city.getId() };

		String orderBy = WeatherDbContract.ForecastTable.COLUMN_NAME_FORECAST_DATE + " ASC";
		return db.query( WeatherDbContract.ForecastTable.TABLE_NAME, projection, Where, whereArgs, null, null, orderBy );                               
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CWeatherDAO.deleteCity()                              */ 
	/*                                                       */ 
	/*********************************************************/
	public int deleteCity( long cityId )
	{
		if( db == null || db.isReadOnly() )
		{
			this.close();
			db = dbHelper.getWritableDatabase();
		}
		
		int ret = -1;
		db.beginTransaction();
		
		try
		{
			String forecastWhere = WeatherDbContract.ForecastTable.COLUMN_NAME_CITY_ID + " = ?";
			String[] forecastWhereArgs = new String[] { "" + cityId };
			ret = db.delete( WeatherDbContract.ForecastTable.TABLE_NAME, forecastWhere, forecastWhereArgs );
			
			String conditionWhere = WeatherDbContract.ConditionTable.COLUMN_NAME_CITY_ID + " = ?";
			String[] conditionWhereArgs = new String[] { "" + cityId };
			ret = db.delete( WeatherDbContract.ConditionTable.TABLE_NAME, conditionWhere, conditionWhereArgs );
			
			String cityWhere = WeatherDbContract.CityTable._ID + " = ?";
			String[] cityWhereArgs = new String[] { "" + cityId };
			ret = db.delete( WeatherDbContract.CityTable.TABLE_NAME, cityWhere, cityWhereArgs );
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
		}
		
		return ret;
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CWeatherDAO.deleteCityForecast()                      */ 
	/*                                                       */ 
	/*********************************************************/
	public int deleteCityForecast( long cityId )
	{
		if( db == null || db.isReadOnly() )
		{
			this.close();
			db = dbHelper.getWritableDatabase();
		}
		
		String forecastWhere = WeatherDbContract.ForecastTable.COLUMN_NAME_CITY_ID + " = ?";
		String[] forecastWhereArgs = new String[] { "" + cityId };
		return db.delete( WeatherDbContract.ForecastTable.TABLE_NAME, forecastWhere, forecastWhereArgs );
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CWeatherDAO.deleteAll()                               */ 
	/*                                                       */ 
	/*********************************************************/
	public int deleteAll()
	{
		if( db == null || db.isReadOnly() )
		{
			this.close();
			db = dbHelper.getWritableDatabase();
		}
		
		int ret = -1;
		db.beginTransaction();
		
		try
		{
			ret = db.delete( WeatherDbContract.ForecastTable.TABLE_NAME, null, null );
			ret = db.delete( WeatherDbContract.ConditionTable.TABLE_NAME, null, null );
			ret = db.delete( WeatherDbContract.CityTable.TABLE_NAME, null, null );
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
		}
		
		return ret;
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CWeatherDAO.update()                                  */ 
	/*                                                       */ 
	/*********************************************************/
	public int update( City city )
	{
		if( db == null || db.isReadOnly() )
		{
			this.close();
			db = dbHelper.getWritableDatabase();
		}
		
		int ret = -1;
		db.beginTransaction();
		
		try
		{
			ContentValues conditionRecord = new ContentValues();
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_CLOUD_COVERAGE, 			city.getCurrentCondition().getCloudCoverPercentage() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_OBSERVATION_TIME,			city.getCurrentCondition().getObservationTime() == null ? null : city.getCurrentCondition().getObservationTime().getTime() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_PRESSURE, 				city.getCurrentCondition().getPressure() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_TEMPERATURE_CELSIUS, 		city.getCurrentCondition().getTemperatureCelsius() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_VISIBILITY, 				city.getCurrentCondition().getVisibility() );			
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_TEMPERATURE_FAHRENHEIT,	city.getCurrentCondition().getTemperatureFahrenheit() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_SPEED_MPH,			city.getCurrentCondition().getWindSpeedMph() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_PRECIPITATION, 			city.getCurrentCondition().getPrecipitation() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_DIRECTION_DEGREES, 	city.getCurrentCondition().getWindDirectionDegrees() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_DIRECTION_COMPASS, 	city.getCurrentCondition().getWindDirectionCompass() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_ICON_URL, 				city.getCurrentCondition().getIconUrl() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_HUMIDITY, 				city.getCurrentCondition().getHumidity() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WIND_SPEED_KMPH, 			city.getCurrentCondition().getWindSpeedKmph() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WEATHER_CODE, 			city.getCurrentCondition().getWeatherCode() );
			conditionRecord.put( WeatherDbContract.ConditionTable.COLUMN_NAME_WEATHER_DESCRIPTION, 		city.getCurrentCondition().getWeatherDescription() );
			
			String conditionWhere = WeatherDbContract.ConditionTable.COLUMN_NAME_CITY_ID + " = ?";
			String[] conditionWhereArgs = new String[] { "" + city.getId() };
			ret = db.update( WeatherDbContract.ConditionTable.TABLE_NAME, conditionRecord, conditionWhere, conditionWhereArgs );
		
			ContentValues cityRecord = new ContentValues();
			cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_NAME, 			city.getName() );
			cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_COUNTRY, 		city.getCountry() );
			cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_LATITUDE, 		city.getLatitude() );
			cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_LONGITUDE,		city.getLongitude() );
			cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_POPULATION,		city.getPopulation() );
			cityRecord.put( WeatherDbContract.CityTable.COLUMN_NAME_URL, 			city.getWeatherUrl() );
			
			String cityWhere = WeatherDbContract.CityTable._ID + " = ?";
			String[] cityWhereArgs = new String[] { "" + city.getId() };
			ret = db.update( WeatherDbContract.CityTable.TABLE_NAME, cityRecord, cityWhere, cityWhereArgs );
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
		}
		
		return ret;
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CWeatherDAO.close()                                   */ 
	/*                                                       */ 
	/*********************************************************/
	public void close()
	{
		if( db != null )
		{
			db.close();
			db = null;
		}
	}
}