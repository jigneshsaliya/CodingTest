package com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

public class StoreRegistrationDetails {

	public static void storeRegDetails(JSONObject details, String localFileStorage) {
		
		File file = new File(localFileStorage+"DeviceRegistrationDetails.properties");
		FileOutputStream fileOut;
		Properties properties = new Properties();

		Iterator<?> keys = details.keys();

		while(keys.hasNext())
		{
			String key = keys.next().toString();
			try 
			{
				properties.setProperty(key, details.get(key).toString());
			} 
			catch (JSONException e) {
				e.printStackTrace();
			}
		}

		try {
			fileOut = new FileOutputStream(file, true);
			properties.store(fileOut, "Device Details");
			fileOut.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
