package gr.teicm.icd.geolocation.impl;

import gr.teicm.icd.geolocation.Geolocation;

public class GeolocationImpl implements Geolocation {
	
	public boolean isOverlap(double lat1, double lat2, double lng1, double lng2, int rad1, int rad2){
		// Using harvesine formula
		
		int R = 6371000; // Earth radius in meters
		// Latitude degrees to radians
		double f1 = lat1 * Math.PI / 180;
		double f2 = lat2 * Math.PI / 180;
		// Distances between latitudes and longitudes (radians)
		double Df = (lat2-lat1) * Math.PI / 180;
		double Dl = (lng2-lng1) * Math.PI / 180;
		double a = Math.sin(Df/2) * Math.sin(Df/2) + Math.cos(f1) * Math.cos(f2) * Math.sin(Dl/2) * Math.sin(Dl/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c;
		// if distance is less than radius1 + radius2 the circles are overlaping
		if (d <= rad1+rad2)
			return true;
		else
			return false;
	}
}
