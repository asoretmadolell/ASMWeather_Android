package com.alejandrosoret.asmweather.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alejandrosoret.asmweather.R;
import com.alejandrosoret.asmweather.model.City;

/*************************************************************/
/*                                                           */ 
/* CityAdapter                                               */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: CityAdapter Class                            */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class CityAdapter extends CursorAdapter
{
	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.CityAdapter()                             */ 
	/*                                                       */ 
	/*********************************************************/
	public CityAdapter( Context context, Cursor cursor )
     {
		super( context, cursor, 0 );
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.bindView()                                */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public void bindView( View view, Context context, Cursor cursor )
     {
		City city = new City( cursor );
		
		TextView cityName = (TextView)view.findViewById( R.id.IDV_CITY_NAME );
		cityName.setText( city.getName() );
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.newView()                                 */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public View newView( Context context, Cursor cursor, ViewGroup viewGroup)
     {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		return inflater.inflate( R.layout.listview_city_item, null );
     }
}
