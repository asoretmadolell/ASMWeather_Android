package com.alejandrosoret.asmweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.alejandrosoret.asmweather.adapters.CityAdapter;
import com.alejandrosoret.asmweather.model.City;
import com.alejandrosoret.asmweather.model.CityList;

/*************************************************************/
/*                                                           */ 
/* MainActivity                                              */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: MainActivity Class                           */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class MainActivity extends ActionBarActivity implements OnItemClickListener
{
	private ListView cityListView;

	/*********************************************************/
	/*                                                       */ 
	/* MainActivity.onCreate()                               */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		cityListView = (ListView) findViewById( R.id.IDV_CITY_LISTVIEW );
		cityListView.setAdapter( new CityAdapter( this ) );
		cityListView.setOnItemClickListener( this );
		
		CityList cityList = new CityList();
		
		City cityMadrid = new City( -1, "Madrid", "Spain", "40.000", "-3.683", 3102644, "Madrid", "http://www.worldweatheronline.com/v2/weather.aspx?q=40.4,-3.6833" );
		City cityBarcelona = new City( -1, "Barcelona", "Spain", "41.383", "2.183", 1570378, "Catalonia", "http://www.worldweatheronline.com/v2/weather.aspx?q=41.3833,2.1833" );
		City cityValencia = new City( -1, "Valencia", "Spain", "39.467", "-0.367", 769897, "Comunidad Valenciana", "http://www.worldweatheronline.com/v2/weather.aspx?q=39.4667,-0.3667" );
		
		cityList.add( cityMadrid );
		cityList.add( cityBarcelona );
		cityList.add( cityValencia );
	}

	/*********************************************************/
	/*                                                       */ 
	/* MainActivity.onItemClick()                            */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
     {
		if( (ListView)parent == cityListView )
		{
			TextView cityName = (TextView)view.findViewById( R.id.IDV_CITY_NAME );
			
			Intent intent = new Intent( MainActivity.this, CityActivity.class );
			Bundle extras = new Bundle();
			extras.putString( ASMApplication.IDRC_CITY_NAME, (String)cityName.getText() );
			intent.putExtras( extras );
			startActivity( intent );
		}
     }
}
