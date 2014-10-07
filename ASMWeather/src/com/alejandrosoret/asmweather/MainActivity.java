package com.alejandrosoret.asmweather;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

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
public class MainActivity extends ActionBarActivity
{

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
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}
}
