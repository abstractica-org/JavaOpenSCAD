package org.abstractica.javaopenscad.tests;

import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.intf.Geometry2D;
import org.abstractica.javaopenscad.intf.Geometry2DFrom2D;
import org.abstractica.javaopenscad.intf.Geometry3D;
import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.intf.Polygon2D;
import org.abstractica.javaopenscad.intf.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestStlModules
{
	public static void main(String[] args) throws IOException
	{
		String moduleDirectoryName = System.getProperty("user.dir")
				.replace("\\", "/")+ "/OpenSCAD/Modules";
		JavaOpenSCAD os = new JavaOpenSCADImpl(moduleDirectoryName, false);
		List<Vector2D> vertices1 = new ArrayList<>();
		vertices1.add(os.vector2D(1,-1));
		vertices1.add(os.vector2D(1,2));
		vertices1.add(os.vector2D(-1,1));
		vertices1.add(os.vector2D(-1,-1));
		Polygon2D polygon1 = os.polygon2D(vertices1, 1);
		Geometry2D geo1 = os.polygon2DGeometry(polygon1);

		Geometry2D t1 = os.translate2D(1,1).add(geo1);
		Geometry2DFrom2D r1 = os.rotate2D(45).add(t1);
		Geometry2D m1 = os.module(r1);

		List<Vector2D> vertices2 = new ArrayList<>();
		vertices2.add(os.vector2D(1,-1));
		vertices2.add(os.vector2D(1,2));
		vertices2.add(os.vector2D(-1,1));
		vertices2.add(os.vector2D(-1,-1));
		Polygon2D polygon2 = os.polygon2D(vertices2, 1);
		Geometry2D geo2 = os.polygon2DGeometry(polygon2);
		Geometry2DFrom2D t2 = os.translate2D(1,1).add(geo2);
		Geometry2DFrom2D r2 = os.rotate2D(45).add(t2);
		Geometry2D m2 = os.module(r2);
		Geometry2DFrom2D union = os.union2D().add(m1).add(m2);

		Geometry3D res1 = os.linearExtrude(5, 0, 1, 1, 10).add(union);
		Geometry3D res2 = os.linearExtrude(5, 0, 1, 1, 10).add(union);
		res1 = os.module(res1);
		res2 = os.module(res2);
		Geometry3D union2 = os.union3D().add(res1).add(res2);


		os.generateOpenSCADFile("OpenSCAD/output.scad", os.module(union2));

		System.out.println(AllStrings.listAllStrings());
	}
}
