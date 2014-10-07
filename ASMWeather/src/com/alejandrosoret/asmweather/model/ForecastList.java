package com.alejandrosoret.asmweather.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

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