package com.alejandrosoret.asmweather.weatherapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.alejandrosoret.asmweather.model.City;
import com.alejandrosoret.asmweather.model.CityList;
import com.alejandrosoret.asmweather.model.Condition;

/*************************************************************/
/*                                                           */ 
/* WorldWeatherApi                                           */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: WorldWeatherApi Class                        */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class WorldWeatherApi
{
	private static final int		HTTP_CONNECT_TIMEOUT_MS       = 15000; // millisecons
	private static final int		HTTP_READ_TIMEOUT_MS          = 10000; // millisecons
	private static final int		IO_BUFFER_SIZE                = 1024;
	private static final String	WORLD_WEATHER_GET_WEATHER_URL = "http://api.worldweatheronline.com/free/v1/weather.ashx";
	private static final String	WORLD_WEATHER_SEARCH_CITY_URL = "http://api.worldweatheronline.com/free/v1/search.ashx";
	private static final String	WORLD_WEATHER_KEY             = "4b2c10814c79b59045a73474b2dccb281b703fb4";

	private Charset			m_Charset;
	private HttpURLConnection	m_Connection;
	private InputStream			m_InputStream;
	private byte[]                m_ImageBytes;

	/*********************************************************/
	/*                                                       */
	/*                                                       */
	/* Class Constructors                                    */
	/*                                                       */
	/*                                                       */
	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.WorldWeatherApi()                     */
	/*                                                       */
	/*********************************************************/
	public WorldWeatherApi()
	{
		this( Charset.forName( "UTF-8" ) );
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.WorldWeatherApi()                     */
	/*                                                       */
	/*********************************************************/
	public WorldWeatherApi( Charset Charset )
	{
		super();
		this.m_Charset = Charset;
	}

	/*********************************************************/
	/*                                                       */
	/*                                                       */
	/* Object Override Methods                               */
	/*                                                       */
	/*                                                       */
	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.finalize()                            */
	/*                                                       */
	/*********************************************************/
	@Override
	protected void finalize() throws Throwable
	{
		this.Close();
		super.finalize();
	}

	/*********************************************************/
	/*                                                       */
	/*                                                       */
	/* Class Methods                                         */
	/*                                                       */
	/*                                                       */
	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.SearchCity()                          */
	/*                                                       */
	/*********************************************************/
	public CityList SearchCity( String Name ) throws IOException, JSONException
	{
		if( Name == null ) return null;
		String Url = WORLD_WEATHER_SEARCH_CITY_URL + "?q=" + Name.replaceAll( " ", "+" ) + "&format=json&num_of_results=50&key=" + WORLD_WEATHER_KEY;
		Connect( new URL( Url ) );

		JSONObject jsonResponse = getJSONObject();
		JSONObject Root = jsonResponse.getJSONObject( "search_api" );
		JSONArray Entries = Root.getJSONArray( "result" );

		CityList cityList = new CityList();
		for( int i = 0; i < Entries.length(); i++ )
		{
			JSONObject jsonObject = Entries.getJSONObject( i );
			City city = new City( (long)i + 1, jsonObject );
			cityList.add( city );
		}

		Close();
		return cityList;
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.SearchLocation()                      */
	/*                                                       */
	/*********************************************************/
	public CityList SearchLocation( String Latitude, String Longitude ) throws IOException, JSONException
	{
		if( Latitude == null || Longitude == null ) return null;
		String Url = WORLD_WEATHER_SEARCH_CITY_URL + "?q=" + Latitude + "+" + Longitude + "&format=json&num_of_results=1&key=" + WORLD_WEATHER_KEY;
		Connect( new URL( Url ) );

		JSONObject jsonResponse = getJSONObject();
		JSONObject Root = jsonResponse.getJSONObject( "search_api" );
		JSONArray Entries = Root.getJSONArray( "result" );

		CityList cityList = new CityList();
		for( int i = 0; i < Entries.length(); i++ )
		{
			JSONObject jsonObject = Entries.getJSONObject( i );
			City city = new City( (long)i + 1, jsonObject );
			cityList.add( city );
		}

		Close();
		return cityList;
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.getCityWeather()                      */
	/*                                                       */
	/*********************************************************/
	public boolean getCityWeather( City City ) throws IOException, JSONException, ParseException
	{
		if( City == null ) return false;

		String Url = WORLD_WEATHER_GET_WEATHER_URL + "?q=" + City.getLatitude() + "+" + City.getLongitude() + "&format=json&extra=localObsTime&num_of_days=5&key=" + WORLD_WEATHER_KEY;
		Connect( new URL( Url ) );

		JSONObject jsonResponse = getJSONObject();
		JSONObject Root = jsonResponse.getJSONObject( "data" );
		JSONArray currentConditionArray = Root.getJSONArray( "current_condition" );

		Condition currentCondition = new Condition( currentConditionArray.getJSONObject( 0 ) );
		City.setCurrentCondition( currentCondition );
		/*
	  //    JSONArray requestArray = Root.getJSONArray( "request" );
	  JSONArray weatherArray = Root.getJSONArray( "weather" );

	  ForecastList forecastList = new ForecastList();
	  for( int i = 0; i < weatherArray.length(); i++ )
	  {
		  JSONObject jsonObject = weatherArray.getJSONObject( i );
		  Forecast forecast = new Forecast( jsonObject );
		  forecastList.add( forecast );
	  }
	  City.setForecastList( forecastList );
		 */
		Close();
		return true;
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.Connect()                             */
	/*                                                       */
	/*********************************************************/
	public void Connect( URL Url ) throws IOException
	{
		if( Url != null )
		{
			m_Connection = (HttpURLConnection)Url.openConnection();
			m_Connection.setConnectTimeout( HTTP_CONNECT_TIMEOUT_MS );
			m_Connection.setReadTimeout( HTTP_READ_TIMEOUT_MS );
			m_Connection.setRequestMethod( "GET" );
			m_Connection.setDoInput( true );

			// World Weather Free API allows only tree calls per second
			try
			{
				Thread.sleep( 1000 );
			}
			catch( InterruptedException exception )
			{
				exception.printStackTrace();
			}
			m_Connection.connect();
			m_InputStream = m_Connection.getInputStream();
		}
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.getString()                           */
	/*                                                       */
	/*********************************************************/
	public String getString() throws IOException
	{
		StringBuffer retBuffer = new StringBuffer();
		char[] Buffer = new char[ 2048 ];
		int BytesRead;

		InputStreamReader Reader = new InputStreamReader( m_InputStream, m_Charset );

		while( ( BytesRead = Reader.read( Buffer ) ) != -1)
		{
			retBuffer.append( Buffer, 0, BytesRead );
		}

		return retBuffer.toString();
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.getJSONObject()                       */
	/*                                                       */
	/*********************************************************/
	public JSONObject getJSONObject() throws JSONException, IOException
	{
		return new JSONObject( getString() );
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.getImage()                            */
	/*                                                       */
	/*********************************************************/
	public Bitmap getImage() throws IOException, InterruptedException
	{
		byte[] Bytes = getBytes();
		return ( Bytes == null ) ? null : BitmapFactory.decodeByteArray( Bytes, 0, Bytes.length );
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.getBytes()                            */
	/*                                                       */
	/*********************************************************/
	public byte[] getBytes() throws IOException, InterruptedException
	{
		if( this.m_ImageBytes != null ) return m_ImageBytes;

		byte[] Buffer = new byte[ IO_BUFFER_SIZE ];
		ByteArrayOutputStream Output = new ByteArrayOutputStream();
		int BytesRead = 0;

		while( ( BytesRead = m_InputStream.read( Buffer ) ) != -1 )
		{
			if( Thread.interrupted() ) throw new InterruptedException();
			Output.write( Buffer, 0, BytesRead );
		}

		try
		{
			this.m_ImageBytes = Output.toByteArray();
			return this.m_ImageBytes;
		}
		finally
		{
			if( Output != null ) Output.close();
		}
	}

	/*********************************************************/
	/*                                                       */
	/* WorldWeatherApi.Close()                               */
	/*                                                       */
	/*********************************************************/
	public void Close() throws IOException
	{
		if( m_InputStream != null ) m_InputStream.close();
		if( m_Connection != null ) m_Connection.disconnect();
	}
}



