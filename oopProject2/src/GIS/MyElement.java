package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class MyElement implements GIS_element {

	private Point3D geom;
	private String name;
	private Meta_data data;
	private String color;
	
	public MyElement(Point3D geom, String name,String color)
	{
		this.geom = geom;
		this.name = name;
		this.color = color;
	}
	
	@Override
	public Geom_element getGeom() {
		
		return this.geom;
	}
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public Meta_data getData() {
		
		return this.data;
	}

	@Override
	public void translate(Point3D vec) {
		
		
	}

}
