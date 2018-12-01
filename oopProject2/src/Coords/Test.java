package Coords;

import java.util.Arrays;

import Geom.Point3D;

public class Test {

	public static void main(String[] args) {
		Point3D p = new Point3D("32.103315,35.209039,670");
		Point3D x = new Point3D("32.106352,35.205225,650");

		System.out.println("BIG TEST");
		Point3D p1 = p.lla2ecef();
		System.out.println(p1.toString());
		Point3D p2 = p1.ecef2lla();
		System.out.println(p2.toString());


		System.out.println("distance3d test");
		double k = distance3d(p,x);
		System.out.println(k);

		System.out.println("azimuth_elevation_dist test");
		double[] k1 = new double[3];
		k1 = azimuth_elevation_dist(p,x);
		System.out.println(Arrays.toString(k1));

		System.out.println("add test");
		Point3D meters = new Point3D("337.6989921,-359.2492069,-20");
		meters = add(x,meters);
		System.out.println(meters.toString());

		System.out.println("vector3d test");
		Point3D vector = vector3D(p,x);
		System.out.println(vector.toString());






	}

	public static Point3D add(Point3D gps, Point3D local_vector_in_meter) {

		Point3D gpsCartesian = gps.lla2ecef(); //transform gps point from LLA to ECEF(cartesian)
		double x = gpsCartesian.x() + local_vector_in_meter.x();
		double y = gpsCartesian.y() + local_vector_in_meter.y();
		double z = gpsCartesian.z() + local_vector_in_meter.z();
		
		Point3D point = new Point3D(x,y,z);
		return point.ecef2lla(); //transform back to LLA

	}
	
	

	/**
	 * computes the 3D distance (in meters) between the two gps like points
	 * source: google
	 */

	public static double distance3d(Point3D gps0, Point3D gps1) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(gps1.x() - gps0.x());
		double lonDistance = Math.toRadians(gps1.y() - gps0.y());
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(gps0.x())) * Math.cos(Math.toRadians(gps1.x()))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = gps0.z() - gps1.z();

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);

	}
	/**
  computes the 3D vector (in meters) between two gps like points 
	 */


	public static Point3D vector3D(Point3D gps0, Point3D gps1) {

		double x = diffLat(gps0,gps1);
		x = toMeterLat(Math.toRadians(x));
		double y = diffLon(gps0,gps1);
		y = toMeterLon(gps0,gps1);
		double z = gps1.z() - gps0.z();
		return new Point3D(x,y,z);
	}
	/**
	 * computes the polar representation of the 3D vector be gps0-->gps1
	 * source: https://www.omnicalculator.com/other/azimuth
	 */
	public static double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		double[] aed = new double[3];
		///contents////
		double radianLat0 = Math.toRadians(gps0.x()) , radianLon0 = Math.toRadians(gps0.y()) ,
				radianLat1 =Math.toRadians(gps1.x()) , radianLon1 = Math.toRadians(gps1.y()) ;
		double longDiff= radianLon1-radianLon0;
		
		double dX = gps0.x() - gps1.x();
		double dY = gps0.y() - gps1.y();
		double dZ = gps0.z() - gps1.z();

		///azimuth////yaw////
		double y = Math.sin(longDiff)*Math.cos(radianLat1);
		double x = Math.cos(radianLat0)*Math.sin(radianLat1)-Math.sin(radianLat0)*Math.cos(radianLat1)*Math.cos(longDiff);
		aed[0] = ( Math.toDegrees(Math.atan2(y, x)) + 360 ) % 360;

		/////elevation//pitch///

		aed[1] = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;

		////distance////
		aed[2] = distance3d(gps0,gps1);

		return aed;


	}
	/**
	 * return true if this point is a valid lat, lon , alt coordinate: [-90,+90],[-180,+180],[-450, +inf]
	 */

	public boolean isValid_GPS_Point(Point3D p) {
		if(p.x()<-90 || p.x()>90)
			return false;

		if(p.y()<-180 || p.y()>180)
			return false;

		if(p.z()<-450)
			return false;

		return true;
	}

	private static double diffLat(Point3D gps0, Point3D gps1)
	{
		return gps1.x() - gps0.x();
	}
	private static double diffLon(Point3D gps0, Point3D gps1)
	{
		return gps1.y() - gps0.y();
	}

	private static double toMeterLat(double val)
	{return Math.sin(val)*6371000;}

	private static double lonNorm(Point3D gps)
	{
		return Math.cos(gps.x()*Math.PI/180);
	}
	private static double lonNorm2(double val)
	{
		return Math.cos(val*Math.PI/180);
	}

	private static double toMeterLon(Point3D gps0,Point3D gps1)
	{
		double diff = Math.toRadians(diffLon(gps0,gps1));
		double norm = lonNorm(gps0);
		return Math.sin(diff)*norm*6371000;
	}

	

}
