package com.alejandrosoret.asmweather.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

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