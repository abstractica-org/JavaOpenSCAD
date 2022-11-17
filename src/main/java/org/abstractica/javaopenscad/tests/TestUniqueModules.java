package org.abstractica.javaopenscad.tests;

import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2DFrom2D;
import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.intf.OpenSCADPolygon2D;
import org.abstractica.javaopenscad.intf.OpenSCADVector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestUniqueModules
{
	public static void main(String[] args) throws IOException
	{
		JavaOpenSCAD os = new JavaOpenSCADImpl(true);
		List<OpenSCADVector2D> vertices1 = new ArrayList<>();
		vertices1.add(os.vector2D(1,-1));
		vertices1.add(os.vector2D(1,2));
		vertices1.add(os.vector2D(-1,1));
		vertices1.add(os.vector2D(-1,-1));
		OpenSCADPolygon2D polygon1 = os.polygon2D(vertices1, 1);
		OpenSCADGeometry2D geo1 = os.polygon2DGeometry(polygon1);
		OpenSCADGeometry2D t1 = os.translate2D(1,1).add(geo1);
		OpenSCADGeometry2DFrom2D r1 = os.rotate2D(45).add(t1);
		OpenSCADGeometry2D m1 = os.module(r1);

		List<OpenSCADVector2D> vertices2 = new ArrayList<>();
		vertices2.add(os.vector2D(1,-1));
		vertices2.add(os.vector2D(1,2));
		vertices2.add(os.vector2D(-1,1));
		vertices2.add(os.vector2D(-1,-1));
		OpenSCADPolygon2D polygon2 = os.polygon2D(vertices2, 1);
		OpenSCADGeometry2D geo2 = os.polygon2DGeometry(polygon2);
		OpenSCADGeometry2DFrom2D t2 = os.translate2D(1,1).add(geo2);
		OpenSCADGeometry2DFrom2D r2 = os.rotate2D(45).add(t2);
		OpenSCADGeometry2D m2 = os.module(r2);
		//t2.add(geo1);

		OpenSCADGeometry2DFrom2D union = os.union2D().add(m1).add(m2);
		os.generateOpenSCADFile("OpenSCAD/output.scad", m1);

		System.out.println(AllStrings.listAllStrings());
	}
}
