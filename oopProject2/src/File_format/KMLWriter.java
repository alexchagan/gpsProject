package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.xml.bind.Marshaller;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.MyElement;
import GIS.MyLayer;
import GIS.MyProject;
import Geom.Geom_element;
import Geom.Point3D;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;


public class KMLWriter {


	/**
	 * This method creates a KML file that contains all the elements in a  layer
	 * @param layer
	 * @throws FileNotFoundException
	 */
	public void createLayer(MyLayer layer) throws FileNotFoundException
	{

		Kml kml = new Kml();
		Document document = kml.createAndSetDocument();
		Iterator<GIS_element> it = layer.iterator();

		while(it.hasNext())
		{
			//data
			GIS_element element = it.next();
			String name = element.getData().getName();
			Point3D point = (Point3D) element.getGeom();
			String color = element.getData().getColor();

			if(color=="blue")
				getColorInfoBlue(document);
			if(color=="green")
				getColorInfoGreen(document);

			//document creation
			document.createAndAddPlacemark().withName(name).withOpen(Boolean.TRUE).withStyleUrl("#style_" + color)
			.withDescription(element.getData().toString())
			.createAndSetPoint().addToCoordinates(point.y(), point.x()); 



			kml.marshal(new File("kmltest1.kml"));

		}

	} 
	/**
	 * This method creates a KML file that contains all the elements in all the layers in the project.
	 * @param project
	 * @throws FileNotFoundException
	 */
	public void createProject(MyProject project) throws FileNotFoundException
	{
		Kml kml = new Kml();
		Document document = kml.createAndSetDocument();
		Iterator<GIS_layer> it = project.iterator();

		while(it.hasNext())
		{
			GIS_layer layer = it.next();
			Iterator<GIS_element> it2 = layer.iterator();
			while(it2.hasNext())
			{
				//data
				GIS_element element = it2.next();
				String name = element.getData().getName();
				Point3D point = (Point3D) element.getGeom();
				String color = element.getData().getColor();

				if(color=="blue")
					getColorInfoBlue(document);
				if(color=="green")
					getColorInfoGreen(document);

				//document creation
				document.createAndAddPlacemark().withName(name).withOpen(Boolean.TRUE).withStyleUrl("#style_" + color)
				.withDescription(element.getData().toString())
				.createAndSetPoint().addToCoordinates(point.y(), point.x()); 
			}
		}
		kml.marshal(new File("multkmltest.kml"));

	}

	/**
	 * Gives info about the color blue so we can set the place-mark in such color.
	 * @param document
	 */
	private void getColorInfoBlue(Document document)
	{
		Icon blue = new Icon()
				.withHref("http://maps.google.com/mapfiles/ms/icons/blue-dot.png");
		Style style = document.createAndAddStyle();
		style.withId("style_" + "blue") // set the stylename to use this style from the placemark
		.createAndSetIconStyle().withScale(1.0).withIcon(blue); // set size and icon
		style.createAndSetLabelStyle().withColor("0000ff").withScale(0.5); // set color and size of the continent name

	}

	/**
	 * Gives info about the color green so we can set the place-mark in such color.
	 * @param document
	 */
	private void getColorInfoGreen(Document document)
	{
		Icon green = new Icon()
				.withHref("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
		Style style = document.createAndAddStyle();
		style.withId("style_" + "green") // set the stylename to use this style from the placemark
		.createAndSetIconStyle().withScale(1.0).withIcon(green); // set size and icon
		style.createAndSetLabelStyle().withColor("00ff00").withScale(0.5); // set color and size of the continent
	}


}



