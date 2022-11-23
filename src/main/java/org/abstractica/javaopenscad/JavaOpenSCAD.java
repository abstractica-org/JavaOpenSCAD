package org.abstractica.javaopenscad;

import org.abstractica.javaopenscad.intf.*;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment.Direction;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment.Horizontal;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment.Vertical;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAttributes;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextFont;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextSize;

import java.io.IOException;
import java.util.List;

public interface JavaOpenSCAD
{
	//Polygons
	OpenSCADGeometry2D polygon2D(List<Double> vertices);
	OpenSCADGeometry2D polygon2D(List<Double> vertices, List<List<Integer>> paths);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 2D operations
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Construction
	OpenSCADGeometry2DFrom2D translate2D(double x, double y);
	OpenSCADGeometry2DFrom2D rotate2D(double deg);
	OpenSCADGeometry2DFrom2D rotateAndProject2D(double xDeg, double yDeg, double zDeg);
	OpenSCADGeometry2DFrom2D scale2D(double x, double y);
	OpenSCADGeometry2DFrom2D resize2D(double x, double y, boolean autoX, boolean autoY);
	OpenSCADGeometry2DFrom2D mirror2D(double normX, double normY);
	OpenSCADGeometry2DFrom2D union2D();
	OpenSCADGeometry2DFrom2D intersection2D();
	OpenSCADGeometry2DFrom2D difference2D();
	OpenSCADGeometry2DFrom2D hull2D();
	OpenSCADGeometry2DFrom2D minkowsky2D();
	OpenSCADGeometry2DFrom2D offset2D(double delta, boolean chamfer);
	OpenSCADGeometry2DFrom2D offsetRound2D(double radius, int angularResolution);
	OpenSCADGeometry2DFrom2D color2D(double r, double g, double b, double a);

	//Text
	OpenSCADTextFont textFont(String fontName, String fontStyle, String language, String script);
	OpenSCADTextSize textSize(double size, double spacing);
	OpenSCADTextAlignment textAlignment(Direction direction, Horizontal horizontal, Vertical vertical);
	OpenSCADTextAttributes textAttributes(OpenSCADTextFont font, OpenSCADTextSize size, OpenSCADTextAlignment alignment);
	OpenSCADGeometry2D text(String text, OpenSCADTextAttributes attributes, int angularResolution);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Polyhedron3D
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3D polyhedron3D(List<Double> vertices, List<List<Integer>> faces);
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 3D to 2D operations
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry2DFrom3D project(boolean cutAtZeroZ);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 3D operations
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3DFrom3D translate3D(double x, double y, double z);
	OpenSCADGeometry3DFrom3D rotate3D(double xDeg, double yDeg, double zDeg);
	OpenSCADGeometry3DFrom3D scale3D(double x, double y, double z);
	OpenSCADGeometry3DFrom3D resize3D(double x, double y, double z, boolean autoX, boolean autoY, boolean autoZ);
	OpenSCADGeometry3DFrom3D mirror3D(double normX, double normY, double normZ);
	OpenSCADGeometry3DFrom3D union3D();
	OpenSCADGeometry3DFrom3D intersection3D();
	OpenSCADGeometry3DFrom3D difference3D();
	OpenSCADGeometry3DFrom3D hull3D();
	OpenSCADGeometry3DFrom3D minkowsky3D();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 2D to 3D operations
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3DFrom2D linearExtrude(double height, double twistDeg, double scale, int slices, int convexity);
	OpenSCADGeometry3DFrom2D rotateExtrude(double angleDeg, int angularResolution, int convexity);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Module generation
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry2D module(OpenSCADGeometry2D geometry);
	OpenSCADGeometry3D module(OpenSCADGeometry3D geometry);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Generate OpenSCAD file
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	void generateOpenSCADFile(String fileName, OpenSCADGeometry geometry) throws IOException;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Save and load STL
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3D loadSTL(String name) throws IOException;
	void saveSTL(String name, OpenSCADGeometry3D geometry) throws IOException;
}
