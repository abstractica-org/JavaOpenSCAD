package org.abstractica.javaopenscad.intf;

import java.util.List;

public interface Polyhedron3D
{
	List<Vector3D> vertices();
	List<Path> faces();
	int convexity();
}
