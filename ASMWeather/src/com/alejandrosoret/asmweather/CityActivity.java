package com.alejandrosoret.asmweather;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/*************************************************************/
/*                                                           */ 
/* CityActivity                                              */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: CityActivity Class                           */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class CityActivity extends ActionBarActivity
{
	/*********************************************************/
	/*                                                       */ 
	/* CityActivity.onCreate()                               */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
	     super.onCreate( savedInstanceState );
	     setContentView( R.layout.activity_city );
	     
	     TextView cityName = (TextView)findViewById( R.id.IDV_CITY_NAME );

	     Bundle extras = getIntent().getExtras();
	     cityName.setText( extras.getString( "IDRC_PHOTO_NAME" ) );
	}
}