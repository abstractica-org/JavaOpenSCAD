package org.abstractica.javaopenscad.tests;

import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2DFrom2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry3D;
import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.intf.OpenSCADVector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestStlModules
{
	public static void main(String[] args) throws IOException
	{
		JavaOpenSCAD os = new JavaOpenSCADImpl(true);
		List<OpenSCADVector2D> vertices = new ArrayList<>();
		vertices.add(os.vector2D(1.0, -1.0));
		vertices.add(os.vector2D(1.0, 1.0));
		vertices.add(os.vector2D(-1.0, 1.0));
		vertices.add(os.vector2D(-1.0, -1.0));
		OpenSCADGeometry2D geo1 = os.polygon2D(vertices);

		OpenSCADGeometry2D t1 = os.translate2D(1,1).add(geo1);
		OpenSCADGeometry2DFrom2D r1 = os.rotate2D(45).add(t1);
		OpenSCADGeometry2D m1 = os.module(r1);

		List<OpenSCADVector2D> vertices2 = new ArrayList<>();
		vertices2.add(os.vector2D(1.0, -1.0));
		vertices2.add(os.vector2D(1.0, 2.0));
		vertices2.add(os.vector2D(-1.0, 1.0));
		vertices2.add(os.vector2D(-1.0, -1.0));
		OpenSCADGeometry2D geo2 = os.polygon2D(vertices2);
		OpenSCADGeometry2DFrom2D t2 = os.translate2D(1,1).add(geo2);
		OpenSCADGeometry2DFrom2D r2 = os.rotate2D(45).add(t2);
		OpenSCADGeometry2D m2 = os.module(r2);
		OpenSCADGeometry2DFrom2D union = os.union2D().add(m1).add(m2);

		OpenSCADGeometry3D res1 = os.linearExtrude(5, 0, 1, 1).add(union);
		OpenSCADGeometry3D res2 = os.linearExtrude(5, 0, 1, 1).add(union);
		res1 = os.module(res1);
		res2 = os.module(res2);
		OpenSCADGeometry3D union2 = os.union3D().add(res1).add(res2);


		os.generateOpenSCADFile("OpenSCAD/output.scad", os.module(union2));

		System.out.println(AllStrings.listAllStrings());
	}
}
