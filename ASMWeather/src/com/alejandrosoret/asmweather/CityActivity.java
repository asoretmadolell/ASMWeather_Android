package com.alejandrosoret.asmweather;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.alejandrosoret.asmweather.db.WeatherDAO;
import com.alejandrosoret.asmweather.model.City;
import com.alejandrosoret.asmweather.model.Condition;
import com.alejandrosoret.asmweather.weatherapi.WorldWeatherApi;

/*************************************************************/
/*                                                           */ 
/* CityActivity                                              */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: CityActivity Class                           */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class CityActivity extends ActionBarActivity
{
	private City city = null;
	private ConditionAsyncTask conditionAsyncTask = null;
	
	/*********************************************************/
	/*                                                       */ 
	/* CityActivity.onCreate()                               */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
	     super.onCreate( savedInstanceState );
	     setContentView( R.layout.activity_city );
	     
	     long cityId = getIntent().getLongExtra( ASMApplication.IDRC_CITY_LIST_ID, -1);
	     if( cityId == -1 ) return;
     	Log.e( "CitiActivity", "CityId = " + cityId );
	     
	     WeatherDAO dao = new WeatherDAO( this );
	     city = dao.selectCity( cityId );
	     
	     if( city == null )
	     {
	     	Log.e( "CitiActivity", "Ehhhhhh: city == null" );
	     	return;
	     }
	     
	     TextView cityName = (TextView)findViewById( R.id.IDV_CITY_NAME );
	     TextView cityCountry = (TextView)findViewById( R.id.IDV_CITY_COUNTRY );
	     TextView cityLatitude = (TextView)findViewById( R.id.IDV_CITY_LATITUDE );
	     TextView cityLongitude = (TextView)findViewById( R.id.IDV_CITY_LONGITUDE );
	     TextView cityPopulation = (TextView)findViewById( R.id.IDV_CITY_POPULATION );
	     cityName.setText( city.getName() );
	     cityCountry.setText( city.getCountry() );
	     cityLatitude.setText( city.getLatitude() );
	     cityLongitude.setText( city.getLongitude() );
	     cityPopulation.setText( "" + city.getPopulation() );
	     
	     conditionAsyncTask = new ConditionAsyncTask();
	     conditionAsyncTask.execute();
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CityActivity.displayConditionData()                   */ 
	/*                                                       */ 
	/*********************************************************/
	private void displayConditionData()
	{
		if( city.getCurrentCondition() != null )
		{
			TextView cityCloudCoverPercentage = (TextView)findViewById( R.id.IDV_CITY_CLOUD_COVER_PERCENTAGE );
			TextView cityObservationTime = (TextView)findViewById( R.id.IDV_CITY_OBSERVATION_TIME );
			TextView cityPressure = (TextView)findViewById( R.id.IDV_CITY_PRESSURE );
			TextView cityTemperatureCelsius = (TextView)findViewById( R.id.IDV_CITY_TEMPERATURE_CELSIUS );
			TextView cityVisibility = (TextView)findViewById( R.id.IDV_CITY_VISIBILITY );
			TextView cityTemperatureFahrenheit = (TextView)findViewById( R.id.IDV_CITY_TEMPERATURE_FAHRENHEIT );
			TextView cityWindSpeedMph = (TextView)findViewById( R.id.IDV_CITY_WIND_SPEED_MPH );
			TextView cityPrecipitation = (TextView)findViewById( R.id.IDV_CITY_PRECIPITATION );
			TextView cityWindDirectionDegrees = (TextView)findViewById( R.id.IDV_CITY_WIND_DIRECTION_DEGREES );
			TextView cityWindDirectionCompass = (TextView)findViewById( R.id.IDV_CITY_WIND_DIRECTION_COMPASS );
			TextView cityHumidity = (TextView)findViewById( R.id.IDV_CITY_HUMIDITY );
			TextView cityWindSpeedKmph = (TextView)findViewById( R.id.IDV_CITY_WIND_SPEED_KMPH );
			TextView cityWeatherCode = (TextView)findViewById( R.id.IDV_CITY_WEATHER_CODE );
			TextView cityWeatherDescription = (TextView)findViewById( R.id.IDV_CITY_WEATHER_DESCRIPTION );
			cityCloudCoverPercentage.setText( "Cloud cover %: " + city.getCurrentCondition().getCloudCoverPercentage() );
			cityObservationTime.setText( "Observation time: " + city.getCurrentCondition().getObservationTime() );
			cityPressure.setText( "Pressure: " + city.getCurrentCondition().getPressure() );
			cityTemperatureCelsius.setText( "Temperature: " + city.getCurrentCondition().getTemperatureCelsius() +"ºC" );
			cityVisibility.setText( "Visibility: " + city.getCurrentCondition().getVisibility() );
			cityTemperatureFahrenheit.setText( "Temperature: " + city.getCurrentCondition().getTemperatureFahrenheit() +"ºF" );
			cityWindSpeedMph.setText( "Wind speed: " + city.getCurrentCondition().getWindSpeedMph() + "Mph" );
			cityPrecipitation.setText( "Precipitation: " + city.getCurrentCondition().getPrecipitation() );
			cityWindDirectionDegrees.setText( "Wind direction: " + city.getCurrentCondition().getWindDirectionDegrees() + " degrees" );
			cityWindDirectionCompass.setText( "Wind direction: " + city.getCurrentCondition().getWindDirectionCompass() + " compass" );
			cityHumidity.setText( "Humidity: " + city.getCurrentCondition().getHumidity() );
			cityWindSpeedKmph.setText( "Wind speed: " + city.getCurrentCondition().getWindSpeedKmph() + "Kmph" );
			cityWeatherCode.setText( "Weather code: " + city.getCurrentCondition().getWeatherCode() );
			cityWeatherDescription.setText( "Weather description :" + city.getCurrentCondition().getWeatherDescription() );
		}
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CityActivity.onBackPressed()                          */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
	public void onBackPressed()
	{
	     super.onBackPressed();
	     if( conditionAsyncTask != null )
	     {
	     	conditionAsyncTask.cancel( true );
	     }
	}
	
	/*************************************************************/
	/*                                                           */ 
	/* ConditionAsyncTask                                        */ 
	/* (c)2014 Alejandro                                         */ 
	/*                                                           */ 
	/* Description: ConditionAsyncTask Class                     */ 
	/*              ASMWeather Project                           */ 
	/*                                                           */ 
	/*                                                           */ 
	/*************************************************************/
	private class ConditionAsyncTask extends AsyncTask< Void, Void, Boolean >
	{
	
		/*********************************************************/
		/*                                                       */ 
		/* CityActivity.doInBackground()                         */ 
		/*                                                       */ 
		/*********************************************************/
		@Override
          protected Boolean doInBackground( Void... params )
          {
			WorldWeatherApi api = new WorldWeatherApi();
			try
               {
				boolean ret = api.getCityWeather( city );
	               return ret;
               }
               catch( IOException e ) { e.printStackTrace(); }
               catch( JSONException e ) { e.printStackTrace(); }
               catch( ParseException e ) { e.printStackTrace(); }
			return false;
          }
		
		/*********************************************************/
		/*                                                       */ 
		/* CityActivity.onPostExecute()                          */ 
		/*                                                       */ 
		/*********************************************************/
		@Override
		protected void onPostExecute( Boolean result )
		{
		     super.onPostExecute( result );
		     if( result == true )
		     {
		     	displayConditionData();
		     }
		}
	}
}