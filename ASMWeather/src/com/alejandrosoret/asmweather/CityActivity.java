package com.alejandrosoret.asmweather;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

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
	     
	     city = ASMApplication.cityList.get( getIntent().getLongExtra( ASMApplication.IDRC_CITY_LIST_ID, -1 ) );
	     
	     TextView cityName = (TextView)findViewById( R.id.IDV_CITY_NAME );
	     cityName.setText( city.getName() );
	     
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
			TextView cityTemperature = (TextView)findViewById( R.id.IDV_CITY_TEMPERATURE );
			cityTemperature.setText( "" + city.getCurrentCondition().getTemperatureCelsius() +"ºC" );
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