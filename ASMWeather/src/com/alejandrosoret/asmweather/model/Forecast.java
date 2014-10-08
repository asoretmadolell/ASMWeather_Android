package com.alejandrosoret.asmweather.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*************************************************************/
/*                                                           */ 
/* Forecast                                                  */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: Forecast Class                               */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class Forecast
{
	private long		id;	
	private long		cityId;	
	private String		iconUrl;
	private int		minTemperatureCelsius;
	private int		windSpeedMph;
	private int		windSpeedKmph;
	private String		windDirection;
	private int		maxTemperatureCelsius;
	private Date		forecastDate;
	private int		weatherCode;
	private int		maxTemperatureFahrenheit;
	private float		precipitation;
	private int		windDirectionDegrees;
	private String		windDirectionCompass;
	private String 	weatherDescription;
	private int		minTemperatureFahrenheit;
		
	/*********************************************************/
	/*                                                       */ 
	/* Forecast.Forecast()                                   */ 
	/*                                                       */ 
	/*********************************************************/
	public Forecast( String iconUrl, int minTemperatureCelsius,
               int windSpeedMph, int windSpeedKmph, String windDirection,
               int maxTemperatureCelsius, Date forecastDate, int weatherCode,
               int maxTemperatureFahrenheit, float precipitation,
               int windDirectionDegrees, String windDirectionCompass,
               String weatherDescription, int minTemperatureFahrenheit )
     {
	     super();
	     this.id = this.cityId = -1;
	     this.iconUrl = iconUrl;
	     this.minTemperatureCelsius = minTemperatureCelsius;
	     this.windSpeedMph = windSpeedMph;
	     this.windSpeedKmph = windSpeedKmph;
	     this.windDirection = windDirection;
	     this.maxTemperatureCelsius = maxTemperatureCelsius;
	     this.forecastDate = forecastDate;
	     this.weatherCode = weatherCode;
	     this.maxTemperatureFahrenheit = maxTemperatureFahrenheit;
	     this.precipitation = precipitation;
	     this.windDirectionDegrees = windDirectionDegrees;
	     this.windDirectionCompass = windDirectionCompass;
	     this.weatherDescription = weatherDescription;
	     this.minTemperatureFahrenheit = minTemperatureFahrenheit;
     }
	
	/*********************************************************/
	/*                                                       */ 
	/* Forecast.Forecast()  JSON object constructor          */ 
	/*                                                       */ 
	/*********************************************************/
	public Forecast( JSONObject jsonObject ) throws JSONException, ParseException
	{
		this( null, 0, 0, 0, null, 0, (Date)null, 0, 0, 0, 0, null, null, 0 );

		JSONArray iconUrlArray = jsonObject.getJSONArray( "weatherIconUrl" );
		iconUrl = iconUrlArray.getJSONObject( 0 ).getString( "value" );
		minTemperatureCelsius = jsonObject.getInt( "tempMin_C" );
		windSpeedMph = jsonObject.getInt( "windspeedMiles" );
		windSpeedKmph = jsonObject.getInt( "windspeedKmph" );
		windDirection = jsonObject.getString( "winddirection" );
		maxTemperatureCelsius = jsonObject.getInt( "tempMax_C" );
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		forecastDate = dateFormat.parse( jsonObject.getString( "date" ) );
		weatherCode = jsonObject.getInt( "weatherCode" );
		maxTemperatureFahrenheit = jsonObject.getInt( "tempMax_F" );
		precipitation = (float)jsonObject.getDouble( "precipMM" );
		windDirectionDegrees = jsonObject.getInt( "winddirDegree" );
		windDirectionCompass = jsonObject.getString( "winddir16Point" );
		JSONArray descriptionArray = jsonObject.getJSONArray( "weatherDesc" );
		weatherDescription = descriptionArray.getJSONObject( 0 ).getString( "value" );
		minTemperatureFahrenheit = jsonObject.getInt( "tempMin_F" );
	}

	/*********************************************************/
	/*                                                       */ 
	/* Forecast getters                                      */ 
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

	public String getIconUrl()
	{
		return iconUrl;
	}

	public int getMinTemperatureCelsius()
	{
		return minTemperatureCelsius;
	}

	public int getWindSpeedMph()
	{
		return windSpeedMph;
	}

	public int getWindSpeedKmph()
	{
		return windSpeedKmph;
	}

	public String getWindDirection()
	{
		return windDirection;
	}

	public int getMaxTemperatureCelsius()
	{
		return maxTemperatureCelsius;
	}

	public Date getForecastDate()
	{
		return forecastDate;
	}

	public int getWeatherCode()
	{
		return weatherCode;
	}

	public int getMaxTemperatureFahrenheit()
	{
		return maxTemperatureFahrenheit;
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

	public String getWeatherDescription()
	{
		return weatherDescription;
	}

	public int getMinTemperatureFahrenheit()
	{
		return minTemperatureFahrenheit;
	}
}