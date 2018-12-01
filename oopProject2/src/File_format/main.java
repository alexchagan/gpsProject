package File_format;

import java.io.FileNotFoundException;

import GIS.MyLayer;
import GIS.MyProject;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		CSVReader csv = new CSVReader();
		
		MyLayer ml = csv.readFromCSV("WigleWifi_20171201110209.csv"); // make a layer of a specific csv file in workspace
		
		System.out.println(ml.get_Meta_data().toString());
		
		KMLWriter kml1 = new KMLWriter();
		kml1.createLayer(ml,"LayerTest.kml");
		
		MultiCSVReader mcsv = new MultiCSVReader();
		MyProject mp = mcsv.readFromMultCSV("C:\\Users\\gogom\\git\\oopProject2\\oopProject2"); // make a project of a specific folder
		
		KMLWriter kml2 = new KMLWriter();
		kml2.createProject(mp,"ProjectTest.kml");
		
		System.out.println(mp.get_Meta_data().toString());
	
		
	}

}
