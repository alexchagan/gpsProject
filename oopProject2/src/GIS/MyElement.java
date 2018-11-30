package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class MyElement implements GIS_element {

	private Point3D geom;
	private String name;
	private Meta_data data;
	private String color;
	
	public MyElement(Point3D geom, String name)
	{
		this.geom = geom;
		this.name = name;
	}
	
	@Override
	public Geom_element getGeom() {
		
		return this.geom;
	}

	@Override
	public Meta_data getData() {
		
		return this.data;
	}

	@Override
	public void translate(Point3D vec) {
		
		
	}

}
