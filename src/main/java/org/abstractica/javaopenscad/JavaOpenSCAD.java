package org.abstractica.javaopenscad;

import org.abstractica.javaopenscad.intf.*;
import org.abstractica.javaopenscad.intf.Path;
import org.abstractica.javaopenscad.intf.Polygon2D;
import org.abstractica.javaopenscad.intf.Vector2D;
import org.abstractica.javaopenscad.intf.text.TextAlignment;
import org.abstractica.javaopenscad.intf.text.TextAlignment.Direction;
import org.abstractica.javaopenscad.intf.text.TextAlignment.Horizontal;
import org.abstractica.javaopenscad.intf.text.TextAlignment.Vertical;
import org.abstractica.javaopenscad.intf.text.TextAttributes;
import org.abstractica.javaopenscad.intf.text.TextFont;
import org.abstractica.javaopenscad.intf.text.TextSize;

import java.io.IOException;

public interface JavaOpenSCAD
{
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vector2D
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	Vector2D vector2D(double x, double y);

	//Polygons
	Path path(Iterable<Integer> path);
	Polygon2D polygon2D(Iterable<Vector2D> vertices, int convexity);
	Polygon2D polygon2D(Iterable<Vector2D> vertices, Iterable<Path> paths, int convexity);
	Geometry2D polygon2DGeometry(Polygon2D polygon);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 2D operations
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Construction
	Geometry2DFrom2D translate2D(double x, double y);
	Geometry2DFrom2D rotate2D(double deg);
	Geometry2DFrom2D rotateAndProject2D(double xDeg, double yDeg, double zDeg);
	Geometry2DFrom2D scale2D(double x, double y);
	Geometry2DFrom2D resize2D(double x, double y, boolean autoX, boolean autoY);
	Geometry2DFrom2D mirror2D(double normX, double normY);
	Geometry2DFrom2D union2D();
	Geometry2DFrom2D intersection2D();
	Geometry2DFrom2D difference2D();
	Geometry2DFrom2D hull2D();
	Geometry2DFrom2D minkowsky2D();
	Geometry2DFrom2D offset2D(double delta, boolean chamfer);
	Geometry2DFrom2D offsetRound2D(double radius, int angularResolution);
	Geometry2DFrom2D color2D(double r, double g, double b, double a);

	//Text
	TextFont textFont(String fontName, String fontStyle, String language, String script);
	TextSize textSize(double size, double spacing);
	TextAlignment textAlignment(Direction direction, Horizontal horizontal, Vertical vertical);
	TextAttributes textAttributes(TextFont font, TextSize size, TextAlignment alignment);
	Geometry2D text(String text, TextAttributes attributes, int angularResolution);


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vector3D
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	Vector3D vector3D(double x, double y, double z);
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Polyhedron3D
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	Polyhedron3D polyhedron3D(Iterable<Vector3D> vertices, Iterable<Path> faces, int convexity);
	Geometry3D polyhedron3DGeometry(Polyhedron3D polyhedron);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 3D to 2D operations
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	Geometry2DFrom3D project(boolean cutAtZeroZ);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 3D operations
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	Geometry3DFrom3D translate3D(double x, double y, double z);
	Geometry3DFrom3D rotate3D(double xDeg, double yDeg, double zDeg);
	Geometry3DFrom3D scale3D(double x, double y, double z);
	Geometry3DFrom3D resize3D(double x, double y, double z, boolean autoX, boolean autoY, boolean autoZ);
	Geometry3DFrom3D mirror3D(double normX, double normY, double normZ);
	Geometry3DFrom3D union3D();
	Geometry3DFrom3D intersection3D();
	Geometry3DFrom3D difference3D();
	Geometry3DFrom3D hull3D();
	Geometry3DFrom3D minkowsky3D();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 2D to 3D operations
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	Geometry3DFrom2D linearExtrude(double height, double twistDeg, double scale, int slices, int convexity);
	Geometry3DFrom2D rotateExtrude(double angleDeg, int angularResolution, int convexity);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Module generation
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	Geometry2D module(Geometry2D geometry);
	Geometry3D module(Geometry3D geometry);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Generate OpenSCAD file
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	void generateOpenSCADFile(String fileName, Geometry geometry) throws IOException;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Save and load STL
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	Geometry3D loadSTL(String name) throws IOException;
	void saveSTL(String name, Geometry3D geometry) throws IOException;
}
