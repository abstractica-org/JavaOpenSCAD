package org.abstractica.javaopenscad.impl;


import org.abstractica.javaopenscad.intf.Geometry3D;
import org.abstractica.javaopenscad.intf.Geometry3DFrom3D;

public abstract class AGeometry3DFrom3D extends AGeometryNode implements Geometry3DFrom3D
{
	protected AGeometry3DFrom3D() {}

	@Override
	public Geometry3DFrom3D add(Geometry3D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
