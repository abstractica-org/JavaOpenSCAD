package org.abstractica.javaopenscad.impl.core;

import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2DFrom2D;

public abstract class AGeometry2DFrom2D extends AGeometryNode implements OpenSCADGeometry2DFrom2D
{
	protected AGeometry2DFrom2D() {}

	@Override
	public OpenSCADGeometry2DFrom2D add(OpenSCADGeometry2D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
