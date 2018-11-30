package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.MyElement;
import GIS.MyLayer;
import Geom.Point3D;

public class CSVReader {
/**
 * This method reads from a CSV file and creates a layer object that contains an array of elements.
 * each element is 1 row in the CSV file.
 * source: yael landau moodle
 * @param path - CSV file path
 * @return layer object
 */
	public MyLayer readFromCSV(String path)

	{
		MyLayer layer = new MyLayer();
		String csvFile = path;
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			br.readLine();
			br.readLine();

			while ((line = br.readLine()) != null) 
			{

				String[] userInfo = line.split(cvsSplitBy);

//				System.out.println("Mac: " + userInfo[0] + " , SSID: " + userInfo[1] +
//						" AuthMode: " + userInfo[2] + " FirstSeen: " + userInfo[3] + " Channel: " 
//						+ userInfo[4] + " RSSI: " + userInfo[5] + " CurrentLatitude: " + userInfo[6] 
//								+ " CurrentLongitude: " + userInfo[7] + " AltitudeMeters: " + userInfo[8] 
//										+ " AccuracyMeters: " + userInfo[9] + " Type: " + userInfo[10] );
				double lat = Double.parseDouble(userInfo[6]);
				double lon = Double.parseDouble(userInfo[7]);
				double alt = Double.parseDouble(userInfo[8]);
				String color;
				if(alt>0)
					color = "green";
				else
					color = "blue";

				MyElement element = new MyElement(new Point3D(lat,lon,alt),userInfo[1],color);
				layer.add(element);
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return layer;
	}

}
