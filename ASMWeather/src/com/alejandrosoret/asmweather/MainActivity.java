package com.alejandrosoret.asmweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.alejandrosoret.asmweather.adapters.CityAdapter;
import com.alejandrosoret.asmweather.db.WeatherDAO;
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
		
		ASMApplication.initializeData();
		
		cityListView = (ListView) findViewById( R.id.IDV_CITY_LISTVIEW );
		cityListView.setAdapter( new CityAdapter( this, new WeatherDAO( this ).selectAllCities() ) );
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
			intent.putExtra( ASMApplication.IDRC_CITY_LIST_ID, id );
			startActivity( intent );
		}
     }
}
