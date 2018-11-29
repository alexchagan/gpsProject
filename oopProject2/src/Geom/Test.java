package Geom;
import Coords.MyCoords;
import Coords.coords_converter;

public class Test {

	public static void main(String[] args) {
		Point3D p = new Point3D("2,4,2");
		Point3D x = new Point3D("3,6,3");
		
		Point3D p2 = p.ecef2lla();
		Point3D x2 = x.ecef2lla();
		
		System.out.println("to lla");
		
		System.out.println(p2.toString());
		System.out.println(x2.toString());
		
		Point3D p3 = p2.lla2ecef();
		Point3D x3 = x2.lla2ecef();
		
		System.out.println("back to ecef");
		
		System.out.println(p3.toString());
		System.out.println(x3.toString());

		

	}

}
