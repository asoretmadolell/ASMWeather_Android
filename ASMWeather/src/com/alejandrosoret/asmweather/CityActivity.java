package com.alejandrosoret.asmweather;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.alejandrosoret.asmweather.model.City;
import com.alejandrosoret.asmweather.model.CityList;

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
	     
	     CityList cityList = ASMApplication.cityList;
	     City city = cityList.get( getIntent().getIntExtra( ASMApplication.IDRC_CITY_LIST_ID, -1 ) );
	     
	     TextView cityName = (TextView)findViewById( R.id.IDV_CITY_NAME );
	     cityName.setText( city.getName() );
	}
}