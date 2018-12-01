package File_format;

import java.io.FileNotFoundException;

import GIS.MyLayer;
import GIS.MyProject;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		CSVReader csv = new CSVReader();
		
		//MyLayer ml = csv.readFromCSV("WigleWifi_20171203085618.csv"); // make a layer of a specific csv file in workspace
		
		//System.out.println(ml.get_Meta_data().toString());
		
		//KMLWriter kaki = new KMLWriter();
		//kaki.createLayer(ml);
		
		MultiCSVReader mcsv = new MultiCSVReader();
		MyProject mp = mcsv.readFromMultCSV("C:\\Users\\gogom\\git\\oopProject2\\oopProject2"); // make a project of a specific folder
		
		KMLWriter kaki2 = new KMLWriter();
		kaki2.createProject(mp);
		
		System.out.println(mp.get_Meta_data().toString());
	
		
	}

}
