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
/* CityList                                                  */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: CityList Class                               */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class CityList
{
	private ArrayList< City >		cityList;
	private TreeMap< Long, City >		cityMap;
	
	/*********************************************************/
	/*                                                       */ 
	/* CityList.CityList()                                   */ 
	/*                                                       */ 
	/*********************************************************/
	public CityList()
     {
	     cityList = new ArrayList< City >();
	     cityMap = new TreeMap<Long, City >();
     }
	
	/*********************************************************/
	/*                                                       */ 
	/* CityList.CityList() JSON Object constructor           */ 
	/*                                                       */ 
	/*********************************************************/
	public CityList( JSONObject jsonObject ) throws JSONException, ParseException
	{
		this();
		
		JSONObject search = jsonObject.getJSONObject( "search_api" );
		JSONArray entries = search.getJSONArray( "result" );
		
		for( int i = 0; i < entries.length(); i++ )
		{
			JSONObject objCity = entries.getJSONObject( i );
			City city = new City( -1, objCity );
			this.add( city );
		}
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CityList getters                                      */ 
	/*                                                       */ 
	/*********************************************************/
	public City get( long id )
	{
		return cityMap.get( id );
	}
	
	public ArrayList< City > getCityList()
	{
		return cityList;
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CityList.add()                                        */ 
	/*                                                       */ 
	/*********************************************************/
	public void add( City... CityArray )
	{
		this.cityList.addAll( Arrays.asList( CityArray ) );
		for( City City : CityArray )
		{
			this.cityMap.put( City.getId(), City );
		}
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CityList.add()                                        */ 
	/*                                                       */ 
	/*********************************************************/
	public void add( City City )
	{
		add( new City[] { City } );
	}
	
	/*********************************************************/
	/*                                                       */ 
	/* CityList.getPosition()                                */ 
	/*                                                       */ 
	/*********************************************************/
	public int getPosition( long id )
	{
		City City = get( id );
		return cityList.indexOf( City );
	}
}
