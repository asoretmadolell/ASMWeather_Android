package com.alejandrosoret.asmweather;

import android.app.Application;

import com.alejandrosoret.asmweather.model.CityList;

/*************************************************************/
/*                                                           */ 
/* ASMApplication                                            */ 
/* (c)2014 Alejandro                                         */ 
/*                                                           */ 
/* Description: ASMApplication Class                         */ 
/*              ASMWeather Project                           */ 
/*                                                           */ 
/*                                                           */ 
/*************************************************************/
public class ASMApplication extends Application
{
	public static CityList cityList;
	public static final String IDRC_CITY_LIST_ID = "IDRC_CITY_LIST_ID";
}