package File_format;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import GIS.MyElement;
import GIS.MyLayer;
import GIS.MyMetaData;
import GIS.MyProject;
import de.micromata.opengis.kml.v_2_2_0.Folder;

public class MultiCSVReader 
{

	public MyProject readFromMultCSV() 
	{
		Date date = new Date();
		ArrayList<MyLayer> arr = new ArrayList<MyLayer>();
		CSVReader csv = new CSVReader();
		

		List<String> filenames = new LinkedList<String>();
       
		
		File folder = new File("C:\\Users\\gogom\\git\\oopProject2\\oopProject2");
	
		
		int counter = listFilesForFolder(folder,arr,csv);
		
		
		MyMetaData data = new MyMetaData(date.toString(),"This is a Project of "+counter+ " layers, was created in: "+date.toString(),null,null);
		MyProject project = new MyProject(data);
		project.addAll(arr);
		return project;

	}

	private int listFilesForFolder(final File folder,ArrayList<MyLayer> arr,CSVReader csv) {
		
		ArrayList<MyLayer> arr2 = arr;
		CSVReader csv2 = csv;
		int counter = 0;
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry,arr2 , csv2);
			} else {
				if(fileEntry.getName().contains(".csv"))
				{
					++counter;
					//filenames.add(fileEntry.getName());
					MyLayer layer = csv.readFromCSV(fileEntry.getAbsolutePath());
					arr.add(layer);
				}
			}
		}
		return counter;
	}


}
