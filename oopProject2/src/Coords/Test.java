package Coords;

import java.util.Arrays;

import Geom.Point3D;

public class Test {

	public static void main(String[] args) {
		Point3D p = new Point3D("32.103315,35.209039,670");
		Point3D x = new Point3D("32.106352,35.205225,650");
		
		Point3D p1 = new Point3D("51.50,0,0");
		Point3D x1 = new Point3D("-22.97,-43.18,0");
		
		System.out.println("distance3d test");
		double k = distance3d(p,x);
		System.out.println(k);
		double[] k1 = new double[3];
		k1 = azimuth_elevation_dist(p1,x1);
		System.out.println("azimuth_elevation_dist test");
		System.out.println(Arrays.toString(k1));

	}

	private static double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double dX = gps0.x() - gps1.x();
		double dY = gps0.y() - gps1.y();
		double dZ = gps0.z() - gps1.z();
		
		double yaw = Math.atan2(dZ, dX);
		double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
		
		//double yaw = azimuth(gps0,gps1);
		//double pitch = distance3d(gps0,gps1);
		//double ele = elevation(gps0,gps1);

		double[] aed = new double[3];
		aed[0] = azimuth(gps0,gps1);
		aed[1] = pitch;
		aed[2] = distance3d(gps0,gps1);

		return aed;
	}

//	/**
//	 * Calculates the azimuth of 2 gps points
//	 * source: https://www.dcode.fr/azimuth , https://www.omnicalculator.com/other/azimuth
//	 * @param gps0
//	 * @param gps1
//	 * @return
//	 */
	private static double azimuth(Point3D gps0, Point3D gps1)
	{
//		System.out.println("TEST");
//		
//		double lonDiff = gps1.y()-gps0.y();
//		
//		
//		System.out.println("Math.atan2(Math.sin("+lonDiff+")*Math.cos("+gps1.x()+"),Math.cos("+gps0.x()+")*Math.sin("+gps1.x()+")-Math.sin("+gps0.x()+"*Math.cos("+gps1.x()+")*Math.cos("+lonDiff+"))");
//		
//		
//		double ans = Math.atan2(Math.sin(lonDiff)*Math.cos(gps1.x()),
//				Math.cos(gps0.x())*Math.sin(gps1.x())-Math.sin(gps0.x())*Math.cos(gps1.x())*Math.cos(lonDiff));
//		
//		
//		return ans;
		double x = Math.cos(gps0.x()) * Math.sin(gps1.x()) - Math.sin(gps0.x()) * Math.cos(gps1.x())
				* Math.cos(gps1.y()-gps0.y());
		double y = Math.sin(gps1.y()-gps0.y()) * Math.cos(gps1.x());
		return Math.atan2(y,x);
		
//		double diffLon = gps1.y() - gps0.y();
//		return Math.atan2((Math.sin(diffLon) * Math.cos (gps1.x())), (Math.cos (gps0.x()) * Math.sin(gps1.x())
//				- Math.sin(gps0.x()) * Math.cos(gps1.x()) * Math.cos(diffLon)));
	}
//	/**
//	 * Calculates the polar distance between 2 gps points
//	 * @param gps0
//	 * @param gps1
//	 * @return
//	 */
//	
//	


	private static double distance3d(Point3D gps0, Point3D gps1) {
		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(gps1.x() - gps0.x());
		double lonDistance = Math.toRadians(gps1.y() - gps0.y());
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(gps0.x())) * Math.cos(Math.toRadians(gps1.x()))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000;  // convert to meters

		double height = gps0.z() - gps1.z();

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}
	
//	public static double distance3d2(Point3D gps0, Point3D gps1) {
//		///////////////////////////////////////////////////////
//		///////// excel calc///////////////////////////////////
//		System.out.println("TEST");
//				double latDiff = gps1.x() - gps0.x(); 
//				double lonDiff = gps1.y() - gps0.y();
//				System.out.println(latDiff);
//				System.out.println(lonDiff);
//				double diffRadianLat = toRadian(latDiff);
//				double diffRadianLon = toRadian(lonDiff);
//				System.out.println(diffRadianLat);
//				System.out.println(diffRadianLon);
//				double MeterLat = toMeterLat(diffRadianLat);
//				double MeterLon = toMeterLon(diffRadianLon);
//				System.out.println(MeterLat);
//				System.out.println(MeterLon);
//				System.out.println("TEST END");
//				return Math.sqrt(MeterLat*MeterLat+MeterLon*MeterLon);
//	}
	
public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
		double x = gps1.x() - gps0.x();
		x = toMeterLat(toRadian(x));
		double y = gps1.y() - gps0.y();
		y = toMeterLon(toRadian(y));
		double z = gps1.z() - gps0.z();
		return new Point3D(x,y,z);
	}
	
	private static double toRadian(double val)
	{return val*Math.PI/180;}

	private static double toMeterLat(double val)
	{return Math.sin(val)*6371000;}

	private static double toMeterLon(double val)
	{return Math.sin(val)*6371000*0.847091;}
	
	private static double radToDeg(double val)
	{
		return val * (180/Math.PI);
	}

}
