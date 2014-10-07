package com.alejandrosoret.asmweather.model;

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
               String weatherUrl, Condition currentCondition )
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
	     this.currentCondition = currentCondition;
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
