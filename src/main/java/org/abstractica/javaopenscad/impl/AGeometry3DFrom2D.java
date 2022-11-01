package org.abstractica.javaopenscad.impl;

import org.abstractica.javaopenscad.intf.Geometry2D;
import org.abstractica.javaopenscad.intf.Geometry3DFrom2D;

public abstract class AGeometry3DFrom2D extends AGeometryNode implements Geometry3DFrom2D
{
	protected AGeometry3DFrom2D() {}

	@Override
	public Geometry3DFrom2D add(Geometry2D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
