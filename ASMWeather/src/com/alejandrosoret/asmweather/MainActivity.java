package com.alejandrosoret.asmweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
		
		ASMApplication.cityList = new CityList();
		ASMApplication.cityList.add( new City( 0, "Madrid", "Spain", "40.000", "-3.683", 3102644, "Madrid", "http://www.worldweatheronline.com/v2/weather.aspx?q=40.4,-3.6833" ) );
		ASMApplication.cityList.add( new City( 1, "Barcelona", "Spain", "41.383", "2.183", 1570378, "Catalonia", "http://www.worldweatheronline.com/v2/weather.aspx?q=41.3833,2.1833" ) );
		ASMApplication.cityList.add( new City( 2, "Valencia", "Spain", "39.467", "-0.367", 769897, "Comunidad Valenciana", "http://www.worldweatheronline.com/v2/weather.aspx?q=39.4667,-0.3667" ) );
		
		cityListView = (ListView) findViewById( R.id.IDV_CITY_LISTVIEW );
		cityListView.setAdapter( new CityAdapter( this, ASMApplication.cityList ) );
		cityListView.setOnItemClickListener( this );
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
			Intent intent = new Intent( MainActivity.this, CityActivity.class );
			intent.putExtra( ASMApplication.IDRC_CITY_LIST_ID, (long)id );
			startActivity( intent );
		}
     }
}
