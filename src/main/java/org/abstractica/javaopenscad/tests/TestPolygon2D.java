package org.abstractica.javaopenscad.tests;

import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.intf.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPolygon2D
{
	public static void main(String[] args) throws IOException
	{
		JavaOpenSCAD os = new JavaOpenSCADImpl(true);
		List<OpenSCADVector2D> vertices = new ArrayList<>();
		vertices.add(os.vector2D(1,-1));
		vertices.add(os.vector2D(1,1));
		vertices.add(os.vector2D(-1,1));
		vertices.add(os.vector2D(-1,-1));
		List<OpenSCADPath> paths = new ArrayList<>();
		List<Integer> path1 = new ArrayList<>();
		path1.add(0);
		path1.add(1);
		path1.add(2);
		path1.add(3);
		paths.add(os.path(path1));
		OpenSCADPolygon2D polygon = os.polygon2D(vertices, paths, 1);
		OpenSCADGeometry2D geometry = os.polygon2DGeometry(polygon);
		os.generateOpenSCADFile("OpenSCAD/output.scad", geometry);
		System.out.println(AllStrings.listAllStrings());
	}
}
