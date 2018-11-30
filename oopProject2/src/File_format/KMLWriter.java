package File_format;

import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.MyElement;
import GIS.MyLayer;
import Geom.Geom_element;
import Geom.Point3D;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;


public class KMLWriter {
	
	public void createElement(MyElement element)
	{
		
	}
	
	
	public void createLayer(MyLayer layer,String docName)
	{
		Kml kml = new Kml();
		Document document = kml.createAndSetDocument().withName(docName);
		Iterator<GIS_element> it = layer.iterator();
		
		while(it.hasNext())
		{
		GIS_element element = it.next();
		String name = element.getName();
		Point3D point = (Point3D) element.getGeom();
		
		
		document.createAndAddPlacemark().withName(name).withOpen(Boolean.TRUE)  
		    .createAndSetPoint().addToCoordinates(point.x(), point.y()); 
		}

		

		
	}
	/**
	public void createProject (MyProject project)
	{
		
		
	}
	*/

}
