package org.abstractica.javaopenscad.intf;

import java.util.List;

public interface Polygon2D
{
	List<Vector2D> vertices();
	List<Path> paths();
	int convexity();
}
