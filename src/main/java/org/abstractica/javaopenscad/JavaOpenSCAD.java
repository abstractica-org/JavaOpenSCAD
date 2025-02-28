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

public interface JavaOpenSCAD
{
	//Vector2D
	OpenSCADVector2D vector2D(double x, double y);
	//Polygons
	OpenSCADGeometry2D polygon2D(Iterable<OpenSCADVector2D> vertices);
	OpenSCADGeometry2D polygon2D(Iterable<OpenSCADVector2D> vertices, Iterable<? extends Iterable<Integer>> paths);

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

	//Text
	OpenSCADTextFont textFont(String fontName, String fontStyle, String language, String script);
	OpenSCADTextSize textSize(double size, double spacing);
	OpenSCADTextAlignment textAlignment(Direction direction, Horizontal horizontal, Vertical vertical);
	OpenSCADTextAttributes textAttributes(OpenSCADTextFont font, OpenSCADTextSize size, OpenSCADTextAlignment alignment);
	OpenSCADGeometry2D text(String text, OpenSCADTextAttributes attributes, int angularResolution);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vector3D
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADVector3D vector3D(double x, double y, double z);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Matrix multiplication
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3DFrom3D multMatrix3D(double m00, double m01, double m02, double m03,
	                                          double m10, double m11, double m12, double m13,
	                                      double m20, double m21, double m22, double m23);
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Polyhedron3D
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3D polyhedron3D(Iterable<OpenSCADVector3D> vertices, Iterable<? extends Iterable<Integer>> faces);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 3D to 2D operations
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry2DFrom3D project(boolean cutAtZeroZ);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 3D operations
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3DFrom3D color3D(double r, double g, double b, double a);
	OpenSCADGeometry3DFrom3D color3D(double r, double g, double b);
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
	OpenSCADGeometry3DFrom2D linearExtrude(double height,
	                                       double twistDeg,
	                                       double scale,
	                                       int slices,
	                                       boolean centerZ);
	OpenSCADGeometry3DFrom2D rotateExtrude(double angleDeg, int angularResolution);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Module generation
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry2D module(OpenSCADGeometry2D geometry);
	OpenSCADGeometry3D module(OpenSCADGeometry3D geometry);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Get min and max from geometry2D and geometry3D
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADVector2D getMin2D(OpenSCADGeometry2D geometry);
	OpenSCADVector2D getMax2D(OpenSCADGeometry2D geometry);
	OpenSCADVector3D getMin3D(OpenSCADGeometry3D geometry);
	OpenSCADVector3D getMax3D(OpenSCADGeometry3D geometry);
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Cache Geometry3D as STL
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3D cacheGeometry3D(OpenSCADGeometry3D geometry) throws IOException;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Generate OpenSCAD file
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	void generateOpenSCADFile(String fileName, OpenSCADGeometry geometry) throws IOException;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Save and load STL
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3D loadSTL(String fileName) throws IOException;
	void saveSTL(String fileName, OpenSCADGeometry3D geometry) throws IOException;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Save and load 3MF
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	OpenSCADGeometry3D load3MF(String fileName) throws IOException;
	void save3MF(String fileName, OpenSCADGeometry3D geometry) throws IOException;
}
