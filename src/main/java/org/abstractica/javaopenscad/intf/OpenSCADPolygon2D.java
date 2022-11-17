package org.abstractica.javaopenscad.intf;

import java.util.List;

public interface OpenSCADPolygon2D
{
	List<OpenSCADVector2D> vertices();
	List<OpenSCADPath> paths();
	int convexity();
}
