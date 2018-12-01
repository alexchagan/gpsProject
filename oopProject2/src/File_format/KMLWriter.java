package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.xml.bind.Marshaller;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.MyElement;
import GIS.MyLayer;
import Geom.Geom_element;
import Geom.Point3D;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;


public class KMLWriter {


	public void createLayer(MyLayer layer) throws FileNotFoundException
	{
		
		Kml kml = new Kml();
		Document document = kml.createAndSetDocument();
		Iterator<GIS_element> it = layer.iterator();

		while(it.hasNext())
		{
			GIS_element element = it.next();
			String name = element.getData().getName();
			Point3D point = (Point3D) element.getGeom();
			String color = element.getData().getColor();
			
			document.createAndAddPlacemark().withName(name).withOpen(Boolean.TRUE)
			.createAndSetPoint().addToCoordinates(point.y(), point.x()); 
			document.setStyleUrl("#placemark-red");
			
			
			kml.marshal(new File("kmltest1.kml"));
							
		}




	} 

}



