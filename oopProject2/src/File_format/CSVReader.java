package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import GIS.Meta_data;
import GIS.MyElement;
import GIS.MyLayer;
import GIS.MyMetaData;
import Geom.Point3D;

public class CSVReader {
	/**
	 * This method reads from a CSV file and creates a layer object that contains an array of elements.
	 * each element is 1 row in the CSV file.
	 * relevant info:
	 * userInfo[1] = name, userInfo[3] = utc, userInfo[6] = lat, userInfo[7] = lon, userInfo[8] = alt, userInfo[10] = type.
	 * source: yael landau moodle
	 * @param path - CSV file path
	 * @return layer object
	 */
	public MyLayer readFromCSV(String path)

	{
		Date date = new Date();
		int counter = 0;
		ArrayList<MyElement> arr = new ArrayList<MyElement>();

		String csvFile = path;
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			br.readLine();
			br.readLine();

			while ((line = br.readLine()) != null) 
			{
				++counter;
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

				MyMetaData data = new MyMetaData(userInfo[3],"This is a placemark named: "+userInfo[1]+
						".\n lat:"+lat+" lon:"+lon+" alt:"+alt+"\n Type: "+userInfo[10]+"\n Was recorded in: "+userInfo[3]+"\n"
						,userInfo[1],color);

				MyElement element = new MyElement(new Point3D(lat,lon,alt),userInfo[1],color,data);

				arr.add(element);

				
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}

		MyMetaData data = new MyMetaData(date.toString(),"This is a layer of "+counter+ " elements, was created in: "+date.toString(),null,null);
		MyLayer layer = new MyLayer(data);
		layer.addAll(arr);
		return layer;
	}

}
