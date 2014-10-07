package com.alejandrosoret.asmweather.model;

import java.util.Date;

/*************************************************************/
/*                                                           */
/* Condition                                                 */
/* (c)2014 Alejandro                                         */
/*                                                           */
/* Description: Condition Class                              */
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class Condition
{
	private long		id;
	private long		cityId;
	private int		cloudCoverPercentage;
	private Date		observationTime;
	private int		pressure;
	private int		temperatureCelsius;
	private int		visibility;
	private int		temperatureFahrenheit;
	private int		windSpeedMph;
	private float		precipitation;
	private int		windDirectionDegrees;
	private String		windDirectionCompass;
	private String		iconUrl;
	private int		humidity;
	private int		windSpeedKmph;
	private int		weatherCode;
	private String		weatherDescription;
	
	/*********************************************************/
	/*                                                       */ 
	/* Condition.Condition()                                 */ 
	/*                                                       */ 
	/*********************************************************/
	public Condition( long id, long cityId, int cloudCoverPercentage,
               Date observationTime, int pressure, int temperatureCelsius,
               int visibility, int temperatureFahrenheit, int windSpeedMph,
               float precipitation, int windDirectionDegrees,
               String windDirectionCompass, String iconUrl, int humidity,
               int windSpeedKmph, int weatherCode, String weatherDescription )
     {
	     super();
	     this.id = id;
	     this.cityId = cityId;
	     this.cloudCoverPercentage = cloudCoverPercentage;
	     this.observationTime = observationTime;
	     this.pressure = pressure;
	     this.temperatureCelsius = temperatureCelsius;
	     this.visibility = visibility;
	     this.temperatureFahrenheit = temperatureFahrenheit;
	     this.windSpeedMph = windSpeedMph;
	     this.precipitation = precipitation;
	     this.windDirectionDegrees = windDirectionDegrees;
	     this.windDirectionCompass = windDirectionCompass;
	     this.iconUrl = iconUrl;
	     this.humidity = humidity;
	     this.windSpeedKmph = windSpeedKmph;
	     this.weatherCode = weatherCode;
	     this.weatherDescription = weatherDescription;
     }

	/*********************************************************/
	/*                                                       */ 
	/* Condition getters                                     */ 
	/*                                                       */ 
	/*********************************************************/
	public long getId()
	{
		return id;
	}

	public long getCityId()
	{
		return cityId;
	}

	public int getCloudCoverPercentage()
	{
		return cloudCoverPercentage;
	}

	public Date getObservationTime()
	{
		return observationTime;
	}

	public int getPressure()
	{
		return pressure;
	}

	public int getTemperatureCelsius()
	{
		return temperatureCelsius;
	}

	public int getVisibility()
	{
		return visibility;
	}

	public int getTemperatureFahrenheit()
	{
		return temperatureFahrenheit;
	}

	public int getWindSpeedMph()
	{
		return windSpeedMph;
	}

	public float getPrecipitation()
	{
		return precipitation;
	}

	public int getWindDirectionDegrees()
	{
		return windDirectionDegrees;
	}

	public String getWindDirectionCompass()
	{
		return windDirectionCompass;
	}

	public String getIconUrl()
	{
		return iconUrl;
	}

	public int getHumidity()
	{
		return humidity;
	}

	public int getWindSpeedKmph()
	{
		return windSpeedKmph;
	}

	public int getWeatherCode()
	{
		return weatherCode;
	}

	public String getWeatherDescription()
	{
		return weatherDescription;
	}
}
