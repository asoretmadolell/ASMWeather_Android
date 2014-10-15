package com.alejandrosoret.asmweather.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*************************************************************/
/*                                                           */ 
/* City                                                      */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: City Class                                   */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class City
{
	private long 		id;
	private String 	name;
	private String 	country;
	private String 	latitude;
	private String 	longitude;
	private long 		population;
	private String 	region;
	private String 	weatherUrl;
	private Condition 	currentCondition;
	
	/*********************************************************/
	/*                                                       */ 
	/* City.City()                                           */
	/*                                                       */ 
	/*********************************************************/
	public City( long id, String name, String country, String latitude,
               String longitude, long population, String region,
               String weatherUrl )
     {
	     super();
	     this.id = id;
	     this.name = name;
	     this.country = country;
	     this.latitude = latitude;
	     this.longitude = longitude;
	     this.population = population;
	     this.region = region;
	     this.weatherUrl = weatherUrl;
	     this.currentCondition = null;
     }
	
	/*********************************************************/
	/*                                                       */ 
	/* City.City() JSON Object constructor                   */ 
	/*                                                       */ 
	/*********************************************************/
	public City( long id, JSONObject jsonObject ) throws JSONException
	{
		this( id, null, null, null, null, 0, null, null );
		
		JSONArray areaNameArray = jsonObject.getJSONArray( "areaName" );
		name = areaNameArray.getJSONObject( 0 ).getString( "value" );
		
		JSONArray countryArray = jsonObject.getJSONArray( "country" );
		country = countryArray.getJSONObject( 0 ).getString( "value" );
		
		latitude = jsonObject.getString( "latitude" );
		longitude = jsonObject.getString( "longitude" );
		population = jsonObject.getLong( "population" );
		
		JSONArray regionArray = jsonObject.getJSONArray( "region" );
		region = regionArray.getJSONObject( 0 ).getString( "value" );
		
		JSONArray weatherUrlArray = jsonObject.getJSONArray( "weatherUrl" );
		weatherUrl = weatherUrlArray.getJSONObject( 0 ).getString( "value" );
	}

	/*********************************************************/
	/*                                                       */ 
	/* City getters                                          */
	/*                                                       */ 
	/*********************************************************/
	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getCountry()
	{
		return country;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public long getPopulation()
	{
		return population;
	}

	public String getRegion()
	{
		return region;
	}

	public String getWeatherUrl()
	{
		return weatherUrl;
	}

	public Condition getCurrentCondition()
	{
		return currentCondition;
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* City setters                                          */
	/*                                                       */ 
	/*********************************************************/
	public void setCurrentCondition( Condition currentCondition )
	{
		this.currentCondition = currentCondition;
	}
}
