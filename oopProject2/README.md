#oopProject2-4
========
This project gives the user tools to create a KML file from data contained in a CSV files and show this data in Google Earth.
It also implements methods of calculation on gps points (represented by latitude,longtitude,altitude) and vectors (represented by meters).
Note: KML writer and CSV reader are separated for convenience

Interfaces:
========
- coords_converter - contains calculation methods on gps points and vectors
- gis_element - represents a placemark (gps point)
- gis_layer - represents a collection of elements
- gis_project - represents a collection of projects
- meta_data - represtens the relevant data of a certain object(element/layer/project)

Relevant classes:
========
- Point3D - represents a point (x,y,z)
- MyCoords - implements coords_converter
- MyElement - implements gis_element
- MyLayer - implements gis_layer
- MyProject - implements gis_project
- MyMetaData - implements meta_data
- CSVReader - read from a single CSV file and makes a layer
- MultiCSVReader - read from all CSV files in the path and makes a project
- KMLWriter - makes KML file from layer/project

Tests:
========
MyCoordsTest - Junit tester for MyCoords

JARS:
========
- jaxb-api-2.2
- jaxb-impl-2.1.17
- JavaAPIforKml-2.2.1
- json-simple-1.1
