package org.abstractica.javaopenscad.impl;

import org.abstractica.javaopenscad.intf.Geometry2DFrom3D;
import org.abstractica.javaopenscad.intf.Geometry3D;

public abstract class AGeometry2DFrom3D extends AGeometryNode implements Geometry2DFrom3D
{
	protected AGeometry2DFrom3D() {}

	@Override
	public Geometry2DFrom3D add(Geometry3D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
