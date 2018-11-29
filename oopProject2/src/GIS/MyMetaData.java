package GIS;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import Geom.Point3D;

public class MyMetaData implements Meta_data {

	String MAC;
	String SSID;
	String AuthMode;
	String FirstSeen;
	int Channel;
	int RSSI;
	int AccuracyMeters;
	String Type;
	
	@Override
	/**
	 * Computes the UTC of current meta-data in long representation
	 */
	public long getUTC() {
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa", Locale.ENGLISH);
		try {
			Date date = format.parse(this.FirstSeen);
			long utc = date.getTime();
			return utc;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		
	}

	

}
