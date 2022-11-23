package org.abstractica.javaopenscad.tests;

import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2DFrom2D;
import org.abstractica.javaopenscad.JavaOpenSCAD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestUniqueModules
{
	public static void main(String[] args) throws IOException
	{
		JavaOpenSCAD os = new JavaOpenSCADImpl(true);
		List<Double> vertices = new ArrayList<>();
		vertices.add(1.0); vertices.add(-1.0);
		vertices.add(1.0); vertices.add(1.0);
		vertices.add(-1.0); vertices.add(1.0);
		vertices.add(-1.0); vertices.add(-1.0);
		OpenSCADGeometry2D geo1 = os.polygon2D(vertices);
		OpenSCADGeometry2D t1 = os.translate2D(1,1).add(geo1);
		OpenSCADGeometry2DFrom2D r1 = os.rotate2D(45).add(t1);
		OpenSCADGeometry2D m1 = os.module(r1);

		List<Double> vertices2 = new ArrayList<>();
		vertices.add(1.0); vertices.add(-1.0);
		vertices.add(1.0); vertices.add(1.0);
		vertices.add(-1.0); vertices.add(1.0);
		vertices.add(-1.0); vertices.add(-1.0);
		OpenSCADGeometry2D geo2 = os.polygon2D(vertices);
		OpenSCADGeometry2DFrom2D t2 = os.translate2D(1,1).add(geo2);
		OpenSCADGeometry2DFrom2D r2 = os.rotate2D(45).add(t2);
		OpenSCADGeometry2D m2 = os.module(r2);
		//t2.add(geo1);

		OpenSCADGeometry2DFrom2D union = os.union2D().add(m1).add(m2);
		os.generateOpenSCADFile("OpenSCAD/output.scad", m1);

		System.out.println(AllStrings.listAllStrings());
	}
}
