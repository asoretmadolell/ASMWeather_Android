package com.alejandrosoret.asmweather;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.alejandrosoret.asmweather.db.WeatherDAO;
import com.alejandrosoret.asmweather.model.City;
import com.alejandrosoret.asmweather.model.CityList;

/*************************************************************/
/*                                                           */ 
/* ASMApplication                                            */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: ASMApplication Class                         */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class ASMApplication extends Application
{
	public static final String IDRC_CITY_LIST_ID = "IDRC_CITY_LIST_ID";
	public static Context context = null;
	public static final String IDRC_FIRST_TIME_RUNNING = "IDRC_FIRST_TIME_RUNNING";

	     /*********************************************************/
	     /*                                                       */
	     /*                                                       */
	     /* Application Override Methods                          */
	     /*                                                       */
	     /*                                                       */
	     /*********************************************************/
	     /*                                                       */
	     /* ASMApplication.onCreate()                             */
	     /*                                                       */
	     /*********************************************************/
	     @Override
	     public void onCreate()
	     {
	          super.onCreate();
	          ASMApplication.context = getApplicationContext();
	     }
	
	     public static void initializeData()
	     {
	     	if( context == null ) return;
	     	if( isFirstTimeRunning() )
	     	{
	     		WeatherDAO dao = new WeatherDAO( context );
	     		
//	     		ASMApplication.cityList = new CityList();
//	     		ASMApplication.cityList.add( new City( 0, "Madrid", "Spain", "40.000", "-3.683", 3102644, "Madrid", "http://www.worldweatheronline.com/v2/weather.aspx?q=40.4,-3.6833" ) );
//	     		ASMApplication.cityList.add( new City( 1, "Barcelona", "Spain", "41.383", "2.183", 1570378, "Catalonia", "http://www.worldweatheronline.com/v2/weather.aspx?q=41.3833,2.1833" ) );
//	     		ASMApplication.cityList.add( new City( 2, "Valencia", "Spain", "39.467", "-0.367", 769897, "Comunidad Valenciana", "http://www.worldweatheronline.com/v2/weather.aspx?q=39.4667,-0.3667" ) );
	     		
	     		dao.insert( new City( 0, "Madrid", "Spain", "40.000", "-3.683", 3102644, "Madrid", "http://www.worldweatheronline.com/v2/weather.aspx?q=40.4,-3.6833" ) );
	     		dao.insert( new City( 1, "Barcelona", "Spain", "41.383", "2.183", 1570378, "Catalonia", "http://www.worldweatheronline.com/v2/weather.aspx?q=41.3833,2.1833" ) );
	     		dao.insert( new City( 2, "Valencia", "Spain", "39.467", "-0.367", 769897, "Comunidad Valenciana", "http://www.worldweatheronline.com/v2/weather.aspx?q=39.4667,-0.3667" ) );
	     		dao.close();
	     	}
	     }

	     public static boolean isFirstTimeRunning()
	     {
	     	if( context == null ) return false;
	     	SharedPreferences preferences = context.getSharedPreferences( "ASMWeather", Context.MODE_PRIVATE );

	     	boolean firstTime = preferences.getBoolean( IDRC_FIRST_TIME_RUNNING, true );

	     	if( firstTime )
	     	{
	     		SharedPreferences.Editor preferencesEditor = preferences.edit();
	     		preferencesEditor.putBoolean( IDRC_FIRST_TIME_RUNNING, false ).commit();
	     	}

	     	return firstTime;
	     }
}