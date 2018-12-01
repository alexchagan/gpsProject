package GIS;

import java.util.Set;
/**
 * This interface represents a gis project that contains multiple layers
 * @author Alex 206262123
 *
 */
public interface GIS_project extends Set<GIS_layer>{
	public Meta_data get_Meta_data(); 
	
}
