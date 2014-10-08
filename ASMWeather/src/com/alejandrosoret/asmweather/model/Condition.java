package com.alejandrosoret.asmweather.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;

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
	public Condition( int cloudCoverPercentage, Date observationTime,
               int pressure, int temperatureCelsius, int visibility,
               int temperatureFahrenheit, int windSpeedMph,
               float precipitation, int windDirectionDegrees,
               String windDirectionCompass, String iconUrl, int humidity,
               int windSpeedKmph, int weatherCode, String weatherDescription )
     {
	     super();
	     this.id = this.cityId = -1;
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
	/* Condition.Condition() JSON object constructor         */ 
	/*                                                       */ 
	/*********************************************************/
	@SuppressLint( "SimpleDateFormat ")
	public Condition( JSONObject jsonObject ) throws JSONException, ParseException
	{
		this( 0, (Date)null, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, null );
		
		cloudCoverPercentage = jsonObject.getInt( "cloudcover" );
		SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm" );
		observationTime = dateFormat.parse( jsonObject.getString( "observation_time" ) );
		pressure = jsonObject.getInt( "pressure" );
		temperatureCelsius = jsonObject.getInt( "temp_C" );
		visibility = jsonObject.getInt( "visibility" );
		temperatureFahrenheit = jsonObject.getInt( "temp_F" );
		windSpeedMph = jsonObject.getInt( "windspeedMiles" );
		precipitation = (float)jsonObject.getDouble( "precipMM" );
		windDirectionDegrees = jsonObject.getInt( "winddirDegree" );
		windDirectionCompass = jsonObject.getString( "winddir16Point" );
		JSONArray iconUrlArray = jsonObject.getJSONArray( "weatherIconUrl" );
		iconUrl = iconUrlArray.getJSONObject( 0 ).getString( "value" );
		humidity = jsonObject.getInt( "humidity" );
		windSpeedKmph = jsonObject.getInt( "windspeedKmph" );
		weatherCode = jsonObject.getInt( "weatherCode" );
		JSONArray descriptionArray = jsonObject.getJSONArray( "weatherDesc" );
		weatherDescription = descriptionArray.getJSONObject( 0 ).getString( "value" );
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
