package Coords;

import Geom.Point3D;
/**
 * 
 * @author Alex Chagan 206262123
 *
 */

public class MyCoords implements coords_converter {

	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in meters)
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

		double x = toMeterLat(toRadian(gps.x())) + local_vector_in_meter.x();
		double y = toMeterLon(toRadian(gps.y())) + local_vector_in_meter.y();
		double z = gps.z() + local_vector_in_meter.z();
		return new Point3D(x,y,z);

	}


	/**
	 * computes the 3D distance (in meters) between the two gps like points
	 * source: google
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		
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

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
		double x = gps1.x() - gps0.x();
		x = toMeterLat(toRadian(x));
		double y = gps1.y() - gps0.y();
		y = toMeterLon(toRadian(y));
		double z = gps1.z() - gps0.z();
		return new Point3D(x,y,z);
	}
	/**
	 * computes the polar representation of the 3D vector be gps0-->gps1
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
		double[] aed = new double[3];
		
		aed[0] = azimuth(gps0,gps1);
		aed[1] = pitch(gps0,gps1);
		aed[2] = distance3d(gps0,gps1);

		return aed;


	}
	/**
	 * return true if this point is a valid lat, lon , alt coordinate: [-90,+90],[-180,+180],[-450, +inf]
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if(p.x()<-90 || p.x()>90)
			return false;

		if(p.y()<-180 || p.y()>180)
			return false;

		if(p.z()<-450)
			return false;

		return true;
	}

	private double toRadian(double val)
	{return val*Math.PI/180;}

	private double toMeterLat(double val)
	{return Math.sin(val)*6371000;}

	private double toMeterLon(double val)
	{return Math.sin(val)*6371000*0.847091;}

	/////////////////////////////////////////////////////////////////
	/**
	 * Calculates the azimuth of 2 gps points
	 * source: https://www.dcode.fr/azimuth
	 * @param gps0
	 * @param gps1
	 * @return
	 */
	
	private double azimuth(Point3D gps0, Point3D gps1)
	{

		double x = Math.cos(gps0.x()) * Math.sin(gps1.x()) - Math.sin(gps0.x()) * Math.cos(gps1.x())
				* Math.cos(gps1.y()-gps0.y());
		double y = Math.sin(gps1.y()-gps0.y()) * Math.cos(gps1.x());
		return Math.atan2(y,x);

	}
	
	/**
	 * Calculates the pitch of the 2 gps points
	 * @param gps0
	 * @param gps1
	 * @return
	 */
	private double pitch(Point3D gps0, Point3D gps1)
	{
		double dX = gps0.x() - gps1.x();
		double dY = gps0.y() - gps1.y();
		double dZ = gps0.z() - gps1.z();
		double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
		return pitch;
	}
	



//	/**
//	 * Calculates the elevation between 2 gps points
//	 * @param gps0
//	 * @param gps1
//	 * @return
//	 */
//	private double elevation(Point3D gps0, Point3D gps1)
//	{
//
//		return (Double) null;
//	}

}


