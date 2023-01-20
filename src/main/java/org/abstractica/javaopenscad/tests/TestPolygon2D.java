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
		vertices.add(os.vector2D(1.0, -1.0));
		vertices.add(os.vector2D(1.0, 2.0));
		vertices.add(os.vector2D(-1.0, 1.0));
		vertices.add(os.vector2D(-1.0, -1.0));
		OpenSCADGeometry2D geometry = os.polygon2D(vertices);
		os.generateOpenSCADFile("OpenSCAD/output.scad", geometry);
		System.out.println(AllStrings.listAllStrings());
	}
}
