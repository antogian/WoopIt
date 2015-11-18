package gr.teicm.icd.geolocation;

public interface Geolocation {
	
	public boolean isOverlap(double lat1, double lat2, double lng1, double lng2, int rad1, int rad2);
}
