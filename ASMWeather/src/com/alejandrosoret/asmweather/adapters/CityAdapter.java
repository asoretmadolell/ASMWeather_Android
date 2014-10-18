package com.alejandrosoret.asmweather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alejandrosoret.asmweather.R;
import com.alejandrosoret.asmweather.model.City;
import com.alejandrosoret.asmweather.model.CityList;

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
public class CityAdapter extends ArrayAdapter< City >
{
	private Context context;
	private CityList cityList;
	
	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.CityAdapter()                             */ 
	/*                                                       */ 
	/*********************************************************/
	public CityAdapter( Context context, CityList cityList )
     {
	     super( context, R.layout.listview_city_item );
	     this.context = context;
	     this.cityList = cityList;
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.getCount()                                */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public int getCount()
     {
	     return cityList.getCityList().size();
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.getItem()                                 */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public City getItem( int position )
     {
	     return cityList.get( position );
     }

	/*********************************************************/
	/*                                                       */ 
	/* CityAdapter.getItemId()                               */ 
	/*                                                       */ 
	/*********************************************************/
	@Override
     public long getItemId( int position )
     {
		City city = cityList.get( position );
		long hashcode = city.hashCode();
		
	     return cityList.get( position ).hashCode();
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
		cityName.setText( cityList.get( position ).getName() );
		
	     return itemView;
     }
}
