package com.alejandrosoret.asmweather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class CityAdapter extends BaseAdapter
{
	private Context context;
	City cityMadrid = new City( -1, "Madrid", "Spain", "40.000", "-3.683", 3102644, "Madrid", "http://www.worldweatheronline.com/v2/weather.aspx?q=40.4,-3.6833" );
	City cityBarcelona = new City( -1, "Barcelona", "Spain", "41.383", "2.183", 1570378, "Catalonia", "http://www.worldweatheronline.com/v2/weather.aspx?q=41.3833,2.1833" );
	City cityValencia = new City( -1, "Valencia", "Spain", "39.467", "-0.367", 769897, "Comunidad Valenciana", "http://www.worldweatheronline.com/v2/weather.aspx?q=39.4667,-0.3667" );
	City[] cities = new City[] { cityMadrid, cityBarcelona, cityValencia };
	
	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.CityAdapter()                             */ 
	/*                                                       */ 
	/*********************************************************/
	public CityAdapter( Context context )
	{
		this.context = context;
	}

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.getCount()                                */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public int getCount()
     {
	     return cities.length;
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.getItem()                                 */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public Object getItem( int position )
     {
	     return null;
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.getItemId()                               */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public long getItemId( int position )
     {
	     return position;
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.getView()                                 */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public View getView( int position, View convertView, ViewGroup parent )
     {
		View itemView = null;
		
		if( convertView == null )
		{
			LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			itemView = inflater.inflate( R.layout.listview_city_item, null );
		}
		else itemView = convertView;
		
		TextView cityName = (TextView)itemView.findViewById( R.id.IDV_CITY_NAME );
		cityName.setText( cities[ position ].getName() );
		
	     return itemView;
     }
}
