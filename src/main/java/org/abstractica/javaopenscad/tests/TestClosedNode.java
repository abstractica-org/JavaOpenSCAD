package org.abstractica.javaopenscad.tests;

import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry3D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry3DFrom3D;
import org.abstractica.javaopenscad.intf.OpenSCADVector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestClosedNode
{
	public static void main(String[] args) throws IOException
	{
		JavaOpenSCAD csg = new JavaOpenSCADImpl(true);
		List<OpenSCADVector2D> vertices1 = new ArrayList<>();
		vertices1.add(csg.vector2D(1.0, -1.0));
		vertices1.add(csg.vector2D(1.0, 1.0));
		vertices1.add(csg.vector2D(-1.0, 1.0));
		vertices1.add(csg.vector2D(-1.0, -1.0));
		OpenSCADGeometry2D polygon1 = csg.polygon2D(vertices1);
		OpenSCADGeometry3D geo1 = csg.linearExtrude(5, 0, 1, 1, true).add(polygon1);

		List<OpenSCADVector2D> vertices2 = new ArrayList<>();
		vertices2.add(csg.vector2D(1.0, -1.0));
		vertices2.add(csg.vector2D(1.0, 2.0));
		vertices2.add(csg.vector2D(-2.0, 1.0));
		vertices2.add(csg.vector2D(-1.0, -1.0));
		OpenSCADGeometry2D polygon2 = csg.polygon2D(vertices2);
		OpenSCADGeometry3D geo2 = csg.linearExtrude(5, 0, 1, 1, true).add(polygon2);
		OpenSCADGeometry3DFrom3D translate1 = csg.translate3D(-1, 0, 0).add(geo2);

		OpenSCADGeometry3DFrom3D diff = csg.difference3D();
		diff.add(geo1);
		diff.add(geo2);
		OpenSCADGeometry3DFrom3D union = csg.union3D();
		union.add(diff);
		OpenSCADGeometry3D mod = csg.module(union);

		//This line should throw an exception, since the node is in a module and therefore closed for change.
		diff.add(geo1);

		csg.generateOpenSCADFile("OpenSCAD/output.scad", mod);
	}
}
