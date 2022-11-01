package org.abstractica.javaopenscad.intf.polygon;

import java.util.List;

public interface Polygon2D
{
	List<Vector2D> vertices();
	List<Path> paths();
}
