package gr.teicm.icd.geolocation.impl;

import org.junit.Assert;
import org.junit.Test;

public class GeolocationImplTest extends GeolocationImpl {
	
	@Test
	public void isOverlapTest(){
		
		double lat1 = 37.9109954830;
		double lng1 = 23.7122565508;
		double lat2 = 37.9120426000;
		double lng2 = 23.7098513000;
		int rad1 = 148;
		int rad2 = 100;
		//Should return true (given circles are overlapping)
		Assert.assertTrue(isOverlap(lat1, lat2, lng1, lng2, rad1, rad2));

	}

}
