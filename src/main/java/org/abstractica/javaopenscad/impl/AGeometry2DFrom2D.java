package org.abstractica.javaopenscad.impl;

import org.abstractica.javaopenscad.intf.Geometry2D;
import org.abstractica.javaopenscad.intf.Geometry2DFrom2D;

public abstract class AGeometry2DFrom2D extends AGeometryNode implements Geometry2DFrom2D
{
	protected AGeometry2DFrom2D() {}

	@Override
	public Geometry2DFrom2D add(Geometry2D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
