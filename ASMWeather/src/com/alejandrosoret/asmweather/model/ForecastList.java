package com.alejandrosoret.asmweather.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*************************************************************/
/*                                                           */ 
/* ForecastList                                              */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: ForecastList Class                           */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class ForecastList
{
	private ArrayList< Forecast >		forecastList;
	private TreeMap< Long, Forecast >	forecastMap;
	
	/*********************************************************/
	/*                                                       */ 
	/* ForecastList.ForecastList()                           */ 
	/*                                                       */ 
	/*********************************************************/
	public ForecastList()
     {
		forecastList = new ArrayList< Forecast >();
		forecastMap = new TreeMap< Long, Forecast >();
     }
	
	/*********************************************************/
	/*                                                       */ 
	/* ForecastList.ForecastList() JSON object constructor   */ 
	/*                                                       */ 
	/*********************************************************/
	public ForecastList( JSONArray entries ) throws JSONException, ParseException
     {
		this();
		
		for( int i = 0; i < entries.length(); i++ )
		{
			JSONObject objForecast = entries.getJSONObject( i );
			Forecast forecast = new Forecast( objForecast );
			this.add( forecast );
		}
     }
	
	/*********************************************************/
	/*                                                       */ 
	/* ForecastList getters                                  */ 
	/*                                                       */ 
	/*********************************************************/
	public Forecast get (long id )
	{
		return forecastMap.get( id );
	}
	
	public ArrayList< Forecast > getForecastList()
	{
		return forecastList;
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* ForecastList.add()                                    */ 
	/*                                                       */ 
	/*********************************************************/
	public void add( Forecast... ForecastArray )
	{
		this.forecastList.addAll( Arrays.asList( ForecastArray ) );
		for( Forecast Forecast : ForecastArray )
		{
			this.forecastMap.put( Forecast.getId(), Forecast );
		}
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* ForecastList.add()                                    */ 
	/*                                                       */ 
	/*********************************************************/
	public void add( Forecast Forecast )
	{
		add( new Forecast[] { Forecast } );
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* ForecastList.getPosition()                            */ 
	/*                                                       */ 
	/*********************************************************/
	public int getPosition( long id )
	{
		Forecast Forecast = get( id );
		return forecastList.indexOf( Forecast );
	}
}