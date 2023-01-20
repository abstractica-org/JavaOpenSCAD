package org.abstractica.javaopenscad.stl;

import java.io.IOException;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		STL stl = new STL();
		STLVector3D normal = new STLVector3D(0, 0, 1);
		STLVector3D v1 = new STLVector3D(0, 0, 0);
		STLVector3D v2 = new STLVector3D(1, 0, 0);
		STLVector3D v3 = new STLVector3D(0, 1, 0);
		STLTriangle triangle = new STLTriangle(normal, v1, v2, v3);
		stl.addTriangle(triangle);
		stl.save("test.stl");
		stl = STL.load("test.stl");
		System.out.println(stl);

		/*
		STL stl = STL.load("OpenSCAD/ModuleCache/M14.stl");
		System.out.println(stl);
		 */
	}
}
