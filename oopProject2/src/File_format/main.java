package File_format;

import java.io.File;
import java.io.FileNotFoundException;

import GIS.MyLayer;
import GIS.MyProject;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		CSVReader csv = new CSVReader();
		MyLayer ml = csv.readFromCSV("WigleWifi_20171203085618.csv");
		
		System.out.println(ml.get_Meta_data().toString());
		
		KMLWriter kaki = new KMLWriter();
		kaki.createLayer(ml);
		
		MultiCSVReader mcsv = new MultiCSVReader();
		MyProject mp = mcsv.readFromMultCSV();
		
		KMLWriter kaki2 = new KMLWriter();
		kaki2.createProject(mp);
		
		System.out.println(mp.get_Meta_data().toString());
	
		
	}

}
