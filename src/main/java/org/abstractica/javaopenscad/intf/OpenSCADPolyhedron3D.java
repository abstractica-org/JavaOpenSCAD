package org.abstractica.javaopenscad.intf;

import java.util.List;

public interface OpenSCADPolyhedron3D
{
	List<OpenSCADVector3D> vertices();
	List<OpenSCADPath> faces();
	int convexity();
}
