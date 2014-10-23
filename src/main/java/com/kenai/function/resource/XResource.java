package com.kenai.function.resource;

import java.lang.reflect.Field;

import android.R;
import android.content.Context;

public class XResource {
	int getResourdidByResourceName(Context context,String name){
		int id=0;
		try{
			Field myField=R.drawable.class.getField(name);
			myField.setAccessible(true);
			try{
				id=myField.getInt(null);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return id;
		
	}

}
